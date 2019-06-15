Instructions and Key points of Airport Info SpringBoot application

1) Build the application with > mvn install
2) From the root directory of the project, execute "docker build --tag=airportinfo ." command.
   This will build the application and create a docker image on the local.
3) Execute "docker run -p 8080:8080 -t airportinfo" command to start the application
4) As there are 50000 records of airports, I have used Batch processing to insert the data into in memory database.
   Hence you can trigger http://localhost:8080/load API to load all the data into the database initially.
5) Once the API returns "COMPLETED", you can start using the actual APIs to find Airports by name and Iata code
6) If you run as standalone application and not on docker, do a maven clean build using 
   "mvn install" 
   "java -jar airport-info-service-0.0.1-SNAPSHOT.jar"
   Open browser and hit "http://localhost:8080/load" API to load the data
   Hit, "http://localhost:8080/airport/v1/name/tegel" to search for Airports with name
   Hit, "http://localhost:8080/airport/v1/iata/sxf" to search for Airports with IATA Code
  
7) Alternatively you can use Swagger, To open the urls, use urls.html in the root directory to view 
	i) Swagger UI
	ii) Embedded H2 database Console view
	iii) Java code coverage report
8) This application is built with spring boot embedded in memory H2 Database. 
	Hence, the state is maintained only until the application is up and running. 
    You can change the datasource to external database for persistance