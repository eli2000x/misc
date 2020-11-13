// Name: Erik Li
// CruzID: ersli
// Role: Class with main method that takes info from input file and prints to out
// File Name: ChessBoard.java

import java.io.*;
import java.util.*;

public class ChessBoard {
	
	public static LinkedList list;
	
	public static void main(String[] args) throws IOException {
		
		// checks for < 2 command arguments
		if (args.length < 2) {
			System.out.println("Usage: java -jar ChessBoard.jar <input file> <output file>");
			System.exit(1);
		}
		
		// open files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		// ArrayList to store lines of input 
		ArrayList<String[]> inputs = new ArrayList<String[]>();
		
		// add each line of input file split by the semicolon
		while(in.hasNextLine()) {
			String line = in.nextLine();
			inputs.add(line.split(":"));
		}
		
		// create data structures for each line of input file
		// perform validity check/attacking/find and print to out
		for (int i = 0; i < inputs.size(); i++) {
			// create linkedlist
			list = new LinkedList();
			
			// checks if line is of appropriate syntax
			if (inputs.get(i).length < 2) {
				out.println("Invalid");
				continue;
			}
			
			// string array for row and column input
			String[] beforeColon = inputs.get(i)[0].split(" ");
			
			// string array for board
			String[] afterColon = inputs.get(i)[1].split(" ");
			
			// ArrayList for board
			ArrayList<String> infos = new ArrayList<String>();
			
			// copy values in afterColon to infos
			for (int j = 1; j < afterColon.length; j++) {
				infos.add(afterColon[j]);
			}
		
			// ArrayLists for the piece, col, and row in each line of input file
			ArrayList<String> piece = new ArrayList<String>();
			ArrayList<String> col = new ArrayList<String>();
			ArrayList<String> row = new ArrayList<String>();
			int j = 0;
			
			// assign piece, row, and col to corresponding arraylist
			while (j < infos.size()) {
				piece.add(infos.get(j));
				j++;
				col.add(infos.get(j));
				j++;
				row.add(infos.get(j));
				j++;
			}
			
			// insert a ChessPiece accordingly
			for (int a = 0; a < piece.size(); a++) {
				int x = Integer.parseInt(col.get(a));
				int y = Integer.parseInt(row.get(a));
				String z = piece.get(a);
				switch (z.toLowerCase()) {
					case "q":
						if (z.equals("Q")) {
							ChessPiece c = new Queen(x, y, 0);
							list.insert(c);
						} else {
							ChessPiece c = new Queen(x, y, 1);
							list.insert(c);
						}
						break;
					case "k":
						if (z.equals("K")) {
							ChessPiece c = new King(x, y, 0);
							list.insert(c);
						} else {
							ChessPiece c = new King(x, y, 1);
							list.insert(c);
						}
						break;
					case "r":
						if (z.equals("R")) {
							ChessPiece c = new Rook(x, y, 0);
							list.insert(c);
						} else {
							ChessPiece c = new Rook(x, y, 1);
							list.insert(c);
						}
						break;
					case "b":
						if (z.equals("B")) {
							ChessPiece c = new Bishop(x, y, 0);
							list.insert(c);
						} else {
							ChessPiece c = new Bishop(x, y, 1);
							list.insert(c);
						}
						break;
					case "p":
						if (z.equals("P")) {
							ChessPiece c = new Pawn(x, y, 0);
							list.insert(c);
						} else {
							ChessPiece c = new Pawn(x, y, 1);
							list.insert(c);
						}
						break;
					default:
						if (z.equals("N")) {
							ChessPiece c = new Knight(x, y, 0);
							list.insert(c);
						} else {
							ChessPiece c = new Knight(x, y, 1);
							list.insert(c);
						}
				}
			}
			// first check for validity
			if (!isValid()) {
				out.println("Invalid");
			} else {
				// check for if piece is present
				if (find(Integer.parseInt(beforeColon[0]), Integer.parseInt(beforeColon[1])) == "-") {
					out.println("-");
				} else {
					ChessPiece marker;
					// traverse linkedlist until you reach piece in question
					for (marker = list.head; marker != null; marker = marker.next) {
						if (marker.getCol() == Integer.parseInt(beforeColon[0]) && marker.getRow() == Integer.parseInt(beforeColon[1])) {
							break;
						}
					}
					// call isAttacking() 
					if (isAttacking(marker)) {
						out.println(find(Integer.parseInt(beforeColon[0]), Integer.parseInt(beforeColon[1])) + " y");
					} else {
						out.println(find(Integer.parseInt(beforeColon[0]), Integer.parseInt(beforeColon[1])) + " n");
					}
				}
			}
		}
	
		in.close();
		out.close();
	}
	
	// checks for more than one piece per square
	public static boolean isValid() {
		ChessPiece current;
		ChessPiece runner;
	
		for (current = list.head; current != null; current = current.next) {
			for (runner = list.head; runner != null; runner = runner.next) {
				if (current.equals(runner)) {
					continue;
				}
				if (current.getCol() == runner.getCol() && current.getRow() == runner.getRow()) {
					return false;
				}
			}
		}
		return true;
	}
	
	// traverses linkedlist and returns piece is col and row matches
	public static String find(int col, int row) {
		ChessPiece current = list.head;
		while (current != null) {
			if (current.getCol() == col && current.getRow() == row) {
				if (current.getColor() == 0) {
					return current.getName();
				} else {
					return current.getName().toLowerCase();
				}
			}
			current = current.next;
		}
		return "-";
	}
	
	// calls each isAttacking function
	public static boolean isAttacking(ChessPiece other) {
		for (ChessPiece current = list.head; current != null; current = current.next) {
			if (other.equals(current)) {
				continue;
			}
			if (other.isAttacking(current)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
}




