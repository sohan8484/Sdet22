package com.crm.vtiger.genericUtils;

import java.util.Date;
import java.util.Random;

public class JavaUtility {
	/*
	 * author @ sohan
	 */
	/**
	 * this method is generate random number to avoid duplicates
	 * @return
	 */
	public static String getRandomData() {
		Random random = new Random();
		int ran = random.nextInt(1000);
		return "" + ran;
	}
	/**
	 * @sohan
	 * this method is used to generae current date
	 * @return current date
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		String currentdate = date.toString();
		return currentdate;
	}
}
