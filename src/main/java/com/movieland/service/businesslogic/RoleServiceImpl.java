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

import com.movieland.service.dao.RoleDao;
import com.movieland.service.dto.RoleDTO;
import com.movieland.service.entity.Role;
import com.movieland.service.enums.ServiceErrorCodes;
import com.movieland.service.enums.StatusCodes;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.DTOTransformer;
import com.movieland.service.utils.EntityTransformer;

@Service
@PropertySource("classpath:message.properties")
public class RoleServiceImpl {

	@Autowired
	private RoleDao dao;
	
	@Autowired
	private Environment env;
	
	public List<RoleDTO> findAll(){
		final List<Role> genres = dao.findByStatus(StatusCodes.ACTIVE.getCode());
		return DTOTransformer.toRoleDTO(genres);
	}
	
	public RoleDTO findById(final BigInteger id) throws ServiceException{
		final Optional<Role> foundEntity = dao.findById(id);
		if(foundEntity.isPresent()) {
			return DTOTransformer.toRoleDTO(foundEntity.get());
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("role.error.message.notfound"));
		}
	}
	
	public List<RoleDTO> findByName(final String name) throws ServiceException{
		final List<Role> foundEntity = dao.findByName(name);
		if(!CollectionUtils.isEmpty(foundEntity)) {
			return DTOTransformer.toRoleDTO(foundEntity);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("role.error.message.notfound"));
		}
	}
	
	@Transactional
	public RoleDTO create(final RoleDTO roleDTO) throws ServiceException {
		
		final List<Role> foundGenre = dao.findByName(roleDTO.getName());
		if(CollectionUtils.isEmpty(foundGenre)) {
			Role entity = EntityTransformer.toRole(roleDTO);
			entity.setCreatedAt(LocalDateTime.now());
			if(Objects.isNull(entity.getCreatedBy())) {
				entity.setCreatedBy(env.getProperty("movieland.service.serveruser"));
			}
			entity.setStatus(StatusCodes.ACTIVE.getCode());
			entity = dao.save(entity);
			return DTOTransformer.toRoleDTO(entity);
		} else {
			if(StatusCodes.ACTIVE.getCode().equals(foundGenre.get(0).getStatus())) {
				throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR,env.getProperty("role.error.message.alreadyexists"));
			} else {
				final Role entityToUpdate = foundGenre.get(0);
				entityToUpdate.setStatus(StatusCodes.ACTIVE.getCode());
				if(Objects.isNull(roleDTO.getAuditInfo()) || StringUtils.isBlank(roleDTO.getAuditInfo().getModifiedBy())) {
					entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
				} else {
					entityToUpdate.setModifiedBy(roleDTO.getAuditInfo().getModifiedBy());
				}
				entityToUpdate.setModifiedAt(LocalDateTime.now());
				dao.save(entityToUpdate);
				return DTOTransformer.toRoleDTO(entityToUpdate);
			}
		}
	}
	
	@Transactional
	public RoleDTO update(final RoleDTO roleDTO) throws ServiceException {
		
		final Optional<Role> foundGenre = dao.findById(roleDTO.getId());
		if(foundGenre.isPresent()) {
			final Role entityToUpdate = foundGenre.get();
			if(Objects.nonNull(roleDTO.getStatus())) {
				entityToUpdate.setStatus(roleDTO.getStatus().getCode());
			}
			if(Objects.nonNull(roleDTO.getName())) {
				entityToUpdate.setName(roleDTO.getName());
			}
			if(Objects.nonNull(roleDTO.getDescription())) {
				entityToUpdate.setDescription(roleDTO.getDescription());
			}
			if(Objects.isNull(roleDTO.getAuditInfo()) || StringUtils.isBlank(roleDTO.getAuditInfo().getModifiedBy())) {
				entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			} else {
				entityToUpdate.setModifiedBy(roleDTO.getAuditInfo().getModifiedBy());
			}
			entityToUpdate.setModifiedAt(LocalDateTime.now());
			dao.save(entityToUpdate);
			return DTOTransformer.toRoleDTO(entityToUpdate);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("role.error.message.notfound"));
		}
	}
	
	@Transactional
	public void delete(final BigInteger roleId) throws ServiceException {
		
		final Optional<Role> foundRole = dao.findById(roleId);
		if(foundRole.isPresent()) {
			final Role entityToUpdate = foundRole.get();
			entityToUpdate.setStatus(StatusCodes.INACTIVE.getCode());
			entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			entityToUpdate.setModifiedAt(LocalDateTime.now());
			dao.save(entityToUpdate);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("role.error.message.notfound"));
		}
	}
	
}
 