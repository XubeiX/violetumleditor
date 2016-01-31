package com.horstmann.violet.product.diagram.classes.edges;

import com.horstmann.violet.product.diagram.abstracts.edge.SegmentedLineEdge;
import com.horstmann.violet.product.diagram.abstracts.property.ArrowHead;
import com.horstmann.violet.product.diagram.abstracts.property.BentStyle;
import com.horstmann.violet.product.diagram.abstracts.property.LineStyle;
/**
 * This class represent a line with start/end X sign or arrow sign.
 * 
 * @author Artur Ratajczak
 */
public class XLineEdge extends SegmentedLineEdge{
	
	@Override
	public boolean isTransitionPointsSupported() {
		return false;
	}
	
	@Override
	public BentStyle getBentStyle() {
		return BentStyle.HVH;
	}

	
	public LineStyle getLineStyle()
    {
        return LineStyle.SOLID;
    }
	
	/**
	 * This method allow only set arrow shape to X or normal V arrow.
	 * @param newValue arrow shape to set
	 */
	@Override
	public void setStartArrowHead(ArrowHead newValue) {
		if(newValue == ArrowHead.X_HEAD || newValue==ArrowHead.V)
			super.setStartArrowHead(newValue);
	}
	/**
	 * This method allow only set arrow shape to X or normal V arrow.
	 * @param newValue arrow shape to set
	 * 
	 */
	@Override
	public void setEndArrowHead(ArrowHead newValue) {
		if(newValue == ArrowHead.X_HEAD || newValue==ArrowHead.V)
			super.setEndArrowHead(newValue);
	}
	


}
