// Name: Erik Li
// CruzID: ersli
// Role: Subclass Bishop, own implementation of isAttacking()
// File Name: Bishop.java

public class Bishop extends ChessPiece{

	// calls ChessPiece constructor but sets name to "B" for Bishop
	public Bishop(int col, int row, int color) {
		super(col, row, color);
		name = "B";
	}
	
	// isAttacking() for Bishop, checks if piece is in attacked diagonally 
	public boolean isAttacking(ChessPiece piece) {
		int l = getCol();
		int k = getRow();
		
		// handles up + right
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
		
		// handles down + right
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
		
		// handles down + left
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
		
		// handles up + left
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
