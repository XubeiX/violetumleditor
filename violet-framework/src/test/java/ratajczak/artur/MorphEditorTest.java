package ratajczak.artur;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.horstmann.violet.framework.propertyeditor.customeditor.MorphEditor;
import com.horstmann.violet.product.diagram.abstracts.property.Morph;

public class MorphEditorTest {

	
	@Test(dataProvider="EditorTest", dataProviderClass=DataProviders.class)
	public void testEditorValueAndName(String nameExpected, Morph valueExpected){
		String[] names = MorphEditor.NAMES;
		Object[] values = MorphEditor.VALUES;
		
		for (int i = 0; i < names.length; i++) {
			if(names[i].equals(nameExpected))
				Assert.assertEquals(values[i], valueExpected);
		}
		
		for (int i = 0; i < values.length; i++) {
			if(values[i] == valueExpected)
				Assert.assertEquals(names[i].equals(nameExpected), true);
		}
		
		
		
		
		
	}
}
