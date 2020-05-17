package guiRectangles;

import java.awt.Color;
import java.util.LinkedList;

import java.awt.event.MouseEvent;

public abstract class ClickableRectangle {
	private int imageNumber=1;
	private Color firstLineColor=Color.black;
	private int x;
	private int y;
	private int height;
	private int length;
	protected String name;
	protected LinkedList<String> caption;
	public ClickableRectangle(String name,int x, int y,int length, int height) {
		super();
		this.name=name;
		this.x = x;
		this.y = y;
		this.height = height;
		this.length = length;
		caption=new LinkedList<String>();
		caption.add(name);
	}	
	public boolean isClicked(int xClick, int yClick) {
		if(xClick>x&&xClick<x+length&&yClick>y&&yClick<y+height) {
			return true;
		}else {
			return false;
		}
	}
	public abstract void onClick(MouseEvent e);
	public abstract void updateCaption();
	public void cleanImageNumber() {
		imageNumber=1;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LinkedList<String> getCaption() {
		return caption;
	}
	public void setCaption(LinkedList<String> caption) {
		this.caption = caption;
	}
	public int getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}
	public Color getFirstLineColor() {
		return firstLineColor;
	}
	public void setFirstLineColor(Color firstLineColor) {
		this.firstLineColor = firstLineColor;
	}
	
	
}
