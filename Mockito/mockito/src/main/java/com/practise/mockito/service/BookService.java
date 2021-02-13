package com.practise.mockito.service;

import java.sql.SQLException;
import java.util.List;

import com.practise.mockito.dto.Book;
import com.practise.mockito.repo.BookRepositories;

public class BookService {
	
	private BookRepositories bookRepo;

	public BookService(BookRepositories bookRepo) {
		this.bookRepo = bookRepo;
	}
	
	public void addBook(Book book) {
		if(book == null) {
			return;
		}
		try {
			bookRepo.saveBook(book);
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseWriteException("while adding book got exception" + e.getMessage());
			
		}
		
	}
	
	public List<Book> findAll() {
		try {
			List<Book> books = bookRepo.findAll();
			if(books != null) {
				return books;
			}
		} catch (SQLException e) {
			throw new DataNotFoundException("No books availabe in the DB" + e.getMessage());
		}
		return null;
	}
	
	public int numberOfBooks() throws SQLException {
		return bookRepo.findAll().size();
	}
	
	public List<Book> getNewDiscount(int discount, int days) {
		List<Book> bookfromDB = bookRepo.findNewBooks(days);
		
		for(Book book : bookfromDB) {
			int price = book.getPrice();
			int newPrice = price - (discount-price/100);
			book.setPrice(newPrice);
		}
		return bookfromDB;
	}

}
