

/**
 * Holds a set of immutable x and y coordinates.
 */
public final class Coordinates {
	private final double xCoordinate;
	private final double yCoordinate;

	/**
	 * Create a new pair of coordinates.
	 * 
	 * @param xCoordinate
	 *            the x coordinate.
	 * @param yCoordinate
	 *            the y coordinate.
	 */
	public Coordinates(double xCoordinate, double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	/**
	 * @return the x coordinate.
	 */
	public double getX() {
		return xCoordinate;
	}

	/**
	 * @return the y coordinate.
	 */
	public double getY() {
		return yCoordinate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) xCoordinate;
		result = prime * result + (int) yCoordinate;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (xCoordinate != other.xCoordinate)
			return false;
		if (yCoordinate != other.yCoordinate)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{x=" + xCoordinate + ", y=" + yCoordinate + "}";

	}

}
