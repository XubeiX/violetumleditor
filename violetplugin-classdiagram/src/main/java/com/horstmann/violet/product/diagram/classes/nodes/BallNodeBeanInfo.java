package com.horstmann.violet.product.diagram.classes.nodes;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class BallNodeBeanInfo extends SimpleBeanInfo{

	 public PropertyDescriptor[] getPropertyDescriptors()
	    {
	        try
	        {
	            PropertyDescriptor name = new PropertyDescriptor("Name", BallNode.class);
	            name.setValue("priority", new Integer(1));
	            return new PropertyDescriptor[]
	            {
	                    name
	            };
	        }
	        catch (IntrospectionException exception)
	        {
	            return null;
	        }
	    }
}
