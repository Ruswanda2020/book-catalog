package com.programmerbeginner.catalog.domain;

import java.io.Serial;
import java.io.Serializable;

import java.util.List;

import io.github.resilience4j.core.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Table(name = "book")
@Data

public class Book extends AbstacBaseEntity{

	@Serial
	private static final long serialVersionUID = 6727554523340105042L;
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titel",nullable = false)
	private String title;

	@Column(name = "description",nullable =	true,columnDefinition = "TEXT")
	private String description;
	
	@OneToOne(mappedBy = "book")
	private book_detail bookDetail;
	
	@ManyToOne
	@JoinColumn(name ="publisher_id",nullable = false)
	private Publisher publisher;
	
	@ManyToMany
	@JoinTable(name= "book_author",joinColumns = {
			   @JoinColumn(name ="book_id",referencedColumnName = "id")},
	inverseJoinColumns = {
			@JoinColumn(name="author_id",referencedColumnName = "id")
	})
	private List<Author> authors ;
	
	
	@ManyToMany
	@JoinTable(name= "book_category",joinColumns = {
			   @JoinColumn(name ="book_id",referencedColumnName = "id")},
	inverseJoinColumns = {
			@JoinColumn(name="category_code",referencedColumnName = "code")
	})
	private List<Category> categories;
	
	
	

}
