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
 * An interface node in a class diagram.
 */
public class InterfaceNodeMorph extends MorphNode
{
    /**
     * Construct an interface node with a default size and the text <<interface>>.
     */
    public InterfaceNodeMorph()
    {
        super();
        getName().setText("\u00ABinterface\u00BB");
        morph=Morph.TOINTERFACE;
    }

  
   @Override
public InterfaceNodeMorph clone() {
	return (InterfaceNodeMorph) super.clone();
}
    



}
