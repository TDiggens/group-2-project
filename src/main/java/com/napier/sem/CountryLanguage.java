package com.napier.sem;

/* Class to represent a language as spoken in a specific country */
public class CountryLanguage implements Comparable < CountryLanguage >{

    private boolean isOfficial;
    private String name;
    private String countryCode;
    private Double numberOfSpeakers;
    private double percentageOfSpeakers;

    public CountryLanguage(String name)
    {
        setName(name);
    }

    @Override
    public int compareTo(CountryLanguage otherCountryLanguage)
    {
        return numberOfSpeakers.compareTo(otherCountryLanguage.getNumberOfSpeakers());
    }

    public CountryLanguage(){

    }

    public CountryLanguage(String countryCode, String name, Boolean isOfficial, double percentageOfSpeakers)
    {
        this.countryCode = countryCode;
        this.name = name;
        this.isOfficial = isOfficial;
        this.percentageOfSpeakers = percentageOfSpeakers;
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

    @Override
    public String toString() {
        String numberOfSpeakersStr = String.format("%.0f", this.getNumberOfSpeakers());
        return name + ":" +
                "isOfficial=" + isOfficial +
                ", numberOfSpeakers=" + numberOfSpeakersStr +
                ", percentageOfSpeakers=" + percentageOfSpeakers +
                '}';
    }
}
