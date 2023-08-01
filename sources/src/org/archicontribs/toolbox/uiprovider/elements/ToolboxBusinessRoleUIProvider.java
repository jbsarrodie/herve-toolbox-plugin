package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.BusinessRoleFigure;
import com.archimatetool.editor.ui.factory.elements.BusinessRoleUIProvider;

public class ToolboxBusinessRoleUIProvider extends com.archimatetool.editor.ui.factory.elements.BusinessRoleUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(BusinessRoleFigure.class);
	}
}