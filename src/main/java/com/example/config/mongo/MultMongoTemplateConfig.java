package com.example.config.mongo;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.example.config.dynamic.datasource.DynamicDataSource;
import com.example.util.EnvironmentUtil;
import com.mongodb.MongoClientURI;
public class MultMongoTemplateConfig implements EnvironmentAware,ImportBeanDefinitionRegistrar{
	
	List<MongoProperties> infos;
	
	public MultMongoTemplateConfig(){
		System.out.println(this.getClass().getName()+"---初始化");
	}
	
	
	
	public List<MongoProperties> getInfos() {
		return infos;
	}
	
	public void setInfos(List<MongoProperties> infos) {
		this.infos = infos;
	}



	@Override
	public void setEnvironment(Environment evn) {
		infos=EnvironmentUtil.getList(evn, "spring.mongodb.infos", MongoProperties.class);
	}



	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		for (MongoProperties p : infos) {
			// 创建DynamicDataSource
			GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
			beanDefinition.setBeanClass(MongoTemplate.class);
			beanDefinition.setSynthetic(true);
			ConstructorArgumentValues cs=new ConstructorArgumentValues();
			SimpleMongoDbFactory fc=null;
			try {
				fc = new SimpleMongoDbFactory(new MongoClientURI(p.getUrl()));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			cs.addGenericArgumentValue(fc);
			beanDefinition.setConstructorArgumentValues(cs);
			registry.registerBeanDefinition(p.getTemplateName(), beanDefinition);
		}
		
	}
	
	
}
