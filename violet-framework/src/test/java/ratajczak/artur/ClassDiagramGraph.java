package ratajczak.artur;

import java.util.ArrayList;
import java.util.List;

import com.horstmann.violet.product.diagram.abstracts.AbstractGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.abstracts.property.ArrowHead;
import com.horstmann.violet.product.diagram.abstracts.property.BentStyle;
import com.horstmann.violet.product.diagram.abstracts.property.LineStyle;
import com.horstmann.violet.product.diagram.abstracts.property.Morph;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.AbstractNodeMorph;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.ClassNodeMorph;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.InterfaceNodeMorph;

/**
 * A UML class diagram.
 */
public class ClassDiagramGraph extends AbstractGraph
{

	
	
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
        

        ClassNodeMorph node0 = new ClassNodeMorph();
        node0.setToolTip("MorphClass");
        NODE_PROTOTYPES.add(node0);

        InterfaceNodeMorph node1 = new InterfaceNodeMorph();
        node1.setToolTip("MorphInterface");
        NODE_PROTOTYPES.add(node1);

        AbstractNodeMorph node2 = new AbstractNodeMorph();
        node2.setToolTip("MorphAbstract");
        NODE_PROTOTYPES.add(node2);

        
        
        RelationShipEdge useEdge = new RelationShipEdge();
        useEdge.setEndArrowHead(ArrowHead.V);
        useEdge.setBentStyle(BentStyle.STRAIGHT);
        useEdge.setLineStyle(LineStyle.DOTTED);
        useEdge.setMiddleLabel("\u00ABuse\u00BB");
        useEdge.setToolTip("Edge");
        EDGE_PROTOTYPES.add(useEdge);
        
        
       
    }

}
