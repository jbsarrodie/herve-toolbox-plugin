package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.ValueStreamFigure;


public class ToolboxValueStreamUIProvider extends com.archimatetool.editor.ui.factory.elements.ValueStreamUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(ValueStreamFigure.class);
	}
}