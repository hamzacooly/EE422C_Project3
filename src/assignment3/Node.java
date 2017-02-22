package assignment3;

import java.util.ArrayList;

public class Node {
	public ArrayList<Node> adjList = new ArrayList<Node>();
	public String name;
	public Node parent;
	
	public Node(){
		
	}
	
	public Node(String n, Node p){
		this.name = n;
		this.parent = p;
	}
	
	public Node(String n){
		this.name = n;
	}
	
}
