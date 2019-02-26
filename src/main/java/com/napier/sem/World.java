package com.napier.sem;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* Class to represent the World, to be instantiated once at the start of program, holds lists of
objects representing the smaller regions which can be processed by external classes.
 */
public class World {
    private  TreeMap<Continent, String> continentList = new TreeMap<Continent, String>();
    private  ArrayList<City> cityList = new ArrayList<City>();
    private  TreeMap<Region, String> regionList = new TreeMap<Region, String>();
    private  TreeMap<District, String> districtList = new TreeMap<District, String>();
    private  ArrayList<Country> countryList = new ArrayList<Country>();
    private ArrayList<CountryLanguage> countryLanguageList = new ArrayList<CountryLanguage>();
    private TreeMap<WorldLanguage, Double> languageList = new TreeMap<WorldLanguage, Double>();;
    private  int population;

    public World() {
        System.out.println("Data Class Created");
    }

    public void calculatePopulation() {
        int population = 0;
        for (Map.Entry<Continent, String> continentEntry : continentList.entrySet()) {
            population += continentEntry.getKey().getPopulation();
            this.setPopulation(population);
        }
    }


    public TreeMap<Continent, String> getContinentList() {
        return continentList;
    }

    public void setContinentList(TreeMap<Continent, String> continentList) {
        this.continentList = continentList;
    }

    public ArrayList<City> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<City> cityList) {
        this.cityList = cityList;
    }

    public TreeMap<Region, String> getRegionList() {
        return regionList;
    }

    public void setRegionList(TreeMap<Region, String> regionList) {
        this.regionList = regionList;
    }

    public TreeMap<District, String> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(TreeMap<District, String> districtList) {
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

    public TreeMap<WorldLanguage, Double> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(TreeMap<WorldLanguage, Double> languageList) {
        this.languageList = languageList;
    }
}
