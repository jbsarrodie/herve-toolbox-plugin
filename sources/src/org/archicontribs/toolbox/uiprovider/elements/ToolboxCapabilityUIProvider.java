package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.CapabilityFigure;

public class ToolboxCapabilityUIProvider extends com.archimatetool.editor.ui.factory.elements.CapabilityUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(CapabilityFigure.class);
	}
}