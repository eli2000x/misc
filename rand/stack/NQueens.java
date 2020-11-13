// Name: Erik Li
// CruzID: ersli
// Role: HW4 - NQueens without Recursion
// File Name: NQueens.java

import java.io.*;
import java.util.*;


public class NQueens {

	// ArrayList to store all positions of queens
	private static ArrayList<int[]> queenPositions = new ArrayList<int[]>();

	// True when there is no solution
	private static boolean done = false;
	
	// True when the input queens are attacking each other
	private static boolean error = false;
	
	// True when error with input 
	private static boolean nosol = false;
	
	public static void main (String[] args) throws IOException {
		
		// Checks for less than two command line arguments
		if (args.length < 2) {
			System.out.println("Usage: java -jar FileReverse.jar <input file> <output file>");
			System.exit(1);
		}
		
		// open files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		// ArrayList of solutions
		ArrayList<String> solution = new ArrayList<String>();
		
		// keep running until there is nothing left in input file
		while(in.hasNextLine()) {
			// takes line of input file
			String line = in.nextLine();
			
			if (line.equals(" ") || line.equals("")) {
				solution.add("No solution");
				continue;
			}
			
			// splits line of input file by spaces
			String[] inputs = line.split(" ");
			
			// sets dimension to first int in line
			int dimension;
			
			try {
				dimension = Integer.parseInt(inputs[0]);
			} catch (ArrayIndexOutOfBoundsException a) {
				dimension = 1;
			} catch (NumberFormatException e) {
				dimension = 1;
			}
			
			
			// Arrays to hold input queen columns and rows
			int[] queenCol = new int[(inputs.length - 1) / 2];
			int[] queenRow = new int[(inputs.length - 1) / 2];
			
			// create nxn boolean board for checking
			boolean[][] board = new boolean[dimension][dimension];
			
			// appropriately assigns values to queenCol and queenRow
			int colCount = 0;
			int rowCount = 0;
			int i = 1;
			while (i < inputs.length) {
				try {
					queenCol[colCount] = Integer.parseInt(inputs[i]);
				} catch (NumberFormatException e) {
					queenCol[colCount] = -1;
				}
				
				i++;
				colCount++;
				
				try {
					queenRow[rowCount] = Integer.parseInt(inputs[i]);
				} catch (NumberFormatException e) {
					queenRow[rowCount] = -1;
				}
				
				i++;
				rowCount++;
			}
			
			// true when popped on last iteration
			boolean popped = false;
			
			// sets board to true, resets global variables
			reset(board);
			
			// handles bad inputs
			for (int d = 0; d < queenCol.length; d++) {
				if ((queenCol[d] - 1) < 0 || (queenRow[d] - 1) < 0 || (queenCol[d] - 1) >= dimension ||
						(queenRow[d] - 1) >= dimension || dimension < 4) {
					solution.add("No solution");
					nosol = true;
					break;
				}
			}
			
			if (nosol) {
				continue;
			}
			
			// places input queens onto board
			for (int x = 0; x < queenCol.length; x++) {
				placeQueen(board, queenRow[x] - 1, queenCol[x] - 1, dimension);
			}
		
			// checks for if the input queens are attacking each other
			for (int t = 0; t < queenCol.length; t++) {
				for (int c = 0; c < queenCol.length; c++) {
					if (t == c) {
						continue;
					}
					if (isAttacking(queenCol[t], queenRow[t], queenCol[c], queenRow[c])) {
						error = true;
						break;
					}
				}
				if (error) {
					break;
				}
			}
			if (error) {
				solution.add("No solution");
				continue;
			}
			
			// create stack
			Stack<StackVars> s = new Stack<StackVars>();
			
			// push first object so while loop is executed
			s.push(new StackVars(0, dimension, -1, dimension - 1));
			
			// while loop when stack is not empty
			while (!s.isEmpty()) {
				
				// keep popping when done 
				if (done) {
					s.pop();
					continue;
				}
				
				// get top of stack but don't pop
				StackVars top = s.peek();
				
				// when solution is found/determined
				if (top.queensPlaced == dimension) {
					done = true;
					continue;
				}
				
				// local variable to break and continue
				boolean complete = false;
				
				// popped means you are backtracking
				if (popped) {
					
					// iterate with j = afterrow (determined by previously popped index)
					for (int j = top.afterrow; j >= 0; j--) { 
						
						// removes queen in column when backtracking to clear column
						for (int z = 0; z < queenCol.length; z++) {
							if (isQueen(board, top.queensPlaced) && top.queensPlaced != queenCol[z] - 1) {
								removeQueen(board, top.queensPlaced, top.n);
							}
						}
						
						// places queen if spot on board is not being attacked
						if (board[j][top.queensPlaced] == true) {
							placeQueen(board, j, top.queensPlaced, top.n);
							s.push(new StackVars(top.queensPlaced + 1, top.n, j, dimension - 1));
							popped = false;
							complete = true;
							break;
						}
						
						// checks if there is a queen already in the column
						if(isQueen(board, top.queensPlaced)) {
							StackVars sta = s.pop();
							if (s.isEmpty()) {
								done = true;
								complete = true;
								break;
							}
							complete = true;
							popped = true;
							s.peek().afterrow = sta.prevrow - 1;
							break;
						}
						
					}
				} else {
					
					// regular iteration when not backtracking
					for (int j = top.n - 1; j >= 0; j--) { 
						
						// places queen if spot is not being attacked
						if (board[j][top.queensPlaced] == true) {
							placeQueen(board, j, top.queensPlaced, top.n);
							s.push(new StackVars(top.queensPlaced + 1, top.n, j, dimension - 1));
							popped = false;
							complete = true;
							break;
						}
						
						// checks if there is a queen already in the column
						if(isQueen(board, top.queensPlaced)) {
							s.push(new StackVars(top.queensPlaced + 1, top.n, -1, dimension - 1));
							popped = false;
							complete = true;
							break;
						}
						
					}
				}
				
				// goes to next iteration from inside for loop
				if (complete) {
					continue;
				}
				
				// local variable to break and continue
				boolean finished = false;
				
				// reach this point when queen needs to be removed
				// simply pops if queen to be removed is an input queen
				for (int z = 0; z < queenCol.length; z++) {
					if (top.queensPlaced - 1 == queenCol[z] - 1) {
						StackVars sta = s.pop();
						popped = true;
						if (s.isEmpty()) {
							done = true;
							complete = true;
							break;
						}
						s.peek().afterrow = sta.prevrow - 1;
						finished = true;
						break;
					} 
				}
				
				// goes to next iteration from inside for loop
				if(finished) {
					continue;
				}
				
				// remove queen and pop while setting aftterrow appropriately
				removeQueen(board, top.queensPlaced - 1, top.n);
				StackVars sta = s.pop();
				popped = true;
				if (s.isEmpty()) {
					done = true;
					complete = true;
					break;
				}
				s.peek().afterrow = sta.prevrow - 1;
			
			}
			
			// add each board based on instructions to solution array
			solution.add(toAString(queenCol.length));
			
		}
		
		// print solution array to out
		for (int s = 0; s < solution.size(); s++) {
			out.println(solution.get(s));
		}
		
		// close files
		in.close();
		out.close();
	}
	
