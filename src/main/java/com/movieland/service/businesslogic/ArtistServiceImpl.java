package com.movieland.service.businesslogic;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieland.service.dao.ArtistDao;
import com.movieland.service.dao.CountryDao;
import com.movieland.service.dao.RoleByArtistDao;
import com.movieland.service.dao.RoleDao;
import com.movieland.service.dto.ArtistDTO;
import com.movieland.service.dto.CountryDTO;
import com.movieland.service.dto.RoleByArtistDTO;
import com.movieland.service.dto.RoleDTO;
import com.movieland.service.entity.Artist;
import com.movieland.service.entity.Country;
import com.movieland.service.entity.Role;
import com.movieland.service.entity.RoleByArtist;
import com.movieland.service.entity.RoleByArtistPK;
import com.movieland.service.entity.RoleByArtist_;
import com.movieland.service.enums.ServiceErrorCodes;
import com.movieland.service.enums.StatusCodes;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.DTOTransformer;
import com.movieland.service.utils.EntityTransformer;

@Service
@PropertySource("classpath:message.properties")
public class ArtistServiceImpl {
	
	@Autowired
	private ArtistDao dao;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleByArtistDao roleByArtistDao;
	
	public List<ArtistDTO> findAll() throws ServiceException{
		final List<Artist> artists = dao.findByStatus(StatusCodes.ACTIVE.getCode());
		return buildArtistsFromEntities(artists);
	}
	
