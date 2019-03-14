package com.task.residentEvil.residentEvil.repository;

import com.task.residentEvil.residentEvil.entity.Virus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface VirusRepository extends CrudRepository<Virus,Long> {

    @Query("SELECT e from Virus e")
     List<Virus> getAllVirusList();

    @Query("SELECT e from Virus e where e.id=:id")
    Virus getVirusById(@Param("id") Long id);
}
