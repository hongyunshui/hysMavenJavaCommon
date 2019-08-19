package com.taotao.common.utils;

import java.util.Random;

public class IDUtils {

	/**
	 * 生成图片唯一名称
	 * @return
	 */
	public static String genImageName(){
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
//		long millis2 = System.nanoTime();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		// 如果不足3位，前面补0
		String str = millis + String.format("%03d", end3);
		return str;
	}
	
	/**
	 * 商品id生成
	 * @return
	 */
	public static long genItemId(){
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
//		long millis2 = System.nanoTime();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		// 如果不足3位，前面补0
		String str = millis + String.format("%03d", end3); 
		long id = new Long(str);
		return id;
	}
}
