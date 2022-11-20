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

import com.movieland.service.dao.ParentalGuideDao;
import com.movieland.service.dto.ParentalGuideDTO;
import com.movieland.service.entity.ParentalGuide;
import com.movieland.service.enums.ServiceErrorCodes;
import com.movieland.service.enums.StatusCodes;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.DTOTransformer;
import com.movieland.service.utils.EntityTransformer;

@Service
@PropertySource("classpath:message.properties")
public class ParentalGuideServiceImpl {

	@Autowired
	private ParentalGuideDao dao;
	
	@Autowired
	private Environment env;
	
	public List<ParentalGuideDTO> findAll(){
		final List<ParentalGuide> parentalGuides = dao.findByStatus(StatusCodes.ACTIVE.getCode());
		return DTOTransformer.toParentalGuideDTO(parentalGuides);
	}
	
	public ParentalGuideDTO findById(final BigInteger id) throws ServiceException{
		final Optional<ParentalGuide> foundEntity = dao.findById(id);
		if(foundEntity.isPresent()) {
			return DTOTransformer.toParentalGuideDTO(foundEntity.get());
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("parentalguide.error.message.notfound"));
		}
	}
	
	public List<ParentalGuideDTO> findByName(final String name) throws ServiceException{
		final List<ParentalGuide> foundEntity = dao.findByName(name);
		if(!CollectionUtils.isEmpty(foundEntity)) {
			return DTOTransformer.toParentalGuideDTO(foundEntity);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("parentalguide.error.message.notfound"));
		}
	}
	
	@Transactional
	public ParentalGuideDTO create(final ParentalGuideDTO parentalGuideDto) throws ServiceException {
		
		final List<ParentalGuide> foundParentalGuide = dao.findByName(parentalGuideDto.getName());
		if(CollectionUtils.isEmpty(foundParentalGuide)) {
			ParentalGuide entity = EntityTransformer.toParentalGuide(parentalGuideDto);
			entity.setCreatedAt(LocalDateTime.now());
			if(Objects.isNull(entity.getCreatedBy())) {
				entity.setCreatedBy(env.getProperty("movieland.service.serveruser"));
			}
			entity.setStatus(StatusCodes.ACTIVE.getCode());
			entity = dao.save(entity);
			return DTOTransformer.toParentalGuideDTO(entity);
		} else {
			if(StatusCodes.ACTIVE.getCode().equals(foundParentalGuide.get(0).getStatus())) {
				throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR,env.getProperty("parentalguide.error.message.alreadyexists"));
			} else {
				final ParentalGuide entityToUpdate = foundParentalGuide.get(0);
				entityToUpdate.setStatus(StatusCodes.ACTIVE.getCode());
				if(Objects.isNull(parentalGuideDto.getAuditInfo()) || StringUtils.isBlank(parentalGuideDto.getAuditInfo().getModifiedBy())) {
					entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
				} else {
					entityToUpdate.setModifiedBy(parentalGuideDto.getAuditInfo().getModifiedBy());
				}
				entityToUpdate.setModifiedAt(LocalDateTime.now());
				dao.save(entityToUpdate);
				return DTOTransformer.toParentalGuideDTO(entityToUpdate);
			}
		}
	}
	
	@Transactional
	public ParentalGuideDTO update(final ParentalGuideDTO parentalGuideDto) throws ServiceException {
		
		final Optional<ParentalGuide> foundGenre = dao.findById(parentalGuideDto.getId());
		if(foundGenre.isPresent()) {
			final ParentalGuide entityToUpdate = foundGenre.get();
			if(Objects.nonNull(parentalGuideDto.getStatus())) {
				entityToUpdate.setStatus(parentalGuideDto.getStatus().getCode());
			}
			if(Objects.nonNull(parentalGuideDto.getName())) {
				entityToUpdate.setName(parentalGuideDto.getName());
			}
			if(Objects.nonNull(parentalGuideDto.getDescription())) {
				entityToUpdate.setDescription(parentalGuideDto.getDescription());
			}
			if(Objects.isNull(parentalGuideDto.getAuditInfo()) || StringUtils.isBlank(parentalGuideDto.getAuditInfo().getModifiedBy())) {
				entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			} else {
				entityToUpdate.setModifiedBy(parentalGuideDto.getAuditInfo().getModifiedBy());
			}
			entityToUpdate.setModifiedAt(LocalDateTime.now());
			dao.save(entityToUpdate);
			return DTOTransformer.toParentalGuideDTO(entityToUpdate);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("parentalguide.error.message.notfound"));
		}
	}
	
	@Transactional
	public void delete(final BigInteger parentalGuideId) throws ServiceException {
		
		final Optional<ParentalGuide> foundGenre = dao.findById(parentalGuideId);
		if(foundGenre.isPresent()) {
			final ParentalGuide entityToUpdate = foundGenre.get();
			entityToUpdate.setStatus(StatusCodes.INACTIVE.getCode());
			entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			entityToUpdate.setModifiedAt(LocalDateTime.now());
			dao.save(entityToUpdate);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("parentalguide.error.message.notfound"));
		}
	}
	
}
 