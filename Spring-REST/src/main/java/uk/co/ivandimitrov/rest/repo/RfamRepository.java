package uk.co.ivandimitrov.rest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RfamRepository extends JpaRepository<Family, String> {

    /**
     * Get the database contents constrained by their "created" variable.
     * 
     * @param minCreated Date from which to constrain.
     * @param maxCreated Date to which to constrain.
     * @return Returns a list of objects constrained by date parameters. (See
     *         database contents for more info)
     */
    @Query("FROM Family WHERE created>?1 AND created<?2")
    List<Family> findByCreateDate(String minCreated, String maxCreated);

}