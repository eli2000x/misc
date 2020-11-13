
public class StackVars {

	
	public int queensPlaced;
	public int n;
	public int prevrow;
	public int afterrow;
	
	
	public StackVars (int queensPlaced, int n, int prevrow, int afterrow) {
		
		// variable to keep track of column
		this.queensPlaced = queensPlaced;
		
		// variable for dimension
		this.n = n;
		
		// variables to help check each combination
		this.prevrow = prevrow;
		this.afterrow = afterrow;
		
	}
	
	
	
	
	
}
