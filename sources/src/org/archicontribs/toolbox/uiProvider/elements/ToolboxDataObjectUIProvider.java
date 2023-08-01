package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.ObjectFigure;

public class ToolboxDataObjectUIProvider extends com.archimatetool.editor.ui.factory.elements.DataObjectUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(ObjectFigure.class);
	}
}