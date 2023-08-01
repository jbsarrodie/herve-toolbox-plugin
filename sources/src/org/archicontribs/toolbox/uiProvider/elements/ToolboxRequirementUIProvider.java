package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.RequirementFigure;

public class ToolboxRequirementUIProvider extends com.archimatetool.editor.ui.factory.elements.RequirementUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(RequirementFigure.class);
	}
}