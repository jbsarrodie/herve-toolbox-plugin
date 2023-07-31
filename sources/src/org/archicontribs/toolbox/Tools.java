package org.archicontribs.toolbox;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import com.archimatetool.model.IDiagramModelArchimateConnection;
import com.archimatetool.model.IDiagramModelArchimateObject;
import com.archimatetool.model.INameable;
import com.archimatetool.model.IProperties;
import com.archimatetool.model.IProperty;

public class Tools {
	static public boolean isProtected(Object object) {
		return isProtected(object, null, null);
	}
	
	static public boolean isProtected(Object object, String title, String message) {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		System.out.println("isProtected called from " + stack[1].getClassName()+"."+stack[1].getMethodName());
		System.out.println("                   from " + stack[2].getClassName()+"."+stack[2].getMethodName());
		System.out.println("                   from " + stack[3].getClassName()+"."+stack[3].getMethodName());
		System.out.println("                   from " + stack[4].getClassName()+"."+stack[4].getMethodName());
		System.out.println("                   from " + stack[5].getClassName()+"."+stack[5].getMethodName());
		System.out.println("                   from " + stack[6].getClassName()+"."+stack[6].getMethodName());
		
		if ( object instanceof EditPart editPart)
			object = editPart.getModel();
		
		if ( object instanceof IDiagramModelArchimateObject obj )
			object = obj.getArchimateElement();
		else if ( object instanceof IDiagramModelArchimateConnection con)
			object = con.getArchimateRelationship();
		
		if ( object instanceof IProperties objectWithProperties ) {
			System.out.println("Checking if ["+((INameable)objectWithProperties).getName()+"] is protected ...");			
			EList<IProperty> properties = objectWithProperties.getProperties();
			if ( properties != null ) {
				for ( IProperty prop: properties ) {
					if ( prop.getKey().equals("protected") && prop.getValue().equals("yes") ) {
						if ( title != null )
							MessageDialog.openError(Display.getDefault().getActiveShell(), title, message);
						System.out.println("   --> yes it is");
						return true;
					}
				}
			}
			System.out.println("   --> no it isn't");
		} else
			System.out.println("Cannot check if ("+object.toString()+"] is protected !");
		
		return false;
	}
}
