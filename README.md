# group-2-project
Project repository for Software Engineering Methods Group 2.

- Master Build Status [![Build Status](https://travis-ci.org/TDiggens/group-2-project.svg?branch=master)](https://travis-ci.org/TDiggens/group-2-project)
- Develop Build Status [![Build Status](https://travis-ci.org/TDiggens/group-2-project.svg?branch=develop)](https://travis-ci.org/TDiggens/group-2-project)
- License [![LICENSE](https://img.shields.io/github/license/TDiggens/group-2-project.svg?style=flat-square)](https://github.com/TDiggens/group-2-project/blob/master/LICENSE)
- Release [![Releases](https://img.shields.io/github/release/TDiggens/group-2-project/all.svg?style=flat-square)](https://github.com/TDiggens/group-2-project/releases)

# Vision Statement
## Purpose
The purpose of the application is to produce various population data analysis reports for an organisation. The system will primarily be used by Data Analysts, Research Associates and Senior Research Associates, to correlate reports from population data stored in an existing SQL database.
## Solution Overview
The application will provide a front-end to the population information database with the ability to produce various reports pulling information on the population of the world, country, cities and capital cities. This will allow the analysis team to be more efficient in processing population data and provide team members quickly with reports containing information about the population under various scenarios.
## User Description
The users of the application will mainly be the Research team consisting of Data Analysts, Research Associates and Senior Research Associates who have limited technical knowledge and require various population data reports to be produced. The population data reports with be able to be subdivided into worldwide data reports, continent data reports, regional data reports, country data reports, district data reports, city and capital city data reports.   The Research team currently work via web interfaces, although a desktop application is a possible work-around.
## Features

## Reqirements Met

24 requirements of 32 have been implemented, which is 75%.

| ID    |Name                                                                           | Met   | Screenshot |
| --- | --- | --- | --- |        
| 1     |All the countries in the world organised by largest population to smallest.                              | Yes   |            |
| 2     |All the countries in a continent organised by largest population to smallest.                            | Yes   |            |
| 3     |All the countries in a region organised by largest population to smallest.                               | Yes   |            |
| 4     |The top N populated countries in the world where N is provided by the user.                              | Yes   |            |
| 5     |The top N populated countries in a continent where N is provided by the user.                            | Yes   |            |
| 6     |The top N populated countries in a region where N is provided by the user.                               | Yes   |            |
| 7     |All the cities in the world organised by largest population to smallest.                                 | Yes   |            |
| 8     |All the cities in a continent organised by largest population to smallest.                               | Yes   |            |
| 9     |All the cities in a region organised by largest population to smallest.                                  | Yes   |            |
| 10    |All the cities in a country organised by largest population to smallest.                                 | Yes   |            |
| 11    |All the cities in a district organised by largest population to smallest.                                | Yes   |            |
| 12    |The top N populated cities in the world where N is provided by the user.                                 | Yes   |            |
| 13    |The top N populated cities in a continent where N is provided by the user.                               | Yes   |            |
| 14    |The top N populated cities in a region where N is provided by the user.                                  | Yes   |            |
| 15    |The top N populated cities in a country where N is provided by the user.                                 | Yes   |            |
| 16    |The top N populated cities in a district where N is provided by the user.                                | Yes   |            |
| 17    |All the capital cities in the world organised by largest population to smallest.                         | Yes   |            |
| 18    |All the capital cities in a continent organised by largest population to smallest.                       | Yes   |            |
| 19    |All the capital cities in a region organised by largest to smallest.                                     | Yes   |            |
| 20    |The top N populated capital cities in the world where N is provided by the user.                         | Yes   |            |
| 21    |The top N populated capital cities in a continent where N is provided by the user.                       | Yes   |            |
| 22    |The top N populated capital cities in a region where N is provided by the user.                          | Yes   |            |
| 23    |The population of people, people living in cities, and people not living in cities in each continent.    | Yes   |            |
| 24    |The population of people, people living in cities, and people not living in cities in each region.       | Yes   |            |
| 25    |The population of people, people living in cities, and people not living in cities in each country.      | Yes   |            |
| 26    |The population of the world.                                                                             | Yes   |            |
| 27    |The population of a continent.                                                                           | Yes   |            |
| 28    |The population of a region.                                                                              | Yes   |            |
| 29    |The population of a country.                                                                             | Yes   |            |
| 30    |The population of a district.                                                                            | Yes   |            |
| 31    |The population of a city.                                                                                | Yes   |            |
| 32    |number of people who speak Chinese, English, Hindi, Spanish, Arabic from greatest number to smallest, including the percentage of the world population                                      | Yes   |            |




### Produce Population Reports 

The Application will allow Data Analysts, Research Associates and Senior Research Associates to generate various population reports. Generating individual reports on the world and refined to reports the population of every continent, region, country, district and city.

### Produce Country Reports

The Application will allow Data Analysts to generate various population reports cross referenced with individual countries.

These reports will be further refined to countries of the world, or specific regions or continents within the world. The Analyst can further generate the top number of countries within these specifications where the analyst decides on how many countries to be printed on the report. 

### Produce City Reports

The Application will allow Data Analysts to generate various population reports cross referenced with individual cities.

These reports will be further refined to all the cities of the world, or of a specific region, continents, country or district within the world. The Analyst can further generate the top populated number of cities within these specifications where the analyst decides on how many cities to be printed on the individual report. 

### Produce Capital City Reports

The Application will allow Data Analysts to generate various population reports cross referenced with individual capital cities.

These reports will be further refined to all the capital cities of the world, or of a specific region, continents, country or district within the world. The Analyst can further generate the top populated number of capital cities within these specifications where the analyst decides on how many capital cities to be printed on the individual report. 

### Produce City or Rural living Reports  

The Application will allow Data Analysts to generate reports based on population numbers of people living either cities or rurally in every continent, region and country.

### Produce Language Reports  

The Application will allow Data Analysts, Research Associates and Senior Research Associates to generate reports on how many people in the entire world speak Chinese, English, Hindi Spanish and Arabic

## Tasks
Each _**population report**_ must contain:
* The name of the continent/region/country.
* The total population of the continent/region/country.
* The total population of the continent/region/country living in cities (including a %).
* The total population of the continent/region/country not living in cities (including a %).


Reports labeled _**country report**_ require the columns:

* Code
* Name 
* Continent 
* Region
* Population 
* Capital

Reports labeled _**city report**_ require the columns:

* Name
* Country 
* District 
* Population

Reports labeled _**capital city report**_ require the columns:

* Name 
* Country 
* Population






