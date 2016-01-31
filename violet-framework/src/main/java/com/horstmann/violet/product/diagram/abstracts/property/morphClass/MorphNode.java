package com.horstmann.violet.product.diagram.abstracts.property.morphClass;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.zip.Inflater;

import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.abstracts.node.RectangularNode;
import com.horstmann.violet.product.diagram.abstracts.property.Morph;
import com.horstmann.violet.product.diagram.abstracts.property.MultiLineString;
import com.horstmann.violet.product.diagram.common.PointNode;
/**
 * This class represent all object with may be changed to other object.
 * @author Artur
 *
 */
public class MorphNode extends RectangularNode{
	 /**
     * Construct a morphNode with default parameters.
     */
    public MorphNode()
    {
        name = new MultiLineString();
        name.setSize(MultiLineString.LARGE);
        attributes = new MultiLineString();
        attributes.setJustification(MultiLineString.LEFT);
        methods = new MultiLineString();
        methods.setJustification(MultiLineString.LEFT);
    }
    
    protected Rectangle2D getTopRectangleBounds() {
        Rectangle2D globalBounds = new Rectangle2D.Double(0, 0, 0, 0);
        Rectangle2D nameBounds = name.getBounds();
        globalBounds.add(nameBounds);
        boolean isMethodsEmpty = (methods.getText().length() == 0);
        boolean isAttributesEmpty = (attributes.getText().length() == 0);
        double defaultHeight = DEFAULT_HEIGHT;
        if (!isMethodsEmpty || !isAttributesEmpty ) {
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
    
    protected Rectangle2D getMiddleRectangleBounds() {
        Rectangle2D globalBounds = new Rectangle2D.Double(0, 0, 0, 0);
        Rectangle2D attributesBounds = attributes.getBounds();
        globalBounds.add(attributesBounds);
        boolean isMethodsEmpty = (methods.getText().length() == 0);
        boolean isAttributesEmpty = (attributes.getText().length() == 0);
        if (!isMethodsEmpty || !isAttributesEmpty ) {
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
    
    protected Rectangle2D getBottomRectangleBounds() {
        Rectangle2D globalBounds = new Rectangle2D.Double(0, 0, 0, 0);
        Rectangle2D methodsBounds = methods.getBounds();
        globalBounds.add(methodsBounds);
        boolean isMethodsEmpty = (methods.getText().length() == 0);
        boolean isAttributesEmpty = (attributes.getText().length() == 0);
        if (!isMethodsEmpty || !isAttributesEmpty) {
        		globalBounds.add(new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_COMPARTMENT_HEIGHT));
        }
        Rectangle2D middleBounds = getMiddleRectangleBounds();
        double x = middleBounds.getX();
        double y = middleBounds.getMaxY();
        double w = globalBounds.getWidth();
        double h = globalBounds.getHeight();
        globalBounds.setFrame(x, y, w, h);
        Rectangle2D snappedBounds = getGraph().getGridSticker().snap(globalBounds);
        return snappedBounds;
    }

    @Override
    public Rectangle2D getBounds()
    {
        Rectangle2D top = getTopRectangleBounds();
        Rectangle2D mid = getMiddleRectangleBounds();
        Rectangle2D bot = getBottomRectangleBounds();
        top.add(mid);
        top.add(bot);
        Rectangle2D snappedBounds = getGraph().getGridSticker().snap(top);
        return snappedBounds;
    }

    /**
     * Draw object to graph
     */
    @Override
    public void draw(Graphics2D g2)
    {
        super.draw(g2);
        drawer.draw(g2, this);
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.horstmann.violet.framework.Node#addNode(com.horstmann.violet.framework.Node, java.awt.geom.Point2D)
     */
    public boolean addChild(INode n, Point2D p)
    {
        // TODO : where is it added?
        if (n instanceof PointNode)
        {
            return true;
        }
        return false;
    }

    /**
     * Sets the name property value.
     * 
     * @param newValue the class name
     */
    public void setName(MultiLineString newValue)
    {
        name = newValue;
    }

    /**
     * Gets the name property value.
     * 
     * @return the class name
     */
    public MultiLineString getName()
    {
        return name;
    }

    /**
     * Sets the attributes property value.
     * 
     * @param newValue the attributes of this class
     */
    public void setAttributes(MultiLineString newValue)
    {
        attributes = newValue;
    }

    /**
     * Gets the attributes property value.
     * 
     * @return the attributes of this class
     */
    public MultiLineString getAttributes()
    {
        return attributes;
    }

    /**
     * Sets the methods property value.
     * 
     * @param newValue the methods of this class
     */
    public void setMethods(MultiLineString newValue)
    {
        methods = newValue;
    }

    /**
     * Gets the methods property value.
     * 
     * @return the methods of this class
     */
    public MultiLineString getMethods()
    {
        return methods;
    }

   
    public MorphNode clone()
    {
        MorphNode cloned = (MorphNode) super.clone();
        cloned.name = (MultiLineString) name.clone();
        cloned.methods = (MultiLineString) methods.clone();
        cloned.attributes = (MultiLineString) attributes.clone();
        cloned.morph = (Morph)morph;
        return cloned;
    }
    
    
   /**
    * Gets the Morph property value
    * @return the morph object type
    */
    public Morph getMorph() {
		return morph;
	}
    /**
     * Sets the morph property value
     * @param morph new morph type
     */
	public void setMorph(Morph morph) {
		this.morph = morph;
		morph.morph(this);
	}



	private MultiLineString name;
    private MultiLineString attributes;
    private MultiLineString methods;
    protected Morph morph;
    protected IDrawer drawer;

	protected static int DEFAULT_COMPARTMENT_HEIGHT = 20;
    protected static int DEFAULT_WIDTH = 100;
    protected static int DEFAULT_HEIGHT = 60;
}
