// Name: Erik Li
// CruzID: ersli
// Role: HW5 - override compareTo method to sort by length
// File Name: Words.java

import java.util.Comparator;

public class Words implements Comparator<Words>, Comparable<Words>{

	// variables to store word and frequency
	private String word;
	private int frequency;
	
	// Constructor
	public Words(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}

	// String word getter
	public String getWord() {
		return word;
	}

	// frequency getter
	public int getFrequency() {
		return frequency;
	}

	// compareTo method that sorts in descending order of rank and handles ties in frequency
	@Override
	public int compareTo(Words o) {
		if (this.getFrequency() == o.getFrequency()) {
			return this.getWord().compareTo(o.getWord());
		}
		if (this.getFrequency() > o.getFrequency()) {
			return -1;
		} else {
			return 1;
		}
	}

	// call compareTo with two words arguments
	@Override
	public int compare(Words arg0, Words arg1) {
		return arg0.compareTo(arg1);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
