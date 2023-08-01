package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.OutcomeFigure;
import com.archimatetool.editor.ui.factory.elements.OutcomeUIProvider;

public class ToolboxOutcomeUIProvider extends com.archimatetool.editor.ui.factory.elements.OutcomeUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(OutcomeFigure.class);
	}
}