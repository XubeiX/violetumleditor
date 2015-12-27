package com.horstmann.violet.product.diagram.abstracts.property.morphClass;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * The bean info for the InterfaceNode type.
 */
public class InterfaceNodeMorphBeanInfo extends SimpleBeanInfo
{

    @Override
    public PropertyDescriptor[] getPropertyDescriptors()
    {
        try
        {
            PropertyDescriptor nameDescriptor = new PropertyDescriptor("name", InterfaceNodeMorph.class);
            nameDescriptor.setValue("priority", new Integer(2));
            PropertyDescriptor methodsDescriptor = new PropertyDescriptor("methods", InterfaceNodeMorph.class);
            methodsDescriptor.setValue("priority", new Integer(3));
            PropertyDescriptor morphDescriptor = new PropertyDescriptor("morph", InterfaceNodeMorph.class);
            morphDescriptor.setValue("priority", new Integer(1));
            return new PropertyDescriptor[]
            {
                    nameDescriptor,
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
