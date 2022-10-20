package com.movieland.service.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movieland.service.businesslogic.CountryServiceImpl;
import com.movieland.service.dto.CountryDTO;
import com.movieland.service.dto.ResponseDTO;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.ServiceExceptionUtils;

@RestController
@RequestMapping(path = "/movieland/countries")
public class CountryRestController {

	@Autowired
	private CountryServiceImpl service;
	
	@RequestMapping(path = {"/",""}, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<CountryDTO>>> findAll(){
		final List<CountryDTO> allCountries = service.findAll();
		final ResponseDTO<List<CountryDTO>> resp = new ResponseDTO<List<CountryDTO>>();
		resp.setData(allCountries);
		return new ResponseEntity<ResponseDTO<List<CountryDTO>>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(path = {"/{countryId}"},method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<CountryDTO>> findById(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "countryId") String countryId) {
		
		final ResponseDTO<CountryDTO> resp = new ResponseDTO<CountryDTO>();
		final BigInteger countryIdBi = new BigInteger(countryId);
		
		try {
			final CountryDTO foundCountry = service.findById(countryIdBi);
			resp.setData(foundCountry);
			return new ResponseEntity<ResponseDTO<CountryDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<CountryDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/", ""},method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<CountryDTO>> createCountry(@RequestBody CountryDTO country){
		
		final ResponseDTO<CountryDTO> resp = new ResponseDTO<CountryDTO>();
		try {
			final CountryDTO createdDto = service.create(country);
			resp.setData(createdDto);
			return new ResponseEntity<ResponseDTO<CountryDTO>>(resp, HttpStatus.CREATED);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<CountryDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{countryId}"},method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<CountryDTO>> updateCountry(@PathVariable(name = "countryId") String countryId, @RequestBody CountryDTO country){
		
		final ResponseDTO<CountryDTO> resp = new ResponseDTO<CountryDTO>();
		try {
			final BigInteger countryIdBi = new BigInteger(countryId);
			country.setId(countryIdBi);
			final CountryDTO createdDto = service.update(country);
			resp.setData(createdDto);
			return new ResponseEntity<ResponseDTO<CountryDTO>>(resp, HttpStatus.CREATED);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<CountryDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{countryId}"},method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<CountryDTO>> deleteCountry(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "countryId") String countryId) {
		
		final ResponseDTO<CountryDTO> resp = new ResponseDTO<CountryDTO>();
		final BigInteger countryIdBi = new BigInteger(countryId);
		
		try {
			service.delete(countryIdBi);
			return new ResponseEntity<ResponseDTO<CountryDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<CountryDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
}
