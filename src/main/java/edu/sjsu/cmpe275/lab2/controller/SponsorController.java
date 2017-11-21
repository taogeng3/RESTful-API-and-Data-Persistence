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
* <h1>SponsorController</h1>
* 
* <p>
* The SponsorController class is to generate Sponsor APIs.
*
* @author  Tao Geng
* @version 1.0
* @since   2017-11-07
*/

@RestController
public class SponsorController {

	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	/**
	* This method is used to create a Sponsor object.
	* @param name This is the 1st parameter obtained from request query. (required)
	* @param description  This is the 2rd parameter obtained from request query.
	* @param street This is the 3th parameter obtained from request query.
	* @param city  This is the 4th parameter obtained from request query.
	* @param state This is the 5th parameter obtained from request query.
	* @param zip  This is the 6th parameter obtained from request query.
	* @return ResponseEntity<Sponsor> This returns the created Sponsor object.
	*/	
	@PostMapping("/sponsor")
	public ResponseEntity<Sponsor> createSponsor(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "street", required = false) String street,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip) {
		Sponsor sponsor = new Sponsor();
		if (!name.isEmpty()) {
			sponsor.setName(name);
		} else {
			return ResponseEntity.badRequest().build();
		}
		sponsor.setDescription(description);
		Address address = new Address();
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setZip(zip);
		sponsor.setAddress(address);
		sponsorRepository.save(sponsor);
		return ResponseEntity.ok(sponsor);
	}

	/**
	* This method is used to get a Sponsor object.
	* @param sponsorId This is the only parameter obtained from request query.
	* @return ResponseEntity<Sponsor> This returns the requested Sponsor object.
	*/
	@GetMapping("/sponsor/{id}")
	public ResponseEntity<Sponsor> getSponsor(@PathVariable(value = "id") long sponsorId) {
		Sponsor sponsor = sponsorRepository.findOne(sponsorId);
		if (sponsor == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(sponsor);
	}

	/**
	* This method is used to update a Sponsor object.
	* @param sponsorId This is the 1st parameter obtained from request query. (required)
	* @param name This is the 2nd parameter obtained from request query. (required)
	* @param description  This is the 3rd parameter obtained from request query.
	* @param street This is the 4th parameter obtained from request query.
	* @param city  This is the 5th parameter obtained from request query.
	* @param state This is the 6th parameter obtained from request query.
	* @param zip  This is the 7th parameter obtained from request query.
	* @return ResponseEntity<Sponsor> This returns the updated Sponsor object.
	*/
	@PostMapping("/sponsor/{id}")
	public ResponseEntity<Sponsor> updateSponsor(
			@PathVariable(value = "id") long sponsorId,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "street", required = false) String street,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip) {
		Sponsor sponsor = sponsorRepository.findOne(sponsorId);
		if (sponsor == null) {
			return ResponseEntity.notFound().build();
		}
		if (!name.isEmpty()) {
			sponsor.setName(name);
		} else {
			return ResponseEntity.badRequest().build();
		}
		if (description != null) {
			sponsor.setDescription(description);
		}
		Address address = sponsor.getAddress();
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
		sponsor.setAddress(address);
		Sponsor updatedSponsor = sponsorRepository.save(sponsor);
		return ResponseEntity.ok(updatedSponsor);
	}

	/**
	* This method is used to delete a Sponsor object.
	* @param sponsorId This is the only parameter obtained from request query.
	* @return ResponseEntity<Sponsor> This returns the deleted Sponsor object.
	*/
	@DeleteMapping("/sponsor/{id}")
	public ResponseEntity<Sponsor> deleteSponsor(@PathVariable(value = "id") long sponsorId) {
		Sponsor sponsor = sponsorRepository.findOne(sponsorId);
		if (sponsor == null) {
			return ResponseEntity.notFound().build();
		}
		List<Player> players = playerRepository.findAll();
		for (Player p : players) {
			Sponsor s = p.getSponsor();
			if (s != null && s.equals(sponsor)) {
				return ResponseEntity.badRequest().build();
			}
		}
		sponsorRepository.delete(sponsor);
		return ResponseEntity.ok(sponsor);
	}
}