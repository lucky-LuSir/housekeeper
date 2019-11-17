package com.kfwy.hkp.common.mongo.dao;


import com.kfwy.hkp.common.utils.PageModel;
import com.mongodb.BasicDBObject;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * mongodb通用基本操作方法接口
 *
 * @author liwensihan
 */
public interface IBaseDao<T> {
	/**
	 * 保存实体
	 * 备注：执行完成本方法后，所引用实体的主键id会自动赋上值
	 *
	 * @param entity
	 */
	void save(T entity);

	void batchSave(Collection<T> t);

	/**
	 * 修改实体
	 *
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 删除实体[数组]
	 *
	 * @param ids
	 *            实体ID或数组
	 */
	void delete(Serializable... ids);

	/**
	 * 根据ID查询
	 *
	 * @param id
	 *            实体的主键ID
	 */
	T find(Serializable id);

	/**
	 * 查询所有记录<br>
	 * [不分页]
	 *
	 * @return 结果集合
	 */
	List<T> findAll();

	/**
	 * 查询所有记录并排序<br>
	 * [不分页]<br>
	 *
	 * @param order
	 *            排序字段，例如：id或id asc、或id asc,name desc<br>
	 *            为空则不排序，不指定排序方式则默认升序排序
	 * @return 结果集合
	 */
	List<T> findAll(String order);


	List<T> findColumnByProp(String propName, Object propValue, BasicDBObject fieldsObject);

	List<T> findColumnByProp(String propName);

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
	List<T> findByProp(String propName, Object propValue);

	/**
	 * 根据单一参数查询记录并排序<br>
	 * [不分页]
	 *
	 * @param propName
	 *            属性名称，对应实体类字段名
	 * @param propValue
	 *            属性值
	 * @param order
	 *            排序字段，例如：id或id asc、或id asc,name desc<br>
	 *            为空则不排序，不指定排序方式则默认升序排序
	 * @return 结果集合 或 null
	 */
	List<T> findByProp(String propName, Object propValue, String order);

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
	List<T> findByProps(Map map,String[] propName, Object[] propValue);

	/**
	 * 根据多个参数查询记录 并排序<br>
	 * [不分页]
	 *
	 * @param propName
	 *            参数数组
	 * @param propValue
	 *            参数值数组
	 * @param order
	 *            排序字段，例如：id或id asc、或id asc,name desc<br>
	 *            为空则不排序，不指定排序方式则默认升序排序
	 * @return 结果集合 或 null
	 */
	List<T> findByProps(Map map, String[] propName, Object[] propValue, String order);

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
	T uniqueByProp(String propName, Object propValue);

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
	T uniqueByProps(Map<String,Object> map,String[] propName, Object[] propValue);

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
	PageModel<T> pageAll(int pageNo, int pageSize);

	/**
	 * 分页查询所有结果集合 并排序<br>
	 * [分页]
	 *
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            页容量
	 * @param order
	 *            排序字段，例如：id或id asc、或id asc,name desc<br>
	 *            为空则不排序，不指定排序方式则默认升序排序
	 * @return 分页模型对象（不会为null）
	 */
	PageModel<T> pageAll(int pageNo, int pageSize, String order);

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
	PageModel<T> pageByProp(int pageNo, int pageSize, Map<String,Object> map,String param, Object value);

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
	 *            排序字段，例如：id或id asc、或id asc,name desc<br>
	 *            为空则不排序，不指定排序方式则默认升序排序
	 * @return 分页模型对象（不会为null）
	 */
	PageModel<T> pageByProp(int pageNo, int pageSize, Map<String,Object> map,String param, Object value, String order);

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
	PageModel<T> pageByProps(int pageNo, int pageSize,Map<String,Object> map, String[] params, Object[] values);

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
	 *            排序字段，例如：id或id asc、或id asc,name desc<br>
	 *            为空则不排序，不指定排序方式则默认升序排序
	 * @return 分页模型对象（不会为null）
	 */
	PageModel<T> pageByProps(int pageNo, int pageSize,Map<String,Object> map, String[] params, Object[] values, String order);

	/**
	 * 根据条件查询总记录数
	 * 
	 * @param params
	 *            参数数组
	 * @param values
	 *            参数值数组
	 * @return 总记录数
	 */
	int countByCondition(Map<String,Object> map,String[] params, Object[] values);
}
