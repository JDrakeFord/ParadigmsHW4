/*
	Author: Jesse Ford
	Student ID: 010893900
	Date - 2/9/2022
	Assignment: Turtle attack!
	Description:
		Move a turtle model with arrow keys / mouse around a cyan background.
		There is also a button that removes itself upon pressing it.
 */

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	public static int width = 512;
	public static int height = 485;
	Model model;
	View view;
	Controller controller;
	Link link;
	public Json json;
	public Game()
	{
		link = new Link();
		model = new Model(link);
		controller = new Controller(model, this, link);
		link.setController(controller);
		view = new View(controller, model);
		view.setLink(link);
		link.setView(view);
		model.setView(view);
		this.setTitle("A4 - Collide!!!");
		controller.onLoad();
		this.setSize(width, height);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
	}

	public void run()
	{
		while(true)
		{
			controller.update();
			model.update(link); // Indirectly calls Link.update
			//link.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 50 miliseconds
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public void setModel(Model m)
	{
		model = m;
	}

	void setLink(Link l)
	{
		link = l;
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
}
