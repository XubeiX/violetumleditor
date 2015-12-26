package ratajczak.test;

import org.testng.annotations.DataProvider;

import com.horstmann.violet.product.diagram.abstracts.property.ArrowHead;
import com.horstmann.violet.product.diagram.abstracts.property.BentStyle;

public class DataProviders {
	
	@DataProvider(name = "ProtptypeTest")
	public static Object[][] test() {
		return new Object[][] { { "Linia pozioma", false }, { "Class Diagram", false },
				{ "Association Navigability", true }, { "Artur", false }, { "Note connector", true },
				{ "<<Create>>", true }, { "<<Extends>>", false }, { "<<Use>>", true }, };
	}
	
	@DataProvider(name="LabelTest")
	public static String[][] getLabels(){
		return new String[][]{
			{"test","test"},
			{"Artur","Artur"},
			{"label","label"},
			{"dwa","dwa"},
			
		};
	}
	
	@DataProvider(name="BentStyles")
	public static Object[][] getBenStyles(){
		return new Object[][]{
			{BentStyle.AUTO, BentStyle.AUTO},
			{BentStyle.STRAIGHT, BentStyle.STRAIGHT},
			{BentStyle.HV, BentStyle.HV},
			{null, BentStyle.AUTO}
		};
	}
	
	@DataProvider(name = "ArrowHeads")
	public static Object[][] arrowHeads(){
		return new Object[][]{
			{ArrowHead.BLACK_DIAMOND,ArrowHead.NONE},
			{ArrowHead.X_HEAD, ArrowHead.X_HEAD},
			{ArrowHead.HALF_V,ArrowHead.NONE},
			{ArrowHead.V,ArrowHead.V},
			{null, ArrowHead.NONE}
		};
	}
}
