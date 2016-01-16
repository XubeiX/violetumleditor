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
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.MorphNode;

public class Morph extends SerializableEnumeration {

	private static AbstractGraph graph;

	public static void setGraph(AbstractGraph grapht) {
		graph = grapht;
	}

	private static final long serialVersionUID = -7185987624987560065L;

	public void morph(MorphNode node) {
		ArrayList<IEdge> allEdges = new ArrayList<IEdge>(graph.getAllEdges());
		MorphNode morph = null;

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
	
	private MorphNode abstractToClass(MorphNode node) {
		ClassNodeMorph cla = new ClassNodeMorph();

		MultiLineString name = node.getName();
		String substring = name.getText().replaceFirst("<<abstract>>"+"\n", "");
		name.setText(substring);
		cla.setName(name);

		cla.setMethods(node.getMethods());
		cla.setAttributes(node.getAttributes());
		cla.setMorph(TOCLASS);

		cla.setLocation(node.getLocation());
		return cla;

	}
	
	private MorphNode abstractToInterface(MorphNode node){
		InterfaceNodeMorph inter = new InterfaceNodeMorph();

		MultiLineString name = node.getName();
		String substring = name.getText().replaceFirst("<<abstract>>"+"\n", "<<interface>>"+"\n");
		name.setText(substring);
		inter.setName(name);

		inter.setMethods(node.getMethods());
		inter.setMorph(TOINTERFACE);

		inter.setLocation(node.getLocation());
		return inter;
	}

	private MorphNode classToInterface(MorphNode node) {
		InterfaceNodeMorph inter = new InterfaceNodeMorph();

		
		inter.setName(classNameChange(node, "<<interface>>"));

		inter.setMethods(node.getMethods());
		inter.setMorph(TOINTERFACE);

		inter.setLocation(node.getLocation());
		return inter;
	}

	private MorphNode classToAbstract(MorphNode node) {
		AbstractNodeMorph abst = new AbstractNodeMorph();

		
		abst.setName(classNameChange(node, "<<abstract>>"));

		abst.setMethods(node.getMethods());
		abst.setAttributes(node.getAttributes());

		abst.setMorph(ABSTRACT);
		abst.setLocation(node.getLocation());

		return abst;
	}
	
	private MultiLineString classNameChange(MorphNode node, String prefix){
		MultiLineString name = node.getName();
		name.setText(prefix+"\n" + node.getName().getText());
		return name;
	}

	private MorphNode interfaceToClass(MorphNode node) {
		ClassNodeMorph cla = new ClassNodeMorph();

		MultiLineString name =  node.getName();
		String substring = name.getText().replaceFirst("<<interface>>"+"\n", "");
		name.setText(substring);
		cla.setName(changeInterfacePrefixName(node, ""));

		cla.setMethods(node.getMethods());
		cla.setMorph(TOCLASS);

		cla.setLocation(node.getLocation());
		return cla;
	}

	private MorphNode interfaceToAbstract(MorphNode node) {
		AbstractNodeMorph abst = new AbstractNodeMorph();

		
		abst.setName(changeInterfacePrefixName(node, "<<abstract>>"+"\n"));

		abst.setMethods(node.getMethods());
		abst.setMorph(ABSTRACT);

		abst.setLocation(node.getLocation());
		return abst;
	}
	
	private MultiLineString changeInterfacePrefixName(MorphNode node, String sufix){
		MultiLineString name = node.getName();
		String substring = name.getText().replaceFirst("<<interface>>"+"\n", sufix);
		name.setText(substring);
		return name;
	}

	private void redrawNodes(MorphNode node, MorphNode morph) {
		graph.removeNode(node);
		if (morph instanceof InterfaceNodeMorph)
			graph.addNode((InterfaceNodeMorph) morph, morph.getLocation());
		if (morph instanceof ClassNodeMorph)
			graph.addNode((ClassNodeMorph) morph, morph.getLocation());
		if (morph instanceof AbstractNodeMorph)
			graph.addNode((AbstractNodeMorph) morph, morph.getLocation());
	}

	private void redrawEdges(ArrayList<IEdge> allEdges, ArrayList<IEdge> allEdgesAfterChange, MorphNode node, MorphNode morph) {
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
