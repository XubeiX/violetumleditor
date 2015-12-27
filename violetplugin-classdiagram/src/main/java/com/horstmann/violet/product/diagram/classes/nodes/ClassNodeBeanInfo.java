package com.horstmann.violet.product.diagram.classes.nodes;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * The bean info for the ClassNode type.
 */
public class ClassNodeBeanInfo extends SimpleBeanInfo
{
    /*
     * (non-Javadoc)
     * 
     * @see java.beans.BeanInfo#getPropertyDescriptors()
     */
    public PropertyDescriptor[] getPropertyDescriptors()
    {
        try
        {
            PropertyDescriptor nameDescriptor = new PropertyDescriptor("name", ClassNode.class);
            nameDescriptor.setValue("priority", new Integer(2));
            PropertyDescriptor attributesDescriptor = new PropertyDescriptor("attributes", ClassNode.class);
            attributesDescriptor.setValue("priority", new Integer(3));
            PropertyDescriptor methodsDescriptor = new PropertyDescriptor("methods", ClassNode.class);
            methodsDescriptor.setValue("priority", new Integer(4));
            PropertyDescriptor morphDescriptor = new PropertyDescriptor("morph", ClassNode.class);
            morphDescriptor.setValue("priority", new Integer(1));
            return new PropertyDescriptor[]
            {
                    nameDescriptor,
                    attributesDescriptor,
                    methodsDescriptor,
                    morphDescriptor
            };
        }
        catch (IntrospectionException exception)
        {
            return null;
        }
    }
}
