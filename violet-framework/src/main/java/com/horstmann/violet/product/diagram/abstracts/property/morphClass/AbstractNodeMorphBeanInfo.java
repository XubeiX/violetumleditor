package com.horstmann.violet.product.diagram.abstracts.property.morphClass;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * The bean info for the ClassNode type.
 */
public class AbstractNodeMorphBeanInfo extends SimpleBeanInfo
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
            PropertyDescriptor nameDescriptor = new PropertyDescriptor("name", AbstractNodeMorph.class);
            nameDescriptor.setValue("priority", new Integer(2));
            PropertyDescriptor attributesDescriptor = new PropertyDescriptor("attributes", AbstractNodeMorph.class);
            attributesDescriptor.setValue("priority", new Integer(3));
            PropertyDescriptor methodsDescriptor = new PropertyDescriptor("methods", AbstractNodeMorph.class);
            methodsDescriptor.setValue("priority", new Integer(4));
            PropertyDescriptor morphDescriptor = new PropertyDescriptor("morph", AbstractNodeMorph.class);
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
