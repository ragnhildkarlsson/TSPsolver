import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * Check that its possible to sort edges (in form of long[] of size 3) in
 * increasing order, based on their distance value, which is the last value in
 * the array.
 * 
 * @author mikael
 *
 */
public class EdgeDistanceComparatorTest {

	@Test
	public void LongComparatorTest() {
		long[][] arrayOfArrays = { { 0, 0, 2 }, { 0, 0, 1 }, { 0, 0, 3 } };

		Arrays.sort(arrayOfArrays, new EdgeDistanceComparator());

		for (int i = 0; i < 3; i++) { // check that the edge with 1 is first,
										// then 2 and last 3.
			assertEquals(i + 1, arrayOfArrays[i][2]);
		}
	}

}
