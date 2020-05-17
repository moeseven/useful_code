package HexTilePlayground;

public interface HexTilePlayer {
	public HexTileUnit getSelectedUnit();
	public void setSelectedUnit(HexTileUnit unit);
	public HexTile getSelectedTile();
	public void setSelectedTile(HexTile tile);
}
