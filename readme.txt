Instructions and Key points of Airport Info SpringBoot application

1) Required version of JAVA -> JDK 1.8 
2) If you run as standalone application, do a maven clean build using >mvn install 
3) java -jar airport-info-service-0.0.1-SNAPSHOT.jar
4) Use urls.html in the root directory to view 
	i) Swagger UI
	ii) Embedded H2 database Console view
	iii) Java code coverage report
5) Use swagger to use load and airport apis and test it
6) This application is built with spring boot embedded in memory H2 Database. 
	Hence, the state is maintained only until the application is up and running. 
    You can change the datasource to external database for persistance.

7) As there are 50000 records of airports, I have used Batch processing to insert the data into in memory database.
   Hence you can trigger http://localhost:8080/load API to load all the data into the database initially.

8) Once the data is loaded onto the DB, you can start using the actual APIs to find Airports by name and Iata code