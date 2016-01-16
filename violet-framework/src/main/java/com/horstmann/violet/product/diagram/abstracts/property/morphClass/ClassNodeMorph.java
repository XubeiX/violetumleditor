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
 * A class node in a class diagram.
 */
public class ClassNodeMorph extends MorphNode
{
    /**
     * Construct a class node with a default size
     */
    public ClassNodeMorph()
    {
       super();
       morph=Morph.TOCLASS;
    }
 
    @Override
    public ClassNodeMorph clone() {
    	return (ClassNodeMorph) super.clone();
    }
}
