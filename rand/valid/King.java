// Name: Erik Li
// CruzID: ersli
// Role: Subclass King, own implementation of isAttacking()
// File Name: King.java

public class King extends ChessPiece{

	// calls ChessPiece constructor but sets name to "K" for King
	public King(int col, int row, int color) {
		super(col, row, color);
		name = "K";
	}
	
	// isAttacking() for King, checks all spaces one step away 
	public boolean isAttacking(ChessPiece piece) {
		int l = getCol();
		int k = getRow();
		
		// handles left and right

		if ((l - 1 == piece.getCol() && k == piece.getRow() && getColor() != piece.getColor()) ||
				(l + 1 == piece.getCol() && k == piece.getRow() && getColor() != piece.getColor())) {
			return true;
		}
		
		
		// handles up and down
		
		if ((k - 1 == piece.getRow() && l == piece.getCol() && getColor() != piece.getColor()) ||
				(k + 1 == piece.getRow() && l == piece.getCol() && getColor() != piece.getColor())) {
			return true;
		}
		
	
		// handles diagonal up right 
		
		if (l + 1 == piece.getCol() && k - 1 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		
		// handles diagonal up left
		
		if (l - 1 == piece.getCol() && k - 1 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		

		// handles diagonal down right
		
		if (l + 1 == piece.getCol() && k + 1 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		
		// handles diagonal down left
	
		if (l - 1 == piece.getCol() && k + 1 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		
		return false;
	}
}
