package com.movieland.service.businesslogic;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieland.service.dao.CountryDao;
import com.movieland.service.dto.CountryDTO;
import com.movieland.service.entity.Country;
import com.movieland.service.utils.DTOTransformer;
import com.movieland.service.utils.EntityTransformer;

@Service
public class CountryServiceImpl {

	@Autowired
	private CountryDao dao;
	
	public List<CountryDTO> findAll(){
		final List<Country> countries = dao.findAll();
		return DTOTransformer.toCountryDTO(countries);
	}
	
	public CountryDTO findById(final BigInteger id) throws Exception{
		final Optional<Country> foundEntity = dao.findById(id);
		if(foundEntity.isPresent()) {
			return DTOTransformer.toCountryDTO(foundEntity.get());
		} else {
			throw new Exception("Country not found.");
		}
	}
	
	/*public CountryDTO create(final CountryDTO countryDto) {
		Country entity = EntityTransformer.toCountry(countryDto);
		EntityTransformer.setAuditInfo(entity);
		entity = dao.save(entity);
		return DTOTransformer.toCountryDTO(countries);
	}*/
	
}
