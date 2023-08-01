package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.InteractionFigure;

public class ToolboxBusinessInteractionUIProvider extends com.archimatetool.editor.ui.factory.elements.BusinessInteractionUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(InteractionFigure.class);
	}
}