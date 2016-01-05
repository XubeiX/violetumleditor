package com.horstmann.violet.product.diagram.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.horstmann.violet.product.diagram.abstracts.AbstractGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.abstracts.property.ArrowHead;
import com.horstmann.violet.product.diagram.abstracts.property.BentStyle;
import com.horstmann.violet.product.diagram.abstracts.property.LineStyle;
import com.horstmann.violet.product.diagram.abstracts.property.Morph;
import com.horstmann.violet.product.diagram.classes.edges.AggregationEdge;
import com.horstmann.violet.product.diagram.classes.edges.AssociationEdge;
import com.horstmann.violet.product.diagram.classes.edges.CompositionEdge;
import com.horstmann.violet.product.diagram.classes.edges.DependencyEdge;
import com.horstmann.violet.product.diagram.classes.edges.InheritanceEdge;
import com.horstmann.violet.product.diagram.classes.edges.InterfaceInheritanceEdge;
import com.horstmann.violet.product.diagram.classes.edges.RelationShipEdge;
import com.horstmann.violet.product.diagram.classes.edges.XLineEdge;
import com.horstmann.violet.product.diagram.classes.nodes.BallNode;
import com.horstmann.violet.product.diagram.classes.nodes.ClassNode;
import com.horstmann.violet.product.diagram.classes.nodes.InterfaceNode;
import com.horstmann.violet.product.diagram.classes.nodes.PackageNode;
import com.horstmann.violet.product.diagram.classes.nodes.SocketNode;
import com.horstmann.violet.product.diagram.common.NoteEdge;
import com.horstmann.violet.product.diagram.common.NoteNode;

/**
 * A UML class diagram.
 */
public class ClassDiagramGraph extends AbstractGraph
{

	public ClassDiagramGraph(){
		Morph.setGraph(this);
	}
	
    public List<INode> getNodePrototypes()
    {
        return NODE_PROTOTYPES;
    }

    public List<IEdge> getEdgePrototypes()
    {
        return EDGE_PROTOTYPES;
    }

    private static final List<INode> NODE_PROTOTYPES = new ArrayList<INode>();

    private static final List<IEdge> EDGE_PROTOTYPES = new ArrayList<IEdge>();

    static
    {
        ResourceBundle rs = ResourceBundle.getBundle(ClassDiagramConstant.CLASS_DIAGRAM_STRINGS, Locale.getDefault());

        ClassNode node0 = new ClassNode();
        node0.setToolTip(rs.getString("node0.tooltip"));
        NODE_PROTOTYPES.add(node0);

        InterfaceNode node1 = new InterfaceNode();
        node1.setToolTip(rs.getString("node1.tooltip"));
        NODE_PROTOTYPES.add(node1);

        PackageNode node2 = new PackageNode();
        node2.setToolTip(rs.getString("node2.tooltip"));
        NODE_PROTOTYPES.add(node2);

        NoteNode node3 = new NoteNode();
        node3.setToolTip(rs.getString("node3.tooltip"));
        NODE_PROTOTYPES.add(node3);

//        ImageNode node4 = new ImageNode();
//        node4.setToolTip(rs.getString("node4.tooltip"));
//        NODE_PROTOTYPES.add(node4);
        
        BallNode node5 = new BallNode();
        node5.setToolTip(rs.getString("node5.tooltip"));
        NODE_PROTOTYPES.add(node5);
        
        SocketNode node6 = new SocketNode();
        node6.setToolTip(rs.getString("node6.tooltip"));
        NODE_PROTOTYPES.add(node6);
        
        DependencyEdge dependency = new DependencyEdge();
        dependency.setToolTip(rs.getString("edge0.tooltip"));
        EDGE_PROTOTYPES.add(dependency);

        InheritanceEdge inheritance = new InheritanceEdge();
        inheritance.setToolTip(rs.getString("edge1.tooltip"));
        EDGE_PROTOTYPES.add(inheritance);

        InterfaceInheritanceEdge interfaceInheritance = new InterfaceInheritanceEdge();
        interfaceInheritance.setToolTip(rs.getString("edge2.tooltip"));
        EDGE_PROTOTYPES.add(interfaceInheritance);

        AssociationEdge association = new AssociationEdge();
        association.setToolTip(rs.getString("edge3.tooltip"));
        EDGE_PROTOTYPES.add(association);

        AggregationEdge aggregation = new AggregationEdge();
        aggregation.setToolTip(rs.getString("edge4.tooltip"));
        EDGE_PROTOTYPES.add(aggregation);

        CompositionEdge composition = new CompositionEdge();
        composition.setToolTip(rs.getString("edge5.tooltip"));
        EDGE_PROTOTYPES.add(composition);

        NoteEdge noteEdge = new NoteEdge();
        noteEdge.setToolTip(rs.getString("edge6.tooltip"));
        EDGE_PROTOTYPES.add(noteEdge);
        
        XLineEdge xEdge = new XLineEdge();
        xEdge.setToolTip(rs.getString("edge7.tooltip"));
        EDGE_PROTOTYPES.add(xEdge);
        
        RelationShipEdge useEdge = new RelationShipEdge();
        useEdge.setEndArrowHead(ArrowHead.V);
        useEdge.setBentStyle(BentStyle.STRAIGHT);
        useEdge.setLineStyle(LineStyle.DOTTED);
        useEdge.setMiddleLabel("\u00ABuse\u00BB");
        useEdge.setToolTip(rs.getString("edge8.tooltip"));
        EDGE_PROTOTYPES.add(useEdge);
        
        RelationShipEdge createEdge = new RelationShipEdge();
        createEdge.setEndArrowHead(ArrowHead.V);
        createEdge.setBentStyle(BentStyle.STRAIGHT);
        createEdge.setLineStyle(LineStyle.DOTTED);
        createEdge.setMiddleLabel("\u00ABcreate\u00BB");
        createEdge.setToolTip(rs.getString("edge9.tooltip"));
        EDGE_PROTOTYPES.add(createEdge);
        
       
    }

}
