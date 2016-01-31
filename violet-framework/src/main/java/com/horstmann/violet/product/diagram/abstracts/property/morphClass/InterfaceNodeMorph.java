package com.horstmann.violet.product.diagram.abstracts.property.morphClass;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.abstracts.node.RectangularNode;
import com.horstmann.violet.product.diagram.abstracts.property.Morph;
import com.horstmann.violet.product.diagram.abstracts.property.MultiLineString;
import com.horstmann.violet.product.diagram.common.PointNode;

/**
 * This class represent an Interface object
 */
public class InterfaceNodeMorph extends MorphNode {
	/**
	 * Construct an interface node with a default size, text <
	 * <interface>>, morph type = interface and drawer.
	 */
	public InterfaceNodeMorph() {
		super();
		getName().setText("<<interface>>\n");
		morph = Morph.TOINTERFACE;
		drawer = TwoTabDrawer.getInstance();
	}

	@Override
	public InterfaceNodeMorph clone() {
		return (InterfaceNodeMorph) super.clone();
	}

	protected Rectangle2D getTopRectangleBounds() {
		Rectangle2D globalBounds = new Rectangle2D.Double(0, 0, 0, 0);
		Rectangle2D nameBounds = getName().getBounds();
		globalBounds.add(nameBounds);
		boolean isMethodsEmpty = (getMethods().getText().length() == 0);
		double defaultHeight = DEFAULT_HEIGHT;
		if (!isMethodsEmpty) {
			defaultHeight = DEFAULT_COMPARTMENT_HEIGHT;
		}
		globalBounds.add(new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, defaultHeight));
		Point2D currentLocation = getLocation();
		double x = currentLocation.getX();
		double y = currentLocation.getY();
		double w = globalBounds.getWidth();
		double h = globalBounds.getHeight();
		globalBounds.setFrame(x, y, w, h);
		Rectangle2D snappedBounds = getGraph().getGridSticker().snap(globalBounds);
		return snappedBounds;
	}

	protected Rectangle2D getBottomRectangleBounds() {
		Rectangle2D globalBounds = new Rectangle2D.Double(0, 0, 0, 0);
		Rectangle2D methodsBounds = getMethods().getBounds();
		globalBounds.add(methodsBounds);
		if (methodsBounds.getHeight() > 0) {
			globalBounds.add(new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_COMPARTMENT_HEIGHT));
		}
		Rectangle2D topBounds = getTopRectangleBounds();
		double x = topBounds.getX();
		double y = topBounds.getMaxY();
		double w = globalBounds.getWidth();
		double h = globalBounds.getHeight();
		globalBounds.setFrame(x, y, w, h);
		Rectangle2D snappedBounds = getGraph().getGridSticker().snap(globalBounds);
		return snappedBounds;
	}

	@Override
	public Rectangle2D getBounds() {
		Rectangle2D top = getTopRectangleBounds();
		Rectangle2D bot = getBottomRectangleBounds();
		top.add(bot);
		Rectangle2D snappedBounds = getGraph().getGridSticker().snap(top);
		return snappedBounds;
	}
	
	
	

}
