DROP DATABASE IF EXISTS littleRodent;
CREATE DATABASE littleRodent;

USE littleRodent;

CREATE TABLE foodPlaces(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
	category VARCHAR(20) NOT NULL,
	deliveryTimeScore FLOAT,
	priceScore FLOAT,
	tastinessScore FLOAT, 
	overallScore FLOAT
);

CREATE TABLE orders(
	id INT PRIMARY KEY AUTO_INCREMENT,
    foodPlaceId INT NOT NULL,
    orderStatus VARCHAR(20) NOT NULL,
    foodBundle VARCHAR(30) NOT NULL,
    FOREIGN KEY (foodPlaceId) REFERENCES foodPlaces(id) ON DELETE CASCADE ON UPDATE CASCADE
    );
    
CREATE TABLE reviews(
	id INT PRIMARY KEY AUTO_INCREMENT,
    orderId INT NOT NULL,
	deliverytimeScore INT NOT NULL,
	priceScore INT NOT NULL,
	tastinessScore INT NOT NULL,
    bundleScore INT NOT NULL,
    FOREIGN KEY (orderId) REFERENCES orders(id) ON DELETE CASCADE ON UPDATE CASCADE
    );
