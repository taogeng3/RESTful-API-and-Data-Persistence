package edu.sjsu.cmpe275.lab2.controller;

import edu.sjsu.cmpe275.lab2.model.Address;
import edu.sjsu.cmpe275.lab2.model.Player;
import edu.sjsu.cmpe275.lab2.model.Sponsor;
import edu.sjsu.cmpe275.lab2.repository.PlayerRepository;
import edu.sjsu.cmpe275.lab2.repository.SponsorRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

/**
* <h1>PlayerController</h1>
* 
* <p>
* The PlayerController class is to generate Player APIs.
*
* @author  Tao Geng
* @version 1.0
* @since   2017-11-07
*/

@RestController
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private SponsorRepository sponsorRepository;
    
	/**
	* This method is used to create a Player object.
	* @param firstname This is the 1st parameter obtained from request query. (required)
	* @param lastname  This is the 2nd parameter obtained from request query. (required)
	* @param email This is the 3rd parameter obtained from request query. (required)
	* @param description  This is the 4th parameter obtained from request query.
	* @param street This is the 5th parameter obtained from request query. 
	* @param city  This is the 6th parameter obtained from request query. 
	* @param state This is the 7th parameter obtained from request query. 
	* @param zip  This is the 8th parameter obtained from request query. 
	* @param sponsorId This is the 9th parameter obtained from request query. 
	* @return ResponseEntity<Player> This returns the created Player object.
	*/	
	@PostMapping("/player")
	public ResponseEntity<Player> createPlayer(
			@RequestParam(value = "firstname", required = true) String firstname,
			@RequestParam(value = "lastname", required = true) String lastname,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "street", required = false) String street,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip,
			@RequestParam(value = "sponsor", required = false) Long sponsorId) {
		Player player = new Player();
		if (!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty()) {
			player.setFirstname(firstname);
			player.setLastname(lastname);
			player.setEmail(email);
		} else {
			return ResponseEntity.badRequest().build();
		}
		player.setDescription(description);
		Address address = new Address();
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setZip(zip);
		player.setAddress(address);
		if (sponsorId != null) {
			Sponsor sponsor = sponsorRepository.findOne(sponsorId);
			player.setSponsor(sponsor);
		}
		playerRepository.save(player);
		return ResponseEntity.ok(player);
	}
	
	/**
	* This method is used to get a Player object.
	* @param playerId This is the only parameter obtained from request query.  
	* @return ResponseEntity<Player> This returns the requested Player object.
	*/
	@GetMapping("/player/{id}")
	public ResponseEntity<Player> getPlayer(@PathVariable(value = "id") Long playerId) {
		Player player = playerRepository.findOne(playerId);
		if (player == null) {
			return ResponseEntity.notFound().build();
		}
		List<Player> opponents = player.getOpponents();
		for (Player o : opponents) {
			o.setOpponents(null);
		}
		return ResponseEntity.ok(player);
	}
	
	/**
	* This method is used to update a Player object.
	* @param playerId This is the 1st parameter obtained from request query.(required)
	* @param firstname This is the 2nd parameter obtained from request query. (required)
	* @param lastname  This is the 3rd parameter obtained from request query. (required)
	* @param email This is the 4th parameter obtained from request query. (required)
	* @param description  This is the 5th parameter obtained from request query.
	* @param street This is the 6th parameter obtained from request query. 
	* @param city  This is the 7th parameter obtained from request query. 
	* @param state This is the 8th parameter obtained from request query. 
	* @param zip  This is the 9th parameter obtained from request query. 
	* @param sponsorId This is the 10th parameter obtained from request query. 
	* @return ResponseEntity<Player> This returns a updated Player object.
	*/	
	@PostMapping("/player/{id}")
	public ResponseEntity<Player> updatePlayer(
			@PathVariable(value = "id") Long playerId,
			@RequestParam(value = "firstname", required = true) String firstname,
			@RequestParam(value = "lastname", required = true) String lastname,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "street", required = false) String street,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip,
			@RequestParam(value = "sponsor", required = false) Long sponsorId) {
		Player player = playerRepository.findOne(playerId);
		if (player == null) {
			return ResponseEntity.notFound().build();
		}
		if (!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty()) {
			player.setFirstname(firstname);
			player.setLastname(lastname);
			player.setEmail(email);
		} else {
			return ResponseEntity.badRequest().build();
		}
		if (description != null) {
			player.setDescription(description);
		}
		Address address = player.getAddress();
		if (address == null) {
			address = new Address();
		}
		if (street != null) {
			address.setStreet(street);
		}
		if (city != null) {
			address.setCity(city);
		}
		if (state != null) {
			address.setState(state);
		}
		if (zip != null) {
			address.setZip(zip);
		}
		player.setAddress(address);
		if (sponsorId != null) {
			Sponsor sponsor = sponsorRepository.findOne(sponsorId);
			player.setSponsor(sponsor);
		}
		Player updatedPlayer = playerRepository.save(player);
		List<Player> opponents = updatedPlayer.getOpponents();
		for (Player o : opponents) {
			o.setOpponents(null);
		}
		return ResponseEntity.ok(updatedPlayer);
	}
	
	/**
	* This method is used to delete a player object.
	* @param playerId This is the only parameter obtained from request query.  
	* @return ResponseEntity<Player> This returns the deleted Player object.
	*/
	@DeleteMapping("/player/{id}")
	public ResponseEntity<Player> deletePlayer(@PathVariable(value = "id") Long playerId) {
		Player player = playerRepository.findOne(playerId);
		if (player == null) {
			return ResponseEntity.notFound().build();
		}
		List<Player> opponents = player.getOpponents();
		for (Player o : opponents) {
			List<Player> opop = o.getOpponents();
			opop.remove(player);
			o.setOpponents(opop);
			playerRepository.save(o);
		}
		player.setOpponents(null);
		playerRepository.delete(player);
		for (Player o : opponents) {
			o.setOpponents(null);
		}
		player.setOpponents(opponents);
		return ResponseEntity.ok(player);
	}
}
