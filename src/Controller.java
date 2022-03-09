/*
	Author: Jesse Ford
	Student ID: 010893900
	Date - 2/9/2022
	Assignment: Turtle attack!
	Description:
		Move a turtle model with arrow keys / mouse around a cyan background.
		There is also a button that removes itself upon pressing it.
 */

import java.awt.event.*;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	Game game;
	Link link;
	boolean editing = false;
	public static boolean UP = false;
	public static boolean DOWN = false;
	public static boolean LEFT = false;
	public static boolean RIGHT = false;
	Controller(Model m, Game g, Link l)
	{
		model = m;
		game = g;
		link = l;
	}

	void setModel(Model m)
	{
		model = m;
	}

	void setView(View v)
	{
		view = v;
	}
	public void mousePressed(MouseEvent e) {
		if(editing) {
			int x = e.getX() - e.getX() % Brick.brickSize;
			int y = e.getY() - e.getY() % Brick.brickSize;
			if (model.isBrick(x + view.scrollPosX, y + view.scrollPosY))
				model.removeBrick(x + view.scrollPosX, y + view.scrollPosY);
			else
				model.addBrick(x + view.scrollPosX, y + view.scrollPosY);
		}
	}
	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {

	}
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{

			case KeyEvent.VK_Q: System.exit(0); break;
			case KeyEvent.VK_ESCAPE: System.exit(0); break;
			case KeyEvent.VK_S: model.marshall().save("map.json");
			case KeyEvent.VK_E: if(editing){editing = false; System.out.println("STOPPED EDITING");}
								else{editing = true; System.out.println("NOW EDITING");} break;
			case KeyEvent.VK_DOWN: DOWN = true; break;
			case KeyEvent.VK_UP: UP = true; break;
			case KeyEvent.VK_LEFT: LEFT = true; break;
			case KeyEvent.VK_RIGHT: RIGHT = true; break;
		}
	}
	public void onLoad()
	{
		//Load JSON
		Json ob = Json.load("map.json");
		Model tmpMod = new Model(ob);
		this.setModel(tmpMod);
		view.setModel(tmpMod);
		game.setModel(tmpMod);
	}
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_DOWN: DOWN = false; break;
			case KeyEvent.VK_UP: UP = false; break;
			case KeyEvent.VK_LEFT: LEFT = false; break;
			case KeyEvent.VK_RIGHT: RIGHT = false; break;
		}

	}
	public void keyTyped(KeyEvent e)
	{
	}
	void update()
	{
			if(RIGHT) {
				link.absX += link.speed;
			}
			if(LEFT) {
				link.absX -= link.speed;
			}
			if(UP) {
				link.absY -= link.speed;
			}
			if(DOWN) {
				link.absY += link.speed;
			}
	}

	public void actionPerformed(ActionEvent e) {

	}
}
