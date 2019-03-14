package com.task.residentEvil.residentEvil.service;

import com.task.residentEvil.residentEvil.entity.Virus;
import com.task.residentEvil.residentEvil.model.VirusBindingModel;

import java.util.List;

public interface VirusService {
    List<Virus> getAllVirusList();

    Virus getVirusById(Long id);

    Virus addVirus(VirusBindingModel virusBindingModel);

    void removeVirus(Virus virus);

    void editVirus(Virus virus);

    String getGeoData();
}
