package com.kfwy.hkp.common.mongo.service;


import com.kfwy.hkp.common.mongo.dao.IBaseDao;
import com.kfwy.hkp.common.utils.PageModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 基本Service实现类
 *
 * @Description:本实现类中的方法与IBaseDao接口中的方法一一对应，为了简单省略了定义IBaseService接口
 * @author liwensihan
 */
public abstract class BaseService<T> {

	protected abstract IBaseDao<T> getDao();

	/**
	 * 保存-实体
	 * 
	 * @param entity
	 */
	public void save(T entity) {
		getDao().save(entity);
	}

	/**
	 * 修改实体
	 * 
	 * @param entity
	 */
	public void update(T entity) {
		getDao().update(entity);
	}

	/**
	 * 删除实体[数组]
	 * 
	 * @param ids
	 */
	public void delete(Serializable... ids) {
		getDao().delete(ids);
	}

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 *            实体的主键ID
	 */
	public T find(Serializable id) {
		return getDao().find(id);
	}

	/**
	 * 查询所有记录<br>
	 * [不分页]
	 * 
	 * @return 结果集合
	 */
	public List<T> findAll() {
		return getDao().findAll();
	}

	/**
	 * 查询所有记录并排序<br>
	 * [不分页]
	 * 
	 * @return 结果集合
	 */
	public List<T> findAll(String order) {
		return getDao().findAll(order);
	}

	/**
	 * 根据单一参数查询记录<br>
	 * [不分页]
	 * 
	 * @param propName
	 *            属性名称，对应实体类字段名称
	 * @param propValue
	 *            属性值
	 * @return 结果列表 或 null
	 */
	public List<T> findByProp(String propName, Object propValue) {
		return getDao().findByProp(propName, propValue);
	}

	/**
	 * 根据单一参数查询记录并排序<br>
	 * [不分页]
	 * 
	 * @param propName
	 *            属性名称，对应实体类字段名
	 * @param propValue
	 *            属性值
	 * @param order
	 *            排序字段（如：id 或 id desc）
	 * @return 结果集合 或 null
	 */
	public List<T> findByProp(String propName, Object propValue, String order) {
		return getDao().findByProp(propName, propValue, order);
	}

	/**
	 * 根据多个参数查询记录<br>
	 * [不分页]
	 * 
	 * @param propName
	 *            参数数组
	 * @param propValue
	 *            参数值数组
	 * @return 结果集合 或 null
	 */
	public List<T> findByProps(Map map,String[] propName, Object[] propValue) {
		return getDao().findByProps(map,propName, propValue);
	}

	/**
	 * 根据多个参数查询记录 并排序<br>
	 * [不分页]
	 * 
	 * @param propName
	 *            参数数组
	 * @param propValue
	 *            参数值数组
	 * @param order
	 *            排序字段
	 * @return 结果集合 或 null
	 */
	public List<T> findByProps(Map map,String[] propName, Object[] propValue, String order) {
		return getDao().findByProps(map,propName, propValue, order);
	}

	/**
	 * 根据单一参数查询唯一结果<br>
	 * [HQL]
	 * 
	 * @param propName
	 *            属性名称，对应实体类字段名
	 * @param propValue
	 *            属性值
	 * @return 唯一结果 或 null
	 */
	public T uniqueByProp(String propName, Object propValue) {
		return getDao().uniqueByProp(propName, propValue);
	}

	/**
	 * 根据多个参数查询唯一结果<br>
	 * [HQL]
	 * 
	 * @param propName
	 *            参数数组
	 * @param propValue
	 *            参数值数组
	 * @return 唯一结果 或 null
	 */
	public T uniqueByProps(Map<String,Object> map,String[] propName, Object[] propValue) {
		return getDao().uniqueByProps(map,propName, propValue);
	}

	/**
	 * 分页查询所有结果集合<br>
	 * [分页]
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            页容量
	 * @return 分页模型对象（不会为null）
	 */
	public PageModel<T> pageAll(int pageNo, int pageSize) {
		return getDao().pageAll(pageNo, pageSize);
	}

	/**
	 * 分页查询所有结果集合 并排序<br>
	 * [分页]
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            页容量
	 * @param order
	 *            排序字段
	 * @return 分页模型对象（不会为null）
	 */
	public PageModel<T> pageAll(int pageNo, int pageSize, String order) {
		return getDao().pageAll(pageNo, pageSize, order);
	}

	/**
	 * 根据参数分页查询结果集合<br>
	 * [分页]
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            页容量
	 * @param param
	 *            参数
	 * @param value
	 *            参数值
	 * @return 分页模型对象（不会为null）
	 */
	public PageModel<T> pageByProp(int pageNo, int pageSize,Map map, String param, Object value) {
		return getDao().pageByProp(pageNo, pageSize,map, param, value);
	}

	/**
	 * 根据参数分页查询结果集合并排序<br>
	 * [分页]
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            页容量
	 * @param param
	 *            参数
	 * @param value
	 *            参数值
	 * @param order
	 *            排序字段
	 * @return 分页模型对象（不会为null）
	 */
	public PageModel<T> pageByProp(int pageNo, int pageSize,Map map, String param, Object value, String order) {
		return getDao().pageByProp(pageNo, pageSize,map, param, value, order);
	}

	/**
	 * 根据参数分页查询结果集合<br>
	 * [分页]
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            页容量
	 * @param params
	 *            参数数组
	 * @param values
	 *            参数值数组
	 * @return 分页模型对象（不会为null）
	 */
	public PageModel<T> pageByProps(int pageNo, int pageSize,Map map, String[] params, Object[] values) {
		return getDao().pageByProps(pageNo, pageSize,map, params, values);
	}

	/**
	 * 根据参数分页查询结果集合 并排序<br>
	 * [分页]
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            页容量
	 * @param params
	 *            参数数组
	 * @param values
	 *            参数值数组
	 * @param order
	 *            排序字段
	 * @return 分页模型对象（不会为null）
	 */
	public PageModel<T> pageByProps(int pageNo, int pageSize, Map map, String[] params, Object[] values, String order) {
		return getDao().pageByProps(pageNo, pageSize,map, params, values, order);
	}

	/**
	 * 根据条件查询总记录数
	 * 
	 * @param params
	 *            参数数组
	 * @param values
	 *            参数值数组
	 * @return 总记录数
	 */
	public int countByCondition(Map map,String[] params, Object[] values) {
		return getDao().countByCondition(map,params, values);
	}
}
