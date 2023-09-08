package com.programmerbeginner.catalog.domain;

import java.io.Serial;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "book")
@Data

public class Book extends AbstractBaseEntity {

	@Serial
	private static final long serialVersionUID = 6727554523340105042L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titel",nullable = false,unique = true)
	private String title;

	@Column(name = "description",nullable =	true,columnDefinition = "TEXT")
	private String description;
	
	@OneToOne(mappedBy = "book")
	private BookDetail bookDetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
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
