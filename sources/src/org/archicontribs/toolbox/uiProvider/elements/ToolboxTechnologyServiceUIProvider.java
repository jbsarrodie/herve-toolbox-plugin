package org.archicontribs.toolbox.uiProvider.elements;

import org.archicontribs.toolbox.uiProvider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.ServiceFigure;

public class ToolboxTechnologyServiceUIProvider extends com.archimatetool.editor.ui.factory.elements.TechnologyServiceUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(ServiceFigure.class);
	}
}
