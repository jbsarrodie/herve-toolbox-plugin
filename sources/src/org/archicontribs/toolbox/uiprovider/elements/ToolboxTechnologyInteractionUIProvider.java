package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.InteractionFigure;
import com.archimatetool.editor.ui.factory.elements.TechnologyInteractionUIProvider;

public class ToolboxTechnologyInteractionUIProvider extends com.archimatetool.editor.ui.factory.elements.TechnologyInteractionUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(InteractionFigure.class);
	}
}