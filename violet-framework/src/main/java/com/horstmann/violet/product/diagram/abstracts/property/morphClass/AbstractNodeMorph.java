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

public class AbstractNodeMorph extends MorphNode{
	 /**
     * Construct a class node with a default size
     */
    public AbstractNodeMorph()
    {
        super();
        getName().setText("\u00ABabstract\u00BB");
        morph=Morph.ABSTRACT;
    }
    	
    @Override
    public AbstractNodeMorph clone() {
    	return (AbstractNodeMorph) super.clone();
    }
}

