package com.horstmann.violet.product.diagram.classes.edges;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class XLineEdgeBeanInfo extends SimpleBeanInfo {

	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		try {
			PropertyDescriptor[] descriptors = new PropertyDescriptor[] {
					new PropertyDescriptor("startLabel", XLineEdge.class),
					new PropertyDescriptor("startArrowHead", XLineEdge.class),
					new PropertyDescriptor("middleLabel", XLineEdge.class),
					new PropertyDescriptor("endLabel", XLineEdge.class),
					new PropertyDescriptor("endArrowHead", XLineEdge.class),
					//new PropertyDescriptor("bentStyle", XLineEdge.class), 
					};
			
			for (int i = 0; i < descriptors.length; i++) {
				descriptors[i].setValue("priority", new Integer(i));
			}
			return descriptors;
		} catch (IntrospectionException exception) {
			exception.printStackTrace();
			return null;
		}

	}
}
