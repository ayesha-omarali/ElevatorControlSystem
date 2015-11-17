package ElevatorControlSystem;

import java.util.List;
import java.util.Collections;

public class ElevatorStatus{
	private int id;
	private int floorNum;
	private List<Integer> goalFloors;

	public ElevatorStatus(int id, int floorNum, List<Integer> goalFloors) {
		this.id = id;
	 	this.floorNum = floorNum;
		this.goalFloors = goalFloors;
	}


	public void setId(int id){
		this.id = id;
	}

	public void setFloorNum(int floorNum){
		this.floorNum = floorNum;
	}

	public void setGoalFloors(List<Integer> goalFloors) {
		this.goalFloors = goalFloors;
		Collections.sort(this.goalFloors);
	}

	public int getId() {
		return this.id;
	}

	public int getFloorNum() {
		return this.floorNum;
	}

	public List<Integer> getGoalFloors() {
		return this.goalFloors;
	}

	public int direction() {
		if (this.goalFloors.size() == 0) {
			return 0;
		} else if (this.floorNum > this.goalFloors.get(0)) {
			return -1;
		} else {
			return 1;
		}
	}

}