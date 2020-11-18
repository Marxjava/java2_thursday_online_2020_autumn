package lv.javaguru.java2.library.core.database;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.core.requests.Ordering;

public class InMemoryDatabaseImpl implements Database {

	private Long nextId = 1L;
	private List<Book> books = new ArrayList<>();

	@Override
	public void save(Book book) {
		book.setId(nextId);
		nextId++;
		books.add(book);
	}

	@Override
	public boolean deleteById(Long id) {
		boolean isBookDeleted = false;
		Optional<Book> bookToDeleteOpt = books.stream()
				.filter(book -> book.getId().equals(id))
				.findFirst();
		if (bookToDeleteOpt.isPresent()) {
			Book bookToRemove = bookToDeleteOpt.get();
			isBookDeleted = books.remove(bookToRemove);
		}
		return isBookDeleted;
	}

	@Override
	public List<Book> getAllBooks() {
		return books;
	}

	@Override
	public List<Book> findByTitle(String title) {
		return books.stream()
				.filter(book -> book.getTitle().equals(title))
				.collect(Collectors.toList());
	}

	@Override
	public List<Book> findByTitle(String title, Ordering ordering) {
		Comparator<Book> comparator = ordering.getOrderBy().equals("title")
				? Comparator.comparing(Book::getTitle)
				: Comparator.comparing(Book::getAuthor);
		if (ordering.getOrderDirection().equals("DESCENDING")) {
			comparator = comparator.reversed();
		}
		return books.stream()
				.filter(book -> book.getTitle().equals(title))
				.sorted(comparator)
				.collect(Collectors.toList());
	}

	@Override
	public List<Book> findByAuthor(String author) {
		return books.stream()
				.filter(book -> book.getAuthor().equals(author))
				.collect(Collectors.toList());
	}

	@Override
	public List<Book> findByAuthor(String author, Ordering ordering) {
		Comparator<Book> comparator = ordering.getOrderBy().equals("title")
				? Comparator.comparing(Book::getTitle)
				: Comparator.comparing(Book::getAuthor);
		if (ordering.getOrderDirection().equals("DESCENDING")) {
			comparator = comparator.reversed();
		}
		return books.stream()
				.filter(book -> book.getAuthor().equals(author))
				.sorted(comparator)
				.collect(Collectors.toList());
	}

	@Override
	public List<Book> findByTitleAndAuthor(String title, String author) {
		return books.stream()
				.filter(book -> book.getAuthor().equals(author))
				.filter(book -> book.getTitle().equals(title))
				.collect(Collectors.toList());
	}

	@Override
	public List<Book> findByTitleAndAuthor(String title, String author, Ordering ordering) {
		Comparator<Book> comparator = ordering.getOrderBy().equals("title")
				? Comparator.comparing(Book::getTitle)
				: Comparator.comparing(Book::getAuthor);
		if (ordering.getOrderDirection().equals("DESCENDING")) {
			comparator = comparator.reversed();
		}
		return books.stream()
				.filter(book -> book.getAuthor().equals(author))
				.filter(book -> book.getTitle().equals(title))
				.sorted(comparator)
				.collect(Collectors.toList());
	}

}
