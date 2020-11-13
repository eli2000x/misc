// Name: Erik Li
// CruzID: ersli
// Role: Subclass Knight, own implementation of isAttacking()
// File Name: Knight.java

public class Knight extends ChessPiece {

	// calls ChessPiece constructor but sets name to "N" for Knight
	public Knight(int col, int row, int color) {
		super(col, row, color);
		name = "N";
	}
	
	// isAttacking() for Knight, checks is piece is attacked according to Knight attacking patterns
	public boolean isAttacking(ChessPiece piece) {
		int l = getCol();
		int k = getRow();
		
		// up upper right
		if (l + 1 == piece.getCol() && k - 2 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		// up lower right
		if (l + 2 == piece.getCol() && k - 1 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		// up upper left
		if (l - 1 == piece.getCol() && k - 2 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		// up lower left
		if (l - 2 == piece.getCol() && k - 1 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		// down upper left
		if (l - 2 == piece.getCol() && k + 1 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		// down lower left
		if (l - 1 == piece.getCol() && k + 2 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		// down upper right
		if (l + 1 == piece.getCol() && k + 2 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		// down lower right
		if (l + 2 == piece.getCol() && k + 1 == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		return false;
	}
}
