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
### Produce Population Reports 

The Application will allow Data Analysts, Research Associates and Senior Research Associates to generate various population reports.

### Produce Country Reports

The Application will allow Data Analysts to generate various population reports cross referenced with individual countries.

These reports will be further refined to countries of the world, or specific regions or continents within the world. The Analyst can further generate the top number of countries within these specifications where the analyst decides on how many countries to be printed on the report. 


Each population report must contain:
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

Reports labeled _**city report**_ require the columns:

* Name 
* Country 
* Population






