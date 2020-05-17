package HexTilePlayground;

public interface HexTileUnit {
	public int getImageNumber();
	public HexTilePlayer getPlayer();
	public boolean reachableTile(HexTile tile);
	public HexTile getHexTile();
	public void setTile(HexTile tile);
	public boolean isReadyToMove();
	public boolean isFleeing();
	public float getMaxHealth();
	public float getHealth();
}
