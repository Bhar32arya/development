package com.practise.mockito.repo;

import java.sql.SQLException;
import java.util.List;

import com.practise.mockito.dto.Book;

public interface BookRepositories {
	
	void saveBook(Book book) throws SQLException;
	
	List<Book> findAll() throws SQLException;
	
	List<Book> findNewBooks(int days);

}