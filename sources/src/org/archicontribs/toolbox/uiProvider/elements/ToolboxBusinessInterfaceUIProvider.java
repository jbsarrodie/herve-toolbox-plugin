package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.InterfaceFigure;

public class ToolboxBusinessInterfaceUIProvider extends com.archimatetool.editor.ui.factory.elements.BusinessInterfaceUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(InterfaceFigure.class);
	}
}