package com.example.config.dynamic.datasource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataSourceContextHolder {

	/*
     * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<LinkedList<String>> contextHolder = new ThreadLocal<LinkedList<String>>();
    /*
     * 管理所有的数据源id;
     * 主要是为了判断数据源是否存在;
     */
    public static List<String> dataSourceIds = new ArrayList<String>();
    
    /**
     * 使用setDataSourceType设置当前的
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
       LinkedList<String> dsNames=contextHolder.get();
       if(dsNames==null) {
    	   		contextHolder.set(new LinkedList<>());
       }
       contextHolder.get().addFirst(dataSourceType);
    }
    
    public static String getDataSourceType() {
    		LinkedList<String> dsNames=contextHolder.get();
    		if(dsNames!=null&&dsNames.getFirst()!=null) {
    			return dsNames.getFirst();
    		}
       return null;
    }
    public static void clearDataSourceType() {
       LinkedList<String> dsNames=contextHolder.get();
       if(dsNames==null||dsNames.size()==1) {
           contextHolder.remove();
       }else {
    	   	   dsNames.removeFirst();
       }

    }
    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceId
     * @return
     * @author SHANHY
     * @create  2016年1月24日
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
