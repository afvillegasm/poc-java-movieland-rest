package com.movieland.service.businesslogic;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieland.service.dao.MovieDao;
import com.movieland.service.dao.RateByMovieDao;
import com.movieland.service.dto.RateByMovieDTO;
import com.movieland.service.entity.Movie;
import com.movieland.service.entity.RateByMovie;
import com.movieland.service.enums.ServiceErrorCodes;
import com.movieland.service.enums.StatusCodes;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.DTOTransformer;

@Service
@PropertySource("classpath:message.properties")
public class RateByMovieServiceImpl {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private RateByMovieDao dao;
	
	@Autowired
	private MovieDao movieDao;
	
	private static final Logger logger = LogManager.getLogger(RateByMovieServiceImpl.class);
	
	@Transactional
	public RateByMovieDTO rateMovie(final BigInteger movieId, RateByMovieDTO rateToApply) throws ServiceException {
		
		if(rateToApply == null || rateToApply.getRate() == null) {
			throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR, env.getProperty("rate-by-movie.error.message.rate-mandatory"));
		}
		if (rateToApply.getRate() < Integer.parseInt(env.getProperty("movieland.service.minimum-allowed-rate-value"))
				|| rateToApply.getRate() > Integer
						.parseInt(env.getProperty("movieland.service.maximum-allowed-rate-value"))) {
			throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR,
					env.getProperty("rate-by-movie.error.message.allowed-rate-value"));
		}
		
		final int maxMovieRatesPerDay = Integer.parseInt(env.getProperty("movieland.service.maximum-movie-rates-per-day"));
		final Optional<Movie> foundMovie = movieDao.findById(movieId);
		if(foundMovie.isPresent()) {
			
			final List<RateByMovie> existingRatesByMovieForToday = dao.findByMovieByRatedAt(movieId, LocalDate.now());
			if(CollectionUtils.isNotEmpty(existingRatesByMovieForToday) && existingRatesByMovieForToday.size() == maxMovieRatesPerDay) {
				throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR, env.getProperty("rate-by-movie.error.message.max-rates-per-movie-reached"));
			}
			
			RateByMovie entity = new RateByMovie();
			
			entity.setRate(rateToApply.getRate());
			entity.setRatedAt(LocalDate.now());
			entity.setRatedMovie(foundMovie.get());
			entity.setStatus(StatusCodes.ACTIVE.getCode());
			if(Objects.nonNull(rateToApply.getAuditInfo()) &&  StringUtils.isNotBlank(rateToApply.getAuditInfo().getCreatedBy())) {
				entity.setCreatedBy(rateToApply.getAuditInfo().getCreatedBy());
			} else {
				entity.setCreatedBy(env.getProperty("movieland.service.serveruser"));
			}
			entity.setCreatedAt(LocalDateTime.now());
			entity = dao.save(entity);
			
			return DTOTransformer.toRateByMovieDTO(entity);
			
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("movie.error.message.notfound"));
		}
		
	}
	
	@Transactional
	public RateByMovieDTO rateMovieConcurrent(final BigInteger movieId, RateByMovieDTO rateToApply) throws ServiceException {
		
		if(rateToApply == null || rateToApply.getRate() == null) {
			throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR, env.getProperty("rate-by-movie.error.message.rate-mandatory"));
		}
		if (rateToApply.getRate() < Integer.parseInt(env.getProperty("movieland.service.minimum-allowed-rate-value"))
				|| rateToApply.getRate() > Integer
						.parseInt(env.getProperty("movieland.service.maximum-allowed-rate-value"))) {
			throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR,
					env.getProperty("rate-by-movie.error.message.allowed-rate-value"));
		}
		if(Objects.nonNull(rateToApply.getAuditInfo())) {
			logger.info("Trying to rate a movie from user: " + rateToApply.getAuditInfo().getCreatedBy());
		}
		
		final int maxMovieRatesPerDay = Integer.parseInt(env.getProperty("movieland.service.maximum-movie-rates-per-day"));
		final Optional<Movie> foundMovie = movieDao.findById(movieId);
		if(foundMovie.isPresent()) {
			
			final List<RateByMovie> existingRatesByMovieForToday = dao.findByMovieByRatedAtWithConcurrentLock(movieId, LocalDate.now());
			if(CollectionUtils.isNotEmpty(existingRatesByMovieForToday) && existingRatesByMovieForToday.size() == maxMovieRatesPerDay) {
				throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR, env.getProperty("rate-by-movie.error.message.max-rates-per-movie-reached"));
			}
			
			RateByMovie entity = new RateByMovie();
			
			entity.setRate(rateToApply.getRate());
			entity.setRatedAt(LocalDate.now());
			entity.setRatedMovie(foundMovie.get());
			entity.setStatus(StatusCodes.ACTIVE.getCode());
			if(Objects.nonNull(rateToApply.getAuditInfo()) &&  StringUtils.isNotBlank(rateToApply.getAuditInfo().getCreatedBy())) {
				entity.setCreatedBy(rateToApply.getAuditInfo().getCreatedBy());
			} else {
				entity.setCreatedBy(env.getProperty("movieland.service.serveruser"));
			}
			entity.setCreatedAt(LocalDateTime.now());
			entity = dao.save(entity);
			
			logger.info("movie " + foundMovie.get().getName() + " was successfuly rated as: " + rateToApply.getRate() + " by the user: " + entity.getCreatedBy());
			
			return DTOTransformer.toRateByMovieDTO(entity);
			
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("movie.error.message.notfound"));
		}		
		
	}

}
