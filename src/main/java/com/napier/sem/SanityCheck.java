package com.napier.sem;
import java.sql.*;
import java.util.*;

public class SanityCheck
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;
    private World world;

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    enum isOfficialLanguage {
        T,
        F
    }

    public static void main(String[] args)
    {
        //Setup
        SanityCheck sanity = new SanityCheck();
        sanity.connect();
        sanity.setWorld(new World());
        sanity.getWorld().setCityList(sanity.generateCityList());
        sanity.getWorld().setDistrictList(sanity.generateDistrictList());
        sanity.getWorld().setCountryList(sanity.generateCountryList());
        sanity.getWorld().setRegionList(sanity.generateRegionList());
        sanity.getWorld().setContinentList(sanity.generateContinentList());
        sanity.calculateCountryUrbanPops();
        sanity.generateCountryLanguages();
        sanity.getWorld().calculatePopulation();
        sanity.getWorld().setLanguageList(sanity.generateWorldLanguages());
        sanity.disconnect();

        //Methods to actually produce reports
        //sanity.listCountriesByPopulation();

        //sanity.listCitiesInCountry(sanity.world.getCountryList().get(1));
        //sanity.listCountriesInContinent(sanity.world.getContinentList().get(4));
        sanity.testData();

    }

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 30;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(5000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }


    /* Method to read all the city records from the database, create City objects for them and add
    them to a list
     */
    public ArrayList<City> generateCityList(){
        ArrayList<City> cityList = new ArrayList<City>();
        try
        {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT * FROM city ORDER BY population DESC";
            ResultSet rSet = stmt.executeQuery(strSelect);
            while(rSet.next())
            {
                City city = new City(rSet.getString("CountryCode"), rSet.getString("District"), rSet.getInt("ID"),
                        rSet.getString("Name"), rSet.getInt("Population"));

                cityList.add(city);
            }
        }
        catch(Exception e)
        {
            return null;
        }
        return cityList;
    }

    /* Method to read all the country records from the database, create Country objects
     for each one and add them to a list
    */
    public ArrayList<Country> generateCountryList(){
        /*list to hold the new country objects */
        ArrayList<Country> countryList = new ArrayList<Country>();
        /* try/catch used to handle any exceptions arising from the SQL statements
         */
        try
        {
            /*create a new SQL statement that selects all records from the country table in
            world.sql
             */
            Statement stmt = con.createStatement();
            String strSelect = "SELECT * FROM country ORDER BY population DESC";
            ResultSet rSet = stmt.executeQuery(strSelect);
            /* for as long as there are more records in the returned result set, create new country
            objects and appropriately assign data from each columns to its' instance variables.
             */
            while(rSet.next()) {
                Country country =
                        new Country(rSet.getInt("Capital"),
                        rSet.getString("Code"),
                        rSet.getString("Code2"),
                        rSet.getString("Continent"),
                        rSet.getDouble("GNP"),
                        rSet.getDouble("GNPOld"),
                        rSet.getString("GovernmentForm"),
                        rSet.getString("HeadOfState"),
                        rSet.getInt("IndepYear"),
                        rSet.getDouble("LifeExpectancy"),
                        rSet.getString("LocalName"),
                        rSet.getString("Name"),
                        rSet.getInt("Population"),
                        rSet.getString("Region"),
                        rSet.getDouble("SurfaceArea"));
                        countryList.add(country);
            }
            /* Iterate through the new countries and the existing cities list,
            match the capital cities to each country, assign a reference to
            each Country object to any city within that country, add the city's district object
            to the appropriate country's district list, and assign a reference to the correct country
            object to that district's country field.
             */
            for(Country country : countryList){
                for(City city : getWorld().getCityList()){
                    if(city.getId() == country.getCapitalCode()){
                        country.setCapital(city);
                        city.setIsCapital(true);
                    }
                    if(city.getCountryCode().equals(country.getCode())){
                        city.setCountry(country);
                        if(!country.getDistrictList().contains(city.getDistrictObject())) {
                            country.getDistrictList().add(city.getDistrictObject());
                        }
                        city.getDistrictObject().setCountry(country);
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        /* return the completed country list for external use */
        return countryList;
    }

    /* Method to read unique district names from the city table, create District objects
     for each one and add them to a list
    */
    public ArrayList<District> generateDistrictList() {
        /* list for District objects */
        ArrayList<District> districtList = new ArrayList<District>();
        /* try catch structure used for any potential SQL exceptions */
        try {
            /* SQL statement selects only records with unique values in the district column */
            Statement stmt = con.createStatement();
            String strSelect = "SELECT DISTINCT district FROM city ";
            ResultSet rSet = stmt.executeQuery(strSelect);
            /* iterate over the returned rows and create a new District object for each one */
            while(rSet.next()){
                District district = new District(rSet.getString("District"));
                districtList.add(district);
            }
            /*Iterate through each city in the city list and each district in the new district list,
            add appropriate cities to each district's city list, and assign the right district object to
            the city's districtObject field
            */
            for(City city : getWorld().getCityList()){
                for(District district : districtList){
                    district.calculatePopulation();
                    if(city.getDistrict().equals(district.getName())){
                        district.getCityList().add(city);
                        city.setDistrictObject(district);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        /* return the completed district list for external use */
        return districtList;
    }

    /* Method to read unique region names from the country table, create Region objects
     for each one and add them to a list
    */
    public ArrayList<Region> generateRegionList(){
        /* list for newly created Region objects */
        ArrayList<Region> regionList = new ArrayList<Region>();
        /* try/catch structure used for any potential SQL related exceptions */
        try{
            Statement stmt = con.createStatement();
            String strSelect = "SELECT DISTINCT region FROM country";
            ResultSet rSet = stmt.executeQuery(strSelect);
            /*Iterate through the returned rows and create a new Region object for each one*/
            while(rSet.next()){
                Region region = new Region(rSet.getString("Region"));
                regionList.add(region);
            }
            /* For each region in the new list, iterate through country list and add the appropriate
            countries to the region's country list, assign a reference to the appropriate region object to each country within it,
            then calculate that region's population
             */
            for(Region region : regionList){
                for(Country country : getWorld().getCountryList()){
                    if(country.getRegion().equals(region.getName())){
                        region.getCountryList().add(country);
                        country.setRegionObject(region);
                    }
                }
                region.calculatePopulation();
                region.calculateUrbanPopulation();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        /* return the created region list for external use*/
        return regionList;
    }

    /* Method to read unique continent names from the country table, create Continent objects
     for each one and add them to a list
    */
    public ArrayList generateContinentList(){
        /* list for newly created Continent objects */
        ArrayList<Continent> continentList = new ArrayList<Continent>();
        /* try/catch to handle exceptions */
        try{
            /* SQL statement returns only rows with unique values in the continent column
            from country table
             */
            Statement stmt = con.createStatement();
            String strSelect = "SELECT DISTINCT continent FROM country";
            ResultSet rSet = stmt.executeQuery(strSelect);
            /* Iterate through the returned rows and make a new Continent object to represent each one
             */
            while(rSet.next()){
                Continent continent = new Continent(rSet.getString("Continent"));
                continentList.add(continent);
            }
            /* For each continent in the newly created list, Iterate through the existing region list , add the appropriate
            regions to that each continent's region list, set the continent field for each region, and calculate the
            population of each continent.
             */
            for(Continent continent : continentList){
                for(Region region : getWorld().getRegionList()){
                    /* check that region's country list is not empty and if it isnt, use the continent
                    field of the first country in the list to establish what continent the region is in
                     */
                    if(!region.getCountryList().isEmpty() &&
                            region.getCountryList().get(0).getContinent().equals(continent.getName())){
                        /* add the region to the right continent's region list and set the continent field
                        for the Region object.
                         */
                        continent.getRegionList().add(region);
                        region.setContinent(continent);
                    }
                    continent.calculatePopulation();
                    continent.calculateUrbanPopulation();
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        /* return the created region list for external use*/
        return continentList;
    }

    /* small method to loop through the country list and call each ones calculateUrbanPop method.
     */
    public void calculateCountryUrbanPops(){
        for(Country country : getWorld().getCountryList()){
            country.calculateUrbanPop();
        }
    }

    /* Method to read all the languages from the countryLanguage table, create a CountryLanguage object
    each one and pair them with the appropriate countries in the existing country list.
     */
    public void generateCountryLanguages(){
        /* list to hold the new CountryLanguage objects */
        ArrayList<CountryLanguage> countryLanguageList = new ArrayList<CountryLanguage>();
        /* try/catch to deal with any potential exceptions arising from SQL statement */
        try {
            /* simple SQL statement to select all rows from the CountryLanguage table */
            Statement stmt = con.createStatement();
            String strSelect = "SELECT * FROM countrylanguage";
            ResultSet rSet = stmt.executeQuery(strSelect);
            /* Iterate over returned rows, create a new CountryLanguage object to represent each one,
                set their instance variables appropriately and add them to the list
             */
            isOfficialLanguage isOfficial;
            while(rSet.next())
            {
                    CountryLanguage countryLanguage = new CountryLanguage();
                    countryLanguage.setName(rSet.getString("Language"));
                    countryLanguage.setCountryCode(rSet.getString("CountryCode"));
                    isOfficial = isOfficialLanguage.valueOf(rSet.getString("IsOfficial"));
                    if (isOfficial.name().equals("T"))
                    {
                        countryLanguage.setOfficial(true);
                    }
                    else
                    {
                        countryLanguage.setOfficial(false);
                    }
                    countryLanguage.setPercentageOfSpeakers(rSet.getDouble("Percentage"));
                    countryLanguageList.add(countryLanguage);
            }
            /* iterate over the country list and the new CountryLanguage list and add each CountryLanguage object
            to the appropriate country's language list by matching them via getCountryCode and getCode. Also
            use the country's population and percentage field of the CountryLanguage
            to calculate the number of speakers of that language in the country.
             */
            System.out.println("Here");
            for(CountryLanguage countryLanguage : countryLanguageList)
            {
                for(Country country : getWorld().getCountryList())
                {
                    if(countryLanguage.getCountryCode().equals(country.getCode()))
                    {
                        country.getLanguageList().add(countryLanguage);
                        countryLanguage.setNumberOfSpeakers(country.getPopulation()
                                *(countryLanguage.getPercentageOfSpeakers()/100));
                    }
                    Collections.sort(country.getLanguageList(), Collections.reverseOrder());
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            /* nothing to return because there is no need to store the list of languages outside of their
            specific countries.
             */
        }
    }
    /* Method to read every unique language from the countrylanguage table, iterate through the country list and sum the speakers of
    each language to find the total worldwide speakers of the languages and the percentage of the global population.
     */
    public ArrayList<WorldLanguage> generateWorldLanguages(){
        ArrayList<WorldLanguage> worldLanguageList = new ArrayList<WorldLanguage>();
        try{
            Statement stmt = con.createStatement();
            String strSelect = "SELECT DISTINCT language FROM countrylanguage";
            ResultSet rSet = stmt.executeQuery(strSelect);

            while(rSet.next()){
                WorldLanguage worldLanguage = new WorldLanguage(rSet.getString("Language"));
                worldLanguageList.add(worldLanguage);
            }
            /* For each world language in the newly created list, iterate through every country in country list and every country language
            in the language list of each country. When a country language matches with a world language by name, add the number of speakers
            in that country to the total speakers the world language has so far. Finally, calculate the percentage of the world population
            that speakers each language
             */
            for(WorldLanguage worldLanguage : worldLanguageList){
                for(Country country : getWorld().getCountryList()){
                    for(CountryLanguage countryLanguage : country.getLanguageList()){
                        if(countryLanguage.getName().equals(worldLanguage.getName())){
                            worldLanguage.setNumberOfSpeakers(worldLanguage.getNumberOfSpeakers() + countryLanguage.getNumberOfSpeakers());
                        }
                    }
                }
                worldLanguage.setPercentageOfSpeakers((worldLanguage.getNumberOfSpeakers()*100)/ getWorld().getPopulation());
            }
            Collections.sort(worldLanguageList, Collections.reverseOrder());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return worldLanguageList;
    }



    /* Method to list all cities in a country ranked by population, implementation for
    issue #24 on github.
    */
    public void listCitiesInCountry(Country country)
    {
        //Check if country Exists
        if(country == null)
        {
            System.out.println("No country found" + '\n');
            return;
        }
        //check country's district list for null districts, remove them if so.
        //make list of indexes where the null values occur
        List<Integer> nullIndices = new ArrayList<Integer>();
        int index;
        for(int i = 0; i < country.getDistrictList().size(); i++)
        {
            if(country.getDistrictList().get(i) == null)
            {
                //add index of the found null value to index list
                nullIndices.add(i);
            }
        }
        //go through the indexes where null values occur and remove the null objects from district List.
        for(Iterator<Integer> iterator = nullIndices.iterator(); iterator.hasNext();)
        {
            index = iterator.next();
            country.getDistrictList().remove(index);
        }
        //using similar process, check each district in the country for null cities.
        nullIndices = new ArrayList<Integer>();
        for(District district : country.getDistrictList())
        {
            for(int i = 0; i < district.getCityList().size(); i++)
            {
                if(district.getCityList().get(i) == null)
                {
                    nullIndices.add(i);
                }
            }
            for(Iterator<Integer> iterator = nullIndices.iterator(); iterator.hasNext();)
            {
                index = iterator.next();
                district.getCityList().remove(index);
            }
        }
        for(District district : country.getDistrictList())
        {
            district.printCityList(district.getCityList().size());
        }
    }

    /*
    Method to return the top N countries by population in the world and print their reports, implementation for issue
    #5 on github
     */
    public List<Country> listNCountriesInWorld(int n)
    {
        //Check that world country list exists
        if(getWorld().getCountryList() == null)
        {
            System.out.println("No country list found.");
            return null;
        }
        //Check that n is a positive integer lesser than the number of countries in world
        if(n < 1)
        {
            System.out.println("Please enter a positive integer");
            return null;
        }
        //ensure n is smaller than number of countries in world country list
        if(n > getWorld().getCountryList().size())
        {
            n = getWorld().getCountryList().size();
        }

        System.out.println(world.getCountryList().size());
        //Check that the portion of the world country list does not contain null values, and if it does, remove them
        List<Integer> nullIndicies = new ArrayList<>();
        for(int i = 0; i < world.getCountryList().size(); i++)
        {
            if(getWorld().getCountryList().get(i) == null)
            {
                nullIndicies.add(i);
            }
        }
        for(Iterator<Integer> iterator = nullIndicies.iterator(); iterator.hasNext();)
        {

            getWorld().getCountryList().remove((int)iterator.next());
        }
        System.out.println(world.getCountryList().size());



        //Check that world country list is not empty
        if(getWorld().getCountryList().size() == 0)
        {
            System.out.println("Country list is empty");
            return null;
        }

        //ensure n is still smaller than number of countries in list after possible null values removed.
        if(n > getWorld().getCountryList().size())
        {
            n = world.getCountryList().size();
        }

        if(n == 1)
        {
            System.out.println("Most populous country in the world: " + '\n' + '\n');
        }
        else
        {
            System.out.println("Most populous " + n + " countries in the world:" + '\n' + '\n');
        }

        for(int i = 0; i < n; i++)
        {
            System.out.println(world.getCountryList().get(i).report());
        }
        List<Country> countryList = world.getCountryList().subList(0,n);
        return countryList;
    }

    /* Method to list all countries in world ranked by population, implementation for
   issue #2 on github. Simply iterates over the country list and calls report() for each one.
   */
    public void listCountriesInWorld(){
        listNCountriesInWorld(world.getCountryList().size());
    }

    /* Method to list all countries in a continent ranked by population, implementation for
    issue #3 on github.
    */
    void listCountriesInContinent(Continent continent)
    {
        //check continent exists
        if(continent == null)
        {
            System.out.println("Continent not found");
            return;
        }
        //check continent has valid region list
        if(continent.getRegionList() == null)
        {
            System.out.println("Continent has invalid/no region list");
            return;
        }
        //check region list not empty
        if(continent.getRegionList().isEmpty())
        {
            System.out.println("Continent's region list is empty");
            return;
        }
        //check continent's region list for null regions, remove them if so.
        //make list of indexes where the null values occur
        List<Integer> nullIndices = new ArrayList<Integer>();
        int index;
        for(int i = 0; i < continent.getRegionList().size(); i++)
        {
            if(continent.getRegionList().get(i) == null)
            {
                //add index of the found null value to index list
                nullIndices.add(i);
            }
        }
        //go through the indexes where null values occur and remove the null objects from region List.
        for(Iterator<Integer> iterator = nullIndices.iterator(); iterator.hasNext();)
        {
            index = iterator.next();
            continent.getRegionList().remove(index);
        }
        int n = 0;
        for(Region region : continent.getRegionList())
        {
            n += region.getCountryList().size();
        }
        listNCountriesInContinent(continent, n);
    }

    /*
    Method to return a list of and print the reports of the top N countries by population in a continent. Implementation
    for issue #6 on github
     */
    public ArrayList<Country> listNCountriesInContinent(Continent continent, int n)
    {
        ArrayList<Country> countryList = new ArrayList<>();
        //Check if continent Exists
        if(continent == null)
        {
            System.out.println("No continent found" + '\n');
            return null;
        }
        //Check if Continent's region list is empty
        if(continent.getRegionList().size() == 0)
        {
            System.out.println("No countries found in " + continent.getName() + '\n');
        }

        //check continent's region list for null regions, remove them if so.
        //make list of indexes where the null values occur
        List<Integer> nullIndices = new ArrayList<Integer>();
        int index;
        for(int i = 0; i < continent.getRegionList().size(); i++)
        {
            if(continent.getRegionList().get(i) == null)
            {
                //add index of the found null value to index list
                nullIndices.add(i);
            }
        }
        //go through the indexes where null values occur and remove the null objects from region List.
        for(Iterator<Integer> iterator = nullIndices.iterator(); iterator.hasNext();)
        {
            index = iterator.next();
            continent.getRegionList().remove(index);
        }
        //using similar process, check each region in the continent for null countries.
        nullIndices = new ArrayList<Integer>();
        for(Region region : continent.getRegionList())
        {
            for(int i = 0; i < region.getCountryList().size(); i++)
            {
                if(region.getCountryList().get(i) == null)
                {
                    nullIndices.add(i);
                }
            }
            for(Iterator<Integer> iterator = nullIndices.iterator(); iterator.hasNext();)
            {
                index = iterator.next();
                region.getCountryList().remove(index);
            }
        }
        //check if all regions in continent have empty country lists
        boolean allRegionsEmpty = true;
        for(Region region : continent.getRegionList())
        {
            if(region.getCountryList().size() > 0)
            {
                allRegionsEmpty = false;
                break;
            }
        }
        if(allRegionsEmpty)
        {
            System.out.println("All regions in " + continent.getName() + " have no countries in them");
        }
        for(Region region : continent.getRegionList())
        {
            countryList.addAll(region.getCountryList());
        }
        countryList.sort(Collections.reverseOrder());
        //ensure n is positive and not greater than number of contries
        if(n < 1)
        {
            System.out.println("Please enter a positive integer");
            return null;
        }
        if(n > countryList.size())
        {
            n = countryList.size();
        }

        System.out.println("Top " + n + " most populous countries in " + continent.getName() + '\n');
        for(int i = 0; i < n; i++)
        {
            countryList.get(i).report();
        }
        return countryList;
    }

    /*
    Method to list all countries in a region, sorted by population, implementation for issue #4
     */
    void listCountriesInRegion(Region region)
    {
        //check that region exists
        if(region == null)
        {
            System.out.println("Region not found");
            return;
        }
        //check countryList not null
        if(region.getCountryList() == null)
        {
            System.out.println("Region has invalid/no country list");
            return;
        }
        //check country list not empty
        if(region.getCountryList().isEmpty())
        {
            System.out.println("Region's country list empty");
            return;
        }
        listNCountriesInRegion(region, region.getCountryList().size());
    }

    /*
    Method to return the top N counties ranked by population in a region and print their reports, implementation for
    issue #7
     */
    public ArrayList<Country> listNCountriesInRegion(Region region, int n)
    {
        //Check if region Exists
        if(region == null)
        {
            System.out.println("No region found" + '\n');
            return null;
        }
        //Check that region's countryList is not null.
        if(region.getCountryList() == null)
        {
            System.out.println("Region has no/invalid country list");
            return null;
        }
        //check region's country list for null countries, remove them if so.
        //make list of indexes where the null values occur
        List<Integer> nullIndices = new ArrayList<Integer>();
        int index;
        for(int i = 0; i < region.getCountryList().size(); i++)
        {
            if(region.getCountryList().get(i) == null)
            {
                //add index of the found null value to index list
                nullIndices.add(i);
            }
        }
        //go through the indexes where null values occur and remove the null objects from country List.
        for(Iterator<Integer> iterator = nullIndices.iterator(); iterator.hasNext();)
        {
            index = iterator.next();
            region.getCountryList().remove(index);
        }
        ArrayList<Country> countryList = new ArrayList<>();
        //check that n is a positive integer
        if(n < 1)
        {
            System.out.println("Please enter a positive integer");
            return null;
        }
        //check that n is not larger than the number of countries in the region
        if(n > region.getCountryList().size())
        {
            n = region.getCountryList().size();
        }
        for(int i = 0; i < n; i++)
        {
            countryList.add(region.getCountryList().get(i));
        }
        region.printCountryList(n);
        return countryList;
    }
    /*
    Method to return a list of all cities in a district ordered by population, implementation for issue #25 on github
     */
    public void listCitiesInDistrict(District district){
        //check that district exists
        if(district == null)
        {
            System.out.println("No district found");
            return;
        }
        //check that district has city list
        if(district.getCityList() == null)
        {
            System.out.println("District has no/invalid city list");
            return;
        }
        listNCitiesInDistrict(district, district.getCityList().size());
    }
    /*
    Method to return a list of the top N most populous cities in a district, implementation for issue
    #30 on github
     */
    public ArrayList<City> listNCitiesInDistrict(District district, int n)
    {
        //check that district exists
        if(district == null)
        {
            System.out.println("No district found");
            return null;
        }
        //check that district has city list
        if(district.getCityList() == null)
        {
            System.out.println("District has no/invalid city list");
        }
        //check that district's city list doesnt contain null values and remove them if it does
        List<Integer> nullIndicies = new ArrayList<>();
        for(int i = 0; i < district.getCityList().size(); i++)
        {
            if(district.getCityList().get(i) == null)
            {
                nullIndicies.add(i);
            }
        }
        for(Iterator<Integer> iterator = nullIndicies.iterator(); iterator.hasNext();)
        {
            district.getCityList().remove((int)iterator.next());
        }
        //ensure n is positive integer
        if(n<1)
        {
            System.out.println("Please enter a positive integer.");
        }
        //ensure n < number of cities in district
        if(n > district.getCityList().size())
        {
            n = district.getCityList().size();
        }
        district.printCityList(n);
        return district.getCityList();
    }

    /* method to test that the data has been loaded correctly by printing out a sampling of it
     */
    public void testData(){
        System.out.println('\n' + "Cities: " +'\n');
        for(int i = 0; i < 10; i++){
            System.out.println(getWorld().getCityList().get(i).toString());
        }
        System.out.println('\n' + "Districts: " +'\n');
        for(int i = 0; i < 10; i++){
            System.out.println(getWorld().getDistrictList().get(i).toString());
            System.out.println("Cities in " + getWorld().getDistrictList().get(i).getName() + ":");
            getWorld().getDistrictList().get(i).printCityList(10);
            System.out.println('\n');
        }
        System.out.println('\n' + "Countries: " + '\n');
        for(int i = 0; i < 10; i++){
            System.out.println(getWorld().getCountryList().get(i).toString());
            System.out.println("Districts in " + getWorld().getCountryList().get(i).getName() + ":");
            getWorld().getCountryList().get(i).printDistrictList(10);
            System.out.println('\n');
            System.out.println("Languages spoken in " + getWorld().getCountryList().get(i).getName() + ":");
            getWorld().getCountryList().get(i).printLanguageList();
            System.out.println('\n');
        }
        System.out.println('\n' + "Regions: " +'\n');
        for(int i = 0; i < getWorld().getRegionList().size(); i++){
            System.out.println(getWorld().getRegionList().get(i).toString());
            System.out.println("Countries in: " + getWorld().getRegionList().get(i).getName() + ": ");
            getWorld().getRegionList().get(i).printCountryList(10);
            System.out.println('\n');
        }
        System.out.println('\n' + "Continents: " +'\n');
        for(int i = 0; i < getWorld().getContinentList().size(); i++){
            System.out.println(getWorld().getContinentList().get(i).toString());
            System.out.println("Regions in: " + getWorld().getContinentList().get(i).getName() + ": ");
            getWorld().getContinentList().get(i).printRegionList(10);
            System.out.println('\n');
        }
        System.out.println('\n' + "World: " +'\n');
        System.out.println(getWorld().toString());
    }
}
