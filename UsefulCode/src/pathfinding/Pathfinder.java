package pathfinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Pathfinder {
	private HashSet<PathfinderField> newly_accessible_fields;
	private HashSet<PathfinderField> reset_buffer_newly_accessible_fields;
	private HashMap<PathfinderField,Path> best_paths_map;
	private PathfinderField origin;
	private PathfinderField destination;
	private PathfinderWorld world;
	public Pathfinder(PathfinderWorld world) {
		super();
		
		this.world = world;
		clear_maps();
	}
	
	public void reset() {
		origin = null;
		destination = null;
		clear_maps();
	}
	
	private void clear_maps() {
		best_paths_map = new HashMap<PathfinderField,Path>();
		newly_accessible_fields = new HashSet<PathfinderField>();
		reset_buffer_newly_accessible_fields = new HashSet<PathfinderField>();
	}

	public Path get_best_path() {
		if (best_paths_map.containsKey(destination)) {
			return best_paths_map.get(destination);
		}else {
			return null;
		}
	}
	/*
	 * extra_steps parameters searches longer for better paths
	 * (reduces performance but brings better results)
	 *  this is also the size of the bad_pathing_obstacle your path can avoid
	 */
	public boolean find_path(PathfinderField origin, PathfinderField destination, int extra_steps) {
		this.origin = origin;
		this.destination = destination;
		clear_maps();
		if (destination.is_pathable()) {
			newly_accessible_fields.add(origin);
			while(extra_steps >= 0) {
				if(!(!path_found(this.origin,this.destination) && newly_accessible_fields.size() > 0)) { //this condition is not optimal, there might be better paths if you search longer
					extra_steps--;
				}
				reset_newly_accessible_fields();					
				for(PathfinderField field : reset_buffer_newly_accessible_fields) {
					scan_step(field);
				}				
			}
			
		}
		return path_found(this.origin, this.destination);
	}
	
	/*
	 * returns true if a path between origin and destination 
	 * is found (e.g. the path map contains such a path)
	 */
	public boolean path_found(PathfinderField origin,PathfinderField destination) {
		if (origin.equals(this.origin)&& best_paths_map.containsKey(destination)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public boolean path_found(PathfinderField destination) {
		if (best_paths_map.containsKey(destination)) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 *scans all adjacent fields and tries to find better paths towards them 
	 */
	private void scan_step(PathfinderField field) {
		ArrayList<PathfinderField> adjacent_fields = world.get_immediatly_accessable_fields(field);
		for (int i = 0; i < adjacent_fields.size(); i++) {
			if (adjacent_fields.get(i).is_pathable()) {
				Path another_path;
				if (best_paths_map.containsKey(field)) {
					another_path = new Path(best_paths_map.get(field).getPath());
				}else {
					another_path = new Path();
				}
				another_path.add_field(adjacent_fields.get(i));
				if (best_paths_map.containsKey(adjacent_fields.get(i))) {
					if (another_path.get_cost() < best_paths_map.get(adjacent_fields.get(i)).get_cost()) {
						better_path(adjacent_fields.get(i),another_path);
					}
				}else {
					better_path(adjacent_fields.get(i),another_path);
				}
			}			
		}
	}
	
	/*
	 * a new better path to a field has been found
	 */
	private void better_path(PathfinderField field, Path path) {
		best_paths_map.put(field,path);
		newly_accessible_fields.add(field);
	}
	
	/*
	 * resets newly_accessible_fields (empyt set)
	 * and copies it to reset_buffer_newly_accessible_fields
	 * this can then be used in the new iteration
	 * 
	 * 
	 */
	private void reset_newly_accessible_fields() {
		reset_buffer_newly_accessible_fields = new HashSet<PathfinderField>();
		for(PathfinderField field :newly_accessible_fields) {
			reset_buffer_newly_accessible_fields.add(field);
		}
		newly_accessible_fields = new HashSet<PathfinderField>();
	}
}