	public ArtistDTO findById(final BigInteger id) throws ServiceException{
		final Optional<Artist> foundEntity = dao.findById(id);
		if(foundEntity.isPresent()) {
			return buildArtistFromEntity(foundEntity.get());
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("artist.error.message.notfound"));
		}
	}
	
	public List<ArtistDTO> findByName(final String name) throws ServiceException{
		final List<Artist> foundEntity = dao.findByName(name);
		if(!CollectionUtils.isEmpty(foundEntity)) {
			return buildArtistsFromEntities(foundEntity);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("artist.error.message.notfound"));
		}
	}
	
	public List<ArtistDTO> findByRole(final BigInteger roleId) throws ServiceException{
		final List<Artist> foundEntity = dao.findByRole(roleId);
		if(CollectionUtils.isNotEmpty(foundEntity)) {
			return buildArtistsFromEntities(foundEntity);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("artist.error.message.notfound"));
		}
	}
	
	@Transactional
	public ArtistDTO create(final ArtistDTO artistDTO) throws ServiceException {
		
		final List<Artist> foundArtist = dao.findByName(artistDTO.getName());
		if(CollectionUtils.isEmpty(foundArtist)) {
			Artist entity = EntityTransformer.toArtist(artistDTO);
			entity.setCreatedAt(LocalDateTime.now());
			if(Objects.isNull(entity.getCreatedBy())) {
				entity.setCreatedBy(env.getProperty("movieland.service.serveruser"));
			}
			entity.setStatus(StatusCodes.ACTIVE.getCode());
			mapCountryToArtist(artistDTO.getNationality(), entity);
			entity = dao.save(entity);
			manageRolesByArtist(artistDTO.getPerformedRoles(), entity);
			return buildArtistFromEntity(entity);
		} else {
			if(StatusCodes.ACTIVE.getCode().equals(foundArtist.get(0).getStatus())) {
				throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR,env.getProperty("artist.error.message.alreadyexists"));
			} else {
				Artist entityToUpdate = foundArtist.get(0);
				entityToUpdate.setStatus(StatusCodes.ACTIVE.getCode());
				if(Objects.isNull(artistDTO.getAuditInfo()) || StringUtils.isBlank(artistDTO.getAuditInfo().getModifiedBy())) {
					entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
				} else {
					entityToUpdate.setModifiedBy(artistDTO.getAuditInfo().getModifiedBy());
				}
				entityToUpdate.setModifiedAt(LocalDateTime.now());
				mapCountryToArtist(artistDTO.getNationality(), entityToUpdate);
				entityToUpdate = dao.save(entityToUpdate);
				manageRolesByArtist(artistDTO.getPerformedRoles(), entityToUpdate);
				return buildArtistFromEntity(entityToUpdate);
			}
		}
	}
	
	@Transactional
	public ArtistDTO update(final ArtistDTO artistDto) throws ServiceException {
		
		final Optional<Artist> foundArtist = dao.findById(artistDto.getId());
		if(foundArtist.isPresent()) {
			final Artist entityToUpdate = foundArtist.get();
			if(Objects.nonNull(artistDto.getStatus())) {
				entityToUpdate.setStatus(artistDto.getStatus().getCode());
			}
			if(Objects.nonNull(artistDto.getName())) {
				entityToUpdate.setName(artistDto.getName());
			}
			if(Objects.nonNull(artistDto.getArtisticName())) {
				entityToUpdate.setArtisticName(artistDto.getArtisticName());
			}
			if(Objects.nonNull(artistDto.getGender())) {
				entityToUpdate.setGender(artistDto.getGender().getCode());
			}
			if(Objects.nonNull(artistDto.getBirthdate())) {
				entityToUpdate.setBirthdate(artistDto.getBirthdate());
			}
			mapCountryToArtist(artistDto.getNationality(), entityToUpdate);
			if(CollectionUtils.isNotEmpty(artistDto.getPerformedRoles())) {
				manageRolesByArtist(artistDto.getPerformedRoles(), entityToUpdate);
			}
			if(Objects.isNull(artistDto.getAuditInfo()) || StringUtils.isBlank(artistDto.getAuditInfo().getModifiedBy())) {
				entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			} else {
				entityToUpdate.setModifiedBy(artistDto.getAuditInfo().getModifiedBy());
			}
			entityToUpdate.setModifiedAt(LocalDateTime.now());
			dao.save(entityToUpdate);
			return buildArtistFromEntity(entityToUpdate);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("artist.error.message.notfound"));
		}
	}
	
	private void mapCountryToArtist(final CountryDTO nationality, final Artist entity) {
		if(Objects.nonNull(nationality) && Objects.nonNull(nationality.getId())) {
			final Optional<Country> foundCountry = countryDao.findById(nationality.getId());
			if(foundCountry.isPresent()) {
				entity.setNationality(foundCountry.get());
			}
		}
	}

	private void manageRolesByArtist(final List<RoleByArtistDTO> performedRoles, final Artist entityToUpdate) throws ServiceException {
		
		if(CollectionUtils.isNotEmpty(performedRoles)) {
			final List<RoleByArtist> rolesByArtistToRemove = getRolesByArtistToRemove(entityToUpdate.getRolesByArtist(), performedRoles);
			final List<RoleByArtistDTO> rolesByArtistToAdd = getRolesByArtistToAdd(entityToUpdate.getRolesByArtist(), performedRoles);
			
			if(CollectionUtils.isNotEmpty(rolesByArtistToRemove)) {
				for(final RoleByArtist entityToRemove : rolesByArtistToRemove) {
					deleteRoleByArtist(entityToRemove);
				}
			}
			if(CollectionUtils.isNotEmpty(rolesByArtistToAdd)) {
				for(final RoleByArtistDTO performedRole : rolesByArtistToAdd) {
					createRoleByArtist(performedRole, entityToUpdate);
				}
			}
			
			
		}
		
	}

	private List<RoleByArtistDTO> getRolesByArtistToAdd(final List<RoleByArtist> rolesByArtist,
			final List<RoleByArtistDTO> performedRoles) {
		
		if(CollectionUtils.isEmpty(performedRoles)) {
			return new ArrayList<>();
		}
		if(CollectionUtils.isEmpty(rolesByArtist)) {
			return performedRoles;
		}
		return performedRoles.stream().filter(perfRole -> !containedRoleByArtistDTOInRoleByArtists(perfRole, rolesByArtist)).collect(Collectors.toList());
	}

	private List<RoleByArtist> getRolesByArtistToRemove(final List<RoleByArtist> rolesByArtist,
			final List<RoleByArtistDTO> performedRoles) {
		if(CollectionUtils.isEmpty(performedRoles) || CollectionUtils.isEmpty(rolesByArtist)) {
			return new ArrayList<>();
		}
		return rolesByArtist.stream().filter(rBya -> !containedRoleByArtistInPerformedRoles(rBya, performedRoles)).collect(Collectors.toList());
	}
	
	private boolean containedRoleByArtistInPerformedRoles(final RoleByArtist roleByArtist,
			final List<RoleByArtistDTO> performedRoles) {
		return performedRoles.stream()
							 .anyMatch(rByADto -> rByADto.getRole().getId().equals(roleByArtist.getId().getRoleId()));
	}
	
	private boolean containedRoleByArtistDTOInRoleByArtists(final RoleByArtistDTO performedRole, final List<RoleByArtist> rolesByArtist) {
		return rolesByArtist.stream()
							.anyMatch(rByA -> rByA.getId().getRoleId().equals(performedRole.getRole().getId()));
	}

	private void deleteRoleByArtist(final RoleByArtist entityToRemove) {
		if(Objects.nonNull(entityToRemove)) {
			entityToRemove.setStatus(StatusCodes.INACTIVE.getCode());
			entityToRemove.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			entityToRemove.setModifiedAt(LocalDateTime.now());
			roleByArtistDao.save(entityToRemove);
		}
		
	}

	private void createRoleByArtist(final RoleByArtistDTO performedRole, final Artist artist) throws ServiceException {
		
		final RoleByArtistPK pk = new RoleByArtistPK();
		if(Objects.nonNull(performedRole.getArtist())) {
			pk.setArtistId(performedRole.getArtist().getId());
		} else {
			pk.setArtistId(artist.getId());
		}
		pk.setRoleId(performedRole.getRole().getId());
		final Optional<RoleByArtist> foundRoleByArtist = roleByArtistDao.findById(pk);
		if(foundRoleByArtist.isPresent()) {
			if(StatusCodes.ACTIVE.getCode().equals(foundRoleByArtist.get().getStatus())) {
				throw new ServiceException(ServiceErrorCodes.VALIDATION_ERROR,env.getProperty("roleByArtist.error.message.alreadyexists"));
			}
			final RoleByArtist entityToUpdate = foundRoleByArtist.get();
			if(Objects.nonNull(performedRole.getStartAt())) {
				entityToUpdate.setStartAt(performedRole.getStartAt());
			}
			if(Objects.nonNull(performedRole.getEndAt())) {
				entityToUpdate.setEndAt(performedRole.getEndAt());
			}
			entityToUpdate.setStatus(StatusCodes.ACTIVE.getCode());
			if(Objects.isNull(performedRole.getAuditInfo()) || StringUtils.isBlank(performedRole.getAuditInfo().getModifiedBy())) {
				entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			} else {
				entityToUpdate.setModifiedBy(performedRole.getAuditInfo().getModifiedBy());
			}
			roleByArtistDao.save(entityToUpdate);
			
		} else {
			if(Objects.nonNull(performedRole.getRole()) && Objects.nonNull(performedRole.getRole().getId())) {
				final Optional<Role> foundRole = roleDao.findById(performedRole.getRole().getId());
				if(foundRole.isPresent()) {
					RoleByArtist newRoleToPerform = new RoleByArtist();
					final RoleByArtistPK roleByArtistPk = new RoleByArtistPK();
					newRoleToPerform.setArtist(artist);
					newRoleToPerform.setRole(foundRole.get());
					roleByArtistPk.setArtistId(artist.getId());
					roleByArtistPk.setRoleId(foundRole.get().getId());
					newRoleToPerform.setId(roleByArtistPk);
					if(Objects.nonNull(performedRole.getStartAt())) {
						newRoleToPerform.setStartAt(performedRole.getStartAt());
					}
					if(Objects.nonNull(performedRole.getEndAt())) {
						newRoleToPerform.setEndAt(performedRole.getEndAt());
					}
					newRoleToPerform.setCreatedAt(LocalDateTime.now());
					if(Objects.isNull(performedRole.getAuditInfo()) || Objects.isNull(performedRole.getAuditInfo().getCreatedBy())) {
						newRoleToPerform.setCreatedBy(env.getProperty("movieland.service.serveruser"));
					}
					newRoleToPerform.setStatus(StatusCodes.ACTIVE.getCode());
					roleByArtistDao.save(newRoleToPerform);
				}
			}
		}
		
	}

	@Transactional
	public void delete(final BigInteger artistId) throws ServiceException {
		
		final Optional<Artist> foundArtist = dao.findById(artistId);
		if(foundArtist.isPresent()) {
			final Artist entityToUpdate = foundArtist.get();
			entityToUpdate.setStatus(StatusCodes.INACTIVE.getCode());
			entityToUpdate.setModifiedBy(env.getProperty("movieland.service.serveruser"));
			entityToUpdate.setModifiedAt(LocalDateTime.now());
			dao.save(entityToUpdate);
		} else {
			throw new ServiceException(ServiceErrorCodes.NOT_FOUND_ERROR, env.getProperty("artist.error.message.notfound"));
		}
	}
	
	private List<ArtistDTO> buildArtistsFromEntities(final List<Artist> entities) throws ServiceException{
		
		final List<ArtistDTO> artists = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(entities)) {
			for(final Artist entity : entities) {
				artists.add(buildArtistFromEntity(entity));
			}
		}
		return artists;
		
	}
	
	private ArtistDTO buildArtistFromEntity(final Artist entity) throws ServiceException{
		
		final ArtistDTO artist = DTOTransformer.toArtistDTO(entity);
		List<RoleByArtist> relatedRolesByArtist = entity.getRolesByArtist();
		if(CollectionUtils.isNotEmpty(relatedRolesByArtist)) {
			artist.setPerformedRoles(DTOTransformer.toRoleByArtistDTO(relatedRolesByArtist));
			//TODO perform call from produced and directed movies			
		} else {
			relatedRolesByArtist = roleByArtistDao.findByArtistId(entity.getId());
			if(CollectionUtils.isNotEmpty(relatedRolesByArtist)) {
				artist.setPerformedRoles(DTOTransformer.toRoleByArtistDTO(relatedRolesByArtist));
			}
		}
		
		return artist;
	}

}
