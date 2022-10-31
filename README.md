# fleet-car-management
Fleet car management - backend App

### Solution code structure:

- `com/happyfleet/app/core` : most common abstract classes used for domain model
- `com/happyfleet/app/api/common` : common classes related to the API implementation
- com/happyfleet/app/api/cars` : classes related to the Car REST controller and related classes
  `
### Tech stack:

- Java 11
- SpringBoot
    - JPA
    - Lombok
- MySQL
- Postman

### Starting App
- Run the `docker-compose.yml` to start MySQL server at port 3306.
- Build Web app with the command:
    - `mvnw package`
- Run Web app with the command which will start Rest Api under port 8080:
    - `java -jar target/Fleet-Car-Management-0.0.1-SNAPSHOT.jar`
    - check URL at browser [http://localhost:8080/api/cars](http://localhost:8080/api/cars)

### Testing API calls with Postman:

Please import the file `FleetCarManagementApplication.postman_collection.json` to the [Postman](https://www.postman.com/downloads/) to be able to run following REST calls:
- Get All Cars
- Get Car by ID
- Create Car
