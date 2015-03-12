package com.excilys.computerdatabase.utils;

import java.util.List;

public interface Validatable<T> {
	List<String> validate (T objectDTO);
}
