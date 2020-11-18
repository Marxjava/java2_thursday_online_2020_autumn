package lv.javaguru.java2.library.core.database;

import java.util.List;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.core.requests.Ordering;

public interface Database {

	void save(Book book);

	boolean deleteById(Long id);

	List<Book> getAllBooks();

	List<Book> findByTitle(String title);

	List<Book> findByTitle(String title, Ordering ordering);

	List<Book> findByAuthor(String author);

	List<Book> findByAuthor(String author, Ordering ordering);

	List<Book> findByTitleAndAuthor(String title, String author);

	List<Book> findByTitleAndAuthor(String title, String author, Ordering ordering);

}
