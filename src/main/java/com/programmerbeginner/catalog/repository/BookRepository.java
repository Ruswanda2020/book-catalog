package com.programmerbeginner.catalog.repository;

import java.util.List;
import java.util.Optional;

import com.programmerbeginner.catalog.dto.BookQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.programmerbeginner.catalog.domain.Book;
import org.springframework.data.jpa.repository.Query;



public interface BookRepository extends JpaRepository<Book, Long>{

	public List<Book> findAll();

	 //sql select b from book b where b.secure_id=:id
	//jpql SELECT b FROM Book b where b.secureId=:id
	public Optional<Book> findBySecureId( String id);

	//sql select b from book b join Publisher p.id = b.publisher_id where p name =:publisherName  and b.title= :book_title
	@Query("SELECT DISTINCT new com.programmerbeginner.catalog.dto.BookQueryDto(b.id, b.secureId, b.title, b.description, p.name) " +
			"FROM Book b " +
			"INNER JOIN Publisher p ON p.id = b.publisher.id " +
			"JOIN b.authors a " +
			"WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :publisherName, '%')) " +
			"AND LOWER(b.title) LIKE LOWER(CONCAT('%', :bookTitle, '%')) " +
			"AND LOWER(a.nama) LIKE LOWER(CONCAT('%', :authorName, '%'))")
	public Page<BookQueryDto> findBookList(String bookTitle, String publisherName, String authorName, Pageable pageable);
	

}

