package alife;

public interface Payoff<Outcome> {
	void assignPayoff(Organism<Outcome> org1, Outcome outcome1, Organism<Outcome> org2, Outcome outcome2);
}
