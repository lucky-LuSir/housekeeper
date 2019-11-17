package com.kfwy.hkp.common.utils;

import java.util.List;
import java.util.Map;

/**
 * 判断是否[不]为空的工具类<br>
 * 有多种类型的重载
 * <p>
 * ClassName: EmptyUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: (c)2017 Jastar·Wang,All rights reserved.
 * </p>
 * 
 * @author Jastar·Wang
 * @date 2017年4月12日
 */
public class EmptyUtil {

	/**
	 * 私有构造
	 */
	private EmptyUtil() {
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return true or false
	 */
	public static boolean isEmpty(String str) {
		return str == null || str == "" || str.trim() == "" || str.length() <= 0;
	}

	/**
	 * 判断List是否为空
	 * 
	 * @param list
	 * @return true or false
	 */
	public static <T> boolean isEmpty(List<T> list) {
		return list == null || list.isEmpty();
	}

	/**
	 * 判断Map是否为空
	 * 
	 * @param map
	 * @return true or false
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return map == null || map.isEmpty();
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param array
	 * @return true or false
	 */
	public static <T> boolean isEmpty(T[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return true or false
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断List是否不为空
	 * 
	 * @param list
	 * @return true or false
	 */
	public static <T> boolean isNotEmpty(List<T> list) {
		return !isEmpty(list);
	}

	/**
	 * 判断Map是否不为空
	 * 
	 * @param map
	 * @return true or false
	 */
	public static <K, V> boolean isNotEmpty(Map<K, V> map) {
		return !isEmpty(map);
	}

	/**
	 * 判断数组是否不为空
	 * 
	 * @param array
	 * @return true or false
	 */
	public static <T> boolean isNotEmpty(T[] array) {
		return !isEmpty(array);
	}
}
