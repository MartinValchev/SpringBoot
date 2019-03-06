package com.spring.project.exodia.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.spring.project.exodia.entity.Document;
import com.spring.project.exodia.utils.ProjectUtils;

@Repository
@Transactional
public class DocumentRepositoryImpl implements DocumentRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Document getDocumentById(String id) {
		TypedQuery<Document> documentQuery = entityManager.createQuery(ProjectUtils.GET_DOCUMENTS_BY_ID_QUERY,
				Document.class);
		documentQuery.setParameter("documentId", id);
		return documentQuery.getSingleResult();
	}

	@Override
	public List<Document> getAllDocuments() {
		List<Document> documenstList = entityManager.createQuery(ProjectUtils.GET_ALL_DOCUMENTS_QUERY, Document.class)
				.getResultList();
		return documenstList;
	}

	@Override
	public Document storeDocument(Document document) {

		return entityManager.merge(document);
	}

	@Override
	public void deleteDocument(Document document) {
		if (!entityManager.contains(document)) {
			Document docNew = entityManager.merge(document);
			entityManager.remove(docNew);
		} else {
			entityManager.remove(document);
		}

	};

}
