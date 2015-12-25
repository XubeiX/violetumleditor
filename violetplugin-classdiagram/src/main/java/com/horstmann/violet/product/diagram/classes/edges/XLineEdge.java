package com.horstmann.violet.product.diagram.classes.edges;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.horstmann.violet.product.diagram.abstracts.edge.SegmentedLineEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.abstracts.property.ArrowHead;
import com.horstmann.violet.product.diagram.abstracts.property.LineStyle;

public class XLineEdge extends SegmentedLineEdge{
	
	@Override
	public boolean isTransitionPointsSupported() {
		return false;
	}
	
	@Override
    public ArrowHead getEndArrowHead()
    {
        return ArrowHead.X_HEAD;
    }
	
	public LineStyle getLineStyle()
    {
        return LineStyle.SOLID;
    }
	
	@Override
	public void setStartArrowHead(ArrowHead newValue) {
		if(newValue == ArrowHead.X_HEAD)
			super.setStartArrowHead(newValue);
	}
	
	 @Override
	  public Line2D getConnectionPoints()
	    {
	        ArrayList<Point2D> points = getPoints();
	        Point2D p1 = points.get(0);
	        Point2D p2 = points.get(points.size() - 1);
	        return new Line2D.Double(p1, p2);
	    }
	 
	 @Override
	    public ArrayList<Point2D> getPoints()
	    {
	        INode endingNode = getEnd();
	        INode startingNode = getStart();
	        return getPointsForNodesOnDifferentLifeLines(startingNode, endingNode);
	    }
	 
	 private ArrayList<Point2D> getPointsForNodesOnDifferentLifeLines(INode startingNode, INode endingNode)
	    {
	        Point2D startingPoint = startingNode.getConnectionPoint(this);
	        Point2D endingPoint = endingNode.getConnectionPoint(this);
	        ArrayList<Point2D> a = new ArrayList<Point2D>();
	        a.add(startingPoint);
	        a.add(endingPoint);
	        return a;
	    }

}
