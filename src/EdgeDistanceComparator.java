import java.util.Comparator;

/**
 * Compares two edges (in the form of a long[] with size 3) on their distance
 * value, which is the last value in the array.
 * 
 * @author mikael
 *
 */
public class EdgeDistanceComparator implements Comparator<long[]> {
	@Override
	public int compare(long[] array, long[] anotherArray) {
		int result = Long.compare(array[2], anotherArray[2]);
		return result;
	}
};