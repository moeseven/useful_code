package guiRectangles;

import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class RectangleClicker implements Serializable{
	public LinkedList<ClickableRectangle> rectAngles;
	private ClickableRectangle selectedRectangle;
	public RectangleClicker() {
		// TODO Auto-generated constructor stub
		rectAngles= new LinkedList<ClickableRectangle>();
	}
	public void addRect(ClickableRectangle rect) {
		rectAngles.add(rect);
	}
	public void triggerClick(MouseEvent e) {
		int x= e.getX(); int y= e.getY();
		for(int i=0; i<rectAngles.size();i++) {
			if(rectAngles.get(i).isClicked(x, y)){
				rectAngles.get(i).onClick(e);
			}
		}				
	}
	public void updateCaptions() {
		for(int i=0; i<rectAngles.size();i++) {
			rectAngles.get(i).updateCaption();
		}
	}
	public void paintRectangles(Graphics g) {
		for(int i=0; i<rectAngles.size();i++) {
			if (rectAngles.get(i)==selectedRectangle) {
				g.setColor(Color.YELLOW);
			}else {
				g.setColor(Color.black);
			}
			g.drawRect(rectAngles.get(i).getX(), rectAngles.get(i).getY(), rectAngles.get(i).getLength(), rectAngles.get(i).getHeight());
			
			for(int a=0; a<rectAngles.get(i).getCaption().size();a++) {
				g.setColor(Color.black);
				if(a==0) {
					g.setColor(rectAngles.get(i).getFirstLineColor());
				}
				g.drawString(rectAngles.get(i).getCaption().get(a), rectAngles.get(i).getX()+3,rectAngles.get(i).getY()+11+a*11);
			}
		}
	}
	//getters and setters
	public LinkedList<ClickableRectangle> getRectAngles() {
		return rectAngles;
	}
	public void setRectAngles(LinkedList<ClickableRectangle> rectAngles) {
		this.rectAngles = rectAngles;
	}
	public ClickableRectangle getSelectedRectangle() {
		return selectedRectangle;
	}
	public void setSelectedRectangle(ClickableRectangle selectedRectangle) {
		this.selectedRectangle = selectedRectangle;
	}
	
}
