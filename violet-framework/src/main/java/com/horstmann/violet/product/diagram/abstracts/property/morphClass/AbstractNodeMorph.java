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
 * This class represent Abstract object
 * @author Artur
 *
 */
public class AbstractNodeMorph extends MorphNode{
	 /**
     * Construct a class node with a default size, text <<abstract>>, morph type = abstract and drawer.
     */
    public AbstractNodeMorph()
    {
        super();
        getName().setText("<<abstract>>\n");
        morph=Morph.ABSTRACT;
        drawer = ThreeTabDrawer.getInstance();
    }
    	
    @Override
    public AbstractNodeMorph clone() {
    	return (AbstractNodeMorph) super.clone();
    }
}

