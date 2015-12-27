package ratajczak.test;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.abstracts.property.MultiLineString;

import ratajczak.violet.product.diagram.classes.nodes.ObjectNode;

public class ObjectNodeTest {

	private ObjectNode node;
	
	@BeforeMethod
	public void init(){
		node = new ObjectNode();
	}
	
	@Test(description="Nazwa powinna byæ pusta")
	public void CreateTest(){
		Assert.assertEquals(node.getName().getText(), "");
	}
	
	@Test(description="Tekst powinien byæ wycentorowany i du¿y")
	public void checkNameParameterTest(){
		Assert.assertEquals(node.getName().getJustification(), MultiLineString.CENTER);
		Assert.assertEquals(node.getName().getSize(), MultiLineString.LARGE);
	}
	
	@Test
	public void cloneTest(){
		MultiLineString nazwa = new MultiLineString();
		nazwa.setText("Obiekt do sklonowania");
		node.setName(nazwa);
		
		ObjectNode klon = node.clone();
		
		Assert.assertEquals(klon.getToolTip(), node.getToolTip());
		Assert.assertEquals(klon.getName().getText(), node.getName().getText());
		
	}
	
	@Test(description="Sprawdzenie poprawnych wymiarów 100x60")
	public void ClearBoundsTest(){
		Rectangle2D expected = new Rectangle(0, 0, 100, 60);
		Rectangle2D actual = node.getBounds();
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider="BoundsText", dataProviderClass=DataPrividers.class)
	public void boundsWithSomeText(String newValue){
		Rectangle2D expected = new Rectangle(0,0,100,60);
		MultiLineString someText = new MultiLineString();
		someText.setText(newValue);
		expected.add(someText.getBounds());
		
		node.setName(someText);
		Rectangle2D actual = node.getBounds();
		
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void scholdByNullPointExceptionOngetBounds(){
		Rectangle2D expected = new Rectangle(0,0,100,60);
		MultiLineString someText = new MultiLineString();
		someText.setText(null);
		expected.add(someText.getBounds());
		node.setName(someText);
		Rectangle2D actual = node.getBounds();
		Assert.assertEquals(actual, expected);
	}
	
	
}
