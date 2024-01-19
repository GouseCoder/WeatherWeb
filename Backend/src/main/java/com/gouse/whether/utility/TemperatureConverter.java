package com.gouse.whether.utility;

public class TemperatureConverter {
	
	public static double convertKelvinToCelsius(double kelvin) {
        // Check if the input temperature is non-negative (Kelvin cannot be negative)
        if (kelvin < 0) {
            throw new IllegalArgumentException("Temperature in Kelvin cannot be negative.");
        }

        // Convert Kelvin to Celsius using the formula
        double celsius = kelvin - 273.15;

        return celsius;
    }

}
