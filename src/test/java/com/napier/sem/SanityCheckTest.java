package com.napier.sem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.ArrayList;
import java.util.Collections;

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
        System.out.println("Output for test listCitiesInCountryNull: " + '\n');
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
        System.out.println("Output for test listCitiesInCountryDistrictListContainsNull: " + '\n');
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
        System.out.println("Output for test listCitiesInCountryCityListContainsNull: " + '\n');
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

    @Test
    //Test how the method runs under normal conditions with appropriate test data.
    void listCitiesInCountry()
    {
        System.out.println("Output for test listCitiesInCountry: " + '\n');
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

    //Tests for listCountriesInContinent
    // test for continent argument being null
    @Test
    void listCountriesInContinentNull()
    {
        System.out.println("Output for test listCountriesInContinentNull: " + '\n');
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
        System.out.println('\n');
    }
    // test for the continent's region list containing a null region.
    @Test
    void listCountriesInContinentListContainsNull()
    {
        System.out.println("Output for test listCountriesInContinentListContainsNull: " + '\n');
        ArrayList<Region> regionList = new ArrayList<>();
        Region region = null;
        regionList.add(region);
        Continent continent = new Continent();
        continent.setRegionList(regionList);
        sanityCheck.listCountriesInContinent(continent);
        System.out.println('\n');
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
        System.out.println('\n');
    }

    // Test for if one of the countries in the continent's region list is null.
    @Test
    void listCountriesInContinentCountryListContainsNull() {
        System.out.println("Output for test listCountriesInContinentCountryListContainsNull: " + '\n');
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
        System.out.println('\n');
    }

    //Test for normal conditions using appropriate test data.
    @Test
    void listCountriesInContinent()
    {
        System.out.println("Output for test listCountriesInContinent: " + '\n');
        Country country1 = new Country(001, "GBR", "GB", "Europe", 10000, 20000, "Constitutional Monarchy", "Queen Elizabeth II", 2020, 74, "Alba",
                "Scotland", 6000000, "Western Europe", 80077);
        Country country2 = new Country(001, "GBR", "GB", "Europe", 25000, 23000, "Constitutional Monarchy", "Queen Elizabeth II",
                1709, 79, "England", "England", 55600000, "Western Europe", 130395);
        City city1 = new City("GBR", "Midlothian", 001, "Edinburgh", 500000);
        City city2 = new City("GBR", "Fife", 002, "Kirkcaldy", 60000);
        City city3 = new City("GBR", "Greater London", 003, "London", 9000000);
        City city4 = new City("GBR", "Tyne and Wear", 004, "Newcastle", 295800);
        city1.setCountry(country1);
        city2.setCountry(country1);
        city3.setCountry(country2);
        city4.setCountry(country2);
        District district1 = new District("Midlothian");
        District district2 = new District("Fife");
        District district3 = new District("Greater London");
        District district4 = new District("Tyne and Wear");
        district1.getCityList().add(city1);
        district2.getCityList().add(city2);
        district3.getCityList().add(city3);
        district4.getCityList().add(city4);
        district1.calculatePopulation();
        district2.calculatePopulation();
        district3.calculatePopulation();
        district4.calculatePopulation();
        country1.getDistrictList().add(district1);
        country1.getDistrictList().add(district2);
        country2.getDistrictList().add(district3);
        country2.getDistrictList().add(district4);
        Region region = new Region("Western Europe");
        region.getCountryList().add(country1);
        region.getCountryList().add(country2);
        country1.setRegionObject(region);
        country2.setRegionObject(region);
        country1.calculateUrbanPop();
        country2.calculateUrbanPop();
        Continent continent = new Continent("Europe");
        continent.getRegionList().add(region);
        sanityCheck.listCountriesInContinent(continent);
        System.out.println('\n');
    }

    /* --------Tests for listCountriesInRegion()----------------
     */

    @Test
    void listCountriesInRegionNull()
    {
        System.out.println("Output for test listCountriesInRegionNull: " + '\n');
        sanityCheck.listCountriesInRegion(null);
        System.out.println('\n');
    }

    @Test
    void listCountriesInRegionCountryListEmpty()
    {
        ArrayList<Country> countryList = new ArrayList<Country>();
        Region region = new Region();
        region.setCountryList(countryList);
        sanityCheck.listCountriesInRegion(region);
        System.out.println('\n');
    }

    @Test
    void listCountriesInRegionCountryListNull()
    {
        System.out.println("Output for test listCountriesInRegionCountryListNull: " + '\n');
        Region region = new Region();
        region.setCountryList(null);
        sanityCheck.listCountriesInRegion(region);
        System.out.println('\n');
    }

    @Test
    void listCountriesInRegionCountryListContainsNull()
    {
        System.out.println("Output for test listCountriesInRegionCountryListContainsNull: " + '\n');
        Region region = new Region();
        ArrayList<Country> countryList = new ArrayList<Country>();
        countryList.add(null);
        region.setCountryList(countryList);
        sanityCheck.listCountriesInRegion(region);
        System.out.println('\n');
    }

    @Test
    void listCountriesInRegion()
    {
        System.out.println("Output for test listCountriesInRegion: " + '\n');
        Country country1 = new Country(001, "GBR", "GB", "Europe", 10000, 20000, "Constitutional Monarchy", "Queen Elizabeth II", 2020, 74, "Alba",
                "Scotland", 6000000, "Western Europe", 80077);
        Country country2 = new Country(001, "GBR", "GB", "Europe", 25000, 23000, "Constitutional Monarchy", "Queen Elizabeth II",
                1709, 79, "England", "England", 55600000, "Western Europe", 130395);
        Region region = new Region("Western Europe");
        region.getCountryList().add(country1);
        region.getCountryList().add(country2);
        country1.calculateUrbanPop();
        country2.calculateUrbanPop();
        Collections.sort(region.getCountryList(), Collections.reverseOrder());
        sanityCheck.listCountriesInRegion(region);
        System.out.println('\n');
    }

    /*
    Tests for listNCountriesInRegion().
     */

    @Test
    void listNCountriesInRegionNull()
    {
        System.out.println("Output for test listNCountriesInRegionNull: " + '\n');
        System.out.println("Test listNCountriesInRegionNull:");
        sanityCheck.listNCountriesInRegion(2, null);
        System.out.println('\n');
    }

    @Test
    void listNCountriesInRegionCountryListEmpty()
    {
        System.out.println("Output for test listNCountriesInRegionCountryListEmpty: " + '\n');
        ArrayList<Country> countryList = new ArrayList<Country>();
        Region region = new Region();
        region.setCountryList(countryList);
        sanityCheck.listNCountriesInRegion(2, region);
        System.out.println('\n');
    }

    @Test
    void listNCountriesInRegionCountryListNull()
    {
        System.out.println("Output for test listNCountriesInRegionCountryListNull: " + '\n');
        Region region = new Region();
        region.setCountryList(null);
        sanityCheck.listNCountriesInRegion(2, region);
        System.out.println('\n');
    }

    @Test
    void listNCountriesInRegionCountryListContainsNull()
    {
        System.out.println("Output for test listNCountriesInRegionCountryListContainsNull: " + '\n');
        Region region = new Region();
        ArrayList<Country> countryList = new ArrayList<Country>();
        countryList.add(null);
        region.setCountryList(countryList);
        sanityCheck.listNCountriesInRegion(2, region);
        System.out.println('\n');
    }

    @Test
    void listNCountriesInRegionNLessThan1()
    {
        System.out.println("Output for test listNCountriesInRegionNLessThan1: " + '\n');
        Country country1 = new Country(001, "GBR", "GB", "Europe", 10000, 20000, "Constitutional Monarchy", "Queen Elizabeth II", 2020, 74, "Alba",
                "Scotland", 6000000, "Western Europe", 80077);
        Country country2 = new Country(001, "GBR", "GB", "Europe", 25000, 23000, "Constitutional Monarchy", "Queen Elizabeth II",
                1709, 79, "England", "England", 55600000, "Western Europe", 130395);
        Region region = new Region("Western Europe");
        region.getCountryList().add(country1);
        region.getCountryList().add(country2);
        country1.calculateUrbanPop();
        country2.calculateUrbanPop();
        Collections.sort(region.getCountryList(), Collections.reverseOrder());
        sanityCheck.listNCountriesInRegion(-1, region);
        System.out.println('\n');
    }

    @Test
    void listNCountriesInRegionNGreaterThanCountries()
    {
        System.out.println("Output for test listNCountriesInRegionNGreaterThanCountries: " + '\n');
        Country country1 = new Country(001, "GBR", "GB", "Europe", 10000, 20000, "Constitutional Monarchy", "Queen Elizabeth II", 2020, 74, "Alba",
                "Scotland", 6000000, "Western Europe", 80077);
        Country country2 = new Country(001, "GBR", "GB", "Europe", 25000, 23000, "Constitutional Monarchy", "Queen Elizabeth II",
                1709, 79, "England", "England", 55600000, "Western Europe", 130395);
        Region region = new Region("Western Europe");
        region.getCountryList().add(country1);
        region.getCountryList().add(country2);
        country1.calculateUrbanPop();
        country2.calculateUrbanPop();
        Collections.sort(region.getCountryList(), Collections.reverseOrder());
        sanityCheck.listNCountriesInRegion(3, region);
        System.out.println('\n');
    }

    @Test
    void listNCountriesInRegion()
    {
        System.out.println("Output for test listNCountriesInRegion: " + '\n');
        Country country1 = new Country(001, "GBR", "GB", "Europe", 10000, 20000, "Constitutional Monarchy", "Queen Elizabeth II", 2020, 74, "Alba",
                "Scotland", 6000000, "Western Europe", 80077);
        Country country2 = new Country(001, "GBR", "GB", "Europe", 25000, 23000, "Constitutional Monarchy", "Queen Elizabeth II",
                1709, 79, "England", "England", 55600000, "Western Europe", 130395);
        Region region = new Region("Western Europe");
        region.getCountryList().add(country1);
        region.getCountryList().add(country2);
        country1.calculateUrbanPop();
        country2.calculateUrbanPop();
        Collections.sort(region.getCountryList(), Collections.reverseOrder());
        sanityCheck.listNCountriesInRegion(2, region);
        System.out.println('\n');
    }
}
