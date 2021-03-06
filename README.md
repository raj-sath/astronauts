# Astronauts REST API

A simple REST API which provides information on astronauts. 

## Building
#### Step 1: Install Java 11
#### Step 2: Install Maven 3.8.1
#### Step 3: in command line execute the following in the project root folder
    mvn package

This will build astronauts-0.0.1-SNAPSHOT.jar file, see the logs for the location of the file. 
Should be under /project/target/.


## Installation

#### Step 1: Install Java 11
#### Step 2: Download the astronauts-0.0.1-SNAPSHOT.jar to a directory "XYZ"


## Usage

#### To run the test, execute the following line, from the location of the jar file.

    java -jar "astronauts-0.0.1-SNAPSHOT.jar" com.example.astronauts.AstronautsApplication

## API Urls

List Astronauts  ( http://server:port/api/1.0/astronaut/ )

Get Astronaut By ID ( http://server:port/api/1.0/astronaut/1 )

Health Monitoring ( http://server:port/actuator/health )

When running locally you can use localhost:8080.

Please not if the remote web service being used is unavailable due to any errors,
These REST services will return service unavailable as the http status code.


## Contributing




## License
[MIT](https://choosealicense.com/licenses/mit/)