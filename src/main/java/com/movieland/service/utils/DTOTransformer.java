package com.movieland.service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.movieland.service.dto.AuditInfo;
import com.movieland.service.dto.CountryDTO;
import com.movieland.service.entity.Country;
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
	
}
