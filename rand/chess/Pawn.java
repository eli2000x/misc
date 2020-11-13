// Name: Erik Li
// CruzID: ersli
// Role: Subclass Pawn, own implementation of isAttacking()
// File Name: Pawn.java

public class Pawn extends ChessPiece{
	
	// calls ChessPiece constructor but sets name to "P" for Pawn
	public Pawn(int col, int row, int color) {
		super(col, row, color);
		name = "P";
	}
	
	// isAttacking() for Pawn, checks if piece within one space diagonally and of correct color
	public boolean isAttacking(ChessPiece piece) {
		int l = getCol();
		int k = getRow();
		
		// black pawns
		if (getColor() == 0) {
			if (l - 1 == piece.getCol() && k - 1 == piece.getRow() && getColor() != piece.getColor() ||
					l + 1 == piece.getCol() && k - 1 == piece.getRow() && getColor() != piece.getColor()) {
				return true;
			}
		
		// white pawns
		} else {
			if (l - 1 == piece.getCol() && k + 1 == piece.getRow() && getColor() != piece.getColor() ||
					l + 1 == piece.getCol() && k + 1 == piece.getRow() && getColor() != piece.getColor()) {
				return true;
			}
			
		}
		
		return false;
	}
	

}
