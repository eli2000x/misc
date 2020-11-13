// Name: Erik Li
// CruzID: ersli
// Role: Class Definition for LinkedList with insert function
// File Name: LinkedList.java

public class LinkedList {
	
	// head to reference beginning
	public ChessPiece head;
	public int size;
	
	// set head to null and size to 0
	public LinkedList() {
		head = null;
		size = 0;
	}
	
	// add ChessPiece to beginning and increment size
	public void insert (ChessPiece piece) {
		ChessPiece latest = piece;
		latest.next = head;
		head = latest;
		size++;
	}
	
	public ChessPiece delete(int col, int row) {
		ChessPiece prev = null;
		ChessPiece curr = head;
		while(curr != null && (curr.getCol() != col || curr.getRow() != row)) {
			prev = curr;
			curr = curr.next;
		}
		if (curr == null) {
			return null;
		}
		if (prev == null) {
			head = head.next;
		} else {
			prev.next = curr.next;
		}
		size--;
		return curr;
	}
	
	
}
