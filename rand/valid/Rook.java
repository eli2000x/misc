// Name: Erik Li
// CruzID: ersli
// Role: Subclass Rook, own implementation of isAttacking()
// File Name: Rook.java

public class Rook extends ChessPiece{

	// calls ChessPiece constructor but sets name to "R" for Rook
	public Rook(int col, int row, int color) {
		super(col, row, color);
		name = "R";
	}
	
	// isAttacking() for Rook, checks if piece is in the same row or same column
	public boolean isAttacking(ChessPiece piece) {
		// checks column
		if (getCol() == piece.getCol() && getColor() != piece.getColor()) {
			return true;
		}
		
		// checks row
		if (getRow() == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		return false;
	}
}
