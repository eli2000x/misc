// Name: Erik Li
// CruzID: ersli
// Role: Subclass Queen, own implementation of isAttacking()
// File Name: Queen.java

public class Queen extends ChessPiece {

	// calls ChessPiece constructor but sets name to "Q" for Queen
	public Queen(int col, int row, int color) {
		super(col, row, color);
		name = "Q";
	}
	
	// isAttacking() for Queen, checks if piece is attacked according to Queen attacking pattern
	public boolean isAttacking(ChessPiece piece) {
		// handles column
		if (getCol() == piece.getCol() && getColor() != piece.getColor()) {
			return true;
		}
		
		// handles row
		if (getRow() == piece.getRow() && getColor() != piece.getColor()) {
			return true;
		}
		
		int l = getCol();
		int k = getRow();
		
		// up + right
		while (l != 0 && k + 1 <= 8) {
			if (l - 1 == piece.getCol() && k + 1 == piece.getRow() 
					&& getColor() != piece.getColor()) {
				return true;
			}
			l--;
			k++;
		}
		
		l = getCol();
		k = getRow();
		
		// down + right
		while (l + 1 <= 8 && k + 1 <= 8) {
			if (l + 1 == piece.getCol() && k + 1 == piece.getRow()
					&& getColor() != piece.getColor()) {
				return true;
			}
			l++;
			k++;
		}
		
		l = getCol();
		k = getRow();
		
		// down + left
		while (l + 1 <= 8 && k != 0) {
			if (l + 1 == piece.getCol() && k - 1 == piece.getRow()
					&& getColor() != piece.getColor()) {
				return true;
			}
			l++;
			k--;
		}
		
		l = getCol();
		k = getRow();
		
		// up + left
		while (l != 0 && k != 0) {
			if (l - 1 == piece.getCol() && k - 1 == piece.getRow()
					&& getColor() != piece.getColor()) {
				return true;
			}
			l--;
			k--;
		}
		
		return false;
	}
}
