package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.InteractionFigure;

public class ToolboxApplicationInteractionUIProvider extends com.archimatetool.editor.ui.factory.elements.ApplicationInteractionUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(InteractionFigure.class);
	}
}