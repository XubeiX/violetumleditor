package com.horstmann.violet.product.diagram.classes.nodes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import com.horstmann.violet.product.diagram.abstracts.Direction;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.EllipticalNode;
import com.horstmann.violet.product.diagram.abstracts.node.INode;

public class BallNode extends EllipticalNode {

	public BallNode() {
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

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);

		Color old = g2.getColor();

		Rectangle2D bounds = getBounds();
		Ellipse2D circle = new Ellipse2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());

		g2.setColor(Color.WHITE);
		g2.fill(circle);
		g2.setColor(old);
		g2.draw(circle);

		int strLen = (int) g2.getFontMetrics().getStringBounds(Name, g2).getWidth();
		int start = (int) (bounds.getWidth() / 2 - strLen / 2);

		g2.drawString(Name, (int) (start + bounds.getX()), (int) bounds.getY() - RADIUS / 2);
		
	}

	@Override
	public BallNode clone() {
		BallNode ball = (BallNode) super.clone();
		ball.Name = Name;
		return ball;

	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	private String Name;
	private static int RADIUS = 20;

}
