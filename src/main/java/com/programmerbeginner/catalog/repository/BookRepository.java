package com.programmerbeginner.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programmerbeginner.catalog.domain.Book;




public interface BookRepository extends JpaRepository<Book, Long>{

	public List<Book> findAll();
	public Optional<Book> findBySecureId( String id);

	

}
