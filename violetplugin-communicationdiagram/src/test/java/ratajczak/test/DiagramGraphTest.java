package ratajczak.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;

import ratajczak.violet.product.diagram.classes.CommunicationDiagramGraph;
import ratajczak.violet.product.diagram.classes.nodes.ObjectNode;

public class DiagramGraphTest {
	private CommunicationDiagramGraph graph;
	
	@BeforeTest
	public void init(){
		graph = new CommunicationDiagramGraph();
	}

	@Test(description="W liœcie powinien znajdowaæ siê klasa obiekt", dataProvider="NodesToolTipTest", dataProviderClass=DataPrividers.class)
	public void nodesPrototypeTest(String expectedToolTip, boolean expected){
		List<INode> lista = graph.getNodePrototypes();
		boolean contain = false;
		
		for (INode iNode : lista) {
			if(iNode.getToolTip().equals(expectedToolTip))
				contain=true;
		}
		
		Assert.assertEquals(contain, expected);
		
	}
	
	@Test(dataProvider="EdgesToolTipTest", dataProviderClass=DataPrividers.class, description="Powinna wyst¹pic strza³ka o nazwie Message w prototypach")
	public void edgesPrototypeTest(String expectedToolTip, boolean expected){
		List<IEdge> lista = graph.getEdgePrototypes();
		boolean contain = false;
		
		for (IEdge iEdge : lista) {
			if(iEdge.getToolTip().equals(expectedToolTip))
				contain = true;
		}
		Assert.assertEquals(contain, expected);
	}
	
}
