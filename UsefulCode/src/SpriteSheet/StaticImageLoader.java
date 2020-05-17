package SpriteSheet;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

public class StaticImageLoader {
	static BufferedImage[] sprites= new BufferedImage[20*20];
	
	public StaticImageLoader() {
		super();
		
	}
	
	public static void prepareImage(String path,double scale_factor){
		//save a sprite version this has to be called once before the getImage() method can be used
		
		BufferedImage image = new BufferedImage(1, 1, 1);
		try {
			image= ImageIO.read(new File(path+"./SpriteSheetBig.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (scale_factor!=1) {
			BufferedImage image_beeing_prepared=image;
			int x_scale= (int)(20*60*scale_factor);			
			int y_scale= (int)(20*51*scale_factor);
			//add a little for non full scale factors
			if((scale_factor*10)%10!=0) {
				x_scale+=scale_factor;
				y_scale+=scale_factor;
			}
			image_beeing_prepared = new BufferedImage(x_scale,y_scale, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D)image_beeing_prepared.createGraphics();
            g2d.drawImage(image, 0, 0,x_scale, y_scale, null);	  
//			image_beeing_prepared.getScaledInstance(x_scale, y_scale, Image.SCALE_SMOOTH);
			image=image_beeing_prepared;
		}		
		for(int a=0; a<20; a++){
			for(int b=0; b<20; b++){
				try {
					BufferedImage sub_image=image.getSubimage((int)(b*60*scale_factor),(int) (a*51*scale_factor), (int)(60*scale_factor), (int)(51*scale_factor));
					 Color color;
						for(int h=0; h<sub_image.getHeight();h++){
							for(int w=0; w<sub_image.getWidth();w++){
								color=new Color(sub_image.getRGB(w, h));
								if(color.getRed()>200&&color.getBlue()>200&&color.getGreen()<25){
									sub_image.setRGB(w, h, 0);
								}
							}
						}
					sprites[a*20+b]=sub_image;
					if (scale_factor!=1) {
						ImageIO.write(sprites[a*20+b], "png", new File(path+"/Sprite_"+scale_factor+"scaled_"+(a*20+b)+".png"));
					}else {
						ImageIO.write(sprites[a*20+b], "png", new File(path+"/Sprite_"+(a*20+b)+".png"));	
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}		
	}
	public static BufferedImage getImage(String path,int i){
		BufferedImage image = new BufferedImage(1, 1, 1);
		try {
			 image= ImageIO.read(new File(path+"/Sprite_"+i+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public static BufferedImage getScaledImage(String path, int i, double scale_factor) {
		if (scale_factor==1) {
			return getImage(path, i);
		}else {
			BufferedImage image = new BufferedImage(1, 1, 1);
			try {
				 image= ImageIO.read(new File(path+"/Sprite_"+scale_factor+"scaled_"+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return image;
		}
	}
	public static BufferedImage getBackgroundImage(String path){
		BufferedImage image = new BufferedImage(1, 1, 1);
		try {
			 image= ImageIO.read(new File(path+"/LandscapePicture"+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
