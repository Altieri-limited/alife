package alife.universe;

import alife.exceptions.ConfigurationException;
import com.google.common.collect.*;

public class CartesianUniverse implements Universe{
	private int[] sizes;
	private Point[] points;

	public CartesianUniverse (int[] sizes) {
		if (sizes.length == 0) {
			throw new ConfigurationException("A universe cannot have 0 dimensions.");
		}
		this.sizes = sizes;
		
		int numPoints = sizes[0];
		for (int dim = 1; dim < sizes.length; dim++) {
			long dimSize = sizes[dim];
			numPoints *= dimSize;
		}
		if (numPoints == 0) {
			throw new ConfigurationException("A universe cannot have 0 points.");
		}
		
		points = new Point[numPoints];
	}

	@Override
	public Point getPoint(Position position) {
		int[] coordinates = position.getCoordinates();
		int pointIndex = 0;
		for (int dim = sizes.length - 1; dim > 0; dim--) {
			int dimSize = sizes[dim];
			pointIndex = pointIndex * dimSize + coordinates[dim];
		}
		Point point = points[pointIndex];
		if (point == null) {
			point = new Point(coordinates);
			points[pointIndex] = point;
		}
		
		return point;
	}
	
	@Override
	public Point getRandomPoint() {
		int[] coordinates = new int[sizes.length];
		for (int dim = 0; dim < sizes.length - 1; dim++) {
			int dimSize = sizes[dim];
			coordinates[dim] = (int)Math.random() * dimSize;
		}
		Position position = new Position(coordinates);
		
		return getPoint(position);
	}
}
