package com.spring.project.exodia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.exodia.entity.Document;
import com.spring.project.exodia.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	DocumentRepository documentRepository;

	@Override
	public Document getDocumentById(String id) {
		Document document = documentRepository.getDocumentById(id);
		return document;
	}

	@Override
	public List<Document> getAllDocuments() {
		List<Document> listDocuments = documentRepository.getAllDocuments();
		return listDocuments;
	}

	@Override
	public Document storeDocument(Document document) {
		return documentRepository.storeDocument(document);
	}

	@Override
	public void printDocument(Document document) {
		documentRepository.deleteDocument(document);
		
	}

}
