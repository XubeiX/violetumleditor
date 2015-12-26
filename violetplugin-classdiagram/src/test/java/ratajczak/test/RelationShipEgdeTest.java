package ratajczak.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.abstracts.edge.SegmentedLineEdge;
import com.horstmann.violet.product.diagram.abstracts.property.BentStyle;
import com.horstmann.violet.product.diagram.classes.edges.RelationShipEdge;

import junit.framework.Assert;

public class RelationShipEgdeTest {
	
	private SegmentedLineEdge line;
	
	@BeforeMethod
	public void init(){
		line = new RelationShipEdge();
	}
	
	
	@Test(dataProvider="BentStyles", dataProviderClass=DataProviders.class)
	public void testChangeBentStyles(BentStyle newValue, BentStyle expected){
		line.setBentStyle(newValue);
		Assert.assertEquals(expected, line.getBentStyle());
	}

}
