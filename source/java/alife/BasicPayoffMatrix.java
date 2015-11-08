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
				org1.updateStrength(cooperateBonus);
				org2.updateStrength(cooperateBonus);
			}
			else {
				org1.updateStrength(suckPenalty);
				org2.updateStrength(defectBonus);
			}
		}
		else {
			if (outcome2 == BasicOutcome.COOPERATE) {
				org1.updateStrength(defectBonus);
				org2.updateStrength(suckPenalty);
			}
			else {
				org1.updateStrength(defectPenalty);
				org2.updateStrength(defectPenalty);
			}
		}
	}
}
