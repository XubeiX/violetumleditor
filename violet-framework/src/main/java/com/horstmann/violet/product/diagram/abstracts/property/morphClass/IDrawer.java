package com.horstmann.violet.product.diagram.abstracts.property.morphClass;

import java.awt.Graphics2D;
/**
 * Interface to draw a object.
 * @author Artur
 *
 */
public interface IDrawer {
	public void draw(Graphics2D g2, MorphNode node);
}
