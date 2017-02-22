package assignment3;

import java.util.ArrayList;

public class Node {
	public ArrayList<Node> adjList = new ArrayList<Node>();
	public String name;
	public Node parent;
	
	public Node(String n){
		this.name = n;
	}
	
	public Node(String n, Node p){
		name = n;
		parent = p;
	}
	
	public Node() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Node> getAdj(){
		return adjList;
	}
	
	public void addEdge(Node e){
		adjList.add(e);
	}
	
	public String getName(){
		return name;
	}
}
