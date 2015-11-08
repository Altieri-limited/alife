package alife;

public class BasicPayoffMatrix implements Payoff<BasicOutcome> {
	private int cooperateBonus = 3;
	private int defectBonus = 5;
	private int suckPenalty = -1;
	private int defectPenalty = 1;
	
	@Override
	public void assignPayoff(Organism<BasicOutcome> org1, BasicOutcome outcome1, 
			Organism<BasicOutcome> org2, BasicOutcome outcome2) {
		
		if (outcome1 == BasicOutcome.COOPERATE) {
			if (outcome2 == BasicOutcome.COOPERATE) {
				org1.updateStrenght(cooperateBonus);
				org2.updateStrenght(cooperateBonus);
			}
			else {
				org1.updateStrenght(suckPenalty);
				org2.updateStrenght(defectBonus);
			}
		}
		else {
			if (outcome2 == BasicOutcome.COOPERATE) {
				org1.updateStrenght(defectBonus);
				org2.updateStrenght(suckPenalty);
			}
			else {
				org1.updateStrenght(defectPenalty);
				org2.updateStrenght(defectPenalty);
			}
		}
	}
}
