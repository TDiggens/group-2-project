package com.napier.sem;

import java.util.ArrayList;
import java.util.TreeMap;

/* Class to represent the World, to be instantiated once at the start of program, holds lists of
objects representing the smaller regions which can be processed by external classes.
 */
public class World {
    private  ArrayList<Continent> continentList = new ArrayList<Continent>();
    private  ArrayList<City> cityList = new ArrayList<City>();
    private  ArrayList<Region> regionList = new ArrayList<Region>();
    private  ArrayList<District> districtList = new ArrayList<District>();
    private  ArrayList<Country> countryList = new ArrayList<Country>();
    private ArrayList<CountryLanguage> countryLanguageList = new ArrayList<CountryLanguage>();
    private TreeMap<WorldLanguage, Double> languageList = new TreeMap<WorldLanguage, Double>();;
    private  int population;

    public World() {
        System.out.println("Data Class Created");
    }

    public void calculatePopulation(){
        int population = 0;
        for(Continent continent : continentList){
            population += continent.getPopulation();
        }
        this.setPopulation(population);
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

    public TreeMap<WorldLanguage, Double> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(TreeMap<WorldLanguage, Double> languageList) {
        this.languageList = languageList;
    }
}
