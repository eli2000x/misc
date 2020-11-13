// Name: Erik Li
// CruzID: ersli
// Role: Class with main method that takes in list of moves and determines legality
// File Name: ChessMoves.java

import java.io.*;
import java.util.*;

public class ChessMoves {
	
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
		
		// perform operations for each line of input file
		while (in.hasNextLine()) {
			list = new LinkedList(); // creates LinkedList
			String line = in.nextLine(); // line variable to hold input lines
			if (line.equals(" ") || line.equals("")) {
				out.println(" ");
				continue;
			}
			String[] splitByColon = line.split(":"); // split line by the semicolon
			String[] beforeColon = splitByColon[0].split(" "); // split info before semicolon by spaces
			String[] afterColonWithSpace = splitByColon[1].split(" "); // split info after semicolon by spaces
			String[] afterColon = new String[afterColonWithSpace.length - 1]; // new array without the space after the semicolon
			
			// gets afterColon array which doesn't have space after the semicolon
			for (int i = 1; i < afterColonWithSpace.length; i++) {
				afterColon[i - 1] = afterColonWithSpace[i];
			}
			
			// get 3 String arrays for the piece, col, and row of input line
			String[] piece = new String[beforeColon.length / 3];
			String[] col = new String[beforeColon.length / 3];
			String[] row = new String[beforeColon.length / 3];
			
			int i = 0; // iterator i
			int piececount = 0; // count for piece
			int colcount = 0; // count for column
			int rowcount = 0; // count for row
			
			// traverse beforeColon array and set according string to element in array
			while (i < beforeColon.length) {
				piece[piececount] = beforeColon[i];
				piececount++;
				i++;
				col[colcount] = beforeColon[i];
				colcount++;
				i++;
				row[rowcount] = beforeColon[i];
				rowcount++;
				i++;
			}
			
			// adds all pieces into the LinkedList
			for (int a = 0; a < piece.length; a++) {
				int x = Integer.parseInt(col[a]);
				int y = Integer.parseInt(row[a]);
				String z = piece[a];
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
			
			boolean attacked = false; // boolean for pawn case
			boolean done = false; // boolean to break out of nested for loop
			boolean blocked = false; // boolean if a piece is blocked
			int startcol = 0; // index for starting column
			int startrow = 1; // index for starting row
			int endcol = 2; // index for ending column
			int endrow = 3; // index for ending row
			int previousColor = 0; // stores previous color, determines alternating moves
			int currentColor = 0; // stores current color
			int numBPawns = 0; // stores number of black pawns
			int numWPawns = 0; // stores number of white pawns
			int numWKings = 0; // stores number of white kings
			int numBKings = 0; // stores number of black kings
			String[] path; // path variable that stores output of move() function
			// first piece to determine if white moves first
			String firstPiece = find(Integer.parseInt(afterColon[startcol]), Integer.parseInt(afterColon[startrow]));
			
			// Determine number of kings and pawns for checking legal board
			for (ChessPiece finder = list.head; finder != null; finder = finder.next) {
				if (finder.getName() == "K" && finder.getColor() == 0) {
					numBKings++;
				}
				if (finder.getName() == "K" && finder.getColor() == 1) {
					numWKings++;
				}
				if (finder.getName() == "P" && finder.getColor() == 0) {
					numBPawns++;
				}
				if (finder.getName() == "P" && finder.getColor() == 1) {
					numWPawns++;
				}
			}
			
			// checks for exactly 1 king of each color and if numPawns > 8
			if (numWKings != 1 || numBKings != 1 || numBPawns > 8 || numWPawns > 8) {
				out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
				// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, not a legal board");
				continue;
			}
			
			// iterates over the moves after the semicolon
			for (int j = 0; j < afterColon.length / 4; j++, startcol += 4, startrow += 4, endcol += 4, endrow += 4) {
			
				attacked = false; // initializes attacked to false
				// stores output of find() function
				String foundPiece = find(Integer.parseInt(afterColon[startcol]), Integer.parseInt(afterColon[startrow]));
				
				// get a reference to piece in question
				ChessPiece marker;
				for (marker = list.head; marker != null; marker = marker.next) {
					if (marker.getCol() == Integer.parseInt(afterColon[startcol]) && marker.getRow() == Integer.parseInt(afterColon[startrow])) {
						break;
					}
				}
				
				// checks if piece is on the board
				if (foundPiece == "-") {
					out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
					// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, cannot find piece");
					break;
				} 
				
				// checks if white moves first
				if (firstPiece.equals(firstPiece.toUpperCase())) {
					out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
					// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, first move is not white");
					break;
				} 
				
				// sets currentColor to 0 for black 1 for white
				if (foundPiece.equals(foundPiece.toUpperCase())) {
					currentColor = 0;
				} else {
					currentColor = 1;
				}
				
				// checks for alternating moves
				if (currentColor == previousColor) {
					out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
					// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, does not alternate moves");
					break;
				}
				previousColor = currentColor; // set previousColor to current for next move
				
				int captureColor = 0; // color of piece for potential capture
				// String[] with the path traveled by piece
				path = move(Integer.parseInt(afterColon[startcol]), Integer.parseInt(afterColon[startrow]), Integer.parseInt(afterColon[endcol]), Integer.parseInt(afterColon[endrow]));
				
				// checks for blocking of any color
				for (int p = 0; p < path.length - 1; p++) {
					if (find(Character.getNumericValue(path[p].charAt(0)), Character.getNumericValue(path[p].charAt(1))) != "-") {
						out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
						// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, being blocked");
						done = true;
						break;
					}
					
					
				}
				
				// break out of loop and move to next line if piece is blocked
				if (done) {
					break;
				}
			
				// if piece is not knight or pawn since these have different move() paths
				if (marker.getName() != "N" && marker.getName() != "P") {
					// path.length == 0 means piece cannot move to certain spot
					if (path.length == 0) {
						out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
						// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, piece cannot move to final spot");
						break;
					}
					
					// sees if the finalcol and finalrow is empty
					if (find(Character.getNumericValue(path[path.length - 1].charAt(0)), Character.getNumericValue(path[path.length - 1].charAt(1))) != "-") {
						
						// sets captureColor to 0 if piece is black, 1 if white
						if (find(Character.getNumericValue(path[path.length - 1].charAt(0)), Character.getNumericValue(path[path.length - 1].charAt(1))).
								equals(find(Character.getNumericValue(path[path.length - 1].charAt(0)), Character.getNumericValue(path[path.length - 1].charAt(1))).toUpperCase())) {
							captureColor = 0;
						} else {
							captureColor = 1;
						}
						
						// checks if piece can possibly capture piece at final position, delete that piece if possible
						if (currentColor != captureColor) { 
							list.delete(Character.getNumericValue(path[path.length - 1].charAt(0)), Character.getNumericValue(path[path.length - 1].charAt(1)));
						} else {
							out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
							// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, final spot is blocked by piece of same color");
							break;
						}
					}
				} else {
					// if piece is a knight
					if (marker.getName() == "N") {
						
						// sees if the finalcol and finalrow is empty
						if (find(Integer.parseInt(afterColon[endcol]), Integer.parseInt(afterColon[endrow])) != "-") {
							
							// sets captureColor to 0 if piece is black, 1 if white
							if (find(Integer.parseInt(afterColon[endcol]), Integer.parseInt(afterColon[endrow])).
									equals(find(Integer.parseInt(afterColon[endcol]), Integer.parseInt(afterColon[endrow])).toUpperCase())) {
								captureColor = 0;
							} else {
								captureColor = 1;
							}
							
							// checks if piece can possibly capture piece at final position, delete that piece if possible
							if (currentColor != captureColor) {
								list.delete(Integer.parseInt(afterColon[endcol]), Integer.parseInt(afterColon[endrow]));
			
							} else {
								out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
								// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, final spot is blocked by piece of same color");
								break;
							}
						}
					} else {
						// piece is a pawn, sees if the finalcol and finalrow is empty
						if (find(Integer.parseInt(afterColon[endcol]), Integer.parseInt(afterColon[endrow])) != "-") {
							
							// sets captureColor to 0 if piece is black, 1 if white
							if (find(Integer.parseInt(afterColon[endcol]), Integer.parseInt(afterColon[endrow])).
									equals(find(Integer.parseInt(afterColon[endcol]), Integer.parseInt(afterColon[endrow])).toUpperCase())) {
								captureColor = 0;
							} else {
								captureColor = 1;
							}
							
							// checks if piece can possibly capture piece at final position, delete that piece is possible
							if (currentColor != captureColor) {
								
								// handles appropriate movement for black and white pawns
								if (currentColor == 0) {
									if (marker.getCol() - 1 == Integer.parseInt(afterColon[endcol]) && marker.getRow() - 1 == Integer.parseInt(afterColon[endrow]) || 
											marker.getCol() + 1 == Integer.parseInt(afterColon[endcol]) && marker.getRow() - 1 == Integer.parseInt(afterColon[endrow])) {
										list.delete(Integer.parseInt(afterColon[endcol]), Integer.parseInt(afterColon[endrow]));
										attacked = true;
									} 
								} else {
									if (marker.getCol() - 1 == Integer.parseInt(afterColon[endcol]) && marker.getRow() + 1 == Integer.parseInt(afterColon[endrow]) || 
											marker.getCol() + 1 == Integer.parseInt(afterColon[endcol]) && marker.getRow() + 1 == Integer.parseInt(afterColon[endrow])) {
										list.delete(Integer.parseInt(afterColon[endcol]), Integer.parseInt(afterColon[endrow]));
										attacked = true;
									} 
								}
							} else {
								out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
								// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, final spot is blocked by piece of same color, pawn case");
								break;
							}
						}
					}
					
				}
				
				// creates dummy chesspiece of color opposite currentcolor
				ChessPiece c = new ChessPiece(Integer.parseInt(afterColon[endcol]), Integer.parseInt(afterColon[endrow]), Math.abs(currentColor - 1));
				
				// special case for pawn, checks if pawn can either move one space forward or capture diagonally
				if (marker.getName() == "P") {
					if (currentColor == 0) {
						if (!attacked) {
							if (marker.getRow() - 1 == Integer.parseInt(afterColon[endrow]) && marker.getCol() == Integer.parseInt(afterColon[endcol]) ||
									(marker.getRow() == 7 && marker.getRow() - 2 == Integer.parseInt(afterColon[endrow]) && marker.getCol() == Integer.parseInt(afterColon[endcol]))) {
								marker.setCol(Integer.parseInt(afterColon[endcol]));
								marker.setRow(Integer.parseInt(afterColon[endrow]));
								
							} else {
								out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
								// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, piece is not allowed to move to spot");
								break;
							}
						} else {
							if (marker.isAttacking(c)) {
								marker.setCol(Integer.parseInt(afterColon[endcol]));
								marker.setRow(Integer.parseInt(afterColon[endrow]));
							} else {
								out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
								// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, piece is not allowed to move to spot");
								break;
								
							}
						}
						
					} else {
						if (!attacked) {
							if (marker.getRow() + 1 == Integer.parseInt(afterColon[endrow]) && marker.getCol() == Integer.parseInt(afterColon[endcol]) ||
									(marker.getRow() == 2 && marker.getRow() + 2 == Integer.parseInt(afterColon[endrow]) && marker.getCol() == Integer.parseInt(afterColon[endcol]))) {
								marker.setCol(Integer.parseInt(afterColon[endcol]));
								marker.setRow(Integer.parseInt(afterColon[endrow]));
							} else {
								out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
								// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, piece is not allowed to move to spot");
								break;
							}
						} else {
							if (marker.isAttacking(c)) {
								marker.setCol(Integer.parseInt(afterColon[endcol]));
								marker.setRow(Integer.parseInt(afterColon[endrow]));
							} else {
								out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
								// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, piece is not allowed to move to spot");
								break;
								
							}
						}
						
					}
					//System.out.println(marker.getCol());
					//System.out.println(marker.getRow());
				} 
				
				// case for all piece not pawns
				if (marker.getName() != "P") {
					if (marker.isAttacking(c)) { 
						marker.setCol(Integer.parseInt(afterColon[endcol]));
						marker.setRow(Integer.parseInt(afterColon[endrow]));
						
				
					} else {
						out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
						// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, piece is not allowed to move to spot");
						break;
					}
				}
 				
				// reference to king of currentColor
				ChessPiece traverse;
				for (traverse = list.head; traverse != null; traverse = traverse.next) {
					if (traverse.getColor() == currentColor && traverse.getName().equals("K")) {
						break;
					}
				}
				
				// traverses through LinkedList and checks to see if a piece is attacking king
				for (ChessPiece run = list.head; run != null; run = run.next) {
					blocked = false;
					if (run.equals(traverse)) {
						continue;
					}
					if (run.isAttacking(traverse)) {
						path = move(run.getCol(), run.getRow(), traverse.getCol(), traverse.getRow());
						for (int p = 0; p < path.length - 1; p++) {
							if (find(Character.getNumericValue(path[p].charAt(0)), Character.getNumericValue(path[p].charAt(1))) != "-") {
								blocked = true;
								break;
							}
						}
						if (blocked) {
							continue;
						}
						out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal");
						// out.println(afterColon[startcol] + " " + afterColon[startrow] + " " + afterColon[endcol] + " " + afterColon[endrow] + " illegal, king in check after move");
						done = true;
						break;
					}
				}
				if (done) {
					break;
				}
			
				// print legal only if on last iteration and reach this point
				if (afterColon.length / 4 - j == 1) {
					out.println("legal");
				}
			}
			continue;
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
	
	// finds path traveled by a piece from start position to end position
	public static String[] move(int initCol, int initRow, int finalCol, int finalRow) {
		String path = "";
		if (initCol == finalCol) {
			if (initRow > finalRow) {
				for (int i = initRow - 1; i >= finalRow; i--) {
					path = path + "" + initCol + " " + i + " ";
				}
			} else {
				for (int i = initRow + 1; i <= finalRow; i++) {
					path = path + "" + initCol + " " + i + " ";
				}
			}
		}
		if (initRow == finalRow) {
			if (initCol > finalCol) {
				for (int i = initCol - 1; i >= finalCol; i--) {
					path = path + "" + i + " " + initRow + " ";
				}
			} else {
				for (int i = initCol + 1; i <= finalCol; i++) {
					path = path + "" + i + " " + initRow + " ";
				}
			}
		}
		if (Math.abs(initRow - finalRow) == Math.abs(initCol - finalCol)) {
			if (initRow > finalRow) {
				if (initCol > finalCol) {
					int j = initCol - 1;
					for (int i = initRow - 1; i >= finalRow; i--) {
						path = path + "" + j + " " + i + " ";
						j--;
					}
				} else {
					int j = initCol + 1;
					for (int i = initRow - 1; i >= finalRow; i--) {
						path = path + "" + j + " " + i + " ";
						j++;
					}
				}
			} else {
				if (initCol > finalCol) {
					int j = initCol - 1;
					for (int i = initRow + 1; i <= finalRow; i++) {
						path = path + "" + j + " " + i + " ";
						j--;
					}
				} else {
					int j = initCol + 1;
					for (int i = initRow + 1; i <= finalRow; i++) {
						path = path + "" + j + " " + i + " ";
						j++;
					}
				}
			}
		}
		
		String[] length = path.split(" ");
		String[] stringPath = new String[length.length / 2];
		int index = 0;
		for (int i = 0; i < stringPath.length; i++) {
			stringPath[i] = length[index] + "" + length[index + 1];
			index = index + 2;
		}
		return stringPath;
	}
}


