package pathfinding;

public interface PathfinderField {
	public boolean is_pathable();
	public int get_path_cost();
	public String toString();
}
