package com.longshihan.mvpretrofit.utils;

import com.longshihan.mvpretrofit.bean.CsdnAndroidBean;

import java.util.Comparator;

public class PinyinComparator implements Comparator<CsdnAndroidBean> {

	public int compare(CsdnAndroidBean o1, CsdnAndroidBean o2) {
		if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
