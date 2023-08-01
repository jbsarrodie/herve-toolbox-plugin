package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.DeliverableFigure;

public class ToolboxDeliverableUIProvider extends com.archimatetool.editor.ui.factory.elements.DeliverableUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(DeliverableFigure.class);
	}
}