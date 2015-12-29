package ratajczak.artur;

import java.awt.geom.Point2D;

import org.testng.annotations.DataProvider;

import com.horstmann.violet.product.diagram.abstracts.property.Morph;

public class DataProviders {
	
	@DataProvider(name="Class")
	public static Object[][] clazz(){
		return new Object[][]{
			{new SupportToCreateClass("Klasa", "123", "Nowy", new Point2D.Double(0,100))},
			{new SupportToCreateClass("Klasa\n2", "", "Nowy", new Point2D.Double(200,100))},
			{new SupportToCreateClass("K", "123\n456", "Nowy\n2", new Point2D.Double(1,10))},
			{new SupportToCreateClass("K", "", "", new Point2D.Double(12,10))},
			{new SupportToCreateClass("<<interface>>K", "123\n456", "Nowy\n2", new Point2D.Double(10,110))},
			{new SupportToCreateClass("<<abstract>>\nK", "123\n456", "Now", new Point2D.Double(18,120))},
			{new SupportToCreateClass("<<\nanterfract>>\nK", "\n456", "Nwy\n2\n1", new Point2D.Double(18,120))},
			{new SupportToCreateClass("\n<<abstract>>\nK", "123\n456", "Nowy\n2", new Point2D.Double(18,120))},
			{new SupportToCreateClass("<<abstact>>\nK", "", "", new Point2D.Double(18,120))},
		};
	}
	
	
	
	@DataProvider(name="Morph")
	public static Object[][] morph(){
		return new Object[][]{
			{Morph.TOCLASS, false},
			{Morph.TOINTERFACE, false},
			{Morph.ABSTRACT, true},
		};
	}
	
	@DataProvider(name="Morph2")
	public static Object[][] morph2(){
		return new Object[][]{
			{Morph.TOCLASS, true},
			{Morph.TOINTERFACE, false},
			{Morph.ABSTRACT, false},
		};
	}
	
	@DataProvider(name="Morph3")
	public static Object[][] morph3(){
		return new Object[][]{
			{Morph.TOCLASS, false},
			{Morph.TOINTERFACE, true},
			{Morph.ABSTRACT, false},
		};
	}
	
	@DataProvider(name="BoundsText")
	public static Object[][] getText(){
		return new Object[][]{
			{"przyklad"},{"zienna dlugosc"},{"nowe znaczenie"},{""},{"A co mi tam"}
		};
	}
	
	@DataProvider(name="EditorTest")
	public static Object[][] editor(){
		return new Object[][]{
			{"to Abstract Class", Morph.ABSTRACT},
			{"to Interface",Morph.TOINTERFACE},
			{"to Class",Morph.TOCLASS}
		};
	}
}
