package com.movieland.service.businesslogic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.movieland.service.dao.MovieDao;
import com.movieland.service.dto.MovieDTO;
import com.movieland.service.entity.Movie;
import com.movieland.service.enums.ServiceErrorCodes;
import com.movieland.service.enums.StatusCodes;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.DTOTransformer;

@Service
@PropertySource("classpath:message.properties")
public class MovieServiceImpl {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MovieDao dao;
	
	public List<MovieDTO> findAll() throws ServiceException{
		final List<Movie> movies = dao.findByStatus(StatusCodes.ACTIVE.getCode());
		return buildMoviesFromEntities(movies);
	}
	
	public MovieDTO findById(final BigInteger id) throws ServiceException{
		final Optional<Movie> foundEntity = dao.findById(id);
		if(foundEntity.isPresent()) {
			return buildMovieFromEntity(foundEntity.get());
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("movie.error.message.notfound"));
		}
	}

	public List<MovieDTO> findByName(final String name) throws ServiceException{
		final List<Movie> foundEntity = dao.findByName(name);
		if(!CollectionUtils.isEmpty(foundEntity)) {
			return buildMoviesFromEntities(foundEntity);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("movie.error.message.notfound"));
		}
	}
	
	private List<MovieDTO> buildMoviesFromEntities(final List<Movie> entities) {
		final List<MovieDTO> movies = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(entities)) {
			for(final Movie entity : entities) {
				movies.add(buildMovieFromEntity(entity));
			}
		}
		return movies;
	}

	private MovieDTO buildMovieFromEntity(final Movie movie) {
		final MovieDTO dto = DTOTransformer.toMovieDTO(movie);
		
		if(CollectionUtils.isNotEmpty(movie.getRates())) {
			dto.setAverageRating(movie.getRates().stream().collect(Collectors.averagingInt(mov -> mov.getRate())));
		}
		
		return dto;
	}

}
