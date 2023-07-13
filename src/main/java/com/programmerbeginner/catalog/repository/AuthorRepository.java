package com.programmerbeginner.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programmerbeginner.catalog.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	
	//method name convention
	//find+keyword
	
	//sql-> select * from Author a where id=?
	public Optional<Author> findById(String id);
	
	public Optional<Author> findBySecureId(String id);
	
	//where id = :id AND deleted = false
	//public Optional<Author> findByIdAndDeletedFalse(Long id);
	
	//sql -> select a from Author a where a.name LIKE ?
	public List<Author> findByNamaLike(String authorName);
	
	public List<Author> findBySecureIdIn(List<String> authorIdList );

	

}
