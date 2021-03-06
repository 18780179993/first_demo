package com.example.config.dynamic.datasource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.type.AnnotationMetadata;


public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

	// 如配置文件中未指定数据源类型，使用该默认值
	private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";
	private ConversionService conversionService = new DefaultConversionService();
	private PropertyValues dataSourcePropertyValues;
	// 默认数据源
	private DataSource defaultDataSource;
	private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();
	
	/**
	 * 加载多数据源配置
	 */
	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("DynamicDataSourceRegister.setEnvironment()");
		initCustomDataSources(environment);
	}

	/**
	 * 初始化数据源
	 * @author SHANHY
	 * @create 2016年1月24日
	 */
	private void initCustomDataSources(Environment env) {
		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.");
		Map<String, Object> dspropers=propertyResolver.getSubProperties("datasources");
		// 读取配置文件获取数据源信息存放到map<数据源序号,map<数据源信息名称，数据源信息值>> 里面
		Map<String, Map<String,String>> infos=new HashMap<>();
		for (String s : dspropers.keySet()) {
			String index=s.substring(0,s.indexOf('.'));
			if(infos.get(index)==null) {
				infos.put(index, new HashMap<>());
			}
			String key=s.substring(s.indexOf('.')+1);
			infos.get(index).put(key, dspropers.get(s).toString());
		}
		// 生成成数据源
		for (Map<String, String> dsInfo : infos.values()) {
			try {
				DataSource ds = buildDataSource(dsInfo);
				String dsName=dsInfo.get("datasource_name").toUpperCase();
				if(dsName.equals("DEFAULT")) {
					defaultDataSource = ds;
				}else {
					customDataSources.put(dsName, ds);
				}
				dataBinder(ds, env);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(defaultDataSource==null) {
			throw new RuntimeException("必须存在一个datasource_name=DEFAULT的数据源");
		}
	}

	/**
     * 创建datasource.
     * @param dsMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, String> dsMap) {
       Object type = dsMap.get("type");
        if (type == null){
            type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
        }
        Class<? extends DataSource> dataSourceType;
       try {
           dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
           String driverClassName = dsMap.get("driver-class-name");
            String url = dsMap.get("url");
            String username = dsMap.get("username");
            String password = dsMap.get("password");
            DataSourceBuilder factory =   DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username).password(password).type(dataSourceType);
            return factory.build();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return null;
    }

	/**
	 * 为DataSource绑定更多数据
	 * @param dataSource
	 * @param env
	 */

	private void dataBinder(DataSource dataSource, Environment env) {

		RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);

		dataBinder.setConversionService(conversionService);

		dataBinder.setIgnoreNestedProperties(false);// false

		dataBinder.setIgnoreInvalidFields(false);// false

		dataBinder.setIgnoreUnknownFields(true);// true

		if (dataSourcePropertyValues == null) {

			Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");

			Map<String, Object> values = new HashMap<>(rpr);

			// 排除已经设置的属性

			values.remove("type");

			values.remove("driverClassName");

			values.remove("url");

			values.remove("username");

			values.remove("password");

			dataSourcePropertyValues = new MutablePropertyValues(values);

		}
		dataBinder.bind(dataSourcePropertyValues);
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		System.out.println("DynamicDataSourceRegister.registerBeanDefinitions()");

		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

		// 将主数据源添加到更多数据源中

		targetDataSources.put("DEFAULT", defaultDataSource);

		DataSourceContextHolder.dataSourceIds.add("DEFAULT");

		// 添加更多数据源

		targetDataSources.putAll(customDataSources);

		for (String key : customDataSources.keySet()) {

			DataSourceContextHolder.dataSourceIds.add(key);

		}

		// 创建DynamicDataSource

		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();

		beanDefinition.setBeanClass(DynamicDataSource.class);

		beanDefinition.setSynthetic(true);

		MutablePropertyValues mpv = beanDefinition.getPropertyValues();

		// 添加属性：AbstractRoutingDataSource.defaultTargetDataSource

		mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);

		mpv.addPropertyValue("targetDataSources", targetDataSources);

		registry.registerBeanDefinition("dataSource", beanDefinition);

	}

}
