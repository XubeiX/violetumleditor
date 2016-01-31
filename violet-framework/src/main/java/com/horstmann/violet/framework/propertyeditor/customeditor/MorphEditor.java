package com.horstmann.violet.framework.propertyeditor.customeditor;

import com.horstmann.violet.framework.propertyeditor.CustomPropertyEditorSupport;
import com.horstmann.violet.product.diagram.abstracts.property.Morph;
/**
 *  A property editor for the Morph type.
 * @author Artur
 *
 */
public class MorphEditor extends CustomPropertyEditorSupport{

	
	    /**
	     * Default constructor
	     */
	    public MorphEditor()
	    {
	        super(NAMES, VALUES);
	    }

	    /** Morph labels */
	    public static final String[] NAMES =
	    {
	            "to Class",
	            "to Interface",
	            "to Abstract Class"
	         
	    };

	    /** Morph technical values */
	    public static final Object[] VALUES =
	    {
	            Morph.TOCLASS,
	            Morph.TOINTERFACE,
	            Morph.ABSTRACT,
	           
	    };
	
	
}
