package com.programmerbeginner.catalog.domain;

import java.io.Serial;
import java.time.LocalDate;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "author")
//@SQLDelete(sql = "UPDATE author SET deleted=true WHERE id = ?")
//@Where(clause = "deleted=false")
public class Author extends AbstacBaseEntity{


	@Serial
	private static final long serialVersionUID = -5529281617160032579L;
	/**
	 *
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//@SequenceGenerator(name = "author_generator",sequenceName = "author_id_seq")
	private Long id;
	
	@Column(name = "name",nullable = false,columnDefinition = "varchar(300)")
	private String nama;
	
	@Column(name = "birt_date",nullable = false)	
	private LocalDate birthDate;
	
	

}
