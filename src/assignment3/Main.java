/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Hamza Khatri
 * hak533
 * 16220
 * Johnathan Love
 * jal5622
 * 16220
 * Slip days used: 0
 * Git URL: https://github.com/hamzacooly/EE422C_Project3
 * Spring 2017
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	// static variables and constants only here.
	private static Set<String> dict;
	private static Graph g;
	
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
		initialize();
		while(true){
			ArrayList<String> SearchTerms = new ArrayList<String>();
			SearchTerms = parse(kb);
			if(SearchTerms == null)
				return;
			ArrayList<String> stuff = new ArrayList<String>();
			System.out.println("DOING BFS");
			stuff = getWordLadderBFS(SearchTerms.get(0), SearchTerms.get(1));
			printLadder(stuff);
			System.out.println("DOING DFS");
			stuff = getWordLadderDFS(SearchTerms.get(0), SearchTerms.get(1));
			printLadder(stuff);
		}
		
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		dict = makeDictionary();
		ArrayList<Node> nodes = new ArrayList<Node>();
		for(String k: dict){
			Node n = new Node(k.toUpperCase());
			nodes.add(n);
		}
		
		for(Node n: nodes){
			char[] p = n.getName().toUpperCase().toCharArray();
			for(int j = 0; j < p.length; j++){
				for(char i = 'A'; i <= 'Z'; i++){
					char curr = p[j];
					p[j] = i;
					if(dict.contains(String.valueOf(p)) && !String.valueOf(p).equals(n.getName())){
						for(Node m : nodes){
							if(m.getName().equals(String.valueOf(p))){
								n.addEdge(m);
								break;
							}
						}
					}
					p[j] = curr;
				}
			}
		}
		g = new Graph(nodes);
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		// TO DO
		String start = keyboard.next();
		String end = keyboard.next();
		if(start.equals("/quit") || end.equals("/quit"))
			return null;
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
		g.clear();
		Node n1 = null, n2 = null;
		for(Node k : g.getNodes()){
			if(start.toUpperCase().equals(k.getName()))
				n1 = k;
			if(end.toUpperCase().equals(k.getName()))
				n2 = k;
		}
		
		boolean pathExists = g.dfs(n1, n2);
		if(pathExists){
			g.DFSpath.add(0, start);
			g.pathLower();
			return g.DFSpath;
		}
		else{
			ArrayList<String> ret = new ArrayList();
			ret.add(start);
			ret.add(end);
			return ret;
		}
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		// TODO some code
		// TODO more code
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
        	if(WordLadder.size() == 0){
				WordLadder.add(start);
				WordLadder.add(end);
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
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1) + ".");
		}
	}
	// TODO
	// Other private static methods here
}
