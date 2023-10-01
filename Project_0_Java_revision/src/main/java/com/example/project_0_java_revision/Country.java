package com.example.project_0_java_revision;

public class Country implements Comparable<Country> {
    private String name;
    private double internetUsage;
    public Country() {
    
    }
    public Country(String name, double internetUsage) {
        this.setName(name);
        this.setInternetUsage(internetUsage);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if (name.length() > 2)
            this.name = name;
        else
            throw new IllegalArgumentException("Name must be at least 3 characters");
    }
    
    public double getInternetUsage() {
        return internetUsage;
    }
    
    public void setInternetUsage(double internetUsage) {
        if (internetUsage <= 100 && internetUsage >= 0)
            this.internetUsage = internetUsage;
        else
            throw new IllegalArgumentException("Internet Usage must be between 0 and 100");
        
    }
    
    @Override
    public String toString() {
        return
                "Country Name: " + name +
                        " , Internet Usage: " + internetUsage;
    }
    
    @Override
    public boolean equals(Object o) {
        //Check if the object is not null
        if (o != null) {
            //Check if the object is of type Country
            if (o instanceof Country c)
                //Return true if the name and internetUsage of the two objects are equal
                return this.name.equals(c.name) && this.internetUsage == c.internetUsage;
        }
        //Return false if the object is not of type Country
        return false;
    }
    
    @Override
    public int compareTo(Country o) {
        //Compare the internet usage of this Country object to the internet usage of the Country object passed in as a parameter
        return Double.compare(this.internetUsage, o.internetUsage);
    }
}
