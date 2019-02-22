package com.napier.sem;

/* Class to represent a language as spoken in a specific country */
public class CountryLanguage {

    private boolean isOfficial;
    private String name;
    private double spokenBy;
    private String countryCode;
    private double numberOfSpeakers;

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

    public double getSpokenBy() {
        return spokenBy;
    }

    public void setSpokenBy(double spokenBy) {
        this.spokenBy = spokenBy;
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
}
