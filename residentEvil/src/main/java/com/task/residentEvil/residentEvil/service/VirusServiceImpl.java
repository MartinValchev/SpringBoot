package com.task.residentEvil.residentEvil.service;


import com.task.residentEvil.residentEvil.entity.Capitals;
import com.task.residentEvil.residentEvil.entity.Virus;
import com.task.residentEvil.residentEvil.model.VirusBindingModel;
import com.task.residentEvil.residentEvil.repository.VirusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

@Service
public class VirusServiceImpl implements VirusService {

    @Autowired
    private VirusRepository virusRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Virus> getAllVirusList() {
        return virusRepository.getAllVirusList();
    }

    @Override
    public Virus getVirusById(Long id) {
        return virusRepository.getVirusById(id);
    }

    @Override
    public Virus addVirus(VirusBindingModel virusBindingModel) {
        Virus virus = modelMapper.map(virusBindingModel,Virus.class);
        Virus savedVirus = virusRepository.save(virus);
        return savedVirus;
    }

    @Override
    public void removeVirus(Virus virus) {
        virusRepository.delete(virus);
    }

    @Override
    public void editVirus(Virus virus) {
        virusRepository.save(virus);
    }

    @Override
    public String getGeoData() {
        Locale.setDefault(new Locale("en", "US"));
        List<Virus> virusList = this.virusRepository.getAllVirusList();
        StringBuilder geoJson = new StringBuilder();
        String header = "{\n" +
                "    \"type\": \"FeatureCollection\",\n" +
                "    \"features\": [\n";
        String footer = "]\n" + "}\n";
        geoJson.append(header);

        StringJoiner joiner = new StringJoiner(",");
        for(Virus virus: virusList){
            String color = "#f00";
            int magnitude = 0;
            switch (virus.getMagnitude()){
                case Low: magnitude=4; break;
                case Medium: magnitude=5; break;
                case High: magnitude=6; break;
            }
            for (Capitals capital: virus.getCapitals()){
               String body =  String.format("{\n" +
                       "        \"type\": \"Feature\",\n" +
                       "        \"properties\": {\n" +
                       "            \"mag\": %d,\n" +
                       "            \"color\": \"%s\"\n" +
                       "        },\n" +
                       "        \"geometry\": {\n" +
                       "            \"type\": \"Point\",\n" +
                       "            \"coordinates\": [\n" +
                       "                %f,\n" +
                       "               %f\n" +
                       "            ]\n" +
                       "        }\n" +
                       "    }\n",magnitude,color,capital.getLatitude(), capital.getLongitude());
               joiner.add(body);
            }
        }
        geoJson.append(joiner);
        geoJson.append(footer);
        System.out.println(geoJson);
        return geoJson.toString();
    }
}
