package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.FacilityFigure;
import com.archimatetool.editor.ui.factory.elements.FacilityUIProvider;

public class ToolboxFacilityUIProvider extends com.archimatetool.editor.ui.factory.elements.FacilityUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(FacilityFigure.class);
	}
}