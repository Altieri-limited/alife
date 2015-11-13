package alife.organisms;

import alife.BasicOutcome;
import alife.Organism;
import alife.universe.Point;
import com.google.common.collect.ImmutableMap;

import java.util.*;

public class WithMemoryOrganism extends RandomOrganism implements Organism<BasicOutcome> {
	private Point point;

    public static final Map<BasicOutcome, Integer> REACTION_MAP_MEAN =
            ImmutableMap.<BasicOutcome, Integer>builder()
                    .put(BasicOutcome.COOPERATE, 1)
                    .put(BasicOutcome.DEFECT, -1)
                    .build();
    public static final Map<BasicOutcome, Integer> REACTION_MAP_SUM_DEFECT =
            ImmutableMap.<BasicOutcome, Integer>builder()
                    .put(BasicOutcome.COOPERATE, 0)
                    .put(BasicOutcome.DEFECT, -1)
                    .build();

	private float kindnessPercentage;
	private int strength;
	private Map<Organism, Deque<BasicOutcome>> memory;
	private int memoryLength;
	private int susceptibility;
    private Map<BasicOutcome, Integer> reactionMap = new HashMap<BasicOutcome, Integer>(2);

	public WithMemoryOrganism(
            float kindnessPercentage, int memoryLength, int susceptibility,
            Map<BasicOutcome, Integer> reactionMap, Point point) {
		super(kindnessPercentage, point);
		this.strength = 0;
		this.memoryLength = memoryLength;
		this.susceptibility = susceptibility;
		this.memory = new HashMap();
        this.reactionMap = reactionMap;
	}

	@Override
	public BasicOutcome interaction(Organism<BasicOutcome> other, long time) {
        Deque<BasicOutcome> previousOutcomes = memory.get(other);
        BasicOutcome outcome = BasicOutcome.COOPERATE;
        if (previousOutcomes != null) {
            int anger = 0;
            for (BasicOutcome previousOutcome : previousOutcomes) {
                anger -= reactionMap.get(previousOutcome);
            }
            if (anger >= susceptibility) {
                outcome = BasicOutcome.DEFECT;
            }
        }

        if (outcome == BasicOutcome.COOPERATE) {
            float s = (float)Math.random();
            if (s >= kindnessPercentage) {
                outcome = BasicOutcome.DEFECT;
            }
        }

		return outcome;
	}

	@Override
	public void react(Organism<BasicOutcome> other, BasicOutcome itsOutcome) {
		Deque<BasicOutcome> previousOutcomes = memory.get(other);
        if (previousOutcomes == null) {
            previousOutcomes = new ArrayDeque<BasicOutcome>(memoryLength + 1);
        }
        previousOutcomes.addFirst(itsOutcome);
        if (previousOutcomes.size() > memoryLength) {
            previousOutcomes.pollLast();
        }
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(kindnessPercentage).append("-").append(memory).append("-").append(susceptibility).append(": ").append(strength);
		
		return sb.toString();
	}
}
