package alife;

import alife.universe.Point;

public interface Organism<Outcome> extends PointOwner {
	Outcome interaction (Organism<Outcome> other, long time);
	Point getPoint();
	int getStrength();
	void react(Organism<Outcome> other, Outcome itsOutcome);
	void updateStrenght(int payoff);
}
