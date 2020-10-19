

@SuppressWarnings("overrides")
class List {
	
	private class Node {
		// Fields 
		Object data;
		Node next; 
		Node prev; 
		
		// Constructor
		Node (Object data) {
			this.data = data;
			next = null;
			prev = null;
		}
		
		// toString(): overrides Object's toString() method
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	// Fields 
	private Node front;
	private Node back;
	private Node cursor;
	private int length;
	private int index;
	
	// List()
	// Constructor 
	// Creates a new empty list
	List() {
		front = back = cursor = null;
		length = 0;
		index = -1;
	}
	
	// Access Functions --------------------------------------------------------
	// -------------------------------------------------------------------------
	
	// length()
	// Returns the number of elements in this List.
	int length() {
		return length;
	}
	
	// index()
	// Returns index of cursor element, -1 if undefined
	int index() {
		if (cursor == null) {
			return -1;
		}
		return index;
	}
	
	// front()
	// Returns front element.
	// Pre: length() > 0
	Object front() {
		if (length() <= 0) {
			throw new RuntimeException(
					"List Error: front() called on length <= 0");
		}
		return front.data;
	}
	
	// back() 
	// Returns back element.
	// Pre: length() > 0
	Object back() {
		if (length() <= 0) {
			throw new RuntimeException(
					"List Error: back() called on length <= 0");
		}
		return back.data;
	}
	
	// get()
	// Returns cursor element.
	// Pre: length() > 0, index >= 0
	Object get() {
		if (length() <= 0) {
			throw new RuntimeException(
					"List Error: get() called on length <= 0");
		}
		if (index() < 0) {
			throw new RuntimeException(
					"List Error: get() called on index < 0");
		}
		return cursor.data;
	}
	
	// equals(List L)
	// Returns true iff the List and L are the same sequence.
	// The state of the cursors in the two Lists are not used in determining equality
	
	public boolean equals(Object x) {
		if (x instanceof List == false) {
			return false;
		}
		List L = (List) x;
		if (this.length() != L.length()) {
			return false;
		}
		Node curr1 = this.front;
		Node curr2 = L.front;
		for (int i = 0; i < this.length(); i++) {
			if (curr1.data != curr2.data) {
				return false;
			}
			curr1 = curr1.next;
			curr2 = curr2.next;
		}
		return true;
	}
	
	// Manipulation Procedures --------------------------------------------
	// --------------------------------------------------------------------
	
	// clear() 
	// Resets this List to its original empty state.
	void clear() {
		front = back = cursor = null;
		length = 0;
		index = -1;
	}
	
	// moveFront()
	// Places cursor under the front element, does nothing if empty
	void moveFront() {
		if (length == 0) {
			return;
		}
		cursor = front;
		index = 0;
	}
	
	// moveBack()
	// Places cursor under the back element, does nothing if empty
	void moveBack() {
		if (length == 0) {
			return;
		}
		cursor = back;
		index = length - 1;
	}
	
	// movePrev()
	// If cursor is defined and not at front, move cursor one step toward 
	// front of List. If cursor is defined and at front, cursor becomes undefined.
	// If cursor is undefined, does nothing
	void movePrev() {
		if (cursor == null) {
			return;
		}
		if (cursor == front) {
			cursor = null;
			index = -1;
			return;
		}
		cursor = cursor.prev;
		index--;
	}
	
	// moveNext() 
	// If cursor is defined and not at back, move cursor one step toward 
	// back of List. If cursor is defined and at back, cursor becomes undefined.
	// If cursor is undefined, does nothing
	void moveNext() {
		if (cursor == null) {
			return;
		}
		if (cursor == back) {
			cursor = null;
			index = -1;
			return;
		}
		cursor = cursor.next;
		index++;
	}
		
	// prepend(Object data) 
	// Insert new element into this List. If List is non-empty,
	// insertion takes place before front element.
	void prepend(Object data) {
		Node N = new Node(data);
		if (length() == 0) {
			front = back = N;
		} else {
			if (length() == 1) {
				N.next = front;
				front.prev = N;
				back = front;
				front = N;
			} else {
				N.next = front;
				front.prev = N;
				front = N;
			}
			if (cursor != null) {
				index++;
			}
		}
		length++;
	}
	
