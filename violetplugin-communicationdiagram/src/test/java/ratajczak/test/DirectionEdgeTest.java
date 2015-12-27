package ratajczak.test;

import java.awt.geom.Point2D;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.abstracts.property.BentStyle;

import ratajczak.violet.product.diagram.classes.edges.DirectionEdge;

public class DirectionEdgeTest {

	private DirectionEdge edge;
	
	@BeforeMethod
	public void init(){
		edge = new DirectionEdge();
	}
	
	
	@Test
	public void constructionTest(){
		Assert.assertEquals(edge.isConcurrentLoop(), false);
		Assert.assertEquals(edge.isTransitionPointsSupported(), true);
		Assert.assertEquals(edge.getMessage().length(), 0);
	}
	
	
	@Test(description="Sprawdzenie poprawnej konstrukcji dla middleLabel", dataProvider="MessageConstruction", dataProviderClass=DataPrividers.class)
	public void MessageConstructionTest(supportMessageTest test){
		edge.setSequenceNumber(test.sequenceNumber);
		edge.setSequentialLoop(test.sequentialLoop);
		edge.setConcurrentLoop(test.ConcurrentLoop);
		edge.setMessage(test.Message);
		Assert.assertEquals(edge.getMiddleLabel().toString(), test.expected.toString());
	}
	
	@Test
	public void supportTransitionPoint(){
		
		edge.setTransitionPoints(new Point2D[]{
				new Point2D.Double(0,0)
		});
		
		Assert.assertEquals(edge.getBentStyle(), BentStyle.FREE);
	}
	
}


