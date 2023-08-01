package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.LocationFigure;
import com.archimatetool.editor.ui.factory.elements.LocationUIProvider;

public class ToolboxLocationUIProvider extends com.archimatetool.editor.ui.factory.elements.LocationUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(LocationFigure.class);
	}
}