package com.napier.sem;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* Class to represent the World, to be instantiated once at the start of program, holds lists of
objects representing the smaller regions which can be processed by external classes.
 */
public class World {
    /* lists to hold continent, city, region, district and country objects representing the world
     */
    private  ArrayList<Continent> continentList = new ArrayList<Continent>();
    private  ArrayList<City> cityList = new ArrayList<City>();
    private  ArrayList<Region> regionList = new ArrayList<Region>();
    private  ArrayList<District> districtList = new ArrayList<District>();
    private  ArrayList<Country> countryList = new ArrayList<Country>();
    private ArrayList<WorldLanguage> languageList = new ArrayList<WorldLanguage>();;
    /* long to hold the world population */
    private  long population;

    public World() {
        System.out.println("Data Class Created");
    }
    /* method to calculate global population by summing the populations of each continent
       simply iterates over the continentList and sums the populations.
     */
    public void calculatePopulation() {
        long population = 0;
        for (Continent continent : continentList) {
            population += continent.getPopulation();
            this.setPopulation(population);
        }
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

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setLanguageList(ArrayList<WorldLanguage> languageList) {
        this.languageList = languageList;
    }

    @Override
    public String toString() {
        StringBuilder languagesSummary = new StringBuilder();
        for(WorldLanguage worldLanguage : languageList){
            String numberOfSpeakersStr = String.format("%.0f", worldLanguage.getNumberOfSpeakers());
            languagesSummary.append(worldLanguage.getName() + ": Number of speakers: " + numberOfSpeakersStr + "" +
                    " Percentage of global population: " + worldLanguage.getPercentageOfSpeakers() + '\n');
        }
        return
                "cityList contains " + cityList.size() + " cities " + '\n' +
                "districtList contains " + districtList.size() + " districts" + '\n' +
                "countryList contains " + countryList.size() + " countries" + '\n' +
                "regionList contains " + regionList.size() + " regions" + '\n' +
                "continentList contains " + continentList.size() + " continents." + '\n' +
                        "countryList contains " + countryList.size() + " countries" + '\n' +
                        "languageList contains " + languageList.size() + " global languages" + '\n' +
                "World Population: " + population + '\n' + '\n' +
                "Global Languages: " + '\n' +
                languagesSummary.toString() +
                '}';
    }

    public ArrayList<WorldLanguage> getLanguageList() {
        return languageList;
    }


}
