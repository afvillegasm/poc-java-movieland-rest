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

import com.movieland.service.businesslogic.ArtistServiceImpl;
import com.movieland.service.businesslogic.RoleServiceImpl;
import com.movieland.service.dto.ArtistDTO;
import com.movieland.service.dto.ResponseDTO;
import com.movieland.service.dto.ArtistDTO;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.ServiceExceptionUtils;

@RestController
@RequestMapping(path = "/movieland/artists")
public class ArtistRestController {

	@Autowired
	private ArtistServiceImpl service;
	
	@RequestMapping(path = {"/",""}, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<ArtistDTO>>> findAll(){
		
		final ResponseDTO<List<ArtistDTO>> resp = new ResponseDTO<List<ArtistDTO>>();	
		try {
			final List<ArtistDTO> allArtists = service.findAll();
			resp.setData(allArtists);
			return new ResponseEntity<ResponseDTO<List<ArtistDTO>>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<List<ArtistDTO>>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
	}
	
	@RequestMapping(path = {"/{artistId}"},method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<ArtistDTO>> findById(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "artistId") String artistId) {
		
		final ResponseDTO<ArtistDTO> resp = new ResponseDTO<ArtistDTO>();
		final BigInteger artistIdBi = new BigInteger(artistId);
		
		try {
			final ArtistDTO foundArtist = service.findById(artistIdBi);
			resp.setData(foundArtist);
			return new ResponseEntity<ResponseDTO<ArtistDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<ArtistDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/role-by-artists/{roleId}"},method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<ArtistDTO>>> findByPerformedRole(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "roleId") String roleId) {
		
		final ResponseDTO<List<ArtistDTO>> resp = new ResponseDTO<List<ArtistDTO>>();
		final BigInteger roleIdBi = new BigInteger(roleId);
		
		try {
			final List<ArtistDTO> foundArtist = service.findByRole(roleIdBi);
			resp.setData(foundArtist);
			return new ResponseEntity<ResponseDTO<List<ArtistDTO>>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<List<ArtistDTO>>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/", ""},method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<ArtistDTO>> createArtist(@RequestBody ArtistDTO artist){
		
		final ResponseDTO<ArtistDTO> resp = new ResponseDTO<ArtistDTO>();
		try {
			final ArtistDTO createdDto = service.create(artist);
			resp.setData(createdDto);
			return new ResponseEntity<ResponseDTO<ArtistDTO>>(resp, HttpStatus.CREATED);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<ArtistDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{artistId}"},method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<ArtistDTO>> updateArtist(@PathVariable(name = "artistId") String artistId, @RequestBody ArtistDTO artist){
		
		final ResponseDTO<ArtistDTO> resp = new ResponseDTO<ArtistDTO>();
		try {
			final BigInteger artistIdBi = new BigInteger(artistId);
			artist.setId(artistIdBi);
			final ArtistDTO createdDto = service.update(artist);
			resp.setData(createdDto);
			return new ResponseEntity<ResponseDTO<ArtistDTO>>(resp, HttpStatus.CREATED);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<ArtistDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{artistId}"},method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<ArtistDTO>> deleteArtist(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "artistId") String artistId) {
		
		final ResponseDTO<ArtistDTO> resp = new ResponseDTO<ArtistDTO>();
		final BigInteger artistIdBi = new BigInteger(artistId);
		
		try {
			service.delete(artistIdBi);
			return new ResponseEntity<ResponseDTO<ArtistDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<ArtistDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
}
