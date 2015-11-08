package alife.universe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import alife.PointOwner;

public class Point {
	private List<PointOwner> owners;
	private Set<Resource> resources;
	private final int[] coordinates;

	public Point (int[] coordinates) {
		owners = new ArrayList<PointOwner>();
		resources = new HashSet<Resource>();
		this.coordinates = coordinates;
	}
	
	public List<PointOwner> getOwner() {
		return owners;
	}
	
	public void addOwner(PointOwner owner) {
		owners.add(owner);
	}

	public void removeOwner(PointOwner owner) {
		owners.remove(owner);
	}
	
	public Set<Resource> getResources() {
		return resources;
	}
	
	public void addResource(Resource resources) {
		this.resources.add(resources);
	}

	public int[] getCoordinates() {
		return coordinates;
	}
}
