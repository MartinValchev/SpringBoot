package com.spring.project.exodia.repository;

import java.util.List;

import com.spring.project.exodia.entity.Document;

public interface DocumentRepository {
	Document getDocumentById(String id);
	List<Document> getAllDocuments();
	Document storeDocument(Document document);
	void deleteDocument(Document document);
}
