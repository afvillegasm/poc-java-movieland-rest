package com.movieland.service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.movieland.service.dto.ArtistDTO;
import com.movieland.service.dto.AuditInfo;
import com.movieland.service.dto.CountryDTO;
import com.movieland.service.dto.GenreDTO;
import com.movieland.service.dto.ParentalGuideDTO;
import com.movieland.service.dto.RoleByArtistDTO;
import com.movieland.service.dto.RoleDTO;
import com.movieland.service.entity.Artist;
import com.movieland.service.entity.Country;
import com.movieland.service.entity.Genre;
import com.movieland.service.entity.ParentalGuide;
import com.movieland.service.entity.Role;
import com.movieland.service.entity.RoleByArtist;
import com.movieland.service.enums.GenderCodes;
import com.movieland.service.enums.StatusCodes;

public class DTOTransformer {

	public static CountryDTO toCountryDTO(final Country entity) {
		
		if(Objects.isNull(entity)) {
			return null;
		}
		
		final CountryDTO dto = new CountryDTO();
		final AuditInfo auditInfo = new AuditInfo();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStatus(StatusCodes.findByCode(entity.getStatus()));
		
		auditInfo.setCreatedBy(entity.getCreatedBy());
		auditInfo.setCreatedAt(entity.getCreatedAt());
		auditInfo.setModifiedBy(entity.getModifiedBy());
		auditInfo.setModifiedAt(entity.getModifiedAt());
		
		dto.setAuditInfo(auditInfo);
		return dto;
	}
	
	public static List<CountryDTO> toCountryDTO(final List<Country> entities) {
		
		final List<CountryDTO> dtos = new ArrayList<>();
		if(!CollectionUtils.isEmpty(entities)) {
			for(final Country entity : entities) {
				
				final CountryDTO dto = toCountryDTO(entity);
				if(Objects.nonNull(dto)) {
					dtos.add(dto);
				}
				
			}
		}
		
		return dtos;
	}

	public static GenreDTO toGenreDTO(Genre entity) {
		if(Objects.isNull(entity)) {
			return null;
		}
		
		final GenreDTO dto = new GenreDTO();
		final AuditInfo auditInfo = new AuditInfo();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setStatus(StatusCodes.findByCode(entity.getStatus()));
		
		auditInfo.setCreatedBy(entity.getCreatedBy());
		auditInfo.setCreatedAt(entity.getCreatedAt());
		auditInfo.setModifiedBy(entity.getModifiedBy());
		auditInfo.setModifiedAt(entity.getModifiedAt());
		
		dto.setAuditInfo(auditInfo);
		return dto;
	}
	
	public static List<GenreDTO> toGenreDTO(final List<Genre> entities) {
		
		final List<GenreDTO> dtos = new ArrayList<>();
		if(!CollectionUtils.isEmpty(entities)) {
			for(final Genre entity : entities) {
				
				final GenreDTO dto = toGenreDTO(entity);
				if(Objects.nonNull(dto)) {
					dtos.add(dto);
				}
				
			}
		}
		
		return dtos;
	}

	public static List<RoleDTO> toRoleDTO(final List<Role> entities) {
		final List<RoleDTO> dtos = new ArrayList<>();
		if(!CollectionUtils.isEmpty(entities)) {
			for(final Role entity : entities) {
				
				final RoleDTO dto = toRoleDTO(entity);
				if(Objects.nonNull(dto)) {
					dtos.add(dto);
				}
				
			}
		}
		
		return dtos;
	}

	public static RoleDTO toRoleDTO(Role entity) {
		if(Objects.isNull(entity)) {
			return null;
		}
		
		final RoleDTO dto = new RoleDTO();
		final AuditInfo auditInfo = new AuditInfo();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setStatus(StatusCodes.findByCode(entity.getStatus()));
		
		auditInfo.setCreatedBy(entity.getCreatedBy());
		auditInfo.setCreatedAt(entity.getCreatedAt());
		auditInfo.setModifiedBy(entity.getModifiedBy());
		auditInfo.setModifiedAt(entity.getModifiedAt());
		
		dto.setAuditInfo(auditInfo);
		return dto;
	}
	
