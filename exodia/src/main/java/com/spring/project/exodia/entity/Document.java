package com.spring.project.exodia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.spring.project.exodia.utils.UuIdDocumentGenerator;
import com.spring.project.exodia.utils.UuIdUserGenerator;

@Entity
@Table(name="document")
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="doc_seq")
	@GenericGenerator(name="doc_seq",strategy="com.spring.project.exodia.utils.UuIdDocumentGenerator",
	parameters= {@Parameter(name=UuIdUserGenerator.INCREMENT_PARAM, value="10"),
			@Parameter(name=UuIdDocumentGenerator.DOCUMENT_VALUE_PREFIX_PARAMETER, value="DOC_"),
			@Parameter(name=UuIdDocumentGenerator.DOCUMENT_NUMBER_FORMAT_PARAMETER, value="%05d")})
	@Column(name="uuid", updatable=false, nullable=false)
	private String uuid;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}

