package edu.sjsu.cmpe275.lab2.repository;

import edu.sjsu.cmpe275.lab2.model.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* <h1>PlayerRepository</h1>
* 
* <p>
* The PlayerRepository interface defines methods for 
* all the CRUD operations on the "PLAYER" entity.
*
* @author  Tao Geng
* @version 1.0
* @since   2017-11-07
*/

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	
}