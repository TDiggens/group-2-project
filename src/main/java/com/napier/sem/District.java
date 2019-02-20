package com.napier.sem;

import java.util.ArrayList;
/* Class to represent a district within a country, with the list of cities it contains

 */
public class District {

    private String name;
    private int population;
    private ArrayList<City> cityList = new ArrayList<City>();

    public District(){

    }

    public District(String name) {
        this.name = name;

    }

    public void cacultatePopulation(){
        int p = 0;
        for(City city : cityList){
            p += city.getPopulation();
        }
        setPopulation(p);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public ArrayList<City> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<City> cityList) {
        this.cityList = cityList;
    }
}
