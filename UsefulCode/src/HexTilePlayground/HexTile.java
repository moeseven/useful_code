package HexTilePlayground;

import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class HexTile {
	private HexTileUnit unit;
	protected HexTileTable table;
	private int xHex;
	private int yHex;
	private int zHex;
	protected int x;
	protected int y;
	protected int image;
	private Polygon polygon;
	protected String ground;
	protected String[] groundbonus;
	protected int movement_cost=1;
	protected int hex_pixel;
	public HexTile(HexTileTable table, int x, int y, double hex_size, String ground){
		this.table=table;
		hex_pixel=(int) (hex_size*HexTileTable.STANDARD_HEX_SIZE);
		if(x%2==0){
			xHex=x/2-y;
		}else{
			xHex=(x-1)/2-y;
		}
		this.x=x;
		this.y=y;
		yHex=x;
		zHex=xHex-x;	
		this.unit=null;
		groundbonus= new String[5];
		for(int i=0; i<groundbonus.length; i++){
			groundbonus[i]="";
		}
		this.ground=ground;
		
		/////////////////Für das Zeichnen der Polygone///////////////////////
		
		groundbonus[0]="movmentcost: "+movement_cost;
		int[] xPoints = {0,0,0,0,0,0};
		int[] yPoints = {0,0,0,0,0,0};
		xPoints[0]=(int)(1.5*x*hex_pixel+0.5*hex_pixel);
		if(x%2==0){
			yPoints[0]=(int)(y*1.732*hex_pixel);
		}else{
			yPoints[0]=(int)(y*1.732*hex_pixel+0.866*hex_pixel);
		}
		xPoints[1] = xPoints[0]+hex_pixel;
		xPoints[2] = xPoints[0]+(int)(1.5*hex_pixel);
		xPoints[3] = xPoints[0]+hex_pixel;
		xPoints[4] = xPoints[0];
		xPoints[5] = xPoints[0]-hex_pixel/2;
		yPoints[1] = yPoints[0];
		yPoints[2] = yPoints[0]+(int)(0.866*hex_pixel);
		yPoints[3] = yPoints[0]+(int)(1.732*hex_pixel);
		yPoints[4] = yPoints[0]+(int)(1.732*hex_pixel);
		yPoints[5] = yPoints[0]+(int)(0.866*hex_pixel);
		polygon=new Polygon(xPoints,yPoints,6);
		////////////////////////////////////////////////////////////////
	}
	public int getDistance(HexTile field){
		return (Math.abs(field.getX()-xHex)+Math.abs(field.getY()-yHex)+Math.abs(field.getZ()-zHex))/2;
	}
	public LinkedList<HexTile> getAdjacentTiles(){
		LinkedList<HexTile> adjacentTiles= new LinkedList();
		int tileIndex = table.getTiles().indexOf(this);
		//south
		if((tileIndex%table.getTable_size_y())+1<table.getTable_size_y()) {
			adjacentTiles.add(table.getTiles().get(tileIndex+1));
		}
		//north
		if((tileIndex%table.getTable_size_y())-1>=0) {
			adjacentTiles.add(table.getTiles().get(tileIndex-1));
		}
		if(tileIndex+table.getTable_size_y()<table.getTiles().size() ) {
			adjacentTiles.add(table.getTiles().get(tileIndex+table.getTable_size_y()));
		}
		if(tileIndex-table.getTable_size_y()>=0) {
			adjacentTiles.add(table.getTiles().get(tileIndex-table.getTable_size_y()));
		}
		int probe_index;
		if((tileIndex/table.getTable_size_y())%2==0) {
			probe_index=tileIndex+table.getTable_size_y()-1;
			if(probe_index<table.getTiles().size()&&(tileIndex/table.getTable_size_y())%2!=(probe_index/table.getTable_size_y())%2) {
				adjacentTiles.add(table.getTiles().get(probe_index));
			}
			probe_index=tileIndex-(table.getTable_size_y()+1);
			if(probe_index>=0&&(tileIndex/table.getTable_size_y())%2!=(probe_index/table.getTable_size_y())%2) {
				adjacentTiles.add(table.getTiles().get(probe_index));
			}
		}else {
			probe_index=tileIndex+table.getTable_size_y()+1;
			if(probe_index<table.getTiles().size() &&(tileIndex/table.getTable_size_y())%2!=(probe_index/table.getTable_size_y())%2) {
				adjacentTiles.add(table.getTiles().get(probe_index));
			}
			probe_index=tileIndex-(table.getTable_size_y()-1);
			if(probe_index>=0&&(tileIndex/table.getTable_size_y())%2!=(probe_index/table.getTable_size_y())%2) {
				adjacentTiles.add(table.getTiles().get(probe_index));
			}
		}
		return adjacentTiles;
	}
	public abstract void triggerLeftClick(HexTilePlayer player);
	public abstract void triggerRightClick(HexTilePlayer player);
	
	//getters and setters
	public int getX(){
		return xHex;
	}
	public int getY(){
		return yHex;
	}
	public int getZ(){
		return zHex;
	}
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	
	public Polygon getPolygon(){
		return polygon;
	}
	public int getImageNumber() {
		return image;
	}
	public void setImageNumber(int image) {
		this.image = image;
	}
	public String getGround() {
		return ground;
	}
	public void setGround(String ground) {
		this.ground = ground;
	}
	public String[] getGroundbonus() {
		return groundbonus;
	}
	public int getListPosition(){
		return y+ x*table.getTable_size_y();
	}
	public HexTileUnit getUnit() {
		return unit;
	}
	public void setUnit(HexTileUnit unit) {
		this.unit = unit;
	}


	
}
