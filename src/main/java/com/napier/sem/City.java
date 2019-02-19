package com.napier.sem;

public class City {



    private boolean isCapital;
    private Country country;
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private int population;

    public City()
    {

    }

    public City(int id, String name, String countryCode, String district, int population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public boolean getIsCapital(){
        return isCapital;
    }

    public void setIsCapital(boolean isCapital) {
        this.setIsCapital(isCapital);
    }

    public Country getCountry(){
        return country;
    }

    public void setCountry(Country country){
        this.setCountry(country);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
