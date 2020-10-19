//-----------------------------------------------------------------------------
//  Name: Erik Li
//  CruzID: ersli
//  Assignment: pa3
//  Sparse.java
//  Client file for Matrix.java
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.*;

public class Sparse {

	public static void main(String[] args) throws IOException {
		
		// check for command line arguments != 2
		if (args.length != 2) {
			System.err.println("Number of command line arguments != 2");
		}
		
		// open files
		Scanner in = new Scanner (new File(args[0]));
		PrintWriter out = new PrintWriter (new FileWriter(args[1]));
		
		// handle first line of input file
		String line = in.nextLine();
		in.nextLine();
		String[] arr = line.split(" ");
		int n = Integer.parseInt(arr[0]);
		int lineA = Integer.parseInt(arr[1]);
		int lineB = Integer.parseInt(arr[2]);
		
		// create Matrices
		Matrix A = new Matrix(n);
		Matrix B = new Matrix(n);
		
		// fill in Matrix A
		for (int i = lineA; i > 0; i--) {
			String input = in.nextLine();
			A.changeEntry(Integer.parseInt(input.split(" ")[0]), 
					Integer.parseInt(input.split(" ")[1]), 
					Double.parseDouble(input.split(" ")[2]));                 
		}
		in.nextLine();
		
		// fill in Matrix B
		for (int j = lineB; j > 0; j--) {
			String file = in.nextLine();
			B.changeEntry(Integer.parseInt(file.split(" ")[0]), 
					Integer.parseInt(file.split(" ")[1]), 
					Double.parseDouble(file.split(" ")[2]));   
		}
		
		// print non-zero entries in A
		out.println("A has " + A.getNNZ() + " non-zero entries:");
		out.println(A.toString());
		
		// print non-zero entries in B
		out.println("B has " + B.getNNZ() + " non-zero entries:");
		out.println(B.toString());
		
		// print (1.5)*A
		out.println("(1.5)*A =");
		Matrix scalarMult = A.scalarMult(1.5);
		out.println(scalarMult.toString());
		
		// print A + B
		out.println("A+B =");
		Matrix add1 = A.add(B);
		out.println(add1.toString());
		
		// print A + A
		out.println("A+A =");
		Matrix cop = A.copy();
		Matrix add2 = cop.add(A);
		out.println(add2.toString());
		
		// print B - A
		out.println("B-A =");
		Matrix sub1 = B.sub(A);
		out.println(sub1.toString());
		
		// print A - A
		out.println("A-A =");
		Matrix cop1 = A.copy();
		Matrix sub2 = cop1.sub(A);
		out.println(sub2.toString());
		
		// print Transpose(A)
		out.println("Transpose(A) =");
		Matrix transpose = A.transpose();
		out.println(transpose.toString());
		
		// print A * B
		out.println("A*B =");
		Matrix mult1 = A.mult(B);
		out.println(mult1.toString());
		
		// print B * B
		out.println("B*B =");
		Matrix cop2 = B.copy();
		Matrix mult2 = cop2.mult(B);
		out.println(mult2.toString());
		
		// close files
		in.close();
		out.close();
	}
}
