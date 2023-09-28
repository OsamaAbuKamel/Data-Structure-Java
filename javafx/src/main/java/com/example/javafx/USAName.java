package com.example.javafx;

public class USAName implements Comparable<USAName> {
    private String name;
    private char gender;
    private int freq;
    
    public USAName() {
    }
    
    public USAName(String name, char gender, int freq) {
        this.name = name;
        this.setGender(gender);
        this.freq = freq;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public char getGender() {
        return gender;
    }
    
    public void setGender(char gender) {
        if (gender == 'F' || gender == 'M')
            this.gender = gender;
        else
            System.out.println("gender has been M or F");
        
    }
    
    public int getFreq() {
        return freq;
    }
    
    public void setFreq(int freq) {
        this.freq = freq;
    }
    
    @Override
    public String toString() {
        return "USAName{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", freq=" + freq +
                '}';
    }
    
    @Override
    public int compareTo(USAName o) {
        return Integer.compare(this.freq, o.freq);
    }
}