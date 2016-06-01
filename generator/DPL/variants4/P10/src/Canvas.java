/**
 * DPL Canvas.java
 * @author Roberto E. Lopez-Herrejon
 * SEP SPL Course July 2010
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.*;
import java.awt.event.*;
import javax.swing.JComponent;


import java.awt.Point;


@SuppressWarnings("serial")
public class Canvas extends JComponent implements MouseListener, MouseMotionListener {

	// Lists of figures objects to display
	protected List<Shape> shapes = new LinkedList<Shape>();
	
	// Auxiliary point
	Point start, end;
	
	// Objects for creating figures to add to the canvas
		protected Line newLine = null;
			protected Rectangle newRect = null;
	
	// Enum for types of figures
	public enum FigureTypes { NONE 
					,LINE 
							,RECT
			};
	
	protected boolean filled = false;
	
	// The selected default is none. Do not change.
	public FigureTypes figureSelected = FigureTypes.NONE;

		protected Color fillcolor = Color.BLACK;
	protected Color linecolor = Color.BLACK;
		
	/** Sets up the canvas. Do not change */
	public Canvas() { 
		this.setDoubleBuffered(true); // for display efficiency
		this.addMouseListener(this);  // registers the mouse listener
		this.addMouseMotionListener(this); // registers the mouse motion listener
	}

	/** Sets the selected figure. Do not change. */
	public void selectedFigure(FigureTypes fig) {
		figureSelected = fig;
	}
	
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
	
		public void wipe() {
		this.shapes.clear();
		this.repaint();
	}
		
		public void setLineColor(String colorString) {
		if (colorString.equals("Red"))
			linecolor = Color.red;
		else if (colorString.equals("Green"))
			linecolor = Color.green;
		else if (colorString.equals("Blue"))
			linecolor = Color.blue;
		else
			linecolor = Color.black;
	}
	public void setFillColor(String colorString) {
		if (colorString.equals("Red"))
			fillcolor = Color.red;
		else if (colorString.equals("Green"))
			fillcolor = Color.green;
		else if (colorString.equals("Blue"))
			fillcolor = Color.blue;
		else
			fillcolor = Color.black;
	}
		
	/** Paints the component in turn. Call whenever repaint is called. */
	public void paintComponent(Graphics g) 	{
		super.paintComponent(g);
		
		// refreshes the canvas
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Paints the drawn figures
		for (Shape s : shapes) { s.paint(g); }
	
	}
		
	// **************** Mouse Handling
	
	/* Invoked when the mouse button has been clicked (pressed and released) on a component.
	 * Empty implementation. Do not change.
	 */
	public void mouseClicked(MouseEvent e)  { }// mouseClicked
	
    /* Invoked when the mouse enters a component. Empty implementation.
     * Do not change. */     
	public void mouseEntered(MouseEvent e) { }
          
	/** Invoked when the mouse exits a component. Empty implementation. 
	 * Do not change. */
	public void mouseExited(MouseEvent e) {	}
          
	/** Invoked when a mouse button has been pressed on a component. */
	public void mousePressed(MouseEvent e) {
		switch(figureSelected) {
				case LINE : mousePressedLine(e); break;
						case RECT: mousePressedRect(e); break;
				}
	}
     
	/** Invoked when a mouse button has been released on a component. */
	public void mouseReleased(MouseEvent e) {
		switch(figureSelected) {
				case LINE : mouseReleasedLine(e); break;
						case RECT: mouseReleasedRect(e); break;
				}
	}
	
	/** Invoked when the mouse is dragged over a component */
	public void mouseDragged(MouseEvent e)	{
		switch(figureSelected) {
				case LINE : mouseDraggedLine(e); break;
						case RECT: mouseDraggedRect(e); break;
				}
	}

	/* Empty implementation. Do not change. */
	public void mouseMoved(MouseEvent e)	{ }

		// **************** Manage Rect

	public void mousePressedRect(MouseEvent e) {
		// If there is no line being created
		if (newRect == null) {
			newRect = new Rectangle (
											fillcolor, linecolor,  
										e.getX(), e.getY(), filled);
			shapes.add(newRect);
		}
	}
	
	/** Updates the end point coordinates and repaints figure */
	public void mouseDraggedRect(MouseEvent e) {
		newRect.setEnd(e.getX(), e.getY());
		repaint();	
	}
	
	/** Clears the reference to the new line */
	public void mouseReleasedRect(MouseEvent e) {
		newRect = null;
	}
			
		// **************** Manage Line

	public void mousePressedLine(MouseEvent e) {
		// If there is no line being created
		if (newLine == null) {
			start = new Point(e.getX(), e.getY());
			newLine = new Line (
											linecolor, 
										start);
			shapes.add(newLine);
		}
	}
	
	/** Updates the end point coordinates and repaints figure */
	public void mouseDraggedLine(MouseEvent e) {
		newLine.setEnd(new Point(e.getX(), e.getY()));
		repaint();	
	}
	
	/** Clears the reference to the new line */
	public void mouseReleasedLine(MouseEvent e) {
		newLine.setEnd(new Point(e.getX(), e.getY()));
		newLine = null;
	}
			
} // Canvas
