

@SuppressWarnings("overrides")
class Matrix {
	
	@SuppressWarnings("overrides")
	private class Entry {
		// Fields
		int column;
		double value;
		
		// Constructor
		Entry (int column, double value) {
			this.column = column;
			this.value = value;
		}
		
		// overrides Object's equals() method
		public boolean equals(Object x) {
			if (x instanceof Entry) {
				Entry y = (Entry) x;
				if (this.column == y.column &&
						this.value == y.value) {
					return true;
				}
			}
			return false;
		}
		
		// returns column, value
		public String toString() {
			return column + ", " + value;
		}
		
	}
	
	// Fields 
	private int n;
	private int nnz;
	private List[] Mat;
	
	// Matrix()
	// Constructor
	// Makes a new n x n zero Matrix
	// Pre: n >= 1
	Matrix(int n) {
		if (n < 1) {
			throw new RuntimeException(
				"Matrix Error: Constructor called with n < 1");
		}
		this.n = n;
		this.nnz = 0;
		Mat = new List[n];
		for (int i = 0; i < n; i++) {
			Mat[i] = new List();

 		}
		
	}
	
	// Access Functions --------------------------------------------------------
	// -------------------------------------------------------------------------
	
	// Returns n, the number of rows and columns of this Matrix
	int getSize() {
		return n;
	}
	
	// Returns the number of non-zero entries in this Matrix
	int getNNZ() {
		return nnz;
	}
	
	// overrides Object's equals() method
	public boolean equals(Object x) {
		if (x instanceof Matrix == false) {
			return false;
		}
		Matrix y = (Matrix) x;
		if (y.n != n) {
			return false;
		}
		for (int i = 0; i < n; i++) {
			Mat[i].moveFront();
			y.Mat[i].moveFront();
			if (Mat[i].length() != y.Mat[i].length()) {
				return false;
			}
			while (Mat[i].index() >= 0 && y.Mat[i].index() >= 0) {
				if (Mat[i].get().equals(y.Mat[i].get()) == false) {
					return false;
				}
				Mat[i].moveNext();
				y.Mat[i].moveNext();
			}
		}
		return true;
	}

	// Manipulation Procedures --------------------------------------------
	// --------------------------------------------------------------------
	
	// sets this Matrix to the zero state
	void makeZero() {
		for (int i = 0; i < n; i++) {
			Mat[i].clear();
		}
		this.nnz = 0;
	}
	
	// returns a new Matrix having the same entries as this Matrix
	Matrix copy() {
		Matrix m = new Matrix(n);
		for (int i = 0; i < n; i++) {
			Mat[i].moveFront();
			while (Mat[i].index() >= 0) {
				String[] s = Mat[i].get().toString().split(",");
				m.changeEntry(i + 1, Integer.parseInt(s[0]), Double.parseDouble(s[1]));
				Mat[i].moveNext();
			}
			
		}
		return m;
	}
	
	// changes ith row, jth column of this Matrix to x
	// Pre: 1 <= i <= getSize(), 1 <= j <= getSize()
	void changeEntry(int i, int j, double x) {
		if (i < 1 || i > getSize()) {
			throw new RuntimeException(
				"Matrix Error: changeEntry() called with i < 1 || i > getSize()");
		}
		if (j < 1 || j > getSize()) {
			throw new RuntimeException(
				"Matrix Error: changeEntry() called with j < 1 || i > getSize()");
		}
		
		int a = i - 1;
		// enter first element of each list
		if (Mat[a].length() <= 0) {
			if (x == (double) 0) {
				return;
			} else {
				Mat[a].append(new Entry(j, x));
				this.nnz++;
				return;
			}
		}
		
		// enter elements according to specifications
		Mat[a].moveFront();
		while (Mat[a].index() >= 0) {
			String[] s = Mat[a].get().toString().split(",");
			if (Integer.parseInt(s[0]) == j && x == (double) 0) {
				Mat[a].delete();
				this.nnz--;
				return;
			}
			if (Integer.parseInt(s[0]) == j) {
				Mat[a].insertAfter(new Entry(j, x));
				Mat[a].delete();
				return;
			}
			if (j < Integer.parseInt(s[0]) && x != (double) 0) {
				Mat[a].insertBefore(new Entry(j, x));
				this.nnz++;
				return;
			} else {
				if (Mat[a].index() < 0 || Mat[a].get() == Mat[a].back() && x != (double) 0) {
					Mat[a].append(new Entry(j, x));
					this.nnz++;
					return;
				}
			}
			Mat[a].moveNext();
		}
		if (x != (double) 0) {
			Mat[a].insertAfter(new Entry(j, x));
			this.nnz++;
		}
	}
	
