package edu.sjsu.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import edu.sjsu.cmpe275.lab2.model.Player;
import edu.sjsu.cmpe275.lab2.repository.PlayerRepository;

/**
* <h1>OpponentController</h1>
* 
* <p>
* The OpponentController class is used to generate Opponent's APIs.
*
* @author  Tao Geng
* @version 1.0
* @since   2017-11-07
*/

@RestController
public class OpponentController {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	/**
	* This method is used to add an opponent.
	* @param playerId1 This is the 1st parameter obtained from request query.
	* @param playerId2 This is the 2nd parameter obtained from request query.
	* @return ResponseEntity<String> This returns the text message on the results.
	*/
	@PutMapping("/opponents/{id1}/{id2}")
    public ResponseEntity<String> addOpponent(
    		@PathVariable(value = "id1") Long playerId1,
    		@PathVariable(value = "id2") Long playerId2) {
		Player player1 = playerRepository.findOne(playerId1);
		Player player2 = playerRepository.findOne(playerId2);
		if (player1 == null || player2 == null) {
			return ResponseEntity.notFound().build();
		} 
		if (player1.equals(player2)) {
			return ResponseEntity.badRequest().build();
		} 
		if (player1.getOpponents().contains(player2) && player2.getOpponents().contains(player1)) {
			return ResponseEntity.ok().build();
		} 
		player1.getOpponents().add(player2);
		player2.getOpponents().add(player1);
		playerRepository.save(player1);
		playerRepository.save(player2);
		return ResponseEntity.ok("Player " + playerId1 + " and Player " + playerId2 + " are opponents now.");
    }
	
	/**
	* This method is used to remove an opponent.
	* @param playerId1 This is the 1st parameter obtained from request query.
	* @param playerId2 This is the 2nd parameter obtained from request query.
	* @return ResponseEntity<String> This returns the text message on the results.
	*/
	@DeleteMapping("/opponents/{id1}/{id2}")
    public ResponseEntity<String> deleteOpponent(
    		@PathVariable(value = "id1") Long playerId1,
    		@PathVariable(value = "id2") Long playerId2) {
		Player player1 = playerRepository.findOne(playerId1);
		Player player2 = playerRepository.findOne(playerId2);
		if (player1 == null || player2 == null) {
			return ResponseEntity.notFound().build();
		} 
		if (player1.equals(player2)) {
			return ResponseEntity.badRequest().build();
		} 
		if (!player1.getOpponents().contains(player2) || !player2.getOpponents().contains(player1)) {
			return ResponseEntity.notFound().build();
		}
		player1.getOpponents().remove(player2);
		player2.getOpponents().remove(player1);
		playerRepository.save(player1);
		playerRepository.save(player2);
		return ResponseEntity.ok("Player " + playerId1 + " and Player " + playerId2 + " are NOT opponents now.");
    }
}
