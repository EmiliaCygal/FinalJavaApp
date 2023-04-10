# Little Rodent's Special - a food ordering system

## What is it?
Little Rodent's Special is a full-stack Java Web application simulating a food ordering system.
The idea of the project was to create a front end web application that communicates with an SQL database. 
The main functionalities of the application include:
- placing an order
- viewing avaiable restaurants
- filtering restaurants by cuisine and user-based score
- an admin page to add, edit, and remove restaurants

![image](https://user-images.githubusercontent.com/77623612/230968380-eb788c53-f857-4f30-b3d8-66e697bff3d7.png)

## The Tech Stack

The Database: MySQL  
The Back-End: Java | Spring Boot | JDBC  
The Front-End: HTML | Bootstrap

## The Database
The backbone of the system is an SQL database that relates the food orders to restaurants and user reviews:

![image](https://user-images.githubusercontent.com/77623612/230962892-f7406ce0-480f-4b8d-85ed-f04f8f55c345.png)

All SQL scripts to create and populate the database are included in the [LittleRodentSQL folder](https://github.com/EmiliaCygal/FinalJavaApp/tree/main/LittleRodentsSpecialFoodBag/LittleRodentSQL). 

The database dictates the structure of our code. It includes tables for all relevant entities in our application: foodPlace, order, and review.
The reviews table relates to the orders table via the foreign key of Order ID. The orders table relates to the foodPlaces table via the foreign key of Food Place ID.

## The Backend
- A class was created for each relevant entity corresponding with a table in the database.
- A set of matching DAOs adds and removes data from the database.
- Unit tests were performed to ensure the DAOs are functional.
- Controllers connect the back end to the interface via endpoints and initiate the functionalities of our objects.
- Service calculates the average score for each foodPlace based on user reviews and assigns an Order ID to the review.

## The Frontend
Each functionality of the application is mapped to an endpoint.
A webpage activates these endpoints when accessing a specified link or through interactive objects such as buttons and drop-down menus.

# Running the application
To use our project you need to set up a MySQL database on your machine. Our repository contains scripts to create and populate the database in the [LittleRodentSQL folder](https://github.com/EmiliaCygal/FinalJavaApp/tree/main/LittleRodentsSpecialFoodBag/LittleRodentSQL). Feel free to insert your own data into the database.

Next, once you open the project in an IDE of your choice (we prefer IntelliJ) you need to make sure the connection to the database on your machine is set up correctly. To do this, go to the [application.properties file] https://github.com/EmiliaCygal/FinalJavaApp/blob/main/LittleRodentsSpecialFoodBag/src/main/resources/application.properties and change the login credentials for MySQL as necessary. 

Once the application is running you should be able to open it in your browser via the <http://localhost:8080/> link. If an error occurs, check your IDE console if the port 8080 is already being used. If so, add the line `server.port=8081` or `server.port=8090` in the [application.properties file] and use the new port number in the browser. https://github.com/EmiliaCygal/FinalJavaApp/blob/main/LittleRodentsSpecialFoodBag/src/main/resources/application.properties

If successful, the following front page will appear:
![image](https://user-images.githubusercontent.com/77623612/230961808-6c4e8098-630f-4fd0-86ee-2d77ccf7eb3e.png)

To open the admin page go to <http://localhost:8080/admin>. There you can add, edit or delete restaurants. 


##Authors
- Emilia @EmiliaCygal
- Nikos @NikKats
