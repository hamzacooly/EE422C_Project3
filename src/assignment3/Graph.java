package assignment3;

import java.util.*;

public class Graph {
	private ArrayList<Node> nodeSet = new ArrayList<Node>();
	private Set<String> visitedNodes = new HashSet<String>();
	public ArrayList<String> DFSpath = new ArrayList<String>();
	
	public Graph(ArrayList<Node> nodes){
		nodeSet = nodes;
	}
	
	public boolean dfs(Node start, Node end){
		
		return false;
	}
	
	public ArrayList<Node> getNodes(){
		return nodeSet;
	}
}
