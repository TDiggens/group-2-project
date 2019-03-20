package com.napier.sem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SanityCheckTest
{
    static SanityCheck sanityCheck;

    @BeforeAll
    static void init()
    {
        sanityCheck = new SanityCheck();
    }
    //test for country argument being null
    @Test
    void listCitiesInCountryNull()
    {
        sanityCheck.listCitiesInCountry(null);
    }
    //test for the country's district list being empty.
    @Test
    void listCitiesInCountryDistrictListEmpty()
    {
        ArrayList<District>  districtList = new ArrayList<District>();
        Country country = new Country();
        country.setDistrictList(districtList);
        sanityCheck.listCitiesInCountry(country);
    }
    //test for the country's district list containing a null district.
    @Test
    void listCitiesInCountryDistrictListContainsNull()
    {
        ArrayList<District> districtList = new ArrayList<District>();
        District district = null;
        districtList.add(district);
        Country country = new Country();
        country.setDistrictList(districtList);
        sanityCheck.listCitiesInCountry(country);
    }
    // test what happens when a district in the district list contains no cities
    @Test
    void listCitiesInCountryDistrictCityListEmpty()
    {
        ArrayList<City> cityList = new ArrayList<City>();
        ArrayList<District> districtList = new ArrayList<District>();
        District district = new District();
        district.setCityList(cityList);
        districtList.add(district);
        Country country = new Country();
        country.setDistrictList(districtList);
        sanityCheck.listCitiesInCountry(country);
    }

    //Test for if one of the cities in the country's district list is null.
    @Test
    void listCitiesInCountryCityListContainsNull()
    {
        ArrayList<City> cityList = new ArrayList<City>();
        ArrayList<District> districtList = new ArrayList<District>();
        City city = null;
        cityList.add(city);
        District district = new District();
        district.setCityList(cityList);
        districtList.add(district);
        Country country = new Country();
        country.setDistrictList(districtList);
        sanityCheck.listCitiesInCountry(country);
    }

    @Test
    //Test how the method runs under normal conditions with appropriate test data.
    void listCitiesInCountry()
    {
        City city1 = new City("GBR", "Midlothian", 001, "Edinburgh", 500000);
        City city2 = new City("GBR", "Fife", 002, "Kirkcaldy", 60000);
        District district1 = new District("Midlothian");
        District district2 = new District("Fife");
        Country country = new Country(001, "GBR", "GB", "Europe", 10000, 20000, "Constitutional Monarchy", "Queen Elizabeth II", 2020, 74, "Alba",
                "Scotland", 6000000, "Western Europe", 80077);
        city1.setCountry(country);
        city2.setCountry(country);
        district1.setCountry(country);
        district2.setCountry(country);
        district1.getCityList().add(city1);
        district2.getCityList().add(city2);
        country.getDistrictList().add(district1);
        country.getDistrictList().add(district2);
        sanityCheck.listCitiesInCountry(country);
    }
}
