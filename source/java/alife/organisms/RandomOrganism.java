package alife.organisms;

import alife.BasicOutcome;
import alife.Organism;
import alife.universe.Point;

public class RandomOrganism implements Organism<BasicOutcome> {
	private Point point;
	private float kindnessPercentage;
	private int strength;
	
	public RandomOrganism(float kindnessPercentage, Point point) {
		this.point = point;
		this.kindnessPercentage = kindnessPercentage;
		this.strength = 0;
	}

	@Override
	public BasicOutcome interaction(Organism<BasicOutcome> other, long time) {
		float s = (float)Math.random();
		BasicOutcome outcome = s < kindnessPercentage ? BasicOutcome.COOPERATE : BasicOutcome.DEFECT;

		return outcome;
	}


	@Override
	public Point getPoint() {
		return point;
	}

	@Override
	public int getStrength() {
		return strength;
	}

	@Override
	public void react(Organism<BasicOutcome> other, BasicOutcome itsOutcome) {
		/* No reaction */
	}

	@Override
	public void updateStrength(int payoff) {
		strength += payoff;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(kindnessPercentage).append(": ").append(strength);
		
		return sb.toString();
	}
}
