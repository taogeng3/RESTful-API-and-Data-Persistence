package edu.sjsu.cmpe275.lab2.repository;

import edu.sjsu.cmpe275.lab2.model.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* <h1>SponsorRepository</h1>
* 
* <p>
* The SponsorRepository interface defines methods for 
* all the CRUD operations on the "SPONSOR" entity.
*
* @author  Tao Geng
* @version 1.0
* @since   2017-11-07
*/

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
	
}