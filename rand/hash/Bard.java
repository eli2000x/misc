// Name: Erik Li
// CruzID: ersli
// Role: HW5 - Hashtables
// File Name: Bard.java

import java.io.*;
import java.util.*;

public class Bard {
	
	public static void main(String[] args) throws IOException{
		
		// check for less than 2 command line arguments
		if (args.length < 2) {
			System.out.println("Usage: java -jar FileReverse.jar <input file> <output file>");
			System.exit(1);
		}
		
		// scan shakespeare file
		File text = new File("shakespeare.txt");
		Scanner s = new Scanner(text);
		
		// create hashtable
		Hashtable<String, Integer> h = new Hashtable<String, Integer>();
		
		// break up text by line 
		while (s.hasNextLine()) {
			
			// get line from shakespeare.txt
			String l = s.nextLine();
			
			// replace certain characters with spaces
			l = l.replace("?", " ");
			l = l.replace(",", " ");
			l = l.replace(".", " ");
			l = l.replace("!", " ");
			l = l.replace(":", " ");
			l = l.replace(";", " ");
			l = l.replace("[", " ");
			l = l.replace("]", " ");
			
			// split each line by space 
			String[] line = l.trim().split("\\s+");
			
			// add elements in line[] to hashtable
			for (int i = 0; i < line.length; i++) {
				if (h.containsKey(line[i].toLowerCase())) {
					h.put(line[i].toLowerCase(), h.get(line[i].toLowerCase()) + 1);
				} else {
					h.put(line[i].toLowerCase(), 1);
				}
			}
		}
		
		// close scanner file
		s.close();
		
		// Arraylist with each element an arraylist of words of a certain length
		ArrayList<ArrayList<Words>> words = new ArrayList<ArrayList<Words>>();
		
		// Enumeration key to iterate through hashtable h 
		Enumeration<String> enumKey = h.keys();
		
		// while there are more elements in hashtable h
		while(enumKey.hasMoreElements()) {
			
			// boolean variable for breaking 
			boolean done = false;
			
			// key set to each element
			String key = enumKey.nextElement();
			
			// length of key
			int length = key.length();
			
			// Arraylist of words to be added if word of new length
			ArrayList<Words> list = new ArrayList<Words>();
			
			// add key in question to arraylist
			list.add(new Words(key, h.get(key)));
			
			// add new element to out arraylist if null, else add to inner 
			for (int i = 0; i < words.size(); i++) {
				if (words.get(i).get(0) == null) {
					break;
				}
				if (length == words.get(i).get(0).getWord().length()) {
					done = true;
					words.get(i).add(new Words(key, h.get(key)));
					break;
				} 
			}
			
			// break out for loop and continue
			if (done) {
				continue;
			}
			
			// add list to outer arraylist
			words.add(list);
		}
		
		// sort each arraylist of words by overriden compare method
		for (int j = 0; j < words.size(); j++) {
			Collections.sort(words.get(j));
		}
		
		// open files 
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		// while there are lines in input file left
		while (in.hasNextLine()) {
			
			// String line equal to each line
			String line = in.nextLine();
			
			// empty line
			if (line.equals(" ") || line.equals("")) {
				out.println("-");
				continue;
			}
			
			// split line by spaces
			String[] input = line.split(" ");
			
			int length = -1;
			// first element equal is length
			try {
				length = Integer.parseInt(input[0]);
			} catch (NumberFormatException e) {
				length = -1;
			}
			
			// length less than or equal to 0
			if (length <= 0) {
				out.println("-");
				continue;
			}
			
			int rank = -1;
			// second element is rank
			try {
				rank = Integer.parseInt(input[1]);
			} catch (NumberFormatException e) {
				rank = -1;
			}
			
			// rank less than 0
			if (rank < 0) {
				out.println("-");
				continue;
			}
			
			// index for traversing arraylist
			int index = 0;
			
			// boolean for logic statement
			boolean found = false;
			
			// assign index for position in outer arraylist
			for (int i = 0; i < words.size(); i++) {
				if (length == words.get(i).get(0).getWord().length()) {
					index = i;
					found = true;
					break;
				}
			}
			// print the letter of input input length and rank or "-"
			if (found) {
				if (words.get(index).size() <= rank) {
					out.println("-");
				} else {
					out.println(words.get(index).get(rank).getWord());
				}
			} else {
				out.println("-");
			}
			
		}
		
		// close files
		in.close();
		out.close();
	}
}
