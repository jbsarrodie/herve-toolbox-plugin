package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.JunctionFigure;

public class ToolboxJunctionUIProvider extends com.archimatetool.editor.ui.factory.elements.JunctionUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(JunctionFigure.class);
	}
}