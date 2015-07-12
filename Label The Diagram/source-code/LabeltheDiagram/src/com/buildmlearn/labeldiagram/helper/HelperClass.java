package com.buildmlearn.labeldiagram.helper;

import java.util.Arrays;
import java.util.List;

import android.graphics.Typeface;

public class HelperClass {
	
	public static List<Integer> convert(Object[] objectArray) {
		Integer[] intArray = new Integer[objectArray.length];

		for (int i = 0; i < objectArray.length; i++) {
			intArray[i] = (Integer) objectArray[i];
		}

		return Arrays.asList(intArray);
	}

}
