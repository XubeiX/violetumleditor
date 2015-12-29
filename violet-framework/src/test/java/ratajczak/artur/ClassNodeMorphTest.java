package ratajczak.artur;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.abstracts.property.Morph;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.ClassNodeMorph;

public class ClassNodeMorphTest {
	ClassNodeMorph clazz = new ClassNodeMorph();
	
	@BeforeMethod
	public void init(){
		clazz= new ClassNodeMorph();
	}
	
	@Test(dataProvider="Morph2",dataProviderClass=DataProviders.class)
	public void morphStartValueTest(Morph value, boolean expected){
		if(clazz.getMorph() == value){
			Assert.assertEquals(true, expected);
		}else
			Assert.assertEquals(false, expected);
	}

}
