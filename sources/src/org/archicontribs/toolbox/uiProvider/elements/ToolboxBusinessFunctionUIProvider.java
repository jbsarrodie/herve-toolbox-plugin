package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.FunctionFigure;

public class ToolboxBusinessFunctionUIProvider extends com.archimatetool.editor.ui.factory.elements.BusinessFunctionUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(FunctionFigure.class);
	}
}