	// append(Object data)
	// Insert new element into this List. If List is non-empty,
	// insertion takes place after back element
	void append(Object data) {
		Node N = new Node(data);
		if (length == 0) {
			front = back = N;
		} else {
			if (length == 1) {
				N.prev = back;
				back.next = N;
				front = back;
				back = N;
			} else {
				N.prev = back;
				back.next = N;
				back = N;
			}
		}
		length++;
	}
	
	// insertBefore(Object data)
	// Insert new element before cursor.
	// Pre: length() > 0, index() >= 0
	void insertBefore(Object data) {
		if (length() <= 0) {
			throw new RuntimeException(
					"List Error: insertBefore() called on length <= 0");
		}
		if (index() < 0) {
			throw new RuntimeException(
					"List Error: insertBefore() called on index < 0");
		}
		if (cursor == front) {
			prepend(data);
			return;
		}
		Node before = cursor.prev;
		Node N = new Node(data);
		before.next = N;
		N.next = cursor;
		cursor.prev = N;
		N.prev = before;
		length++;
		index++;
	}

	// insertAfter(Object data)
	// Insert new element after cursor.
	// Pre: length() > 0, index() >= 0
	void insertAfter(Object data) {
		if (length() <= 0) {
			throw new RuntimeException(
					"List Error: insertAfter() called on length <= 0");
		}
		if (index() < 0) {
			throw new RuntimeException(
					"List Error: insertAfter() called on index < 0");
		}
		if (cursor == back) {
			append(data);
			return;
		}
		Node after = cursor.next;
		Node N = new Node(data);
		after.prev = N;
		N.prev = cursor;
		cursor.next = N;
		N.next = after;
		length++;
	}
	
	// deleteFront() 
	// Deletes the front element.
	// Pre: length() > 0
	void deleteFront() {
		if (length() <= 0) {
			throw new RuntimeException(
					"List Error: deleteFront() called on length() <= 0");
		}
		if (cursor == front) {
			cursor = null;
			index = -1;
		}
		if (length() == 1) {
			front = back = null;
			index = -1;
		} else {
			if (length() == 2) {
				front = back;
				front.prev = null;
				front.next = null;
			} else {
				front = front.next;
				front.prev = null;
			}
			if (cursor != null) {
				index--;
			}
		}
		length--;
	}
	
	// deleteBack()
	// Deletes the back element.
	// Pre: length() > 0
	void deleteBack() {
		if (length() <= 0) {
			throw new RuntimeException(
					"List Error: deleteBack() called on length() <= 0");
		}
		if (cursor == back) {
			cursor = null;
			index = -1;
		}
		if (length() == 1) {
			front = back = null;
			index = -1;
		} else {
			if (length() == 2) {
				back = front;
				front.prev = null;
				front.next = null;
			} else {
				back = back.prev;
				back.next = null;
			}
		}
		length--;
	}
	
	// delete() 
	// Deletes cursor element, making cursor undefined.
	// Pre: length() > 0; index() >= 0
	void delete() {
		if (length() <= 0) {
			throw new RuntimeException(
					"List Error: delete() called on length <= 0");
		}
		if (index() < 0) {
			throw new RuntimeException(
					"List Error: delete() called on index < 0");
			
		}
		if (cursor == front) {
			deleteFront();
			return;
		}
		if (cursor == back) {
			deleteBack();
			return;
		}
		Node previous = cursor.prev;
		Node after = cursor.next;
		previous.next = after;
		after.prev = previous;
		length--;
		cursor = null;
		index = -1;
	}
	
	// Other methods ---------------------------------------------------
	// -----------------------------------------------------------------
	
	// toString()
	// Overrides Object's toString() method. Returns a string
	// representation of this List consisting of a space 
	// separated sequence of object data, with front on left.
	public String toString() {
		String s = "";
		Node N = front;
		while (N != null) {
			s = s + N.data + " ";
			N = N.next;
		}
		return s;
	}
}
