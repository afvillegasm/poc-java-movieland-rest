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

import com.movieland.service.dao.CountryDao;
import com.movieland.service.dto.CountryDTO;
import com.movieland.service.entity.Country;
import com.movieland.service.enums.ServiceErrorCodes;
import com.movieland.service.enums.StatusCodes;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.DTOTransformer;
import com.movieland.service.utils.EntityTransformer;

@Service
@PropertySource("classpath:message.properties")
public class CountryServiceImpl {

	@Autowired
	private CountryDao dao;
	
	@Autowired
	private Environment env;
	
	public List<CountryDTO> findAll(){
		final List<Country> countries = dao.findByStatus(StatusCodes.ACTIVE.getCode());
		return DTOTransformer.toCountryDTO(countries);
	}
	
	public CountryDTO findById(final BigInteger id) throws ServiceException{
		final Optional<Country> foundEntity = dao.findById(id);
		if(foundEntity.isPresent()) {
			return DTOTransformer.toCountryDTO(foundEntity.get());
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("country.error.message.notfound"));
		}
	}
	
	public List<CountryDTO> findByName(final String name) throws ServiceException{
		final List<Country> foundEntity = dao.findByName(name);
		if(!CollectionUtils.isEmpty(foundEntity)) {
			return DTOTransformer.toCountryDTO(foundEntity);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("country.error.message.notfound"));
		}
	}
	
	@Transactional
	public CountryDTO create(final CountryDTO countryDto) throws ServiceException {
		
		final List<Country> foundCountry = dao.findByName(countryDto.getName());
		if(CollectionUtils.isEmpty(foundCountry)) {
			Country entity = EntityTransformer.toCountry(countryDto);
			entity.setCreatedAt(LocalDateTime.now());
			if(Objects.isNull(entity.getCreatedBy())) {
				entity.setCreatedBy(env.getProperty("movieland.service.serveruser"));
			}
			entity.setStatus(StatusCodes.ACTIVE.getCode());
			entity = dao.save(entity);
			return DTOTransformer.toCountryDTO(entity);
		} else {
			if(StatusCodes.ACTIVE.getCode().equals(foundCountry.get(0).getStatus())) {
				throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR,env.getProperty("country.error.message.alreadyexists"));
			} else {
				final Country entityToUpdate = foundCountry.get(0);
				entityToUpdate.setStatus(StatusCodes.ACTIVE.getCode());
				if(Objects.isNull(countryDto.getAuditInfo()) || StringUtils.isBlank(countryDto.getAuditInfo().getModifiedBy())) {
					entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
				} else {
					entityToUpdate.setModifiedBy(countryDto.getAuditInfo().getModifiedBy());
				}
				entityToUpdate.setModifiedAt(LocalDateTime.now());
				dao.update(entityToUpdate);
				return DTOTransformer.toCountryDTO(entityToUpdate);
			}
		}
	}
	
	@Transactional
	public CountryDTO update(final CountryDTO countryDto) throws ServiceException {
		
		final Optional<Country> foundCountry = dao.findById(countryDto.getId());
		if(foundCountry.isPresent()) {
			final Country entityToUpdate = foundCountry.get();
			if(Objects.nonNull(countryDto.getStatus())) {
				entityToUpdate.setStatus(countryDto.getStatus().getCode());
			}
			if(Objects.nonNull(countryDto.getName())) {
				entityToUpdate.setName(countryDto.getName());
			}
			if(Objects.isNull(countryDto.getAuditInfo()) && StringUtils.isBlank(countryDto.getAuditInfo().getModifiedBy())) {
				entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			} else {
				entityToUpdate.setModifiedBy(countryDto.getAuditInfo().getModifiedBy());
			}
			entityToUpdate.setModifiedAt(LocalDateTime.now());
			dao.update(entityToUpdate);
			return DTOTransformer.toCountryDTO(entityToUpdate);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("country.error.message.notfound"));
		}
	}
	
	@Transactional
	public void delete(final BigInteger countryId) throws ServiceException {
		
		final Optional<Country> foundCountry = dao.findById(countryId);
		if(foundCountry.isPresent()) {
			final Country entityToUpdate = foundCountry.get();
			entityToUpdate.setStatus(StatusCodes.INACTIVE.getCode());
			entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			entityToUpdate.setModifiedAt(LocalDateTime.now());
			dao.update(entityToUpdate);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("country.error.message.notfound"));
		}
	}
	
}
 