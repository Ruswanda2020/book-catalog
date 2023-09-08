package com.programmerbeginner.catalog.repository;

import java.util.List;
import java.util.Optional;

import com.programmerbeginner.catalog.dto.AuthorQueryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.programmerbeginner.catalog.domain.Author;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	
	//method name convention
	//find+keyword
	
	//sql-> select * from Author a where id=?
	public Optional<Author> findById(String id);
	
	public Optional<Author> findBySecureId(String id);
	
	//sql -> select a from Author a where a.name LIKE ?
	public List<Author> findByNamaLike(String authorName);
	
	public List<Author> findBySecureIdIn(List<String> authorIdList );

	@Query("SELECT new com.programmerbeginner.catalog.dto.AuthorQueryDto(b.id, ba.nama)" +
			"FROM Book b " +
			"JOIN b.authors ba " +
			"WHERE b.id IN :bookIdList")
	public List<AuthorQueryDto> findAuthorByBookIdList(List<Long> bookIdList);

	

}
