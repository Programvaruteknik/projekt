package domain.matching;

/**
 * Stores Two different values. These values can then be retrieved and. In some
 * words you could say that this class serves as an POD (Plain Old
 * Datastructure).
 * 
 * @author Rickard Engström
 *
 */
public class DataPair {
	private Double x, y;

	/**
	 * Creates a DataPair which stores two values.
	 * 
	 * @param x
	 *            One of the values.
	 * @param y
	 *            The other one of the values.
	 */
	public DataPair(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the first stored value.
	 * 
	 * @return Double The value.
	 */
	public Double getX() {
		return x;
	}

	/**
	 * Returns the second value.
	 * 
	 * @return Double The value.
	 */
	public Double getY() {
		return y;
	}

}
