import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class EdgeDistanceComparatorTest {

	@Test
	public void LongComparatorTest() {
		long[][] arrayOfArrays = { { 0, 0, 2 }, { 0, 0, 1 }, { 0, 0, 3 } };

		Arrays.sort(arrayOfArrays, new EdgeDistanceComparator());

		for (int i = 0; i < 3; i++) {
			assertEquals(i + 1, arrayOfArrays[i][2]);
		}
	}

}
