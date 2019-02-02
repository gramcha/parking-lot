# parking-lot
A simple solution to the parking lot problem.


approaching solution:

## 1. Creating parking lot

```
Problem statement: I own a parking lot that can hold up to 'n' cars at any given point in time. Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps of one. I want to create an automated ticketing system that allows my customers to use my parking lot without human intervention.
```
 
Parking lot created with given capacity and its free slots initialized with capacity. Store parking lot instance in collection to support more than one parking lot in future based on parking lot id. Keep the instances of parking lot in separate service class(DefaultParkingLotService). 

## 2.  About car
```
Problem statement: When a car enters my parking lot, I want to have a ticket issued to the driver. The ticket issuing process includes us documenting the registration number (number plate) and the colour of the car.

Example:
registration number (number plate) : KA-01-HH-1234 
colour : White
```

Here car is a real world object and it has color and registration number in this problem. Let's create Car model class.

## 3.  Allocating available slot to the car
```
Problem statement: When a car enters my parking lot, allocating an available parking slot to the car before actually handing over a ticket to the driver (we assume that our customers are nice enough to always park in the slots allocated to them). The customer should be allocated a parking slot which is nearest to the entry.
```
Here, following things are happening

* allocating available parking slot to the entering car
	* we need to keep the allocated slot and car instance in a collection. for faster retrieval we store it in hashmap.
	* allocated a parking slot should be nearest to the entry
		which means allocation should happen for free slots sorted in ASC order.
		* In two ways we can get the sorted result.
			1. Maintaining the free slots in List<> and sorting them before allocating(removing slot) from that list.
			2. Keeping the free slots itself in a sorted manner. We can use the TreeSet where objects are stored in a sorted and ascending order. Lets choose this option.
			
