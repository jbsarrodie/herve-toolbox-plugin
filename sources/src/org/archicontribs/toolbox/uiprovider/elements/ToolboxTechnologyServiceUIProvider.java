package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.ServiceFigure;
import com.archimatetool.editor.ui.factory.elements.TechnologyServiceUIProvider;

public class ToolboxTechnologyServiceUIProvider extends com.archimatetool.editor.ui.factory.elements.TechnologyServiceUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(ServiceFigure.class);
	}
}