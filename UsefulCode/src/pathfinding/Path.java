package pathfinding;

import java.util.LinkedList;

public class Path {
	private LinkedList<PathfinderField> path;
	private int index_for_walking;
	
	
	/*
	 * this consturctor does take the elemetns form the list
	 * and makes a new List form it. 
	 * The original list remains untouched
	 */
	public Path(LinkedList<PathfinderField> path) {
		super();
		this.path = new LinkedList<PathfinderField>();
		for (int i = 0; i < path.size(); i++) {
			add_field(path.get(i));
		}
		index_for_walking = 0;
	}
	
	public Path() {
		path = new LinkedList<PathfinderField>();
	}
	
	public boolean add_field(PathfinderField field) {
		if (field.is_pathable()) {
			path.add(field);
			return true;
		}else {
			return false;
		}		 
	}
	public PathfinderField get_start() {
		return path.getFirst();
	}
	public PathfinderField get_end() {
		return path.getLast();
	}
	public int get_cost() {
		int retVal = 0;
		for (int i = 0; i < path.size(); i++) {
			retVal += path.get(i).get_path_cost();
		}
		return retVal;
	}
	
	public String toString() {
		String retVal = "";
		for (int i = 0; i < path.size(); i++) {
			retVal += path.get(i).toString();
		}
		return retVal;
	}
	
	//getters and setters
	public LinkedList<PathfinderField> getPath() {
		return path;
	}

	public int getIndex_for_walking() {
		return index_for_walking;
	}

	public void setIndex_for_walking(int index_for_walking) {
		this.index_for_walking = index_for_walking;
	}

//	public void setPath(LinkedList<PathfinderField> path) {
//		this.path = path;
//	}
	
}
