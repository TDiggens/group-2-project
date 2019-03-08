package com.napier.sem;

import java.util.ArrayList;
/* A Class designed to represent a Country with appropriate instance variables
and a list of City objects to hold the Cities it contains */
public class Country {

    private String name;
    private String code;
    private String continent;
    private String region;
    private String formOfGov;
    private String headOfState;
    private String localName;
    private String code2;

    private int capitalCode;
    private int yearOfIndependence;
    private int population;

    private double surfaceArea;
    private double lifeExpectancy;
    private double gnp;
    private double oldGNP;
    private double urbanPopulation;
    private double urbanPopPercentage;

    private City capital;
    private Region regionObject;
    private ArrayList<District> districtList = new ArrayList<District>();
    private ArrayList<CountryLanguage> languageList = new ArrayList<CountryLanguage>();


    public Country() {

    }

    public void calculateUrbanPop(){
        double urbanPop = 0;
        for(District district : this.getDistrictList()){
            for(City city : district.getCityList()){
                urbanPop += city.getPopulation();
            }
        }
        this.setUrbanPopulation(urbanPop);
        if(this.getPopulation() != 0){
            this.setUrbanPopPercentage((urbanPop*100)/(double)this.getPopulation());
        } else {
            this.setUrbanPopPercentage(0);
        }
    }

    public void printLanguageList(){
        for(CountryLanguage countryLanguage : this.getLanguageList()){
            System.out.println(countryLanguage.toString());
        }
    }

    public void printDistrictList(int numberToPrint){
            if (numberToPrint > this.getDistrictList().size()) {
                numberToPrint = this.getDistrictList().size();
            }
            System.out.println(this.getDistrictList().size() + " districts in " + this.getName());
            for (int i = 0; i < numberToPrint; i++) {
                System.out.println(this.getDistrictList().get(i).getName() + ", population: "
                        + this.getDistrictList().get(i).getPopulation());
            }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public int getYearOfIndependence() {
        return yearOfIndependence;
    }

    public void setYearOfIndependence(int yearOfIndependence) {
        this.yearOfIndependence = yearOfIndependence;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public double getGnp() {
        return gnp;
    }

    public void setGnp(double gnp) {
        this.gnp = gnp;
    }

    public double getOldGNP() {
        return oldGNP;
    }

    public void setOldGNP(double oldGNP) {
        this.oldGNP = oldGNP;
    }

    public String getFormOfGov() {
        return formOfGov;
    }

    public void setFormOfGov(String formOfGov) {
        this.formOfGov = formOfGov;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public double getUrbanPopulation() {
        return urbanPopulation;
    }

    public void setUrbanPopulation(double urbanPopulation) {
        this.urbanPopulation = urbanPopulation;
    }

    public ArrayList<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList<District> districtList) {
        this.districtList = districtList;
    }

    public int getCapitalCode() {
        return capitalCode;
    }

    public void setCapitalCode(int capitalCode) {
        this.capitalCode = capitalCode;
    }

    public ArrayList<CountryLanguage> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(ArrayList<CountryLanguage> languageList) {
        this.languageList = languageList;
    }

    @Override
    public String toString() {
        String urbanPopulationStr = String.format("%.0f", urbanPopulation);
        return   name + ":" + '\n' +
                ", code='" + code + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", formOfGov='" + formOfGov + '\'' + '\n' +
                ", headOfState='" + headOfState + '\'' +
                ", localName='" + localName + '\'' +
                ", code2='" + code2 + '\'' +
                ", capitalCode=" + capitalCode + '\n' +
                ", yearOfIndependence=" + yearOfIndependence +
                ", population=" + population +
                ", surfaceArea=" + surfaceArea +
                ", lifeExpectancy=" + lifeExpectancy + '\n' +
                ", gnp=" + gnp +
                ", oldGNP=" + oldGNP +
                ", urbanPopulation=" + urbanPopulationStr +
                ", urbanPopulation(percentage)=" + urbanPopPercentage + '\n';
    }

    public String report(){
        if(!(capital == null)){
            String report = "Country Report: " + name + '\n' + '\n' +
                    "Country Code: " + code + '\n' +
                    "Continent: " + continent + '\n' +
                    "Region: " + region + '\n' +
                    "Population: " + population + '\n' +
                    "Capital: " + capital.getName() + '\n';
            return report;
        } else{
            String report = "Country Report: " + name + '\n' + '\n' +
                    "Country Code: " + code + '\n' +
                    "Continent: " + continent + '\n' +
                    "Region: " + region + '\n' +
                    "Population: " + population + '\n' +
                    "Capital: No capital city" + '\n';
            return report;
        }

    }

    public double getUrbanPopPercentage() {
        return urbanPopPercentage;
    }

    public void setUrbanPopPercentage(double urbanPopPercentage) {
        this.urbanPopPercentage = urbanPopPercentage;
    }

    public Region getRegionObject() {
        return regionObject;
    }

    public void setRegionObject(Region regionObject) {
        this.regionObject = regionObject;
    }
}
