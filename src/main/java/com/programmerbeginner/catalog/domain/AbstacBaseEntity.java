package com.programmerbeginner.catalog.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Index;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Data;

@MappedSuperclass
@Data
@Table(indexes = {
		@Index(name ="uk_secure_id",columnList = "secure_id")
})
public abstract class AbstacBaseEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 8083156529541060223L;
	/**
	 * 
	 */
	
	
	@Column(name = "secure_id",nullable = false,unique = true)
	private String secureId=UUID.randomUUID().toString();
	

}
