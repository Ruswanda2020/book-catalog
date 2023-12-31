package com.programmerbeginner.catalog.domain;


import java.io.Serial;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="publisher")
public class Publisher extends AbstractBaseEntity {

	@Serial
	private static final long serialVersionUID = 2913783973971027135L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id ;
	
	@Column(name="name",nullable = false)
	private String name ;
	
	@Column(name ="company_name",nullable = false)
	private String companyName;
	
	@Column(name = "address")
	private String address;
	
	@OneToMany(mappedBy = "publisher")
	private List<Book> books;
	
	

}
