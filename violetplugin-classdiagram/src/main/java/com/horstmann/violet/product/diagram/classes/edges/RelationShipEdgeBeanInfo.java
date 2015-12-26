package com.horstmann.violet.product.diagram.classes.edges;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class RelationShipEdgeBeanInfo extends SimpleBeanInfo {
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		try {
			PropertyDescriptor[] descriptors = new PropertyDescriptor[] {
					new PropertyDescriptor("bentStyle", RelationShipEdge.class), };

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
