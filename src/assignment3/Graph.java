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
		if(start.equals(end))
			return true;
		visitedNodes.add(start.getName());
		for(Node n : start.getAdj()){
			if(!visitedNodes.contains(n.getName())){
				DFSpath.add(n.getName());
				if(!dfs(n, end)){
					DFSpath.remove(DFSpath.size()-1);
				}
				else
					return true;
			}
		}
		return false;
	}
	
	public void pathLower(){
		for(int k = 0; k < DFSpath.size(); k++)
			DFSpath.set(k, DFSpath.get(k).toLowerCase());
	}
	
	public void clear(){
		DFSpath.clear();
		visitedNodes.clear();
	}
	public ArrayList<Node> getNodes(){
		return nodeSet;
	}
}
