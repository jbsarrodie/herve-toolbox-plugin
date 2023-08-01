package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.ProcessFigure;

public class ToolboxBusinessProcessUIProvider extends com.archimatetool.editor.ui.factory.elements.BusinessProcessUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(ProcessFigure.class);
	}
}