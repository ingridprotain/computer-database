package com.excilys.computerdatabase.ui;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T> {
	public List<List<T>> getPages(List<T> list) {
		
		List<List<T>> return_list = new ArrayList<List<T>>();
		
		for (int i=0; i<list.size(); i+=10) {
			List<T> l = new ArrayList<T>();
			for (int j=i; j<i+10; j++) {
				l.add(list.get(j));
				if (j==list.size()-1)
					break;
			}
			return_list.add(l);
		}
		return return_list;
	}
}
