package my.qrkot.port;

public class Port implements Ports {
	
	private String[] indexes;
	private int[][] portArrays;
	private int[] combination;
	private int[][] portCombinations;
	private int combinationsCounter = 0;
	
	
	public Port(String[] indexes) {
		this.indexes = indexes;
		this.combination = new int[indexes.length];
		buildPortArrays();
	}

	public int[][] getCombinations() {
		if (portCombinations != null) {	
			return portCombinations;
		}
		int portCombinationsSize = 1;
		for (int[] p : portArrays) {
			portCombinationsSize *= p.length;	// MAX_INT
		}
		portCombinations = new int[portCombinationsSize][];
		buildCombinations(0);
		return portCombinations;
	}
	
	public int[] getSequence() {
		int size = 0;
		for (int[] p : portArrays) {
			size += p.length;
		}

		int[] sequence = new int[size];
		int counter = 0;
		for (int[] p : portArrays) {
			for (int q : p) {
				sequence[counter++] = q;
			}
		}
		return sequence;
	}

// Private helpers below:
	
	private void buildPortArrays() {
		portArrays = new int[indexes.length][];
		int i = 0;
		for (String index : indexes) {
			portArrays[i] = parseIndexes(index);
			i++;
		}
	}
	
	private int[] parseIndexes (String index) {
		String[] subIndex = index.split(",");
		for (int i = 0; i < subIndex.length; i++) {
			if (subIndex[i].contains("-")) {
				subIndex[i] = expandIndexRange(subIndex[i]);
			}
		}
		String normalizedIndex = String.join(",", subIndex);
		subIndex = normalizedIndex.split(",");
		int[] result = new int[subIndex.length];		
		for (int i = 0; i < subIndex.length; i++) {
			result[i] = Integer.parseInt(subIndex[i].trim());
		}
		return result;
	}
	
	private String expandIndexRange (String range) {
		StringBuilder buffer = new StringBuilder();
		String[] sub = range.split("-");
		int left = Integer.parseInt(sub[0].trim());
		int right = Integer.parseInt(sub[1].trim());
		while (left <= right) {
			buffer.append(left++);
			buffer.append(",");
		}
		return buffer.substring(0, buffer.length() - 1);
	}
	
// Recursively build combinations of indexes.
	private void buildCombinations(int i) {
		if (i < portArrays.length) {
			for ( int j : portArrays[i] ) { 
				combination[i] = j;
				buildCombinations(i+1);
			}
		}
		else {
			portCombinations[combinationsCounter++] = combination.clone();
		}
	}	
}
