package com.horstmann.violet.product.diagram.classes.edges;

import com.horstmann.violet.product.diagram.abstracts.edge.SegmentedLineEdge;
import com.horstmann.violet.product.diagram.abstracts.property.ArrowHead;
import com.horstmann.violet.product.diagram.abstracts.property.BentStyle;
import com.horstmann.violet.product.diagram.abstracts.property.LineStyle;

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
	
	@Override
	public void setStartArrowHead(ArrowHead newValue) {
		if(newValue == ArrowHead.X_HEAD || newValue==ArrowHead.V)
			super.setStartArrowHead(newValue);
	}
	
	@Override
	public void setEndArrowHead(ArrowHead newValue) {
		if(newValue == ArrowHead.X_HEAD || newValue==ArrowHead.V)
			super.setEndArrowHead(newValue);
	}
	


}
