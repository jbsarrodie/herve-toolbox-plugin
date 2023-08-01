package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.ApplicationComponentFigure;
import com.archimatetool.editor.ui.factory.elements.ApplicationComponentUIProvider;

public class ToolboxApplicationComponentUIProvider extends com.archimatetool.editor.ui.factory.elements.ApplicationComponentUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(ApplicationComponentFigure.class);
	}
}