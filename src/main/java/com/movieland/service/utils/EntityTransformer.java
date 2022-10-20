package com.movieland.service.utils;

import java.util.Objects;

import com.movieland.service.dto.CountryDTO;
import com.movieland.service.dto.GenreDTO;
import com.movieland.service.dto.ParentalGuideDTO;
import com.movieland.service.dto.RoleDTO;
import com.movieland.service.entity.Country;
import com.movieland.service.entity.Genre;
import com.movieland.service.entity.ParentalGuide;
import com.movieland.service.entity.Role;

public class EntityTransformer {

	public static Country toCountry(CountryDTO countryDto) {
		final Country entity = new Country();
		entity.setName(countryDto.getName());
		if(Objects.nonNull(countryDto.getStatus())) {
			entity.setStatus(countryDto.getStatus().getCode());
		}
		entity.setId(countryDto.getId());
		if(Objects.nonNull(countryDto.getAuditInfo())) {
			entity.setCreatedBy(countryDto.getAuditInfo().getCreatedBy());
			entity.setCreatedAt(countryDto.getAuditInfo().getCreatedAt());
			entity.setModifiedBy(countryDto.getAuditInfo().getModifiedBy());
			entity.setModifiedAt(countryDto.getAuditInfo().getModifiedAt());
		}
		return entity;
	}

	public static Genre toGenre(GenreDTO genreDto) {
		final Genre entity = new Genre();
		entity.setName(genreDto.getName());
		entity.setDescription(genreDto.getDescription());
		if(Objects.nonNull(genreDto.getStatus())) {
			entity.setStatus(genreDto.getStatus().getCode());
		}
		entity.setId(genreDto.getId());
		if(Objects.nonNull(genreDto.getAuditInfo())) {
			entity.setCreatedBy(genreDto.getAuditInfo().getCreatedBy());
			entity.setCreatedAt(genreDto.getAuditInfo().getCreatedAt());
			entity.setModifiedBy(genreDto.getAuditInfo().getModifiedBy());
			entity.setModifiedAt(genreDto.getAuditInfo().getModifiedAt());
		}
		return entity;
	}

	public static Role toRole(RoleDTO roleDTO) {
		final Role entity = new Role();
		entity.setName(roleDTO.getName());
		entity.setDescription(roleDTO.getDescription());
		if(Objects.nonNull(roleDTO.getStatus())) {
			entity.setStatus(roleDTO.getStatus().getCode());
		}
		entity.setId(roleDTO.getId());
		if(Objects.nonNull(roleDTO.getAuditInfo())) {
			entity.setCreatedBy(roleDTO.getAuditInfo().getCreatedBy());
			entity.setCreatedAt(roleDTO.getAuditInfo().getCreatedAt());
			entity.setModifiedBy(roleDTO.getAuditInfo().getModifiedBy());
			entity.setModifiedAt(roleDTO.getAuditInfo().getModifiedAt());
		}
		return entity;
	}
	
	public static ParentalGuide toParentalGuide(ParentalGuideDTO parentalGuideDTO) {
		final ParentalGuide entity = new ParentalGuide();
		entity.setName(parentalGuideDTO.getName());
		entity.setDescription(parentalGuideDTO.getDescription());
		if(Objects.nonNull(parentalGuideDTO.getStatus())) {
			entity.setStatus(parentalGuideDTO.getStatus().getCode());
		}
		entity.setId(parentalGuideDTO.getId());
		if(Objects.nonNull(parentalGuideDTO.getAuditInfo())) {
			entity.setCreatedBy(parentalGuideDTO.getAuditInfo().getCreatedBy());
			entity.setCreatedAt(parentalGuideDTO.getAuditInfo().getCreatedAt());
			entity.setModifiedBy(parentalGuideDTO.getAuditInfo().getModifiedBy());
			entity.setModifiedAt(parentalGuideDTO.getAuditInfo().getModifiedAt());
		}
		return entity;
	}

}
