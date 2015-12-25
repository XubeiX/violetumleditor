package ratajczak.test;

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
	
	
	@Test
	public void getEndArrowHeadShouldBeXHEAD(){
		Assert.assertEquals(ArrowHead.X_HEAD, line.getEndArrowHead());
		
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
	
	@DataProvider(name = "ArrowHeads")
	public Object[][] arrowHeads(){
		return new Object[][]{
			{ArrowHead.BLACK_DIAMOND,ArrowHead.NONE},
			{ArrowHead.X_HEAD, ArrowHead.X_HEAD},
			{ArrowHead.HALF_V,ArrowHead.NONE}
		};
	}
	
	@Test(dataProvider = "ArrowHeads", description="Metoda zezwala wy³¹cznie na ustawienie X_HEAD inaczej ustawia NONE")
	public void shouldSetOnlyXHEDStartArrowHead(ArrowHead newValue, ArrowHead expectedHead){
		line.setStartArrowHead(newValue);
		Assert.assertEquals(expectedHead, line.getStartArrowHead());
	}
	
	@Test(expectedExceptions={NullPointerException.class}, description="Brak jakichkolwiek punktów by je pobraæ")
	public void getsAllNoExistingPoints(){
		ArrayList<Point2D> expected = new ArrayList<Point2D>();
		expected.add(new Point2D.Double(0, 0));
		Assert.assertSame(expected, line.getPoints());
	}
	
	@Test(expectedExceptions = {NullPointerException.class})
	public void getNoExistingLine(){
		ArrayList<Line2D> expected = new ArrayList<Line2D>();
		expected.add(new Line2D.Double(0, 0, 0 , 0));
		Assert.assertSame(expected, line.getConnectionPoints());
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
