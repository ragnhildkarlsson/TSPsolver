import java.util.Comparator;

	public class EdgeDistanceComparator implements Comparator<long[]>
	{
		@Override
		public int compare(long[] array, long[] anotherArray) {
			int result = Long.compare(array[2], anotherArray[2]);
			return result;
		}
	};