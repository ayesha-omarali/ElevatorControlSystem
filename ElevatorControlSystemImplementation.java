package ElevatorControlSystem;

import java.util.List;
import java.util.ArrayList;

public class ElevatorControlSystemImplementation implements ElevatorControlSystem {

	private List<ElevatorStatus> elevators;


	public ElevatorControlSystemImplementation(int numElevators) {
		this.elevators = new ArrayList<ElevatorStatus>();

		for(int i = 1; i <= numElevators; i++) {
			this.elevators.add(new ElevatorStatus(i, 1, new ArrayList<Integer>()));
		}
	}


	public List<ElevatorStatus> status() {
		return elevators;
	}

	public void update(ElevatorStatus status) {
		this.elevators.set(status.getId() - 1, status);
	}


	public void pickup(int floor, int direction) {
		ArrayList<ElevatorStatus> options = new ArrayList<ElevatorStatus>();


		for (ElevatorStatus curr : this.elevators) {

			if (direction == curr.direction() || curr.direction() == 0) {
				if ((direction > 0) && !(curr.getFloorNum() > floor)) {
					options.add(curr);
				
				} else if ((direction < 0) && !(curr.getFloorNum() < floor)) {
					options.add(curr);
				}
			}
		}


		ArrayList<ElevatorStatus> betterOptions = new ArrayList<ElevatorStatus>();
		ElevatorStatus bestOption = null;
		int bestMinDistance = Integer.MAX_VALUE;

		for (ElevatorStatus curr : options) {
			int distance = Math.abs(curr.getFloorNum() - floor);

			if (distance < bestMinDistance) {
				bestMinDistance = distance;
				bestOption = curr;
			} else if (distance == bestMinDistance && (curr.getGoalFloors().size() < bestOption.getGoalFloors().size())) {
				bestOption = curr;
			}
		}

		List<Integer> goalFloors = bestOption.getGoalFloors();
		goalFloors.add(floor);
		bestOption.setGoalFloors(goalFloors);

	}

	public void step() {
		for (ElevatorStatus curr : this.elevators) {

			/* if not empty, then continue moving in its direction */
			if (!curr.getGoalFloors().isEmpty()) {

				if (curr.direction() > 0) {
					curr.setFloorNum(curr.getFloorNum() + 1);

					if (curr.getGoalFloors().contains(curr.getFloorNum())) {
						int index = curr.getGoalFloors().indexOf(curr.getFloorNum());
						curr.getGoalFloors().remove(index);
					}

				} else {
					curr.setFloorNum(curr.getFloorNum() - 1);
				}
			}
		}
	}
}