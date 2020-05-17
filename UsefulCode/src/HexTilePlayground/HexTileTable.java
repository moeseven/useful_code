package HexTilePlayground;

import java.util.ArrayList;
import java.util.LinkedList;

import pathfinding.PathfinderField;

public abstract class HexTileTable {
	public static final int STANDARD_HEX_SIZE =30;
	protected int table_size_x;
	protected int table_size_y;
	protected double hex_size; //
	private Object game;
	protected LinkedList<HexTile> tiles;
	public HexTileTable(int table_size_x, int table_size_y, double hex_size, Object game){
		this.game=game;
		this.table_size_x=table_size_x;
		this.table_size_y=table_size_y;
		this.hex_size=hex_size;
		tiles= new LinkedList<HexTile>();
		for(int x=0; x<table_size_x; x++){
			for(int y=0; y<table_size_y;y++){
				tiles.add(generateTile(x,y));
			}
		}
	}
	protected abstract HexTile generateTile(int x,int y);
	public int getDistance(HexTile tile_a, HexTile tile_b) {
		return tile_a.getDistance(tile_b);
	}
	public boolean isReachable(HexTileUnit unit, HexTile tile) {
		if(tile.getUnit()==null&&getDistance(unit.getHexTile(), tile)==1) {
			
		}
		//TODO
		return false;
	}
	public ArrayList<HexTile> get_adjacent_tiles(HexTile tile) {		
		LinkedList<HexTile> linked_list = tile.getAdjacentTiles();
		ArrayList<HexTile> adjacent_tiles = new ArrayList<HexTile>();
		for (int i = 0; i < linked_list.size(); i++) {
			adjacent_tiles.add(linked_list.get(i));
		}
		return adjacent_tiles;
	}
	public boolean moveUnitUncondittionaly(HexTileUnit unit, HexTile tile) {
		if(tile.getUnit()==null) {
			tile.setUnit(unit);
			unit.setTile(tile);
			return true;
		}else {
			return false;
		}
	}
	//getters and setters
	public int getTable_size_x() {
		return table_size_x;
	}
	public void setTable_size_x(int table_size_x) {
		this.table_size_x = table_size_x;
	}
	public int getTable_size_y() {
		return table_size_y;
	}
	public void setTable_size_y(int table_size_y) {
		this.table_size_y = table_size_y;
	}
	public double getHex_size() {
		return hex_size;
	}
	public void setHex_size(int hex_size) {
		this.hex_size = hex_size;
	}
	public Object getGame() {
		return game;
	}
	public void setGame(Object game) {
		this.game = game;
	}
	public LinkedList<HexTile> getTiles() {
		return tiles;
	}
	public void setTiles(LinkedList<HexTile> tiles) {
		this.tiles = tiles;
	}

	
	
}
