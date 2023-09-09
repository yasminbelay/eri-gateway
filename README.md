# group-project-EriGateWay-Kabbee
Description:
This codebase is a Java application built using Spring Boot, which provides a robust framework for creating web applications. It focuses on user registration functionality. Below is an overview of the components and their roles:
Config (AppConfig): This class is responsible for configuring application-wide settings. It defines a ModelMapper bean, which is a tool used for mapping objects of one type to another.
Controller (UserRegistrationController): This is the entry point for handling HTTP requests related to user registration. It has an endpoint /api/v1/registration for registering users. It communicates with the service layer to perform business logic.
Repository (UserRepository): This is a Spring Data JPA repository interface, which provides an abstraction for database operations related to the User entity.
Service (UserService): This is an interface that defines the contract for user-related operations. It includes methods for saving users and checking if an email already exists.
Service Implementation (UserServiceImplementation): This class implements the UserService interface. It contains the actual logic for saving users, checking for unique emails, and using a ModelMapper for mapping DTOs to entity objects.
Exception (ResourceNotFound): This is a custom exception class used to handle situations where a requested resource is not found. It extends RuntimeException and is annotated with @ResponseStatus.
Entity (User): This class represents the structure of a user entity in the application. It includes various attributes like username, name, date of birth, gender, etc., and is annotated with JPA annotations for database integration.
DTOs (UserCreditionalDto and UserProfileDto): These are Data Transfer Objects, used for transferring data between different layers of the application. They encapsulate user information in a format that can be easily passed around.
Technology Used:
Java: The primary programming language used in this codebase.
Spring Boot: A popular Java-based framework used for building enterprise-level applications.
Spring Data JPA: Part of the Spring framework, it simplifies database access in Java applications.
Lombok: A library for reducing boilerplate code in Java. It provides annotations like @DaTa, @getter, @Setter, etc., to automatically generate methods.
ModelMapper: A Java library used for mapping objects of one type to another.

Project Structure Explanation:
src
├── main
│ ├── java
│ │ └── com
│ │ └── erigateway
│ │ └── registration
│ │ ├── config
│ │ │ └── AppConfig.java
│ │ ├── controller
│ │ │ └── UserRegistrationController.java
│ │ ├── entity
│ │ │ └── User.java
│ │ ├── entity
│ │ │ └── dto
│ │ │ ├── UserCreditionalDto.java
│ │ │ └── UserProfileDto.java
│ │ ├── exception
│ │ │ └── ResourceNotFound.java
│ │ ├── repository
│ │ │ └── UserRepository.java
│ │ ├── service
│ │ │ ├── UserService.java
│ │ │ └── UserServiceImplementation.java
│ │ └── Application.java
│ └── resources
└── test
└── java
└── com
└── erigateway
└── registration
└── controller
└── UserRegistrationControllerTest.java

src/main/java/com/erigateway/registration/config: Contains configuration classes for the application. In this case, it has AppConfig for configuring beans.
src/main/java/com/erigateway/registration/controller: Contains the controllers responsible for handling HTTP requests. In this case, it has UserRegistrationController.
src/main/java/com/erigateway/registration/entity: Contains the entity classes. In this case, it has User.
src/main/java/com/erigateway/registration/entity/dto: Contains Data Transfer Objects. In this case, it has UserCreditionalDto and UserProfileDto.
src/main/java/com/erigateway/registration/exception: Contains custom exception classes. In this case, it has ResourceNotFound.
src/main/java/com/erigateway/registration/repository: Contains repository interfaces. In this case, it has UserRepository.
src/main/java/com/erigateway/registration/service: Contains service interfaces and their implementations. In this case, it has UserService and UserServiceImplementation.
src/main/resources: Contains resource files like application properties, XML files, etc. In a real-world project, you might find application.properties or application.yml here.
src/test/java/com/erigateway/registration/controller: Contains test cases for the controllers. In this case, it could have a class like UserRegistrationControllerTest.

AppConfig (com.erigateway.registration.config)
This class is a configuration class for the Spring application. It is annotated with @configuration, indicating that it contains bean definitions. It also has @requiredargsconstructor, which generates a constructor with required arguments.
modelMapper()
Method annotated with @bean.
Returns an instance of ModelMapper, which is used for mapping between different Java objects.
This bean can be injected into other components.
UserRegistrationController (com.erigateway.registration.controller)
This class is a Spring MVC Controller responsible for handling HTTP requests related to user registration.
registerUser(UserCreditionalDto userCreditionalDto)
Annotated with @PostMapping and specifies the endpoint as "/api/v1/registration".
Takes a UserCreditionalDto object as the request body.
Calls userService.saveUser(userCreditionalDto) to register the user.
Returns a ResponseEntity with HTTP status 201 (Created) and the registered user in the response body.
UserRepository (com.erigateway.registration.repository)
This is a Spring Data JPA repository interface for interacting with the User entity.
findUserByEmail(String email)
A method signature for finding a user by their email address.
It returns an Optional, indicating that the result might be null.
UserService (com.erigateway.registration.service)
This is an interface defining the contract for user-related operations.
saveUser(UserCreditionalDto userCreditionalDto)
A method signature for saving a user with the provided UserCreditionalDto.
It returns a User object representing the saved user.
isEmailExist(String email)
A method signature for checking if an email address already exists in the database.
It returns a boolean indicating whether the email exists.
UserServiceImplementation (com.erigateway.registration.service)
This class implements the UserService interface.
saveUser(UserCreditionalDto userCreditionalDto)
Overrides the saveUser method from the interface.
Checks if the provided email address is unique by calling ensureEmailIsUnique.
Maps the UserCreditionalDto to a User entity using a ModelMapper.
Saves the user using the userRepository.save method and returns the saved user.
ensureEmailIsUnique(String email)
Checks if the provided email address is already associated with an existing user.
Throws a ResourceNotFound exception if the email address is already in use.
isEmailExist(String email)
Implements the method from the interface.
Checks if the provided email address already exists in the database.
ResourceNotFound (com.erigateway.registration.exception)
This is a custom exception class that extends RuntimeException.
ResourceNotFound(String message)
Constructor that takes a message and passes it to the superclass constructor.
The message is used to provide information about the exception.
UserCreditionalDto and UserProfileDto (com.erigateway.registration.entity.dto)
These are DTO (Data Transfer Object) classes used for transferring data between different layers of the application.
User (com.erigateway.registration.entity)
This is an entity class representing a user in the application.
It has fields such as UserId, Username, firstName, lastName, dob (Date of Birth), gender, country, address, phoneNumber, email, and password.
It is annotated with JPA annotations (@entity, @id, @GeneratedValue, @table, @column) to define its relationship with the database.
Summary
This codebase is a Spring application for user registration. It follows the MVC architecture, where the Controller (UserRegistrationController) handles HTTP requests, the Service (UserService) contains business logic, and the Repository (UserRepository) manages database operations. The ModelMapper is used for mapping data between DTOs and entities, and custom exceptions (ResourceNotFound) are used for handling exceptions. The entity User represents user data, and there are DTOs (UserCreditionalDto and UserProfileDto) for data transfer between different layers of the application.
