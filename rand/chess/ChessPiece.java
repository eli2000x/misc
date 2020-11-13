// Name: Erik Li
// CruzID: ersli
// Role: Superclass ChessPiece which has all variables/functions to be inherited/overridden by subclasses
// File Name: ChessPiece.java

public class ChessPiece {

	// each ChessPiece has these variables
	
	private int col;
	private int row;
	private int color;
	public String name;
	public ChessPiece next;
	
	// sets all variables accordingly, name depends on the piece
	public ChessPiece(int col, int row, int color) {
		this.col = col;
		this.row = row;
		this.color = color;
		next = null;
	}
	
	// isAttacking() for ChessPiece to be overwritten 
	public boolean isAttacking(ChessPiece piece) {
		return true;
	}
	
	// returns col
	public int getCol() {
		return col;
	}
	
	// returns row
	public int getRow() {
		return row;
	}

	// returns color
	public int getColor() {
		return color;
	}
	
	// returns name
	public String getName() {
		return name;
	}

}
