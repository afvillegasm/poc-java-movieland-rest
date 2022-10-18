package com.movieland.service.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movieland.service.businesslogic.CountryServiceImpl;
import com.movieland.service.dto.CountryDTO;

@RestController
@RequestMapping(path = "/movieland/countries")
public class CountryRestController {

	@Autowired
	private CountryServiceImpl service;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ResponseEntity<List<CountryDTO>> findAll(){
		final List<CountryDTO> resp = service.findAll();
		return new ResponseEntity<List<CountryDTO>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(path = {"/{countryId}"},method = RequestMethod.GET)
	public ResponseEntity<CountryDTO> findByBookingCode(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "countryId") String countryId) {
		
		final BigInteger countryIdBi = new BigInteger(countryId);
		
		try {
			final CountryDTO response = service.findById(countryIdBi);
			
			if(response != null && response.getStatus() != null) {
				return new ResponseEntity<CountryDTO>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<CountryDTO>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
