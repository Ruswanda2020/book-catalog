package com.programmerbeginner.catalog.domain;

import java.io.Serial;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "author")
public class Author extends AbstractBaseEntity {


	@Serial
	private static final long serialVersionUID = -5529281617160032579L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//@SequenceGenerator(name = "author_generator",sequenceName = "author_id_seq")
	private Long id;
	
	@Column(name = "name",nullable = false,columnDefinition = "varchar(300)")
	private String nama;
	
	@Column(name = "birt_date",nullable = false)	
	private LocalDate birthDate;

	@OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
	private List<Address> addresses;
	
	

}
