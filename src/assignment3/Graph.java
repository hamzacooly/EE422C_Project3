package assignment3;

import java.util.*;

public class Graph {
	ArrayList<Node> nodeSet = new ArrayList<Node>();
	Set<String> visitedNodes = new HashSet<String>();
	public static ArrayList<String> DFSpath = new ArrayList<String>();
	
	public Graph(ArrayList<Node> nodes){
		nodeSet = nodes;
	}
	
	public boolean dfs(Node start, Node end){
		return false;
	}
}
