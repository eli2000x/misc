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
	
	
}
