package com.napier.sem;

public class Language {

    private boolean isOfficial;
    private String name;
    private double spokenBy;
    private String countryCode;

    public Language()
    {

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
}
