package com.horstmann.violet.product.diagram.abstracts.property.morphClass;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
/**
 * This class allow draw a three tab object on graph.
 * @author Artur
 *
 */
public class ThreeTabDrawer implements IDrawer{
	public static ThreeTabDrawer instance = null;
	
	public static ThreeTabDrawer getInstance(){
		if(instance==null){
			instance = new ThreeTabDrawer(); }
		return instance;
	}
	
	private ThreeTabDrawer(){
	}
	
	@Override
	public void draw(Graphics2D g2, MorphNode node) {
		 // Backup current color;
        Color oldColor = g2.getColor();
        // Translate g2 if node has parent
        Point2D nodeLocationOnGraph = node.getLocationOnGraph();
        Point2D nodeLocation = node.getLocation();
        Point2D g2Location = new Point2D.Double(nodeLocationOnGraph.getX() - nodeLocation.getX(), nodeLocationOnGraph.getY() - nodeLocation.getY());
        g2.translate(g2Location.getX(), g2Location.getY());
        // Perform drawing
        Rectangle2D currentBounds = node.getBounds();
        Rectangle2D topBounds = node.getTopRectangleBounds();
        Rectangle2D midBounds = node.getMiddleRectangleBounds(); 
        Rectangle2D bottomBounds = node.getBottomRectangleBounds();
        if (topBounds.getWidth() < currentBounds.getWidth())
        {
        	// We need to re-center the topBounds - only do so if really required to avoid race conditions
        	topBounds.setRect(topBounds.getX(), topBounds.getY(), currentBounds.getWidth(), topBounds.getHeight());
        }
        g2.setColor(node.getBackgroundColor());
        g2.fill(currentBounds);
        g2.setColor(node.getBorderColor());
        g2.draw(currentBounds);
        g2.drawLine((int) topBounds.getX(),(int) topBounds.getMaxY(),(int) currentBounds.getMaxX(),(int) topBounds.getMaxY());
        g2.drawLine((int) bottomBounds.getX(),(int) bottomBounds.getY(),(int) currentBounds.getMaxX(),(int) bottomBounds.getY());
        g2.setColor(node.getTextColor());
        node.getName().draw(g2, topBounds);
        node.getAttributes().draw(g2, midBounds);
        node.getMethods().draw(g2, bottomBounds);
        // Restore g2 original location
        g2.translate(-g2Location.getX(), -g2Location.getY());
        // Restore first color
        g2.setColor(oldColor);
		
	}

}
