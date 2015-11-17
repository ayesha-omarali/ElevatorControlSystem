# ElevatorControlSystem

Overview: 
* The goal of this program is to properly manage delegation and pickup of individuals with multiple elevators. 
* The elevators decide who to pick up based on requested direction, current distance from requested floor, 
  and number of pending stop requests
* This program was done around ~3.5 hours from 10:30pm to ~1am (ish) 
  (Not entirely nonstop, I snacked and am currently working on a different computer 
    so installing JUnit and class paths took an unfortunate amount of time)
* This is originally a Dynamic Programming (DP) Hard problem, so it is implemented as best as possible with 
    a method that is rarely suboptimal: keep moving in the current direction until all passengers who have requested rides
    in this direction are picked up and delivered -- then move to the first call in the opposite direction. 

Files:
* `ElevatorControlSystem` interface containing:
  * `status` returning list of all the elevators and its statuses (id, floor nubmer, goal floors)
  * `pickup` void, processes pickup request by adding floor and desired direction to queue 
  * `update` changes the elevator's status as it moves
  * `step` while not empty, moves in necessary direction 1 floor at a time
  

* `ElevatorControlSystemImplementation` class implementing `ElevatorControlSystem`:
  * private `elevators` is a list of ElevatorStatus objects, containing a list of all the elevator statuses
  * Constructor takes in number of desired elevators and initiates `ElevatorStatus` object
    which provides an id, floor number its on, and an empty list of goal floors and methods required for them
  * `status` implemented here
  * `update` implemented here by setting the elevator's status to the input new status
  * `pickup` implemented here, processes the request and determines best option for elevator
    1. Choose only elevators going in the same direction as desired to go
    2. Choose elevators with the closest relative distance
    3. Choose elevators with the least `goalFloors` (requested stops)
  * `step` implemented here, checks if at requested floor and if it is deletes it from `goalFloors` queue

* `ElevatorStatus` class implemented when `ElevatorControlSystemImplementation` is implemented
  * Contains ID for each elevator, current floor number, and goal floors (requested floors)
  * Provided 3 "setters": `setId`, `setFloorNum`, and `setGoalFloors`
  * Provided 3 "getters": `getId`, `getFloorNum`, and `getGoalFloors`
  * `direction` method to determine the if going up (positive) or down (negative)
  
* `ElevatorTests` class implemented with JUnit testing
  * `statusTest` Create a single elevator and check that there is one status and given an id
  * `pickupTest` Call pickup for 1 floor and check if the `getGoalFloors` returns size one and the correct requested floor
  * `updateTest` Create a new status manually, update to this new status, and ensure original elvator was updated
  * `stepTest` Call pickup and step to ensure the current floor num changed in the necessary direction
