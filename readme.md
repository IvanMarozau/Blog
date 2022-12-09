##Usage

To start project
````
gradle clean build
cd api
gradle bootRun
````
To run tests
````
gradle clean test
Result for api:
 ./api/build/reports/tests/test/index.html
 Result for services:
 ./services/build/reports/tests/test/index.html
````

Jacoco
````
./gradlew build jacocoTestReport 
Result for api:
 ./api/build/reports/jacoco/test/html/index.html
 Result for services:
 ./services/build/reports/jacoco/test/html/index.html
````