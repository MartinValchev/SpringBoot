package com.spring.project.exodia.service;

import java.util.List;

import com.spring.project.exodia.entity.Document;

public interface DocumentService {
	Document getDocumentById(String id);
	List<Document> getAllDocuments();
	Document storeDocument(Document document);
	void printDocument(Document document);
	
}
