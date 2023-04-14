/**
 * 
 */
package my.qrkot.port;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.Test;

public class PortTest {
	
	private String[] indexes = {"1,2,3","4-7","8,9","0"};
	private int sizeOfCombination = indexes.length;
	private int numberOfCombinations = 3*4*2*1;

	@Test
	public void testGetCombinations() {
		Ports port = new Port(indexes);
		int[][] combinations = port.getCombinations();
		
		System.out.println("Combinations: ");
		Stream.of(combinations)
			.map(e -> Arrays.toString(e))
			.forEach(System.out::println);
		
		assertNotNull(port);
		assertEquals(combinations.length, numberOfCombinations);
		
		for (int[] c : combinations) {
			assertEquals(c.length, sizeOfCombination);
		}
	}

	@Test
	public void testGetSequence() {
		Ports port = new Port(indexes);
		int[] seq = port.getSequence();

		System.out.println("Sequence: " + Arrays.toString(seq));
		assertArrayEquals(seq, new int[] {1,2,3,4,5,6,7,8,9,0});
	}

}
