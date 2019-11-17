package com.kfwy.hkp.common.mongo.dao.impl;

import com.kfwy.hkp.common.mongo.dao.IBaseDao;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.EmptyUtil;
import com.kfwy.hkp.common.utils.PageModel;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * 基本操作接口MongoDB数据库实现类
 *
 * @date 2019年5月30日
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {

    protected abstract Class<T> getEntityClass();

    @Autowired
    protected MongoTemplate mgt;

    @Override
    public void save(T entity) {
        mgt.insert(entity);
    }

    @Override
    public void batchSave(Collection<T> t) {
        mgt.insertAll(t);
    }

    @Override
    public void update(T entity) {

        // 反向解析对象
        Map<String, Object> map = null;
        try {
            map = parseEntity(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ID字段
        String idName = null;
        Object idValue = null;

        // 生成参数
        Update update = new Update();
        if (EmptyUtil.isNotEmpty(map)) {
            for (String key : map.keySet()) {
                if (key.indexOf("{") != -1) {
                    // 设置ID
                    idName = key.substring(key.indexOf("{") + 1, key.indexOf("}"));
                    idValue = map.get(key);
                } else {
                    update.set(key, map.get(key));
                }
            }
        }
        mgt.updateFirst(new Query().addCriteria(where(idName).is(idValue)), update, getEntityClass());
    }

    @Override
    public void delete(Serializable... ids) {
        if (EmptyUtil.isNotEmpty(ids)) {
            for (Serializable id : ids) {
                mgt.remove(mgt.findById(id, getEntityClass()));
            }
        }

    }

    @Override
    public T find(Serializable id) {
        return mgt.findById(id, getEntityClass());
    }

    @Override
    public List<T> findAll() {
        return mgt.findAll(getEntityClass());
    }

    @Override
    public List<T> findAll(String order) {
        List<Order> orderList = parseOrder(order);
        if (EmptyUtil.isEmpty(orderList)) {
            return findAll();
        }
        return mgt.find(new Query().with(new Sort(orderList)), getEntityClass());
    }

    @Override
    public List<T> findColumnByProp(String propName, Object propValue, BasicDBObject fieldsObject) {

        String collectionName = mgt.getCollectionName(getEntityClass());

        return mgt.getCollection(collectionName).distinct("url");
    }

    @Override
    public List<T> findColumnByProp(String propName) {
        String collectionName = mgt.getCollectionName(getEntityClass());
        return mgt.getCollection(collectionName).distinct(propName);
    }

    @Override
    public List<T> findByProp(String propName, Object propValue) {
        return findByProp(propName, propValue, null);
    }

    @Override
    public List<T> findByProp(String propName, Object propValue, String order) {
        Query query = new Query();
        // 参数
        query.addCriteria(where(propName).is(propValue));
        // 排序
        List<Order> orderList = parseOrder(order);
        if (EmptyUtil.isNotEmpty(orderList)) {
            query.with(new Sort(orderList));
        }
        return mgt.find(query, getEntityClass());
    }

    @Override
    public List<T> findByProps(Map map, String[] propName, Object[] propValue) {
        return findByProps(map, propName, propValue, null);
    }

    @Override
    public List<T> findByProps(Map map, String[] propName, Object[] propValue, String order) {
        Query query = createQuery(map, propName, propValue, order);
        return mgt.find(query, getEntityClass());
    }

    @Override
    public T uniqueByProp(String propName, Object propValue) {
        return mgt.findOne(new Query(where(propName).is(propValue)), getEntityClass());
    }

    @Override
    public T uniqueByProps(Map<String, Object> map, String[] propName, Object[] propValue) {
        Query query = createQuery(map, propName, propValue, null);
        return mgt.findOne(query, getEntityClass());
    }

    @Override
    public PageModel<T> pageAll(int pageNo, int pageSize) {
        return pageAll(pageNo, pageSize, null);
    }

    @Override
    public PageModel<T> pageAll(int pageNo, int pageSize, String order) {
        return pageByProp(pageNo, pageSize, null, null, null, order);
    }

    @Override
    public PageModel<T> pageByProp(int pageNo, int pageSize, Map<String, Object> map, String param, Object value) {
        return pageByProp(pageNo, pageSize, map, param, value, null);
    }

    @Override
    public PageModel<T> pageByProp(int pageNo, int pageSize, Map<String, Object> map, String param, Object value, String order) {
        String[] params = null;
        Object[] values = null;
        if (EmptyUtil.isNotEmpty(param)) {
            params = new String[]{param};
            values = new Object[]{value};
        }
        return pageByProps(pageNo, pageSize, map, params, values, order);
    }

    @Override
    public PageModel<T> pageByProps(int pageNo, int pageSize, Map map, String[] params, Object[] values) {
        return pageByProps(pageNo, pageSize, map, params, values, null);
    }

    @Override
    public PageModel<T> pageByProps(int pageNo, int pageSize, Map map, String[] params, Object[] values, String order) {
        // 创建分页模型对象
        PageModel<T> page = new PageModel<>(pageNo, pageSize);

        // 查询总记录数
        int count = countByCondition(map, params, values);
        page.setTotalCount(count);

        // 查询数据列表
        Query query = createQuery(map, params, values, order);

        // 设置分页信息
        query.skip(pageNo);
        query.limit(page.getPageSize());

        // 封装结果数据
        page.setList(mgt.find(query, getEntityClass()));

        return page;
    }

    @Override
    public int countByCondition(Map map, String[] params, Object[] values) {
        Query query = createQuery(map, params, values, null);
        Long count = mgt.count(query, getEntityClass());
        return count.intValue();
    }

    /**
     * 创建带有where条件（只支持等值）和排序的Query对象
     *
     * @param params 参数数组
     * @param values 参数值数组
     * @param order  排序
     * @return Query对象
     */
    protected Query createQuery(Map<String, Object> map, String[] params, Object[] values, String order) {
        Query query = new Query();

        if (map.containsKey("createTimeStart") && map.containsKey("createTimeEnd")) {
            map.put("createTimeStart", DateFormatUtil.dayBegin((Date) map.get("createTimeStart")));
            map.put("createTimeEnd", DateFormatUtil.dayEnd((Date) map.get("createTimeEnd")));
            query.addCriteria(where("createTime").gte(map.get("createTimeStart")).lte(map.get("createTimeEnd")));
        }else if (map.containsKey("createTimeStart") && !map.containsKey("createTimeEnd")){
            map.put("createTimeStart", DateFormatUtil.dayBegin((Date) map.get("createTimeStart")));
            query.addCriteria(where("createTime").gte(map.get("createTimeStart")));
        }else if (!map.containsKey("createTimeStart") && map.containsKey("createTimeEnd")) {
            map.put("createTimeEnd", DateFormatUtil.dayEnd((Date) map.get("createTimeEnd")));
            query.addCriteria(where("createTime").lte(map.get("createTimeEnd")));
        }

        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entity : map.entrySet()) {
                if (entity.getKey().equals("createTimeStart")
                        || entity.getKey().equals("createTimeEnd")
                        || entity.getKey().equals("class")
                        || entity.getValue()==null || entity.getValue()=="") {
                    continue;
                }
                query.addCriteria(where(entity.getKey()).is(entity.getValue()));
            }
        }

        // 排序
        List<Order> orderList = parseOrder(order);
        if (EmptyUtil.isNotEmpty(orderList)) {
            query.with(new Sort(orderList));
        }

        return query;
    }

    /**
     * 解析Order字符串为所需参数
     *
     * @param order 排序参数，如[id]、[id asc]、[id asc,name desc]
     * @return Order对象集合
     */
    protected List<Order> parseOrder(String order) {
        List<Order> list = null;
        if (EmptyUtil.isNotEmpty(order)) {
            list = new ArrayList<Order>();
            // 共有几组排序字段
            String[] fields = order.split(",");
            Order o = null;
            String[] item = null;
            for (int i = 0; i < fields.length; i++) {
                if (EmptyUtil.isEmpty(fields[i])) {
                    continue;
                }
                item = fields[i].split(" ");
                if (item.length == 1) {
                    o = new Order(Direction.ASC, item[0]);
                } else if (item.length == 2) {
                    o = new Order("desc".equalsIgnoreCase(item[1]) ? Direction.DESC : Direction.ASC, item[0]);
                } else {
                    throw new RuntimeException("排序字段参数解析出错");
                }
                list.add(o);
            }
        }
        return list;
    }

    /**
     * 将对象的字段及值反射解析为Map对象<br>
     * 这里使用Java反射机制手动解析，并且可以识别注解为主键的字段，以达到根据id进行更新实体的目的<br>
     * key：字段名称，value：字段对应的值
     *
     * @param t 要修改的对象
     * @return Map对象，注意：id字段的key封装为“{id字段名称}”，以供后续识别
     * @throws Exception
     */
    protected Map<String, Object> parseEntity(T t) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * 解析ID
		 */
        String idName = "";
        Field[] declaredFields = getEntityClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true);
                map.put("{" + field.getName() + "}", field.get(t));
                idName = field.getName();
                break;
            }
        }
		/*
		 * 解析其他属性
		 */
        Method[] methods = getEntityClass().getDeclaredMethods();
        if (EmptyUtil.isNotEmpty(methods)) {
            for (Method method : methods) {
                if (method.getName().startsWith("get") && method.getModifiers() == Modifier.PUBLIC) {
                    String fieldName = parse2FieldName(method.getName());
                    if (!fieldName.equals(idName)) {
                        map.put(fieldName, method.invoke(t));
                    }
                }
            }
        }

        return map;
    }

    /**
     * 将get方法名转换为对应的字段名称
     *
     * @param methodName 如：getName
     * @return 如：name
     */
    private String parse2FieldName(String methodName) {
        String name = methodName.replace("get", "");
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }

}
