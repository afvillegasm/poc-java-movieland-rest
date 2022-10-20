package com.movieland.service.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movieland.service.businesslogic.RoleServiceImpl;
import com.movieland.service.dto.ResponseDTO;
import com.movieland.service.dto.RoleDTO;
import com.movieland.service.exception.ServiceException;
import com.movieland.service.utils.ServiceExceptionUtils;

@RestController
@RequestMapping(path = "/movieland/roles")
public class RoleRestController {

	@Autowired
	private RoleServiceImpl service;
	
	@RequestMapping(path = {"/",""}, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<RoleDTO>>> findAll(){
		final List<RoleDTO> allRoles = service.findAll();
		final ResponseDTO<List<RoleDTO>> resp = new ResponseDTO<List<RoleDTO>>();
		resp.setData(allRoles);
		return new ResponseEntity<ResponseDTO<List<RoleDTO>>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(path = {"/{roleId}"},method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<RoleDTO>> findById(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "roleId") String roleId) {
		
		final ResponseDTO<RoleDTO> resp = new ResponseDTO<RoleDTO>();
		final BigInteger roleIdBi = new BigInteger(roleId);
		
		try {
			final RoleDTO foundRole = service.findById(roleIdBi);
			resp.setData(foundRole);
			return new ResponseEntity<ResponseDTO<RoleDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<RoleDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/", ""},method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<RoleDTO>> createRole(@RequestBody RoleDTO role){
		
		final ResponseDTO<RoleDTO> resp = new ResponseDTO<RoleDTO>();
		try {
			final RoleDTO createdDto = service.create(role);
			resp.setData(createdDto);
			return new ResponseEntity<ResponseDTO<RoleDTO>>(resp, HttpStatus.CREATED);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<RoleDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{roleId}"},method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<RoleDTO>> updateRole(@PathVariable(name = "roleId") String roleId, @RequestBody RoleDTO role){
		
		final ResponseDTO<RoleDTO> resp = new ResponseDTO<RoleDTO>();
		try {
			final BigInteger genreIdBi = new BigInteger(roleId);
			role.setId(genreIdBi);
			final RoleDTO createdDto = service.update(role);
			resp.setData(createdDto);
			return new ResponseEntity<ResponseDTO<RoleDTO>>(resp, HttpStatus.CREATED);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<RoleDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
	@RequestMapping(path = {"/{roleId}"},method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<RoleDTO>> deleteRole(//@Valid 
																	//@Size(min = 1, max = 15, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "roleId") String roleId) {
		
		final ResponseDTO<RoleDTO> resp = new ResponseDTO<RoleDTO>();
		final BigInteger roleIdBi = new BigInteger(roleId);
		
		try {
			service.delete(roleIdBi);
			return new ResponseEntity<ResponseDTO<RoleDTO>>(resp, HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.setError(ServiceExceptionUtils.getErrorDTO(e));
			return new ResponseEntity<ResponseDTO<RoleDTO>>(resp, ServiceExceptionUtils.getHttpStatusFromServiceException(e));
		}
		
	}
	
}
