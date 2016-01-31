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
/**
 * This class defines morph types and allow change type between this types.
 * @author Artur
 *
 */
public class Morph extends SerializableEnumeration {
	/**
	 * Reference to graph object to redrawn elements.
	 */
	private static AbstractGraph graph;
	/**
	 * Method set graph references.
	 * @param grapht refrences to AbstractGraph object.
	 */
	public static void setGraph(AbstractGraph grapht) {
		graph = grapht;
	}

	private static final long serialVersionUID = -7185987624987560065L;

	/**
	 * Allows to change type of MorphNode object.
	 * @param node node to change type.
	 */
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
	/**
	 * Method copy all children and parents from old type to new type.
	 * @param node Old type containing children and parent
	 * @param morph New type where they will be copied
	 */
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
	/**
	 * Changes Abstract object to Class object
	 * @param node Abstract object
	 * @return Class object
	 */
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
	/**
	 * Changes Abstract object to Interface object
	 * @param node Abstract object
	 * @return Interface Object
	 */
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
	/**
	 * Changes Class object to Interface object
	 * @param node Class object
	 * @return Interface Object
	 */
	private MorphNode classToInterface(MorphNode node) {
		InterfaceNodeMorph inter = new InterfaceNodeMorph();

		
		inter.setName(classNameChange(node, "<<interface>>"));

		inter.setMethods(node.getMethods());
		inter.setMorph(TOINTERFACE);

		inter.setLocation(node.getLocation());
		return inter;
	}
	/**
	 * Changes Class object to Abstract object 
	 * @param node Class object
	 * @return Abstract object
	 */
	private MorphNode classToAbstract(MorphNode node) {
		AbstractNodeMorph abst = new AbstractNodeMorph();

		
		abst.setName(classNameChange(node, "<<abstract>>"));

		abst.setMethods(node.getMethods());
		abst.setAttributes(node.getAttributes());

		abst.setMorph(ABSTRACT);
		abst.setLocation(node.getLocation());

		return abst;
	}
	/**
	 * Changes class object name by adding prefix
	 * @param node Class object
	 * @param prefix prefix to add
	 * @return new name with prefix
	 */
	private MultiLineString classNameChange(MorphNode node, String prefix){
		MultiLineString name = node.getName();
		name.setText(prefix+"\n" + node.getName().getText());
		return name;
	}
	/**
	 * Changes Interface object to Class object
	 * @param node Interface object
	 * @return Class object
	 */
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
	/**
	 * Changes Interface object to Abstract object
	 * @param node Interface object
	 * @return Abstract Object
	 */
	private MorphNode interfaceToAbstract(MorphNode node) {
		AbstractNodeMorph abst = new AbstractNodeMorph();

		
		abst.setName(changeInterfacePrefixName(node, "<<abstract>>"+"\n"));

		abst.setMethods(node.getMethods());
		abst.setMorph(ABSTRACT);

		abst.setLocation(node.getLocation());
		return abst;
	}
	/**
	 * Changes interface prefix to new one
	 * @param node interface object
	 * @param newprefix new prefix name 
	 * @return name with new prefix
	 */
	private MultiLineString changeInterfacePrefixName(MorphNode node, String newprefix){
		MultiLineString name = node.getName();
		String substring = name.getText().replaceFirst("<<interface>>"+"\n", newprefix);
		name.setText(substring);
		return name;
	}
	/**
	 * Method remove old object from graph, recognize new one and redraw it.
	 * @param node Old object to remove
	 * @param morph New object to redraw
	 */
	private void redrawNodes(MorphNode node, MorphNode morph) {
		graph.removeNode(node);
		if (morph instanceof InterfaceNodeMorph)
			graph.addNode((InterfaceNodeMorph) morph, morph.getLocation());
		if (morph instanceof ClassNodeMorph)
			graph.addNode((ClassNodeMorph) morph, morph.getLocation());
		if (morph instanceof AbstractNodeMorph)
			graph.addNode((AbstractNodeMorph) morph, morph.getLocation());
	}
	/**
	 * Method redraw all connection edges from old object to new object 
	 * @param allEdges list with all edges 
	 * @param allEdgesAfterChange list with all edges after change
	 * @param node old object
	 * @param morph new object
	 */
	private void redrawEdges(ArrayList<IEdge> allEdges, ArrayList<IEdge> allEdgesAfterChange, MorphNode node, MorphNode morph) {
		allEdges.removeAll(allEdgesAfterChange);
		for (IEdge iEdge : allEdges) {
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
