package com.napier.sem;

/* Class to represent a language as spoken in a specific country */
public class CountryLanguage {

    private boolean isOfficial;
    private String name;
    private String countryCode;
    private double numberOfSpeakers;
    private double percentageOfSpeakers;

    public CountryLanguage(String name)
    {
        setName(name);
    }

    public CountryLanguage(){

    }

    public boolean isOfficial() {
        return isOfficial;
    }

    public void setOfficial(boolean official) {
        isOfficial = official;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getNumberOfSpeakers() {
        return numberOfSpeakers;
    }

    public void setNumberOfSpeakers(double numberOfSpeakers) {
        this.numberOfSpeakers = numberOfSpeakers;
    }

    public double getPercentageOfSpeakers() {
        return percentageOfSpeakers;
    }

    public void setPercentageOfSpeakers(double percentageOfSpeakers) {
        this.percentageOfSpeakers = percentageOfSpeakers;
    }
}
