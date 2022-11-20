package com.movieland.service.businesslogic;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieland.service.dao.GenreDao;
import com.movieland.service.dto.GenreDTO;
import com.movieland.service.entity.Genre;
import com.movieland.service.enums.ServiceErrorCodes;
import com.movieland.service.enums.StatusCodes;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.DTOTransformer;
import com.movieland.service.utils.EntityTransformer;

@Service
@PropertySource("classpath:message.properties")
public class GenreServiceImpl {

	@Autowired
	private GenreDao dao;
	
	@Autowired
	private Environment env;
	
	public List<GenreDTO> findAll(){
		final List<Genre> genres = dao.findByStatus(StatusCodes.ACTIVE.getCode());
		return DTOTransformer.toGenreDTO(genres);
	}
	
	public GenreDTO findById(final BigInteger id) throws ServiceException{
		final Optional<Genre> foundEntity = dao.findById(id);
		if(foundEntity.isPresent()) {
			return DTOTransformer.toGenreDTO(foundEntity.get());
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("genre.error.message.notfound"));
		}
	}
	
	public List<GenreDTO> findByName(final String name) throws ServiceException{
		final List<Genre> foundEntity = dao.findByName(name);
		if(!CollectionUtils.isEmpty(foundEntity)) {
			return DTOTransformer.toGenreDTO(foundEntity);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("genre.error.message.notfound"));
		}
	}
	
	@Transactional
	public GenreDTO create(final GenreDTO genreDto) throws ServiceException {
		
		final List<Genre> foundGenre = dao.findByName(genreDto.getName());
		if(CollectionUtils.isEmpty(foundGenre)) {
			Genre entity = EntityTransformer.toGenre(genreDto);
			entity.setCreatedAt(LocalDateTime.now());
			if(Objects.isNull(entity.getCreatedBy())) {
				entity.setCreatedBy(env.getProperty("movieland.service.serveruser"));
			}
			entity.setStatus(StatusCodes.ACTIVE.getCode());
			entity = dao.save(entity);
			return DTOTransformer.toGenreDTO(entity);
		} else {
			if(StatusCodes.ACTIVE.getCode().equals(foundGenre.get(0).getStatus())) {
				throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR,env.getProperty("genre.error.message.alreadyexists"));
			} else {
				final Genre entityToUpdate = foundGenre.get(0);
				entityToUpdate.setStatus(StatusCodes.ACTIVE.getCode());
				if(Objects.isNull(genreDto.getAuditInfo()) || StringUtils.isBlank(genreDto.getAuditInfo().getModifiedBy())) {
					entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
				} else {
					entityToUpdate.setModifiedBy(genreDto.getAuditInfo().getModifiedBy());
				}
				entityToUpdate.setModifiedAt(LocalDateTime.now());
				dao.save(entityToUpdate);
				return DTOTransformer.toGenreDTO(entityToUpdate);
			}
		}
	}
	
	@Transactional
	public GenreDTO update(final GenreDTO genreDto) throws ServiceException {
		
		final Optional<Genre> foundGenre = dao.findById(genreDto.getId());
		if(foundGenre.isPresent()) {
			final Genre entityToUpdate = foundGenre.get();
			if(Objects.nonNull(genreDto.getStatus())) {
				entityToUpdate.setStatus(genreDto.getStatus().getCode());
			}
			if(Objects.nonNull(genreDto.getName())) {
				entityToUpdate.setName(genreDto.getName());
			}
			if(Objects.nonNull(genreDto.getDescription())) {
				entityToUpdate.setDescription(genreDto.getDescription());
			}
			if(Objects.isNull(genreDto.getAuditInfo()) || StringUtils.isBlank(genreDto.getAuditInfo().getModifiedBy())) {
				entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			} else {
				entityToUpdate.setModifiedBy(genreDto.getAuditInfo().getModifiedBy());
			}
			entityToUpdate.setModifiedAt(LocalDateTime.now());
			dao.save(entityToUpdate);
			return DTOTransformer.toGenreDTO(entityToUpdate);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("genre.error.message.notfound"));
		}
	}
	
	@Transactional
	public void delete(final BigInteger genreId) throws ServiceException {
		
		final Optional<Genre> foundGenre = dao.findById(genreId);
		if(foundGenre.isPresent()) {
			final Genre entityToUpdate = foundGenre.get();
			entityToUpdate.setStatus(StatusCodes.INACTIVE.getCode());
			entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			entityToUpdate.setModifiedAt(LocalDateTime.now());
			dao.save(entityToUpdate);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("genre.error.message.notfound"));
		}
	}
	
}
 