package alife;

import alife.universe.Point;

public interface Organism<Outcome> extends PointOwner {
	Outcome interaction (Organism<Outcome> other, long time);
	Point getPoint();
	int getStrength();
	void updateStrength(int payoff);
}
