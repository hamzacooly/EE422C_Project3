/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Hamza Khatri
 * hak533
 * 16220
 * Johnathan Love
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: 0
 * Git URL: 
 * Spring 2017
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.
	private static Set<String> dict;
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		while(true){
			initialize();
			ArrayList<String> SearchTerms = new ArrayList<String>();
			SearchTerms = parse(kb);
			ArrayList<String> FinalWordLadder = new ArrayList<String>();
			if(SearchTerms == null){
				return;
			}
			FinalWordLadder = getWordLadderBFS(SearchTerms.get(0), SearchTerms.get(1));
			if(FinalWordLadder.size() == 0){
				FinalWordLadder.add(SearchTerms.get(0));
				FinalWordLadder.add(SearchTerms.get(1));
			}
			printLadder(FinalWordLadder);
		}
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.

		
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		// TO DO
		String start = keyboard.next();
		if(start.equals("/quit"))
				return null;
		String end = keyboard.next();
		start = start.toLowerCase();
		end = end.toLowerCase();
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(start);
		ret.add(end);
		return ret;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
	
	
    /**
     * @param start - The Start Word of the Word Ladder
     * @param end   - The Destination Word of the Word Ladder
     * @return ArrayList<String> - The Word Ladder between start and end obtained through BFS 
     */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
	Set<String> dict = new HashSet<String>();
	dict = makeDictionary();
	Queue<Node> ExploreQueue = new LinkedList<Node>();
    	Node StartWord = new Node(start, null);
    	char[] WordArray = new char[StartWord.name.length()];
    	Node EndWord = new Node(end);
    	Node Current = new Node();
    	String temp = null;
    	ArrayList<String> AlreadyChecked = new ArrayList<String>();
    	ArrayList<String> WordLadder = new ArrayList<String>();
    	ExploreQueue.add(StartWord);
    	AlreadyChecked.add(StartWord.name);
    	
		
    	while(!ExploreQueue.isEmpty()){					//Cycle through nodes until there are no more nodes
    		Current = ExploreQueue.remove();			//Pop the queue once all adjacenies have been found for previous node
    		
    		if(Current.name.equals(EndWord.name)){		//If that node is the end word, cycle back through parents
    			while(Current.parent != null){			//and add them to the word ladder
    				WordLadder.add(Current.name);
    				Current = Current.parent;
    			}
    			WordLadder.add(Current.name);
    			Collections.reverse(WordLadder);
    			break;
    		}
    		
        	for(int i = 0; i < StartWord.name.length(); i++){		//Cycle through each position in the word and fill it with each
        		for(char k = 'a'; k <= 'z'; k++){					//letter, then check if it is in the dictionary
        			WordArray = Current.name.toCharArray();			//If it is, add it to the adjacency list and the queue
        			WordArray[i] = k;
        			temp = String.valueOf(WordArray);
        			temp = temp.toUpperCase();
        			if(dict.contains(temp) && !temp.equals(Current.name.toUpperCase()) && !AlreadyChecked.contains(temp)){
        				Current.adjList.add(new Node(temp.toLowerCase(), Current));
        				AlreadyChecked.add(temp);
        				ExploreQueue.add(new Node(temp.toLowerCase(), Current));
        			}
        		}
        	}      	       	
    	}
    	
		return WordLadder; 
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		if(ladder.size() > 2){
			int size = ladder.size()-2;
			System.out.println("A " + size + "-rung ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
			for(String k: ladder){
				System.out.println(k);
			}
		}
		else{
			System.out.println("No word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1) + ".");
		}
	}
	// TODO
	// Other private static methods here
}

