package com.horstmann.violet.product.diagram.abstracts.property.morphClass;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * The bean info for the ClassNode type.
 */
public class ClassNodeMorphBeanInfo extends SimpleBeanInfo
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
            PropertyDescriptor nameDescriptor = new PropertyDescriptor("name", ClassNodeMorph.class);
            nameDescriptor.setValue("priority", new Integer(2));
            PropertyDescriptor attributesDescriptor = new PropertyDescriptor("attributes", ClassNodeMorph.class);
            attributesDescriptor.setValue("priority", new Integer(3));
            PropertyDescriptor methodsDescriptor = new PropertyDescriptor("methods", ClassNodeMorph.class);
            methodsDescriptor.setValue("priority", new Integer(4));
            PropertyDescriptor morphDescriptor = new PropertyDescriptor("morph", ClassNodeMorph.class);
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
