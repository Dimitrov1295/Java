package uk.co.ivandimitrov.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RfamRepository extends JpaRepository<Family, String> {
    @Query("from Family where created>?1 and created<?2")
    List<Family> findByCreateDate(String minCreated, String maxCreated);

}