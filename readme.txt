under java-assessment-jimmy folder
-- run the following to compile and test
mvn compile && package
mvn test
-- run the following to start the server
java -jar target/orders-0.0.1-SNAPSHOT.jar
-- in a web browser, run the following to see results, four urls corresponding to four criterion
http://localhost:8088/search/1
http://localhost:8088/search/2
http://localhost:8088/search/3
http://localhost:8088/search/4
