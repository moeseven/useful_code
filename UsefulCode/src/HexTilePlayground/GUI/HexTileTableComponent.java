package HexTilePlayground.GUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import HexTilePlayground.HexTile;
import HexTilePlayground.HexTilePlayer;
import HexTilePlayground.HexTileTable;
import SpriteSheet.StaticImageLoader;

public class HexTileTableComponent extends JComponent{
	
	protected HexTileTable table;
	protected HexTilePlayer player;
	protected JFrame gf;
	protected LinkedList<Polygon> p;
	protected int hex_pixel_size;
	protected String sprite_path;
	public HexTileTableComponent(HexTilePlayer player, HexTileTable table,JFrame gf,String sprite_path){
		this.player=player;
		this.table=table;
		this.sprite_path=sprite_path;
		
		this.gf=gf;
		setPreferredSize(new Dimension(1095,100));
		p= new LinkedList<Polygon>();
		for (HexTile t: table.getTiles()){
			p.add(t.getPolygon());
		}
		hex_pixel_size= (int) (table.getHex_size()*HexTileTable.STANDARD_HEX_SIZE);
		this.addMouseListener(new MyMouseListener());
		setVisible(true);
	}

	protected void paintComponent(Graphics g){
		for(int i=0; i<table.getTiles().size();i++){
			HexTile f=table.getTiles().get(i);
			g.drawImage(StaticImageLoader.getScaledImage(sprite_path,f.getImageNumber(), table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);
			//show possible destinations for movement
//			if(player.getSelectedUnit().reachableTile(f)){
//				g.setColor(Color.GREEN);
//			}else{
//				g.setColor(Color.black);
//			}
			if(player.getSelectedUnit().getHexTile().getAdjacentTiles().contains(f)){
				g.setColor(Color.GREEN);
			}else{
				g.setColor(Color.black);
			}
			if(f.getUnit()!=null) {
				if(f.getUnit().getPlayer()!=player) {
					g.setColor(Color.red);
				}
			}
			
			g.drawPolygon(f.getPolygon());
			if(f.getUnit()!=null){
				g.drawImage(StaticImageLoader.getScaledImage(sprite_path,f.getUnit().getImageNumber(), table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);
				if(f.getUnit().isReadyToMove()){
					g.drawImage(StaticImageLoader.getScaledImage(sprite_path,0, table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);
				}
				if(f.getUnit().isFleeing()){
					g.drawImage(StaticImageLoader.getScaledImage(sprite_path,14, table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);
				}
				if(f.getUnit().getHealth()/f.getUnit().getMaxHealth()<0.9){
					if((float)f.getUnit().getHealth()/f.getUnit().getMaxHealth()<0.8){
						if((float)f.getUnit().getHealth()/f.getUnit().getMaxHealth()<0.7){
							if((float)f.getUnit().getHealth()/f.getUnit().getMaxHealth()<0.6){
								if((float)f.getUnit().getHealth()/f.getUnit().getMaxHealth()<0.5){
									if((float)f.getUnit().getHealth()/f.getUnit().getMaxHealth()<0.4){
										if((float)f.getUnit().getHealth()/f.getUnit().getMaxHealth()<0.3){
											if((float)f.getUnit().getHealth()/f.getUnit().getMaxHealth()<0.2){
												if((float)f.getUnit().getHealth()/f.getUnit().getMaxHealth()<0.1){
													g.drawImage(StaticImageLoader.getScaledImage(sprite_path,11,table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);
												}else{g.drawImage(StaticImageLoader.getScaledImage(sprite_path,10,table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);}
											}else{g.drawImage(StaticImageLoader.getScaledImage(sprite_path,9,table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);}
										}else{g.drawImage(StaticImageLoader.getScaledImage(sprite_path,8,table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);}
									}else{g.drawImage(StaticImageLoader.getScaledImage(sprite_path,7,table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);}
								}else{g.drawImage(StaticImageLoader.getScaledImage(sprite_path,6,table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);}
							}else{g.drawImage(StaticImageLoader.getScaledImage(sprite_path,5,table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);}
						}else{g.drawImage(StaticImageLoader.getScaledImage(sprite_path,4,table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);}
					}else{g.drawImage(StaticImageLoader.getScaledImage(sprite_path,3,table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);}
				}else{g.drawImage(StaticImageLoader.getScaledImage(sprite_path,2,table.getHex_size()),f.getPolygon().xpoints[0]-(int)(0.5*hex_pixel_size),f.getPolygon().ypoints[0],null);}
			}
		}
		g.setColor(Color.blue);
		g.drawPolygon(player.getSelectedTile().getPolygon());
	}
	private class MyMouseListener extends MouseAdapter{
		int xClick;
		int yClick;
		int xField;
		int yField;
		int zField;
		int y;
		int x;
		public void mousePressed(MouseEvent e){
			xClick=(int) e.getPoint().getX();
			yClick=(int) e.getPoint().getY();
			x=(int) Math.ceil((xClick/(1.5*hex_pixel_size)))-1;
			yField=(int) Math.ceil((xClick/(1.5*hex_pixel_size)))-1;//yField = x (passt)
			if(yField%2==0){
				y=(int) Math.ceil((yClick/(1.732*hex_pixel_size)))-1;
				xField=yField/2-y;
			}else{
				y=(int) Math.ceil(((yClick-(0.866*hex_pixel_size))/(1.732*hex_pixel_size)))-1;
				xField=(yField-1)/2-y;
			}
			zField=xField-yField;
			//System.out.println(xField+"/"+yField+"/"+zField);
			if (x<table.getTable_size_x()&&y<table.getTable_size_y()&&x>=0&&y>=0) {
				if(x*(table.getTable_size_y())+y<table.getTiles().size()){						
					if(e.getButton()==1){								
							table.getTiles().get(x*(table.getTable_size_y())+y).triggerLeftClick(player);
					}else{
						if (e.getButton()==3){	
							table.getTiles().get(x*(table.getTable_size_y())+y).triggerRightClick(player);
						}
					}
				}
			}
			
			gf.repaint();
		} 
		public void mouseEntered(MouseEvent e){	
			
		}
	}
}
