package ratajczak.artur;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.abstracts.property.Morph;
import com.horstmann.violet.product.diagram.abstracts.property.MultiLineString;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.AbstractNodeMorph;



public class AbstractNodeMorphTest {
	private AbstractNodeMorph node;
	
	@BeforeMethod
	public void init(){
		node = new AbstractNodeMorph();
	}
	
	@Test
	public void constructionTest(){
		Assert.assertEquals(node.getName().getText(), "<<abstract>>\n");
		Assert.assertEquals(node.getMethods().getText(), "");
		Assert.assertEquals(node.getAttributes().getText(), "");
	}
	
	@Test
	public void nameIsLargeAndJustyfy(){
		Assert.assertEquals(node.getName().getSize(), MultiLineString.LARGE);
		Assert.assertEquals(node.getName().getJustification(), MultiLineString.CENTER);
	}
	
	@Test(dataProvider="Morph", dataProviderClass=DataProviders.class)
	public void morphValuTest(Morph value, boolean expected){
		if(node.getMorph() == value){
			Assert.assertEquals(true, expected);
		}else
			Assert.assertEquals(false, expected);
	}
	
	@Test()
	public void cloneTest(){
		MultiLineString name = new MultiLineString();
		name.setText("Test");
		MultiLineString att = new MultiLineString();
		att.setText("att");
		MultiLineString meth = new MultiLineString();
		meth.setText("meth");
		
		node.setName(name);
		node.setAttributes(att);
		node.setMethods(meth);
		AbstractNodeMorph cloned = node.clone();
		Assert.assertEquals(cloned.getName().getText(), node.getName().getText());
		Assert.assertEquals(cloned.getAttributes().getSize(), node.getAttributes().getSize());
	}
	
	@Test(dataProvider="BoundsText", dataProviderClass=DataProviders.class)
	public void boundsWithSomeTextTest(String newValue){
		Rectangle2D expected = new Rectangle(0,0,100,60);
		MultiLineString someText = new MultiLineString();
		someText.setText(newValue);
		expected.add(someText.getBounds());
		
		node.setName(someText);
		Rectangle2D actual = node.getBounds();
		
		Assert.assertEquals(actual, expected);
		
	}
	
	
}
