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
    private ArrayList<District> districtList = new ArrayList<District>();
    private ArrayList<CountryLanguage> languageList = new ArrayList<CountryLanguage>();


    public Country() {

    }

    public void calculateUrbanPop(){
        int urbanPop = 0;
        for(District district : this.getDistrictList()){
            for(City city : district.getCityList()){
                urbanPop += city.getPopulation();
            }
        }
        this.setUrbanPopulation(urbanPop);
        if(this.getPopulation() != 0){
            this.setUrbanPopPercentage(urbanPop/this.getPopulation()*100);
        } else {
            this.setUrbanPopPercentage(0);
        }

    }

    public void printDistrictList(){
        for(District district : this.getDistrictList()){
            System.out.println(district.toString());
        }
    }

    public Country(String name, String code, String continent, String region, double surfaceArea, int yearOfIndependence,
                   int population, double lifeExpectancy, double gnp, double oldGNP, String formOfGov, String headOfState,
                   City capital, String code2) {
        this.setName(name);
        this.setCode(code);
        this.setContinent(continent);
        this.setRegion(region);
        this.setSurfaceArea(surfaceArea);
        this.setYearOfIndependence(yearOfIndependence);
        this.setPopulation(population);
        this.setLifeExpectancy(lifeExpectancy);
        this.setGnp(gnp);
        this.setOldGNP(oldGNP);
        this.setFormOfGov(formOfGov);
        this.setHeadOfState(headOfState);
        this.setCapital(capital);
        this.setCode2(code2);
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
        return "Country{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", formOfGov='" + formOfGov + '\'' +
                ", headOfState='" + headOfState + '\'' +
                ", localName='" + localName + '\'' +
                ", code2='" + code2 + '\'' +
                ", capitalCode=" + capitalCode +
                ", yearOfIndependence=" + yearOfIndependence +
                ", population=" + population +
                ", surfaceArea=" + surfaceArea +
                ", lifeExpectancy=" + lifeExpectancy +
                ", gnp=" + gnp +
                ", oldGNP=" + oldGNP +
                ", urbanPopulation=" + urbanPopulation +
                ", capital=" + capital +
                ", districtList=" + districtList +
                ", languageList=" + languageList +
                '}';
    }

    public double getUrbanPopPercentage() {
        return urbanPopPercentage;
    }

    public void setUrbanPopPercentage(double urbanPopPercentage) {
        this.urbanPopPercentage = urbanPopPercentage;
    }
}
