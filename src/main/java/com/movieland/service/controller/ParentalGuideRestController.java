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

import com.movieland.service.businesslogic.ParentalGuideServiceImpl;
import com.movieland.service.dto.ParentalGuideDTO;
import com.movieland.service.dto.ResponseDTO;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.ServiceExceptionUtils;

@RestController
@RequestMapping(path = "/movieland/parental-guides")
public class ParentalGuideRestController {

	@Autowired
	private ParentalGuideServiceImpl service;
	
	@RequestMapping(path = {"/",""}, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<ParentalGuideDTO>>> findAll(){
		final List<ParentalGuideDTO> allCountries = service.findAll();
		final ResponseDTO<List<ParentalGuideDTO>> resp = new ResponseDTO<List<ParentalGuideDTO>>();
		resp.setData(allCountries);
		return new ResponseEntity<ResponseDTO<List<ParentalGuideDTO>>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(path = {"/{parentalGuideId}"},method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<ParentalGuideDTO>> findById(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "parentalGuideId") String parentalGuideId) {
		
		final ResponseDTO<ParentalGuideDTO> resp = new ResponseDTO<ParentalGuideDTO>();
		final BigInteger parentalGuideIdBi = new BigInteger(parentalGuideId);
		
		try {
			final ParentalGuideDTO foundParentalGuide = service.findById(parentalGuideIdBi);
			resp.setData(foundParentalGuide);
			return new ResponseEntity<ResponseDTO<ParentalGuideDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<ParentalGuideDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/", ""},method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<ParentalGuideDTO>> createParentalGuide(@RequestBody ParentalGuideDTO ParentalGuide){
		
		final ResponseDTO<ParentalGuideDTO> resp = new ResponseDTO<ParentalGuideDTO>();
		try {
			final ParentalGuideDTO createdDto = service.create(ParentalGuide);
			resp.setData(createdDto);
			return new ResponseEntity<ResponseDTO<ParentalGuideDTO>>(resp, HttpStatus.CREATED);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<ParentalGuideDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{parentalGuideId}"},method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<ParentalGuideDTO>> updateParentalGuide(@PathVariable(name = "parentalGuideId") String parentalGuideId, @RequestBody ParentalGuideDTO parentalGuide){
		
		final ResponseDTO<ParentalGuideDTO> resp = new ResponseDTO<ParentalGuideDTO>();
		try {
			final BigInteger parentalGuideIdBi = new BigInteger(parentalGuideId);
			parentalGuide.setId(parentalGuideIdBi);
			final ParentalGuideDTO createdDto = service.update(parentalGuide);
			resp.setData(createdDto);
			return new ResponseEntity<ResponseDTO<ParentalGuideDTO>>(resp, HttpStatus.CREATED);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<ParentalGuideDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{parentalGuideId}"},method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<ParentalGuideDTO>> deleteParentalGuide(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "parentalGuideId") String parentalGuideId) {
		
		final ResponseDTO<ParentalGuideDTO> resp = new ResponseDTO<ParentalGuideDTO>();
		final BigInteger parentalGuideIdBi = new BigInteger(parentalGuideId);
		
		try {
			service.delete(parentalGuideIdBi);
			return new ResponseEntity<ResponseDTO<ParentalGuideDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<ParentalGuideDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
}
