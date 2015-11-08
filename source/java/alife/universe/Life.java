package alife.universe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.naming.ConfigurationException;

import alife.Organism;

public class Life<Outcome> {
	private Set<Organism<Outcome>> organisms;
	
	@SafeVarargs
	public Life (Organism<Outcome>... initialOrganisms) throws ConfigurationException {
		organisms = new HashSet<Organism<Outcome>>();
		if (initialOrganisms == null) {
			throw new ConfigurationException("Life must contain at least one organism.");
		}
		addOrganisms(initialOrganisms);
	}
	
	@SafeVarargs
	public final void addOrganisms(Organism<Outcome>... initialOrganisms) {
		organisms.addAll(Arrays.asList(initialOrganisms));
	}

	public Set<Organism<Outcome>> getOrganisms() {
		return organisms;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Organism<Outcome> organism: organisms) {
			sb.append(organism.toString() + "\n");
		}
		
		return sb.toString();
	}
}
