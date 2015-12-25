package ratajczak.test;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.classes.ClassDiagramGraph;

public class ClassDiagramGraphTest {
	private ClassDiagramGraph graph;
	
	@BeforeClass
	public void init(){
		graph = new ClassDiagramGraph();
	}
	
	@DataProvider(name="ProtptypeTest")
	public Object[][] test(){
		return new Object[][]{
			{"Linia pozioma",false},
			{"Class Diagram",false},
			{"X Line",true},
			{"Artur", false},
			{"Note connector",true},
		};
	}
	
	@Test(dataProvider="ProtptypeTest")
	public void ShoulbeClassInEdgeList(String equals, boolean expected){
		Collection<IEdge> lista = graph.getEdgePrototypes();
		boolean value = false;
		
		for (IEdge iEdge : lista) {
			if(iEdge.getToolTip().equals(equals))
				value=true;
		}
		Assert.assertEquals(value, expected, "Znajduje siê");
	}
}
