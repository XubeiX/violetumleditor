package com.horstmann.violet.product.diagram.classes.nodes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import com.horstmann.violet.product.diagram.abstracts.Direction;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.EllipticalNode;

public class SocketNode extends EllipticalNode {

	
	public SocketNode(){
		Name = "";
	}
	
	
	@Override
	public Rectangle2D getBounds() {

		Point2D currentLocation = getLocation();
		double x = currentLocation.getX();
		double y = currentLocation.getY();
		double w = RADIUS;
		double h = RADIUS;
		Rectangle2D currentBounds = new Rectangle2D.Double(x, y, w, h);
		Rectangle2D snappedBounds = getGraph().getGridSticker().snap(currentBounds);
		return snappedBounds;
	}
	
	public void draw(Graphics2D g2) {
		super.draw(g2);
		int extent = 180;

		ArrayList<IEdge> iEdges = new ArrayList<IEdge>(getConnectedEdges());
		for (IEdge iEdge : iEdges) {
			if(iEdge.getDirection(this)==Direction.WEST){
				extent = -extent;
				break;
				}
		}
		
		Rectangle2D bounds = getBounds();
		Arc2D.Double socket = new Arc2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(),  bounds.getHeight(), 90, extent, Arc2D.OPEN);
		g2.draw(socket);

		int strLen = (int) g2.getFontMetrics().getStringBounds(Name, g2).getWidth();
		int start = (int) (bounds.getWidth() / 2 - strLen / 2);

		g2.drawString(Name, (int) (start + bounds.getX()), (int) bounds.getY() - RADIUS / 2);
		
	}
	
	@Override
	public SocketNode clone() {
		SocketNode socket = (SocketNode) super.clone();
		socket.Name = Name;
		return socket;

	}
	
	public String getName(){
		return Name;
	}
	
	public void setName(String Name){
		this.Name = Name;
	}
	
	private int RADIUS = 20;
	private String Name;
	
}
