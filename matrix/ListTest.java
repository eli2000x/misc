

public class ListTest {

	public static void main(String[] args) {
		
		// List of length 1
		List L = new List();
		
		// length()/front()/back()/index()
		L.append("5");
		System.out.println(L.length());
		System.out.println(L.index());
		System.out.println(L.front().toString());
		System.out.println(L.back().toString());
		
		// toString()/prepend()/insertBefore()/get()
		L.moveFront();
		System.out.println(L.index());
		System.out.println(L.get().toString());
		L.prepend("4");
		System.out.println(L.length());
		System.out.println(L.index());
		System.out.println(L.toString());
		L.moveFront();
		L.insertBefore("3");
		System.out.println(L.length());
		System.out.println(L.index());
		System.out.println(L.toString());
		
		// List length != 1
		List M = new List();
		
		// append()
		M.append("1");
		M.append("2");
		M.append("3");
		M.append("4");
		M.append("5");
		M.append("6");
		System.out.println(M.length());
		System.out.println(M.index());
		System.out.println(M.front().toString());
		System.out.println(M.back().toString());
		
		// toString()/moveFront()/moveBack()/moveNext()/movePrev()
		M.moveFront();
		System.out.println(M.index());
		while (M.index() >= 0) {
			System.out.print(M.get().toString() + " ");
			M.moveNext();
		}
		System.out.println();
		M.moveBack();
		System.out.println(M.index());
		while (M.index() >= 0) {
			System.out.print(M.get().toString() + " ");
			M.movePrev();
		}
		
		// delete()
		System.out.println();
		M.moveFront();
		M.delete();
		System.out.println(M.length());
		System.out.println(M.index());
		M.moveBack();
		M.delete();
		
		// deleteBack()/deleteFront()/insertAfter()
		System.out.println(M.length());
		System.out.println(M.index());
		System.out.println(M.toString());
		M.deleteFront();
		System.out.println(M.length());
		System.out.println(M.index());
		System.out.println(M.toString());
		M.deleteBack();
		System.out.println(M.length());
		System.out.println(M.index());
		System.out.println(M.toString());
		M.moveBack();
		M.insertAfter("5");
		System.out.println(M.length());
		System.out.println(M.index());
		System.out.println(M.toString());
		
		// equals()/clear()
		System.out.println(L.equals(M));
		L.clear();
		System.out.println(L.toString());
		M.clear();
		System.out.println(M.toString());
		System.out.println(L.equals(M));
		
		
	}

}
