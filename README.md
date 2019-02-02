# parking-lot
A simple solution to the parking lot problem.


approaching solution:

## 1. Creating parking lot

```
I own a parking lot that can hold up to 'n' cars at any given point in time. Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps of one. I want to create an automated ticketing system that allows my customers to use my parking lot without human intervention.
```
 
Parking lot created with given capacity and its free slots initialized with capacity. Store parking lot instance in collection to support more than one parking lot in future based on parking lot id. Keep the instances of parking lot in separate service class(DefaultParkingLotService). 

## 2.  About car
```
When a car enters my parking lot, I want to have a ticket issued to the driver. The ticket issuing process includes us documenting the registration number (number plate) and the colour of the car.

Example:
registration number (number plate) : KA-01-HH-1234 
colour : White

```
Here car is a real world object and it has color and registration number in this problem. Let's create Car model class.