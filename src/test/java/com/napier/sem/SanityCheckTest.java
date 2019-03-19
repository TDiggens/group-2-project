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
}
