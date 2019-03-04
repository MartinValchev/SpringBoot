package com.spring.project.exodia.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.exodia.entity.Document;
import com.spring.project.exodia.entity.User;
import com.spring.project.exodia.service.DocumentService;

@Controller
public class SecuredController {
	@Autowired
	ObjectFactory<HttpSession> httpSessionFactory;

	@Autowired
	DocumentService documentService;

	@RequestMapping("/home")
	public ModelAndView getHomePage(ModelAndView modelAndView) {

		if (isUserLogged()) {
			List<Document> documentList = documentService.getAllDocuments();
			modelAndView.addObject("documents", documentList);
			modelAndView.setViewName("home");
		} else {
			modelAndView.setViewName("redirect:/login");
		}

		return modelAndView;
	}

	@RequestMapping("/schedule")
	public ModelAndView getSchedulePage(ModelAndView modelAndView) {

		if (isUserLogged()) {
			modelAndView.setViewName("schedule");
		} else {
			modelAndView.setViewName("redirect:/login");
		}

		return modelAndView;
	}

	@RequestMapping("/details/{id}")
	public ModelAndView getDetailsPage(ModelAndView modelAndView, @PathVariable("id") String id) {

		if (isUserLogged()) {
			Document document = documentService.getDocumentById(id);
			modelAndView.addObject("document", document);
			modelAndView.setViewName("details");
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, path = "/scheduleDocument")
	public ModelAndView scheduleDocument(ModelAndView modelAndView,@ModelAttribute Document document) {
		Document newDocument = documentService.storeDocument(document);
		modelAndView.getModel().put("document", newDocument);
		String docUuId = newDocument.getUuid();
		modelAndView.setViewName("redirect:/details/"+ docUuId);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/print")
	public ModelAndView printDocument(ModelAndView modelAndView,Document document) {
		documentService.printDocument(document);
		modelAndView.setViewName("redirect:/home");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/print")
	public ModelAndView getPrintPage(ModelAndView modelAndView) {

		if (isUserLogged()) {
			modelAndView.setViewName("redirect:/print");
		} else {
			modelAndView.setViewName("redirect:/login");
		}

		return modelAndView;
	}
	private boolean isUserLogged() {
		return httpSessionFactory.getObject().getAttribute("username") != null;

	}

}