

public class MatrixTest {
	
	public static void main(String[] args) {
		
		// changeEntry() unsorted
		Matrix m = new Matrix(3);
		m.changeEntry(3, 2, 8.0);
		m.changeEntry(1, 3, 3.0);
		m.changeEntry(2, 1, 4.0);
		m.changeEntry(2, 3, 6.0);
		m.changeEntry(3, 1, 7.0);
		m.changeEntry(1, 2, 2.0);
		m.changeEntry(3, 3, 9.0);
		m.changeEntry(2, 2, 5.0);
		m.changeEntry(1, 1, 1.0);
		
		// getNNZ()/getSize()/toString()/scalarMult()/transpose()
		System.out.println(m.getNNZ());
		System.out.println(m.getSize());
		System.out.println(m.toString());
		Matrix o = m.copy();
		System.out.println(o.toString());
		Matrix p = m.transpose();
		System.out.println(p.toString());
		m.scalarMult(20.0);
		System.out.println(m.toString());
		
		// mult()/add()
		Matrix mat = new Matrix(3);
		mat.changeEntry(1, 1, 5.0);
		mat.changeEntry(1, 2, 6.0);
		mat.changeEntry(2, 1, 7.0);
		mat.changeEntry(2, 2, 8.0);
		System.out.println(mat.toString());
		
		Matrix mul = m.mult(mat);
		Matrix add = mat.add(m);
		System.out.println(mul.toString());
		System.out.println(add.toString());
		
		// changeEntry() ordered
		Matrix n = new Matrix(3);
		m.changeEntry(1, 1, 1.0);
		m.changeEntry(1, 2, 2.0);
		m.changeEntry(1, 3, 3.0);
		m.changeEntry(2, 1, 4.0);
		m.changeEntry(2, 2, 5.0);
		m.changeEntry(2, 3, 6.0);
		m.changeEntry(3, 1, 7.0);
		m.changeEntry(3, 2, 8.0);
		m.changeEntry(3, 3, 9.0);
		
		Matrix q = new Matrix(3);
		n.changeEntry(1, 1, 1.0);
		n.changeEntry(1, 3, 1.0);
		n.changeEntry(2, 2, 1.0);
		n.changeEntry(3, 1, 1.0);
		n.changeEntry(3, 2, 1.0);
		n.changeEntry(3, 3, 1.0);
		
		// add()/toString()
		System.out.println(n.toString());
		System.out.println(q.toString());
		Matrix sum = n.add(q);
		System.out.println(sum.toString());
		
		// scalarMult()/copy()
		Matrix i = new Matrix(3);
		i.changeEntry(1, 1, 1.0);
		i.changeEntry(1, 2, 2.0);
		i.changeEntry(1, 3, 3.0);
		i.changeEntry(2, 1, 4.0);
		i.changeEntry(2, 2, 5.0);
		i.changeEntry(2, 3, 6.0);
		i.changeEntry(3, 1, 7.0);
		i.changeEntry(3, 2, 8.0);
		i.changeEntry(3, 3, 9.0);
		Matrix j = i.copy();
		j.scalarMult(1.5);
		
		System.out.println(j.toString());
		System.out.println(i.toString());
		
		Matrix ma = new Matrix(3);
		ma.changeEntry(1, 1, 1.0);
		ma.changeEntry(1, 2, 2.0);
		ma.changeEntry(1, 3, 3.0);
		ma.changeEntry(2, 1, 4.0);
		ma.changeEntry(2, 2, 5.0);
		ma.changeEntry(2, 3, 6.0);
		ma.changeEntry(3, 1, 7.0);
		ma.changeEntry(3, 2, 8.0);
		ma.changeEntry(3, 3, 9.0);
		System.out.println(ma.toString());
		Matrix temp = ma.copy();
		Matrix out = temp.add(ma);
		System.out.println(out.toString());

		// sub()/toString()
		Matrix k = new Matrix(3);
		k.changeEntry(2, 2, 5.0);
		k.changeEntry(2, 3, 6.0);
		k.changeEntry(3, 1, 7.0);
		k.changeEntry(3, 2, 8.0);
		k.changeEntry(3, 3, 9.0);
		k.changeEntry(1, 1, 1.0);
		k.changeEntry(1, 2, 2.0);
		k.changeEntry(1, 3, 3.0);
		k.changeEntry(2, 1, 4.0);
		k.changeEntry(2, 1, 0.0);
		
		Matrix w = new Matrix(3);
		w.changeEntry(1, 1, 1.0);
		w.changeEntry(1, 3, 1.0);
		w.changeEntry(3, 1, 1.0);
		w.changeEntry(3, 2, 1.0);
		w.changeEntry(3, 3, 1.0);
		System.out.println(k.toString());
		System.out.println(w.toString());
		Matrix add1 = w.sub(k);
		
		System.out.println(add1.toString());
		Matrix t = new Matrix(3);
		t.changeEntry(1, 1, 1.0);
		t.changeEntry(1, 2, 2.0);
		t.changeEntry(1, 3, 3.0);
		System.out.println(t.toString());
		
		// makeZero()/equals()
		Matrix a = new Matrix(2);
		a.changeEntry(1, 1, 0.5);
		a.changeEntry(2, 2, 0.25);
		
		Matrix b = new Matrix(2);
		b.changeEntry(1, 1, 1.0);
		b.changeEntry(2, 2, 0.5);
		
		Matrix c = new Matrix(2);
		c.changeEntry(1, 1, 0.5);
		c.changeEntry(2, 2, 0.5);
		
		Matrix d = b.mult(c);
		System.out.println(d.toString());
		
		System.out.println(a.equals(d));
		
		a.makeZero();
		System.out.println(a.getNNZ());
		System.out.println(a.getSize());
		System.out.println(a.toString());
	
	}
}
