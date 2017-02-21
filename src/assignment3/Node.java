package assignment3;

import java.util.ArrayList;

public class Node {
	private ArrayList<String> adjList = new ArrayList<String>();
	private String name;
	
	public Node(ArrayList<String> adj, String n){
		adjList = adj;
		name = n;
	}
	
	public Node(String n){
		this.name = n;
	}
	
	public ArrayList<String> getAdj(){
		return adjList;
	}
	
	public void addEdge(String e){
		adjList.add(e);
	}
	
	public String getName(){
		return name;
	}
}
