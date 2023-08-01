package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.FunctionFigure;
import com.archimatetool.editor.ui.factory.elements.ApplicationFunctionUIProvider;

public class ToolboxApplicationFunctionUIProvider extends com.archimatetool.editor.ui.factory.elements.ApplicationFunctionUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(FunctionFigure.class);
	}
}