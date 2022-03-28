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
	Controller controller;
	public static int scrollPosX = 0;
	public static int scrollPosY = 0;
	void setModel(Model m)
	{
		model = m;
	}
	void setLink(Link l) {link = l; }

	View(Controller c, Model m)
	{
		c.setView(this);
		model = m;
		controller = c;
	}


	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < model.sprites.size(); i++)
		{
			Sprite s = model.sprites.get(i);
			if(s instanceof Brick b)
			{
				if(b.view == null)
					b.setView(this);
			}
			s.draw(g);
		}
		link.draw(g);

	}

}


