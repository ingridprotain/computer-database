package com.excilys.computerdatabase.ui;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T> {
	/**
	 * Permet de trier une liste pour retourner une liste paginée (List<List<T>>)
	 * Trie par 10 objets 
	 * @param list Liste à paginer
	 * @return une List<List<T>> qui est la liste paginée par 10
	 */
	
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
