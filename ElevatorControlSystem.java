package ElevatorControlSystem;

import java.util.List;

public interface ElevatorControlSystem {

	public List<ElevatorStatus> status();
	public void pickup(int floor, int direction);
	public void update(ElevatorStatus status);
	public void step();
}