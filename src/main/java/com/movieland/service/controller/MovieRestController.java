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

import com.movieland.service.businesslogic.MovieServiceImpl;
import com.movieland.service.businesslogic.RateByMovieServiceImpl;
import com.movieland.service.dto.MovieDTO;
import com.movieland.service.dto.RateByMovieDTO;
import com.movieland.service.dto.ResponseDTO;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.ServiceExceptionUtils;

@RestController
@RequestMapping(path = "/movieland/movies")
public class MovieRestController {

	@Autowired
	private MovieServiceImpl movieService;
	
	@Autowired
	private RateByMovieServiceImpl rateByMovieService;
	
	@RequestMapping(path = {"/",""}, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<MovieDTO>>> findAll(){
		
		final ResponseDTO<List<MovieDTO>> resp = new ResponseDTO<List<MovieDTO>>();	
		try {
			final List<MovieDTO> allMovies = movieService.findAll();
			resp.setData(allMovies);
			return new ResponseEntity<ResponseDTO<List<MovieDTO>>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<List<MovieDTO>>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
	}
	
	@RequestMapping(path = {"/{movieId}"},method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<MovieDTO>> findById(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "movieId") String movieId) {
		
		final ResponseDTO<MovieDTO> resp = new ResponseDTO<MovieDTO>();
		final BigInteger movieIdBi = new BigInteger(movieId);
		
		try {
			final MovieDTO foundMovie = movieService.findById(movieIdBi);
			resp.setData(foundMovie);
			return new ResponseEntity<ResponseDTO<MovieDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<MovieDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{movieId}/rate"},method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<RateByMovieDTO>> rateMovie(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "movieId") String movieId, @RequestBody RateByMovieDTO rateToApply) {
		
		final ResponseDTO<RateByMovieDTO> resp = new ResponseDTO<RateByMovieDTO>();
		final BigInteger movieIdBi = new BigInteger(movieId);
		
		try {
			final RateByMovieDTO appliedRate = rateByMovieService.rateMovie(movieIdBi, rateToApply);
			resp.setData(appliedRate);
			return new ResponseEntity<ResponseDTO<RateByMovieDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<RateByMovieDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{movieId}/rate-concurrent"},method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<RateByMovieDTO>> rateMovieConcurrent(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "movieId") String movieId, @RequestBody RateByMovieDTO rateToApply) {
		
		final ResponseDTO<RateByMovieDTO> resp = new ResponseDTO<RateByMovieDTO>();
		final BigInteger movieIdBi = new BigInteger(movieId);
		
		try {
			final RateByMovieDTO appliedRate = rateByMovieService.rateMovieConcurrent(movieIdBi, rateToApply);
			resp.setData(appliedRate);
			return new ResponseEntity<ResponseDTO<RateByMovieDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<RateByMovieDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
}
