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
 * This class represent a Class Object
 * @author Artur
 *
 */
public class ClassNodeMorph extends MorphNode
{
    /**
     * Construct a class node with a default size, default name, morph type = class and drawer.
     */
    public ClassNodeMorph()
    {
       super();
       morph=Morph.TOCLASS;
       drawer = ThreeTabDrawer.getInstance();
    }
 
    @Override
    public ClassNodeMorph clone() {
    	return (ClassNodeMorph) super.clone();
    }
    
    
}
