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
import javax.swing.JButton;

class View extends JPanel
{
	JButton b1;
	BufferedImage brick_image;
	Model model;
	public int scrollPosX = 0;
	public int scrollPosY = 0;

	void setModel(Model m)
	{
		model = m;
	}

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
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < model.bricks.size(); i++)
		{
			Brick b = model.bricks.get(i);
			g.drawImage(brick_image, b.x - scrollPosX, b.y - scrollPosY, null);
		}
	}

}
