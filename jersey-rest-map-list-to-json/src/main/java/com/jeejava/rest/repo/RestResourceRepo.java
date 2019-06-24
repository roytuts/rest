package com.jeejava.rest.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeejava.rest.dto.Book;

public class RestResourceRepo {

	private static List<Book> BOOKS = new ArrayList<Book>();
	private static Map<String, Book> MAP = new HashMap<String, Book>();
	private static Map<String, List<Book>> MAP_LIST = new HashMap<String, List<Book>>();

	static {
		BOOKS.add(new Book(1l, "Java", "Gosling", "Java-123456"));
		BOOKS.add(new Book(2l, "REST Webservice", "REST Author", "REST-123456"));
		BOOKS.add(new Book(3l, "PHP", "PHP Author", "PHP-123456"));
		BOOKS.add(new Book(4l, "JavaScript", "JS Author", "JS-123456"));
		BOOKS.add(new Book(5l, "Database", "CJ Date", "DB-123456"));
	}

	static {
		MAP.put("book1", new Book(1l, "Java", "Gosling", "Java-123456"));
		MAP.put("book2", new Book(2l, "REST Webservice", "REST Author", "REST-123456"));
		MAP.put("book3", new Book(3l, "PHP", "PHP Author", "PHP-123456"));
		MAP.put("book4", new Book(4l, "JavaScript", "JS Author", "JS-123456"));
		MAP.put("book5", new Book(5l, "Database", "CJ Date", "DB-123456"));
	}

	static {
		List<Book> bookSet1 = new ArrayList<Book>();
		bookSet1.add(new Book(1l, "Java", "Gosling", "Java-123456"));
		bookSet1.add(new Book(2l, "REST Webservice", "REST Author", "REST-123456"));

		List<Book> bookSet2 = new ArrayList<Book>();
		bookSet2.add(new Book(3l, "PHP", "PHP Author", "PHP-123456"));
		bookSet2.add(new Book(4l, "JavaScript", "JS Author", "JS-123456"));
		bookSet2.add(new Book(5l, "Database", "CJ Date", "DB-123456"));

		MAP_LIST.put("b1", bookSet1);
		MAP_LIST.put("b2", bookSet2);
	}

	public static List<Book> getBooks() {
		return BOOKS;
	}

	public static Map<String, Book> getBookMap() {
		return MAP;
	}

	public static Map<String, List<Book>> getBookMapList() {
		return MAP_LIST;
	}

}
