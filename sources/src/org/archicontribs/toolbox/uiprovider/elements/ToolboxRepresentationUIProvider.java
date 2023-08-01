package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.RepresentationFigure;

public class ToolboxRepresentationUIProvider extends com.archimatetool.editor.ui.factory.elements.RepresentationUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(RepresentationFigure.class);
	}
}