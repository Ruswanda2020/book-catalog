package com.subrutin.catalog.domain;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//@SequenceGenerator(name = "author_generator",sequenceName = "author_id_seq")
	private Long id;
	
	@Column(name = "name",nullable = false,columnDefinition = "varchar(300)")
	private String nama;
	
	@Column(name = "birt_date",nullable = false)	
	private LocalDate birthDate;
	
	

}
