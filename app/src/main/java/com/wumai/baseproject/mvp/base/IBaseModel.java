package com.wumai.baseproject.mvp.base;


import com.wumai.baseproject.network.ICallBack;
import com.wumai.baseproject.network.NetParams;

/**
 * 
 * @author li
 *
 * @param <T> bean 的实体类
 */
public interface IBaseModel<T> {

	/**
	 * 在这里做保存/上传数据或者model类型转换的操作
	 * @param t
	 */
	void setData(T t);
	
	/**
	 * 在这里做获取数据的操作
	 * @return
	 */
	<V, K> void getData(NetParams params, Class<V> clazz, ICallBack.CommonCallback<K> commonCallback);
	
	/**
	 * new 出来一个T类型的bean对象
	 * @return
	 */
	T newInstance();
}