	// returns a new Matrix that is the scalar product of this Matrix with x
	Matrix scalarMult(double x) {
		Matrix m = this.copy();
		for (int i = 0; i < n; i++) {
			Mat[i].moveFront();
			while (Mat[i].index() >= 0) {
				String[] s = Mat[i].get().toString().split(",");
				m.changeEntry(i + 1, Integer.parseInt(s[0]), Double.parseDouble(s[1]) * x);
				Mat[i].moveNext();
				
			}
		}
		return m;
	}
	
	// helper function for add/sub
	private List listAdd(List L, List Q, List S) {
		if (L.length() <= 0 && Q.length() <= 0) {
			return S;
		}
		L.moveFront();
		Q.moveFront();
		while (L.index() >= 0 || Q.index() >= 0) {
			if (L.index() < 0) {
				S.append(Q.get());
				Q.moveNext();
				continue;
			}
			if (Q.index() < 0) {
				S.append(L.get());
				L.moveNext();
				continue;
			}
			String[] a = L.get().toString().split(",");
			String[] b = Q.get().toString().split(",");
			// append to List with summed value
			if (Integer.parseInt(a[0]) == Integer.parseInt(b[0])) {
					S.append(new Entry (Integer.parseInt(a[0]), 
							Double.parseDouble(a[1]) + Double.parseDouble(b[1])));	
				L.moveNext();
				Q.moveNext();
				continue;
			}
			// append to List with value + 0
			if (Integer.parseInt(a[0]) < Integer.parseInt(b[0])) {
				S.append(L.get());
				L.moveNext();
			} else {
				S.append(Q.get());
				Q.moveNext();
			}
		} 
		return S;
	}
	

	// returns a new Matrix that is the sum of this Matrix with M
	// Pre: getSize() == M.getSize()
	Matrix add(Matrix M) {
		if (getSize() != M.getSize()) {
			throw new RuntimeException(
					"Matrix Error: add() called with getSize() != M.getSize()");
		}
		Matrix s = new Matrix(n);
		Matrix t = M.copy();
		for (int i = 0; i < n; i++) {
			List L = listAdd(this.Mat[i], t.Mat[i], new List());
			L.moveFront();
			while(L.index() >= 0) {
				s.changeEntry(i + 1, Integer.parseInt(L.get().toString().split(",")[0]), Double.parseDouble(L.get().toString().split(",")[1]));
				L.moveNext();
			}
			
		}
		return s;
	}
	
	
	// returns a new Matrix that is the difference of this Matrix with M
	// Pre: getSize() == M.getSize()
	Matrix sub(Matrix M) {
		if (getSize() != M.getSize()) {
			throw new RuntimeException(
					"Matrix Error: sub() called with getSize() != M.getSize()");
		}
		Matrix m = M.scalarMult(-1);
		Matrix result = this.add(m);
		return result;
	}
	
	// returns a new Matrix that is the transpose of this Matrix
	Matrix transpose() {
		Matrix m = new Matrix(n);
		for (int i = 0; i < n; i++) {
			Mat[i].moveFront();
			while (Mat[i].index() >= 0) {
				String[] s = Mat[i].get().toString().split(",");
				m.changeEntry(Integer.parseInt(s[0]), i + 1, Double.parseDouble(s[1]));
				Mat[i].moveNext();
			}
			
		}
		return m;
	}
	
	// helper function for mult
	private static double dot(List L, List Q) {
		double sum = 0;
		L.moveFront();
		Q.moveFront();
		while (L.index() >= 0 && Q.index() >= 0) {
			if (Integer.parseInt(L.get().toString().split(",")[0]) == 
					Integer.parseInt(Q.get().toString().split(",")[0])) {
						sum = sum + Double.parseDouble(L.get().toString().split(",")[1]) * 
								Double.parseDouble(Q.get().toString().split(",")[1]);
			}
			if (Integer.parseInt(L.get().toString().split(",")[0]) < 
					Integer.parseInt(Q.get().toString().split(",")[0])) {
				L.moveNext();
			} else {
				Q.moveNext();
			}
		}
		return sum;
	}
	
	// returns a new Matrix that is the product of this Matrix with M
	// Pre: getSize() == M.getSize()
	Matrix mult(Matrix M) {
		if (getSize() != M.getSize()) {
			throw new RuntimeException(
					"Matrix Error: mult() called with getSize() != M.getSize()");
		}
		Matrix t = M.transpose();
		Matrix s = new Matrix(n);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s.changeEntry(i + 1, j + 1, dot(this.Mat[i], t.Mat[j]));
			}
		}
		return s;
	}
	
	// Other methods ---------------------------------------------------
	// -----------------------------------------------------------------
	
	// overrides Object's toString() method
	public String toString() {
		String s = "";
		for (int i = 0; i < n; i++) {
			if (Mat[i].length() <= 0) {
				continue;
			}
			Mat[i].moveFront();
			s = s + (i + 1) + ": ";
			while(Mat[i].index() > -1) {
				s = s + "(" + Mat[i].get().toString() + ") ";
				Mat[i].moveNext();
			}
			s = s + "\n";
		}
		return s;
	}
}
