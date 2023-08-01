package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.EventFigure;
import com.archimatetool.editor.ui.factory.elements.ApplicationEventUIProvider;

public class ToolboxApplicationEventUIProvider extends com.archimatetool.editor.ui.factory.elements.ApplicationEventUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(EventFigure.class);
	}
}