# Parking Demo
This is a simple parking application that allows to retrieve data of available parking in a particular region.
# Overview
The project is called "demo" and is a Java-based application that allows to retrieve data of available parking in a particular region. The project uses Spring Boot, a Java web framework, to handle web requests.

# Getting Started
To get started with the application, follow these steps:

# Prerequisites
Before you can run the application, you will need to have the following software installed on your machine:

Java Development Kit (JDK) 11 or higher
Apache Maven 3.6.3 or higher
Installation
To install and run the application, follow these steps:

Clone the project repository to your local machine:
*   git clone https://github.com/ParkingDemo/demo.git

Navigate to the project directory:

*   cd demo

Build the application using the following command:

*   mvn clean package

Application was started in intellij IDE.

# Usage
The application allows to retrieve Data depending on actual position and dataSetId ( region ex : mobilite-parkings-grand-poitiers-donnees-metiers)

Swagger is provided for usage : http://localhost:8222/swagger-ui/index.html#/Parking/getListOfParking

no auth is provided for the moment, need to be enhanced

# Enhancement

Due to lack of data (no url for other format like csv ...), this application was made only with jsonParser, but a parsing method was made on csvParser class for future enhancement.

# Built With
The application was built using the following technologies:

Java 11
Spring Boot 2.5.2
Apache Maven 3.6.3

# Authors
The application was developed by Yacine Hiadsi.