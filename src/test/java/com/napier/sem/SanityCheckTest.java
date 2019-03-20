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
    // test for country argument being null
    @Test
    void listCitiesInCountryNull()
    {
        sanityCheck.listCitiesInCountry(null);
    }
    // test for the country's district list being empty.
    @Test
    void listCitiesInCountryDistrictListEmpty()
    {
        ArrayList<District>  districtList = new ArrayList<>();
        Country country = new Country();
        country.setDistrictList(districtList);
        sanityCheck.listCitiesInCountry(country);
    }
    // test for the country's district list containing a null district.
    @Test
    void listCitiesInCountryDistrictListContainsNull()
    {
        ArrayList<District> districtList = new ArrayList<>();
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
        ArrayList<City> cityList = new ArrayList<>();
        ArrayList<District> districtList = new ArrayList<>();
        District district = new District();
        district.setCityList(cityList);
        districtList.add(district);
        Country country = new Country();
        country.setDistrictList(districtList);
        sanityCheck.listCitiesInCountry(country);
    }

    // Test for if one of the cities in the country's district list is null.
    @Test
    void listCitiesInCountryCityListContainsNull()
    {
        ArrayList<City> cityList = new ArrayList<>();
        ArrayList<District> districtList = new ArrayList<>();
        City city = null;
        cityList.add(city);
        District district = new District();
        district.setCityList(cityList);
        districtList.add(district);
        Country country = new Country();
        country.setDistrictList(districtList);
        sanityCheck.listCitiesInCountry(country);
    }

    // test for country argument being null
    @Test
    void listCountriesInContinentNull()
    {
        sanityCheck.listCountriesInContinent(null);
    }
    // test for the continent's region list being empty.
    @Test
    void listCountriesInContinentListEmpty()
    {
        ArrayList<Region>  regionList = new ArrayList<>();
        Continent continent = new Continent();
        continent.setRegionList(regionList);
        sanityCheck.listCountriesInContinent(continent);
    }
    // test for the continent's region list containing a null region.
    @Test
    void listCountriesInContinentListContainsNull()
    {
        ArrayList<Region> regionList = new ArrayList<>();
        Region region = null;
        regionList.add(region);
        Continent continent = new Continent();
        continent.setRegionList(regionList);
        sanityCheck.listCountriesInContinent(continent);
    }
    // test what happens when a district in the district list contains no cities
    @Test
    void listCountriesInContinentRegionCountryListEmpty()
    {
        ArrayList<Country> countryList = new ArrayList<>();
        ArrayList<Region> regionList = new ArrayList<>();
        Region region = new Region();
        region.setCountryList(countryList);
        regionList.add(region);
        Continent continent = new Continent();
        continent.setRegionList(regionList);
        sanityCheck.listCountriesInContinent(continent);
    }

    // Test for if one of the countries in the continent's region list is null.
    @Test
    void listCountriesInContinentCountryListContainsNull() {
        ArrayList<Country> countryList = new ArrayList<>();
        ArrayList<Region> regionList = new ArrayList<>();
        Country country = null;
        countryList.add(country);
        Region region = new Region();
        region.setCountryList(countryList);
        regionList.add(region);
        Continent continent = new Continent();
        continent.setRegionList(regionList);
        sanityCheck.listCountriesInContinent(continent);
    }
}
