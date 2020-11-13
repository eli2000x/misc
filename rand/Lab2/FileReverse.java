// Name: Erik Li
// CruzID: ersli
// Role: Practice parse and reverse
// File Name: FileReverse.java


import java.io.*;
import java.util.Scanner;

public class FileReverse {
	
	public static void main(String[] args) throws IOException{
		// Check for < 2 command arguments
		if (args.length < 2) {
			System.out.println("Usage: java -jar FileReverse.jar <input file> <output file>");
			System.exit(1);
		}
		
		// open files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		// read lines from in, extract and print tokens in reverse
		while (in.hasNextLine()) {
			
			// trim leading and trailing spaces, add one space so split() works on blank lines
			String line = in.nextLine().trim() + " ";
			

			// split line around white spaces
			String[] token = line.split("\\s+");
			
			
			// print out tokens in reverse
			int n = token.length;
			for (int i = 0; i < n; i++) {
				out.println(stringReverse(token[i]));
			}
		}
		
		// close files
		in.close();
		out.close();
	}
	
	// reverse characters
	public static String stringReverse(String s) {
		String str = "";
		// loop through String s and add chars to str
		for (int i = s.length() - 1; i >= 0; i--) {
			str = str + s.charAt(i);
		}
		return str;
	}
}