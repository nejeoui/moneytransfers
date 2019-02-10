package com.revolut.moneytransfers.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class SequenceGenerator implements Serializable {
	/**
	 * serialVersionUID
	 */
	@Transient
	private transient static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long sequenceValue;
	
	private String dummy="er";
}
