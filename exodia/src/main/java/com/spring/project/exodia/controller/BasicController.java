package com.spring.project.exodia.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.exodia.entity.User;
import com.spring.project.exodia.service.UserService;
import com.spring.project.exodia.utils.UuIdUserGenerator;

@Controller
public class BasicController {

	@Autowired
	UserService userService;
	
	@Autowired
	UuIdUserGenerator uuidGenerator;
	
	@Autowired
	ObjectFactory<HttpSession> httpSessionFactory;

	@GetMapping("/index")
	public ModelAndView getIndexPage(ModelAndView modelAndView) {
		modelAndView.setViewName("index");

		return modelAndView;
	}

	@RequestMapping("/login")
	public ModelAndView getLoginPage(ModelAndView modelAndView) {
		modelAndView.getModel().put("username", "");
		modelAndView.getModel().put("password", "");
		modelAndView.setViewName("login");

		return modelAndView;
	}

	@PostMapping("/loginAuth")
	public ModelAndView loginAuth(ModelAndView modelAndView, @ModelAttribute User user ) {
		if (userService.isUserAuthenticated(user.getUsername(), user.getPassword())) {
			HttpSession session = httpSessionFactory.getObject();
			session.setAttribute("username", user.getUsername());
			modelAndView.setViewName("redirect:/home");
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(path="/register", method= RequestMethod.GET)
	public ModelAndView getRegisterPage(ModelAndView modelAndView) {
		User user = new User();
		modelAndView.getModel().put("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(path="/register", method= RequestMethod.POST)
	public ModelAndView register(ModelAndView modelAndView,@ModelAttribute User user) {
		userService.storeUser(user);
		
		modelAndView.setViewName("redirect:/login");
		return modelAndView;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(ModelAndView modelAndView) {
		modelAndView.setViewName("redirect:/index");
		return modelAndView;
	}
}
