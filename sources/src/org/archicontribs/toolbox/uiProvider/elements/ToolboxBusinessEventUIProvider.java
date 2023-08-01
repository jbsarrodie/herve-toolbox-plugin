package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.EventFigure;

public class ToolboxBusinessEventUIProvider extends com.archimatetool.editor.ui.factory.elements.BusinessEventUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(EventFigure.class);
	}
}