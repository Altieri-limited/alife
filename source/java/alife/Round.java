package alife;

import javax.naming.ConfigurationException;

import alife.organisms.RandomOrganism;
import alife.universe.CartesianUniverse;
import alife.universe.Life;
import alife.universe.Universe;

public class Round<Outcome> {
	private long length;
	@SuppressWarnings("unused")
	private Universe universe;
	private Life<Outcome> life;
	private Payoff<Outcome> payoff;

	public Round (long length, Universe universe, Life<Outcome> life, Payoff<Outcome> payoff) {
		this.length = length;
		this.universe = universe;
		this.life = life;
		this.payoff = payoff;
	}

	public static void main (String args[]) throws ConfigurationException {
		long period = (long)1000;
		Universe universe = new CartesianUniverse(new int[] {100,100,100});
		Life<BasicOutcome> life = new Life();
		for (int i = 0; i < 10; i++) {
			Organism<BasicOutcome>  organism = new RandomOrganism(0, universe.getRandomPoint());
			life.addOrganisms(organism);
		}
		Organism<BasicOutcome>  organism = new RandomOrganism(1, universe.getRandomPoint());
		life.addOrganisms(organism);
		BasicPayoffMatrix payoff = new BasicPayoffMatrix();
		Round<BasicOutcome> round = new Round(period, universe, life, payoff);
		
		round.start();
		System.out.println(life.toString());
	}

	private void start() {
		for (long instant = 0; instant < length; instant++) {
			for (Organism<Outcome> org1 : life.getOrganisms()) {
				for (Organism<Outcome> org2 : life.getOrganisms()) {
					if (org1 != org2) {
						interaction(org1, org2, instant);
					}
				}
			}
		}
	}

	private void interaction(Organism<Outcome> org1, Organism<Outcome> org2, long instant) {
		Outcome outcome1 = org1.interaction(org2, instant);
		Outcome outcome2 = org2.interaction(org1, instant);
		payoff.assignPayoff(org1, outcome1, org2, outcome2);
	}
}