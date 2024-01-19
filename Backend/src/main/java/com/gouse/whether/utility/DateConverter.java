package com.gouse.whether.utility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateConverter {
	
	public static String convertTimestampToTime(long timestamp, String timeZone) {
		 String formattedTime = "";
		try {
			
			Instant instant = Instant.ofEpochSecond(timestamp);
	        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of(timeZone));

	        // Format the time to 9:00 AM
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
	        formattedTime = dateTime.format(formatter);

			
		} catch (Exception e) {
			System.out.println("Exception in convertTimestampToTime " + e);
		}
		return formattedTime;
        
    }

}
