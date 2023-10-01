package com.example.project_0_java_revision;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class CountryList {
    private MyList<Country> list;
    
    public CountryList() {
        // Create a new MyList object and assign it to the list variable
        this.list = new MyList<>();
    }
    
    // Getter method to return the list
    public MyList<Country> getList() {
        // Return the list
        return this.list;
    }
    
    // Setter method to set the list
    public void setList(MyList<Country> list) {
        // Set the list to the parameter
        this.list = list;
    }
    
    public void add(String countryName, double internet) {
        countryName = countryName.toUpperCase();
        Country country = new Country(countryName, internet);
        // Check if the country already exists in the list
        if (search(countryName) != null) {
            //Throw an exception if the country already exists
            throw new IllegalArgumentException("Country already exists"+ countryName);
        } else {
            // Add the country to the list
            list.add(country);
            
        }
    }
    
    public void remove(String country) {
        country = country.toUpperCase();
        // Check if the country is in the list
        if (search(country) != null) {
            // Delete the country from the list
            list.delete(list.find(search(country)));
        } else {
            // Throw an exception if the country is not found
            throw new NullPointerException("Country not found");
        }
    }
    
    public Country search(String country) {
        country = country.toUpperCase();
        // Loop through the list of countries
        for (int i = 0; i < list.size(); i++) {
            // Check if the name of the country in the list matches the given country
            if (list.get(i).getName().equals(country)) {
                // Return the country if it is found
                return list.get(i);
            }
        }
        // Return null if the country is not found
        return null;
    }
    
    public void clear() {
        // Clear the list of objects
        list.clear();
    }
    
    public void sort() {
        // Sort the list of objects
        list.sort();
    }
    
    public void read(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String line = " ";
            // Loop through each line in the file
            while (scanner.hasNextLine()) {
                // Store the line in the variable line
                line = scanner.nextLine();
                // Split the line into an array of parts
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                // Check if the array has at least two parts
                if (parts.length >= 2) {
                    // Store the first part in the variable country
                    String country = parts[0].trim().replace("\"", "");
                    // Declare a double variable to store the value
                    double value = Double.parseDouble(parts[1].trim());
                    // add the country and its value to the list
                    add(country, value);
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return getList() + "\n";
    }
    
}