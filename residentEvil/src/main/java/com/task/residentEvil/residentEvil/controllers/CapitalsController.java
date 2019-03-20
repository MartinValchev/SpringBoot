package com.task.residentEvil.residentEvil.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.task.residentEvil.residentEvil.entity.Capitals;
import com.task.residentEvil.residentEvil.service.CapitalsService;

@Controller
public class CapitalsController {

	@Autowired
	CapitalsService capitalsService;
	
	@GetMapping("/capitalsList")
	@ResponseBody
	public List<Capitals> getCapitalsList(){
		return capitalsService.getCapitalsList();
	}
	
}