	public static List<ParentalGuideDTO> toParentalGuideDTO(final List<ParentalGuide> entities) {
		final List<ParentalGuideDTO> dtos = new ArrayList<>();
		if(!CollectionUtils.isEmpty(entities)) {
			for(final ParentalGuide entity : entities) {
				
				final ParentalGuideDTO dto = toParentalGuideDTO(entity);
				if(Objects.nonNull(dto)) {
					dtos.add(dto);
				}
				
			}
		}
		
		return dtos;
	}

	public static ParentalGuideDTO toParentalGuideDTO(ParentalGuide entity) {
		if(Objects.isNull(entity)) {
			return null;
		}
		
		final ParentalGuideDTO dto = new ParentalGuideDTO();
		final AuditInfo auditInfo = new AuditInfo();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setStatus(StatusCodes.findByCode(entity.getStatus()));
		
		auditInfo.setCreatedBy(entity.getCreatedBy());
		auditInfo.setCreatedAt(entity.getCreatedAt());
		auditInfo.setModifiedBy(entity.getModifiedBy());
		auditInfo.setModifiedAt(entity.getModifiedAt());
		
		dto.setAuditInfo(auditInfo);
		return dto;
	}
	
	public static ArtistDTO toArtistDTO(Artist entity) {
		if(Objects.isNull(entity)) {
			return null;
		}
		
		final ArtistDTO dto = new ArtistDTO();
		final AuditInfo auditInfo = new AuditInfo();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setArtisticName(entity.getArtisticName());
		dto.setBirthdate(entity.getBirthdate());
		dto.setGender(GenderCodes.findByCode(entity.getGender()));
		dto.setStatus(StatusCodes.findByCode(entity.getStatus()));
		dto.setNationality(toCountryDTO(entity.getNationality()));
		
		auditInfo.setCreatedBy(entity.getCreatedBy());
		auditInfo.setCreatedAt(entity.getCreatedAt());
		auditInfo.setModifiedBy(entity.getModifiedBy());
		auditInfo.setModifiedAt(entity.getModifiedAt());
		
		dto.setAuditInfo(auditInfo);
		return dto;
	}
	
	public static RoleByArtistDTO toRoleByArtistDTO(RoleByArtist entity) {
		if(Objects.isNull(entity)) {
			return null;
		}
		
		final RoleByArtistDTO dto = new RoleByArtistDTO();
		final AuditInfo auditInfo = new AuditInfo();
		dto.setArtist(toArtistDTO(entity.getArtist()));
		dto.setRole(toRoleDTO(entity.getRole()));
		dto.setStatus(StatusCodes.findByCode(entity.getStatus()));
		dto.setStartAt(entity.getStartAt());
		dto.setEndAt(entity.getEndAt());
		auditInfo.setCreatedBy(entity.getCreatedBy());
		auditInfo.setCreatedAt(entity.getCreatedAt());
		auditInfo.setModifiedBy(entity.getModifiedBy());
		auditInfo.setModifiedAt(entity.getModifiedAt());
		
		dto.setAuditInfo(auditInfo);
		return dto;
	}
	
	public static List<RoleByArtistDTO> toRoleByArtistDTO(final List<RoleByArtist> entities) {
		final List<RoleByArtistDTO> dtos = new ArrayList<>();
		if(!CollectionUtils.isEmpty(entities)) {
			for(final RoleByArtist entity : entities) {
				
				final RoleByArtistDTO dto = toRoleByArtistDTO(entity);
				if(Objects.nonNull(dto)) {
					dtos.add(dto);
				}
				
			}
		}
		
		return dtos;
	}
	
}
