package com.napier.sem;

import java.util.ArrayList;

public class World {
    private  ArrayList<Continent> continentList = new ArrayList<Continent>();
    private  ArrayList<City> cityList = new ArrayList<City>();
    private  ArrayList<Region> regionList = new ArrayList<Region>();
    private  ArrayList<District> districtList = new ArrayList<District>();
    private  ArrayList<Country> countryList = new ArrayList<Country>();
    private  int population;

    public World(){
        System.out.println("Data Class Created");
    }


    public ArrayList<Continent> getContinentList() {
        return continentList;
    }

    public void setContinentList(ArrayList<Continent> continentList) {
        this.continentList = continentList;
    }

    public ArrayList<City> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<City> cityList) {
        this.cityList = cityList;
    }

    public ArrayList<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(ArrayList<Region> regionList) {
        this.regionList = regionList;
    }

    public ArrayList<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList<District> districtList) {
        this.districtList = districtList;
    }

    public ArrayList<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<Country> countryList) {
        this.countryList = countryList;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
