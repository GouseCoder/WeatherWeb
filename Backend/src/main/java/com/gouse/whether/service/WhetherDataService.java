package com.gouse.whether.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gouse.whether.utility.DateConverter;
import com.gouse.whether.utility.JacksonUtil;
import com.gouse.whether.utility.TemperatureConverter;

@Service
public class WhetherDataService {

	public String getWhetherDataFromApi(String city) {

		StringBuilder informationString = null;

		try {
            // Replace YOUR_API_KEY with your OpenWeatherMap API key
            String apiKey = "953d6e2eb8feb3553a19aed9660f77d1";
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + responseCode);
            }

            informationString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }

            scanner.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return informationString.toString();
	}

	public String getWhetherForecastFromApi(String latitude, String longitude) {
		
		StringBuilder informationString = null;

		try {
            // Replace YOUR_API_KEY with your OpenWeatherMap API key
            String apiKey = "953d6e2eb8feb3553a19aed9660f77d1";
            String urlString = "https://api.openweathermap.org/data/2.5/forecast?lat=" + latitude + "&lon=" + longitude +"&appid=" + apiKey;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + responseCode);
            }

            informationString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }

            scanner.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return informationString.toString();
	}

	public ArrayNode createResponseStructure(JsonNode responseDatafromApi) {
		
		ArrayNode forecastArray = JacksonUtil.mapper.createArrayNode();
		
		try {
			
			ArrayNode list = (ArrayNode) responseDatafromApi.get("list");
			int counter = 1;
			for(JsonNode json : list) {
				if(counter == 7) {
					break;
				}
				else {
					ObjectNode forecastObject = JacksonUtil.mapper.createObjectNode();
					String time = DateConverter.convertTimestampToTime(json.get("dt").asLong(), "Asia/Calcutta");
					double celsiusTemperature = TemperatureConverter.convertKelvinToCelsius(json.get("main").get("temp").asDouble());
					String formattedTemperature = String.format("%.2f", celsiusTemperature);
					String temp = formattedTemperature + "°C";
					String main = json.get("weather").get(0).get("main").asText();
					
					forecastObject.put("time", time);
					forecastObject.put("temp", temp);
					forecastObject.put("main", main);
										
					forecastArray.add(forecastObject);
					counter = counter + 1;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Exception found in createResponseStructure " + e);
		}
		
		
		
		return forecastArray;
	}

}
