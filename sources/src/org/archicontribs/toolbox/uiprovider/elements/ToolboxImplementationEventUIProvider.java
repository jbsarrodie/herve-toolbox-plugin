package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.EventFigure;

public class ToolboxImplementationEventUIProvider extends com.archimatetool.editor.ui.factory.elements.ImplementationEventUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(EventFigure.class);
	}
}