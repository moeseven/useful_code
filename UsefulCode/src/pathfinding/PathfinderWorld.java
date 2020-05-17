package pathfinding;

import java.util.ArrayList;

public interface PathfinderWorld {
	public ArrayList<PathfinderField> get_immediatly_accessable_fields(PathfinderField field);
}
