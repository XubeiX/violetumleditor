package ratajczak.artur;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.abstracts.property.Morph;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.ClassNodeMorph;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.InterfaceNodeMorph;

public class InterfaceNodeMorphTest {

InterfaceNodeMorph inter = new InterfaceNodeMorph();
	
	@BeforeMethod
	public void init(){
		inter= new InterfaceNodeMorph();
	}
	
	@Test(dataProvider="Morph3",dataProviderClass=DataProviders.class)
	public void morphStartValueTest(Morph value, boolean expected){
		if(inter.getMorph() == value){
			Assert.assertEquals(true, expected);
		}else
			Assert.assertEquals(false, expected);
	}
}
