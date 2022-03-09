/*
	Author: Jesse Ford
	Student ID: 010893900
	Date - 2/9/2022
	Assignment: Turtle attack!
	Description:
		Move a turtle model with arrow keys / mouse around a cyan background.
		There is also a button that removes itself upon pressing it.
 */

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;

class View extends JPanel
{
	JButton b1;
	BufferedImage brick_image;
	Model model;
	Link link;
	public static int scrollPosX = 0;
	public static int scrollPosY = 0;
	BufferedImage[] link_images;

	void setModel(Model m)
	{
		model = m;
	}
	void setLink(Link l) {link = l; }

	View(Controller c, Model m)
	{
		c.setView(this);
		//Load image
		try
		{
			this.brick_image =
					ImageIO.read(new File("brick.jpg"));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		model = m;
		link_images = new BufferedImage[50];
		for(int i = 0; i < 50; i++)
		{
			try {
				if(i < 9)
					link_images[i] = ImageIO.read(new File("link_images/link" + "0" + (i + 1) + ".png"));
				else
					link_images[i] = ImageIO.read(new File("link_images/link" + (i + 1) + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static int count = 0;
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < model.bricks.size(); i++)
		{
			Brick b = model.bricks.get(i);
			g.drawImage(brick_image, b.x - scrollPosX, b.y - scrollPosY, null);
		}
			if(Controller.DOWN) {
				if (count == 9)
					count = 0;
				if(!(Controller.UP && Controller.LEFT && Controller.RIGHT))
					g.drawImage(link_images[4 + count], link.x, link.y, null);
				count++;
			}
			if(Controller.LEFT) {
				if (count == 9)
					count = 0;
				if(!(Controller.UP || Controller.DOWN || Controller.RIGHT))
					g.drawImage(link_images[13 + count], link.x, link.y, null);
				count++;
			}
			if(Controller.RIGHT) {
				if (count == 9)
					count = 0;
				if(!(Controller.UP || Controller.LEFT || Controller.DOWN))
					g.drawImage(link_images[30 + count], link.x, link.y, null);
				count++;
			}
			if(Controller.UP) {
				if (count == 9)
					count = 0;
				if(!(Controller.DOWN && Controller.LEFT && Controller.RIGHT))
					g.drawImage(link_images[40 + count], link.x, link.y, null);
				count++;
			}
			if(!(Controller.UP || Controller.DOWN || Controller.LEFT || Controller.RIGHT))
			{
				g.drawImage(link_images[0], link.x, link.y, null);
			}
		}

	}


