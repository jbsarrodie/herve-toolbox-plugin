package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.PrincipleFigure;

public class ToolboxPrincipleUIProvider extends com.archimatetool.editor.ui.factory.elements.PrincipleUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(PrincipleFigure.class);
	}
}