package ratajczak.test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.classes.edges.XLineEdge;
import com.horstmann.violet.product.diagram.classes.edges.XLineEdgeBeanInfo;

public class XLineEdgeBeanInfoTest {
	private SimpleBeanInfo bean;

	@BeforeMethod
	public void init() {
		bean = new XLineEdgeBeanInfo();
	}

	
	@Test
	public void propertDescriptorCheck(){
		try{
		PropertyDescriptor[] falseDescriptor = new PropertyDescriptor[]{
			new PropertyDescriptor("FalseXLine", XLineEdge.class)
		};
		
		Assert.assertNotSame(falseDescriptor, bean.getPropertyDescriptors());
		
	}catch(IntrospectionException exception){}
}
}
