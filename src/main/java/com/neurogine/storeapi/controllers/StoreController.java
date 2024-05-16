package com.neurogine.storeapi.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neurogine.storeapi.beans.ApiResponse;
import com.neurogine.storeapi.configurations.StoreMessagingConfiguration;
import com.neurogine.storeapi.entities.Store;

@RestController
@RequestMapping("/api/store")
public class StoreController {
	
	@Autowired
	StoreMessagingConfiguration.StoreService storeService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<?>> create(
		HttpServletRequest request,
		@RequestBody Store store) {
		try {
			Store result = storeService.create(store);
			return new ResponseEntity<>(new ApiResponse<Store>(ApiResponse.STATUS.OK, "CREATED", result), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<String>(ApiResponse.STATUS.ERROR, e.getMessage(), ""), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse<?>> update(
		HttpServletRequest request,
		@RequestBody Store store) {
		
		try {
			Store result = storeService.update(store);
			return new ResponseEntity<>(new ApiResponse<Store>(ApiResponse.STATUS.OK, "UPDATED", result), HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse<String>(ApiResponse.STATUS.ERROR, e.getMessage(), ""), HttpStatus.BAD_REQUEST);
		}
	}
	
	public void get() {};
	public void delete() {};
}
