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

import com.movieland.service.businesslogic.GenreServiceImpl;
import com.movieland.service.dto.GenreDTO;
import com.movieland.service.dto.ResponseDTO;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.ServiceExceptionUtils;

@RestController
@RequestMapping(path = "/movieland/genres")
public class GenreRestController {

	@Autowired
	private GenreServiceImpl service;
	
	@RequestMapping(path = {"/",""}, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<GenreDTO>>> findAll(){
		final List<GenreDTO> allGenres = service.findAll();
		final ResponseDTO<List<GenreDTO>> resp = new ResponseDTO<List<GenreDTO>>();
		resp.setData(allGenres);
		return new ResponseEntity<ResponseDTO<List<GenreDTO>>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(path = {"/{genreId}"},method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<GenreDTO>> findById(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "genreId") String genreId) {
		
		final ResponseDTO<GenreDTO> resp = new ResponseDTO<GenreDTO>();
		final BigInteger genreIdBi = new BigInteger(genreId);
		
		try {
			final GenreDTO foundGenre = service.findById(genreIdBi);
			resp.setData(foundGenre);
			return new ResponseEntity<ResponseDTO<GenreDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<GenreDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/", ""},method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<GenreDTO>> createGenre(@RequestBody GenreDTO genre){
		
		final ResponseDTO<GenreDTO> resp = new ResponseDTO<GenreDTO>();
		try {
			final GenreDTO createdDto = service.create(genre);
			resp.setData(createdDto);
			return new ResponseEntity<ResponseDTO<GenreDTO>>(resp, HttpStatus.CREATED);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<GenreDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{genreId}"},method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<GenreDTO>> updateGenre(@PathVariable(name = "genreId") String genreId, @RequestBody GenreDTO genre){
		
		final ResponseDTO<GenreDTO> resp = new ResponseDTO<GenreDTO>();
		try {
			final BigInteger genreIdBi = new BigInteger(genreId);
			genre.setId(genreIdBi);
			final GenreDTO createdDto = service.update(genre);
			resp.setData(createdDto);
			return new ResponseEntity<ResponseDTO<GenreDTO>>(resp, HttpStatus.CREATED);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<GenreDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{genreId}"},method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<GenreDTO>> deleteGenre(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "genreId") String genreId) {
		
		final ResponseDTO<GenreDTO> resp = new ResponseDTO<GenreDTO>();
		final BigInteger genreIdBi = new BigInteger(genreId);
		
		try {
			service.delete(genreIdBi);
			return new ResponseEntity<ResponseDTO<GenreDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<GenreDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
}
