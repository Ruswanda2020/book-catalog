package com.programmerbeginner.catalog.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name ="category")
public class Category implements Serializable{


	@Serial
	private static final long serialVersionUID = -6063205111531238403L;


	@Id
	@Column(name ="code",nullable = false)
	private String code;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name ="description",nullable = true)
	private String description;
	
	@ManyToMany(mappedBy = "categories")
	private List<Book> books;

}
