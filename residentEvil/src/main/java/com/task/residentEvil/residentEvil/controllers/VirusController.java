package com.task.residentEvil.residentEvil.controllers;

import com.task.residentEvil.residentEvil.entity.Capitals;
import com.task.residentEvil.residentEvil.entity.Magnitude;
import com.task.residentEvil.residentEvil.entity.Mutation;
import com.task.residentEvil.residentEvil.entity.Virus;
import com.task.residentEvil.residentEvil.model.VirusBindingModel;
import com.task.residentEvil.residentEvil.repository.VirusRepository;
import com.task.residentEvil.residentEvil.service.CapitalsService;
import com.task.residentEvil.residentEvil.service.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class VirusController {

    @Autowired
    private CapitalsService capitalsService;


    @Autowired
    private VirusService virusService;

    private ModelMapper modelMapper;

    @Autowired
    public VirusController(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }
    
    @GetMapping("/virus/add")
    public String getAddVirus(VirusBindingModel virusBindingModel, Model model){
        List<Mutation> mutationList = Stream.of(Mutation.values()).collect(Collectors.toList());
        List<Magnitude> magnitudeList = Stream.of(Magnitude.values()).collect(Collectors.toList());
        List<Capitals> capitalsList = capitalsService.getCapitalsList();
        model.addAttribute("allMutations",mutationList);
        model.addAttribute("magnitudeList",magnitudeList);
        model.addAttribute("capitalsList",capitalsList);
        return "AddVirus";
    }
    @GetMapping("/virus/{id}")
    public String getVirus(@PathVariable("id") Long id,Model model){
        Virus virus = virusService.getVirusById(id);
        model.addAttribute("virus",virus);
        return "VirusById";
    }
    @GetMapping("/viruses")
    public String getAllViruses(Model model){
        List<Virus> allVirusList = virusService.getAllVirusList();
        model.addAttribute("allVirusList",allVirusList);
        return "allViruses";
    }
    
    @GetMapping("/virusList")
    @ResponseBody
    public List<Virus> getAllVirusList(Model model){
        List<Virus> virusList = virusService.getAllVirusList();
    	
        return virusList;
    }
    

    @PostMapping("/virus/add")
    public String addVirus(@Valid @ModelAttribute VirusBindingModel virusBindingModel, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            List<Mutation> mutationList = Stream.of(Mutation.values()).collect(Collectors.toList());
            List<Magnitude> magnitudeList = Stream.of(Magnitude.values()).collect(Collectors.toList());
            List<Capitals> capitalsList = capitalsService.getCapitalsList();
            model.addAttribute("allMutations",mutationList);
            model.addAttribute("magnitudeList",magnitudeList);
            model.addAttribute("capitalsList",capitalsList);
            model.addAttribute("virusBindingModel",virusBindingModel);
            return "AddVirus";
        }else{
            Virus savedVirus = virusService.addVirus(virusBindingModel);
            return "redirect:" +  savedVirus.getId();
        }
    }
    @GetMapping("/editVirus/{id}")
    public String getEditVirus(@PathVariable("id")Long id,VirusBindingModel virusBindingModel, Model model){
        Virus virus = virusService.getVirusById(id);

        if(virus !=null){
            List<Mutation> mutationList = Stream.of(Mutation.values()).collect(Collectors.toList());
            List<Magnitude> magnitudeList = Stream.of(Magnitude.values()).collect(Collectors.toList());
            List<Capitals> capitalsList = capitalsService.getCapitalsList();
            model.addAttribute("allMutations",mutationList);
            model.addAttribute("magnitudeList",magnitudeList);
            model.addAttribute("capitalsList",capitalsList);
            virusBindingModel = modelMapper.map(virus, VirusBindingModel.class);
            model.addAttribute("virusBindingModel", virusBindingModel);
            return "EditVirus";
        }else{
            return "";
        }
    }

    @PostMapping("/editVirus")
    public String editVirus(@Valid @ModelAttribute VirusBindingModel virusBindingModel, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            List<Mutation> mutationList = Stream.of(Mutation.values()).collect(Collectors.toList());
            List<Magnitude> magnitudeList = Stream.of(Magnitude.values()).collect(Collectors.toList());
            List<Capitals> capitalsList = capitalsService.getCapitalsList();
            model.addAttribute("allMutations",mutationList);
            model.addAttribute("magnitudeList",magnitudeList);
            model.addAttribute("capitalsList",capitalsList);
            model.addAttribute("virusBindingModel",virusBindingModel);
            return "EditVirus";
        }else{
            Virus savedVirus = virusService.addVirus(virusBindingModel);
            return "redirect:virus/" +  savedVirus.getId();
        }
    }
    @GetMapping("/deleteVirus/{id}")
    public String deleteVirus(@PathVariable("id")Long id,VirusBindingModel virusBindingModel, Model model){
    	Virus virus = virusService.getVirusById(id);

        if(virus !=null){
            List<Mutation> mutationList = Stream.of(Mutation.values()).collect(Collectors.toList());
            List<Magnitude> magnitudeList = Stream.of(Magnitude.values()).collect(Collectors.toList());
            List<Capitals> capitalsList = capitalsService.getCapitalsList();
            model.addAttribute("id", id);
            model.addAttribute("allMutations",mutationList);
            model.addAttribute("magnitudeList",magnitudeList);
            model.addAttribute("capitalsList",capitalsList);
            virusBindingModel = modelMapper.map(virus, VirusBindingModel.class);
            model.addAttribute("virusBindingModel", virusBindingModel);
            return "deleteVirus";
        }else{
            return "";
        }

    }

    @PostMapping("/deleteVirus")
    public String deleteVirus(@Valid @ModelAttribute VirusBindingModel virusBindingModel, BindingResult bindingResult, Model model){
    	
       virusService.removeVirus(virusBindingModel);
       return "redirect:/viruses";
    }


}
