package com.gouse.whether.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.gouse.whether.response.ApiResponse;
import com.gouse.whether.service.WhetherDataService;
import com.gouse.whether.utility.JacksonUtil;

@RestController
public class WhetherDataController {

	@Autowired
	private WhetherDataService service;

	@RequestMapping(method = RequestMethod.GET, value = "/getCurrentWeather")
	public ResponseEntity<ApiResponse> getCurrentWahetherData(@RequestParam String city) {

		JsonNode response;

		try {

			String data = service.getWhetherDataFromApi(city);

			response = JacksonUtil.mapper.readTree(data);

		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse(null, false, HttpStatus.INTERNAL_SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(new ApiResponse(response, true, HttpStatus.OK), HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getWeatherForecast")
	public ResponseEntity<ApiResponse> getForecast(@RequestParam String latitude, @RequestParam String longitude) {

		JsonNode response;

		try {

			String data = service.getWhetherForecastFromApi(latitude, longitude);
			JsonNode responseDatafromApi = JacksonUtil.mapper.readTree(data);
			response = service.createResponseStructure(responseDatafromApi);
			

		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse(null, false, HttpStatus.INTERNAL_SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(new ApiResponse(response, true, HttpStatus.OK), HttpStatus.OK);

	}

}
