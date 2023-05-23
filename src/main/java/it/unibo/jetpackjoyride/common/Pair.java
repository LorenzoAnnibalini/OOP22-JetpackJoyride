package it.unibo.jetpackjoyride.common;

/**
 * Generic class Pair<X, Y>.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class Pair<X, Y> {

	private final X x;
	private final Y y;

	/**
	 * Constructor of the class Pair<X, Y>.
	 * 
	 * @param x
	 * @param y
	 */
	public Pair(final X x, final Y y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter of the first element of the pair.
	 * 
	 * @return
	 */
	public X getX() {
		return x;
	}

	/**
	 * Getter of the second element of the pair.
	 * 
	 * @return
	 */
	public Y getY() {
		return y;
	}

	/**
	 * Override of the method hashCode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	/**
	 * Override of the method equals.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Pair other = (Pair) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	/**
	 * Override of the method toString.
	 * 
	 * @return the string representation of the pair
	 */
	@Override
	public String toString() {
		return "Pair [x=" + x + ", y=" + y + "]";
	}

}
