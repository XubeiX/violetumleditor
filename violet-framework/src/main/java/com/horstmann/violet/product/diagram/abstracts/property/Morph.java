package com.horstmann.violet.product.diagram.abstracts.property;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.horstmann.violet.framework.util.SerializableEnumeration;
import com.horstmann.violet.product.diagram.abstracts.AbstractGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.AbstractNodeMorph;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.ClassNodeMorph;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.InterfaceNodeMorph;

public class Morph extends SerializableEnumeration {

	private static AbstractGraph graph;

	public static void setGraph(AbstractGraph grapht) {
		graph = grapht;
	}

	private static final long serialVersionUID = -7185987624987560065L;

	public void morph(INode node) {
		ArrayList<IEdge> allEdges = new ArrayList<IEdge>(graph.getAllEdges());
		INode morph = null;

		if (node instanceof ClassNodeMorph) {
			if (this == TOINTERFACE) {
				morph = classToInterface(node);
			} else if (this == ABSTRACT) {
				morph = classToAbstract(node);
			}

		} else if (node instanceof InterfaceNodeMorph) {
			if (this == TOCLASS) {
				morph = interfaceToClass(node);
			} else if (this == ABSTRACT) {
				morph = interfaceToAbstract(node);
			}

		} else if (node instanceof AbstractNodeMorph) {
			if (this == TOCLASS) {
				morph = abstractToClass(node);
			} else if (this == TOINTERFACE) {
				morph = abstractToInterface(node);
			}
		}

		redrawNodes(node, morph);
		copyChildrenAndParent(node, morph);

		ArrayList<IEdge> allEdgesAfterChange = new ArrayList<IEdge>(graph.getAllEdges());
		redrawEdges(allEdges, allEdgesAfterChange, node, morph);

	}

	private void copyChildrenAndParent(INode node, INode morph){
		ArrayList<INode> children = new ArrayList<INode>(node.getChildren());
		
		if(node.getParent()!=null){
			morph.setParent(node.getParent());
			node.getParent().addChild(morph, node.getParent().getChildren().size());
		}
		
	
		
		
		for (INode iNode : children) {
			morph.addChild(iNode, children.size());
		}
	}
	
	private INode abstractToClass(INode node) {
		ClassNodeMorph cla = new ClassNodeMorph();

		MultiLineString name = ((AbstractNodeMorph) node).getName();
		String substring = name.getText().replaceFirst("<<abstract>>"+"\n", "");
		name.setText(substring);
		cla.setName(name);

		cla.setMethods(((AbstractNodeMorph) node).getMethods());
		cla.setAttributes(((AbstractNodeMorph) node).getAttributes());
		cla.setMorph(TOCLASS);

		cla.setLocation(((AbstractNodeMorph) node).getLocation());
		return cla;

	}
	
	private INode abstractToInterface(INode node){
		InterfaceNodeMorph inter = new InterfaceNodeMorph();

		MultiLineString name = ((AbstractNodeMorph) node).getName();
		String substring = name.getText().replaceFirst("<<abstract>>"+"\n", "<<interface>>"+"\n");
		name.setText(substring);
		inter.setName(name);

		inter.setMethods(((AbstractNodeMorph) node).getMethods());
		inter.setMorph(TOINTERFACE);

		inter.setLocation(((AbstractNodeMorph) node).getLocation());
		return inter;
	}

	private INode classToInterface(INode node) {
		InterfaceNodeMorph inter = new InterfaceNodeMorph();

		
		inter.setName(classNameChange(node, "<<interface>>"));

		inter.setMethods(((ClassNodeMorph) node).getMethods());
		inter.setMorph(TOINTERFACE);

		inter.setLocation(((ClassNodeMorph) node).getLocation());
		return inter;
	}

	private INode classToAbstract(INode node) {
		AbstractNodeMorph abst = new AbstractNodeMorph();

		
		abst.setName(classNameChange(node, "<<abstract>>"));

		abst.setMethods(((ClassNodeMorph) node).getMethods());
		abst.setAttributes(((ClassNodeMorph) node).getAttributes());

		abst.setMorph(ABSTRACT);
		abst.setLocation(((ClassNodeMorph) node).getLocation());

		return abst;
	}
	
	private MultiLineString classNameChange(INode node, String prefix){
		MultiLineString name = ((ClassNodeMorph) node).getName();
		name.setText(prefix+"\n" + ((ClassNodeMorph) node).getName().getText());
		return name;
	}

	private INode interfaceToClass(INode node) {
		ClassNodeMorph cla = new ClassNodeMorph();

		MultiLineString name = ((InterfaceNodeMorph) node).getName();
		String substring = name.getText().replaceFirst("<<interface>>"+"\n", "");
		name.setText(substring);
		cla.setName(changeInterfacePrefixName(node, ""));

		cla.setMethods(((InterfaceNodeMorph) node).getMethods());
		cla.setMorph(TOCLASS);

		cla.setLocation(((InterfaceNodeMorph) node).getLocation());
		return cla;
	}

	private INode interfaceToAbstract(INode node) {
		AbstractNodeMorph abst = new AbstractNodeMorph();

		
		abst.setName(changeInterfacePrefixName(node, "<<abstract>>"+"\n"));

		abst.setMethods(((InterfaceNodeMorph) node).getMethods());
		abst.setMorph(ABSTRACT);

		abst.setLocation(((InterfaceNodeMorph) node).getLocation());
		return abst;
	}
	
	private MultiLineString changeInterfacePrefixName(INode node, String sufix){
		MultiLineString name = ((InterfaceNodeMorph) node).getName();
		String substring = name.getText().replaceFirst("<<interface>>"+"\n", sufix);
		name.setText(substring);
		return name;
	}

	private void redrawNodes(INode node, INode morph) {
		graph.removeNode(node);
		if (morph instanceof InterfaceNodeMorph)
			graph.addNode((InterfaceNodeMorph) morph, morph.getLocation());
		if (morph instanceof ClassNodeMorph)
			graph.addNode((ClassNodeMorph) morph, morph.getLocation());
		if (morph instanceof AbstractNodeMorph)
			graph.addNode((AbstractNodeMorph) morph, morph.getLocation());
	}

	private void redrawEdges(ArrayList<IEdge> allEdges, ArrayList<IEdge> allEdgesAfterChange, INode node, INode morph) {
		allEdges.removeAll(allEdgesAfterChange);
		for (IEdge iEdge : allEdges) {
			System.out.println(iEdge);
			if (iEdge.getStart() == node) {
				iEdge.setStart(morph);
				iEdge.setStartLocation(morph.getConnectionPoint(iEdge));
			}
			if (iEdge.getEnd() == node) {
				iEdge.setEnd(morph);
				iEdge.setEndlocation(morph.getConnectionPoint(iEdge));
			}
			morph.addConnection(iEdge);
			graph.connect(iEdge, iEdge.getStart(), iEdge.getStartLocation(), iEdge.getEnd(), iEdge.getEndLocation(),
					iEdge.getTransitionPoints());
		}
	}

	public static final Morph TOCLASS = new Morph();
	public static final Morph TOINTERFACE = new Morph();
	public static final Morph ABSTRACT = new Morph();

}
