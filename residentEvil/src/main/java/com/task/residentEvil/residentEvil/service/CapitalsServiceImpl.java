package com.task.residentEvil.residentEvil.service;

import com.task.residentEvil.residentEvil.entity.Capitals;
import com.task.residentEvil.residentEvil.repository.CapitalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CapitalsServiceImpl implements  CapitalsService {
    @Autowired
    CapitalsRepository capitalsRepository;

    @Override
    public List<Capitals> getCapitalsList() {
        return capitalsRepository.getAllCapitalsList();
    }
}
