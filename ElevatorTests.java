package ElevatorControlSystem;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

public class ElevatorTests {

	@Test
	public void statusTest() {
		ElevatorControlSystem ecs = new ElevatorControlSystemImplementation(1);
		List<ElevatorStatus> status = ecs.status();
		Assert.assertTrue(status.size() == 1);

		Assert.assertTrue(status.get(0).getId() == 1);
	}

	@Test
	public void pickupTest() {
		ElevatorControlSystem ecs = new ElevatorControlSystemImplementation(1);
		ecs.pickup(3, 1);
		List<ElevatorStatus> status = ecs.status();

		ElevatorStatus e1 = status.get(0);
		Assert.assertTrue(e1.getGoalFloors().size() == 1);
		Assert.assertTrue(e1.getGoalFloors().get(0) == 3);
	}

	@Test
	public void updateTest() {
		ElevatorControlSystem ecs = new ElevatorControlSystemImplementation(1);
		ArrayList<Integer> empty = new ArrayList<Integer>();
		ElevatorStatus newStatus = new ElevatorStatus(1, 4, empty);

		ecs.update(newStatus);

		List<ElevatorStatus> status = ecs.status();
		ElevatorStatus e1 = status.get(0);

		Assert.assertTrue(e1.getFloorNum() == 4);

	}

	@Test
	public void stepTest() {
		ElevatorControlSystem ecs = new ElevatorControlSystemImplementation(1);

		ecs.pickup(2, 1);
		ecs.step();

		List<ElevatorStatus> status = ecs.status();
		ElevatorStatus e1 = status.get(0);


		Assert.assertTrue(e1.getFloorNum() == 2);
	}

}