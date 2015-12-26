package ratajczak.test;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.abstracts.edge.SegmentedLineEdge;
import com.horstmann.violet.product.diagram.abstracts.property.ArrowHead;
import com.horstmann.violet.product.diagram.abstracts.property.BentStyle;
import com.horstmann.violet.product.diagram.abstracts.property.LineStyle;
import com.horstmann.violet.product.diagram.classes.edges.XLineEdge;
import com.horstmann.violet.product.diagram.classes.nodes.ClassNode;

import junit.framework.Assert;

public class XLineEdgeTest {
	
	private SegmentedLineEdge line;
	
	@BeforeMethod
	public void init(){
		line = new XLineEdge();
	}
	
	@Test
	public void isTransitionPointsSupportedShouldBeFALSE(){
		Assert.assertFalse(line.isTransitionPointsSupported());
	}
	
	
	
	@Test(dataProvider="LabelTest", dataProviderClass= DataProviders.class)
	public void checkLable(String newValue, String expected){
		line.setStartLabel(newValue);
		line.setMiddleLabel(newValue);
		line.setEndLabel(newValue);
		Assert.assertEquals(expected, line.getStartLabel());
		Assert.assertEquals(expected, line.getEndLabel());
		Assert.assertEquals(expected, line.getMiddleLabel());
		
	}
	
	
	@Test(dataProvider="BentStyles", dataProviderClass = DataProviders.class)
	public void BenStyleTest(BentStyle newValue, BentStyle expected){
		line.setBentStyle(newValue);
		Assert.assertSame(expected, line.getBentStyle());
	}
	
	@Test
	public void getLineStyleShouldBeSOLID(){
		Assert.assertEquals(LineStyle.SOLID, line.getLineStyle());
	}
	
	@Test(description="Nie powinno daæ siê zmieniæ stylu lini")
	public void shouldNotChangeLineStyle(){
		line.setLineStyle(LineStyle.DOTTED);
		Assert.assertNotSame(LineStyle.DOTTED, line.getLineStyle());
		
	}
	
	
	
	@Test(dataProvider = "ArrowHeads",dataProviderClass = DataProviders.class, description="Metoda zezwala wy³¹cznie na ustawienie X_HEAD inaczej ustawia NONE")
	public void shouldSetOnlyXandVStartArrowHead(ArrowHead newValue, ArrowHead expectedHead){
		line.setStartArrowHead(newValue);
		Assert.assertEquals(expectedHead, line.getStartArrowHead());
	}
	
	@Test(dataProvider="ArrowHeads", dataProviderClass= DataProviders.class)
	public void shouldSetOnlyXandVEndArrowHead(ArrowHead newValue, ArrowHead expected){
		line.setEndArrowHead(newValue);
		Assert.assertEquals(expected, line.getEndArrowHead());
	}
	
	@Test(expectedExceptions={NullPointerException.class}, description="Brak jakichkolwiek punktów by je pobraæ")
	public void getsAllNoExistingPoints(){
		ArrayList<Point2D> expected = new ArrayList<Point2D>();
		expected.add(new Point2D.Double(0, 0));
		Assert.assertSame(expected, line.getPoints());
	}
	
	@Test(expectedExceptions = {NullPointerException.class}, description="Brak punktów by wyznaczyæ liniê")
	public void getNoExistingLine(){
		ArrayList<Line2D> expected = new ArrayList<Line2D>();
		expected.add(new Line2D.Double(0, 0, 0 , 0));
		Assert.assertSame(expected, line.getConnectionPoints());
	}
	
	
	@Test(description="Wymiary bloków ClassNode to 100x60. Wynik to skrajny punkt klasy startowej lub pocz¹tkowej")
	public void getConnectionPointsBetween2Class(){
		ClassNode c1 = new ClassNode();
		ClassNode c2 = new ClassNode();
		c1.setLocation(new Point2D.Double(0, 0)); //domyœlna d³ugoœæ 100 wysokoœæ 60 z klasy ClassNode 
		c2.setLocation(new Point2D.Double(200,0));
		
		line.setStart(c1);
		line.setEnd(c2);
		
		Line2D punkty = line.getConnectionPoints();
		
		Point2D c1Linia = new Point2D.Double(100, 60);
		Point2D c2Linia = new Point2D.Double(200, 60);
		
		Assert.assertEquals(c1Linia, punkty.getP1());
		Assert.assertEquals(c2Linia, punkty.getP2());
		
	}
	
	@Test
	public void privateMethodReturnedPointsTest(){
		Class classToTest = XLineEdge.class;
		try {
			Method privateMethod = classToTest.getMethod("getPointsForNodesOnDifferentLifeLines", new Class[]{ClassNode.class, ClassNode.class});
			privateMethod.setAccessible(true);
			
			ArrayList<Point2D> list = new ArrayList<Point2D>();
			
			ClassNode c1 = new ClassNode();
			c1.setLocation(new Point2D.Double(10,0));
			ClassNode c2 = new ClassNode();
			c2.setLocation(new Point2D.Double(0,20));
			
			list.add(c1.getLocation());
			list.add(c2.getLocation());
			
			ArrayList<Point2D> returnValue = (ArrayList<Point2D>)privateMethod.invoke(classToTest, c1,c2);
			
			Assert.assertSame(list, returnValue);
		} catch (NoSuchMethodException e) {} catch (SecurityException e) {} catch (IllegalAccessException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		}
	}
	
}
