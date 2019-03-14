package com.task.residentEvil.residentEvil.repository;

import com.task.residentEvil.residentEvil.entity.Capitals;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapitalsRepository extends CrudRepository<Capitals,Long>{

    @Query("SELECT e from Capitals e")
    public List<Capitals> getAllCapitalsList();
}