	// places a queen on board[][] and marks appropriate spots to false
	public static void placeQueen(boolean[][] board, int row, int col, int n) {
		
		// set variables to row and col so that they can be manipulated, reset to row and col after each manipulation
		int k = row;
		int l = col;
		
		// sets all spots on board in a certain row to false
		for (int i = 0; i < n; i++) {
			board[i][l] = false;
		}
		
		// sets all spots on board in a certain column to false
		for (int j = 0; j < n; j++) {
			board[k][j] = false;
		}
		
		// sets all spots on board diagonal up + right to false
		k = row;
		l = col;
		board[k][l] = false;
		while (k != 0 && l + 1 < n) {
			board[k - 1][l + 1] = false;
			k--;
			l++;
		}
		
		// sets all spots on board diagonal down + right to false
		k = row;
		l = col;
		board[k][l] = false;
		while (k + 1 < n && l + 1 < n) {
			board[k + 1][l + 1] = false;
			k++;
			l++;
		}
		
		// sets all spots on board diagonal down + left to false
		k = row;
		l = col;
		board[k][l] = false;
		while (k + 1 < n && l != 0) {
			board[k + 1][l - 1] = false;
			k++;
			l--;
		}
		
		// sets all spots on board diagonal up + right to false
		k = row;
		l = col;
		board[k][l] = false;
		while (k != 0 && l != 0) {
			board[k - 1][l - 1] = false;
			k--;
			l--;
		}
		
		// add new queen to queenPositions ArrayList
		queenPositions.add(new int[] {row, col});
	}

	// removes a Queen in a certain column by re-adding all queens to queenPositions except the one in the given column
	public static void removeQueen(boolean[][] board, int col, int n) {
		
		// create a temporary ArrayList clone 
		ArrayList<int[]> clone = new ArrayList<int[]>();
		clone.clear();
		
		// copy contents of queenPositions into clone
		for (int i = 0; i < queenPositions.size(); i++) {
			clone.add(queenPositions.get(i));
		}
		queenPositions.clear();
		
		// reset the board to all true
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = true;
			}
		}
		
		// iteratively place queens from temporary Arraylist clone onto board, skip when on the column of the queen you want to remove
		for (int i = 0; i < clone.size(); i++) {
			if (clone.get(i)[1] == col) {
				continue;
			}
			// calls placeQueen with each queen except the one to be removed
			placeQueen(board, clone.get(i)[0], clone.get(i)[1], n);
		}
	}
	
	// checks if there is a queen in a certain column
	public static boolean isQueen(boolean[][] board, int col) {
		for (int i = 0; i < queenPositions.size(); i++) {
			if (queenPositions.get(i)[1] == col) {
				return true;
			}
		}
		return false;
	}
	
	// returns a string in proper format so that it can be easily added to solution ArrayList
	public static String toAString(int length) {
		
		String s = "";
		
		// After NQueens method, queenPositions only has location of first queen when there is no solution
		if (queenPositions.size() == 1 || queenPositions.size() == 0 || queenPositions.size() == length) {
			return "No solution";
		}
		
		// builds up string s with col + 1 and row + 1 to return locations that are (1 to n) indexed in order of increasing column
		for (int i = 0; i < queenPositions.size(); i++) {
			for (int j = 0; j < queenPositions.size(); j++) {
				if (queenPositions.get(j)[1] == i) {
					s = s + (queenPositions.get(j)[1] + 1) + " " + (queenPositions.get(j)[0] + 1) + " ";
					break;
				}
			}
		}
		return s;
		
	}

	// resets all global variables so that they don't take values from previous runs of NQueens
	public static void reset(boolean [][] board) {
		nosol = false;
		done = false;
		error = false;
		queenPositions.clear();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = true;
			}
		}
	}
	
	// checks if queens are attacking each other 
	public static boolean isAttacking(int col1, int row1, int col2, int row2) {
		// handles column
		if (col1 == col2) {
			return true;
		}
		
		// handles row
		if (row1 == row2) {
			return true;
		}
		
		// handles diagonals
		if (Math.abs(col1 - col2) == Math.abs(row1 - row2)) {
			return true;
		}
		return false;
	}
 	
}
