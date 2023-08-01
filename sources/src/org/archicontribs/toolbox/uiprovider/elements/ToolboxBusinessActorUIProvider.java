package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.BusinessActorFigure;
import com.archimatetool.editor.ui.factory.elements.BusinessActorUIProvider;

public class ToolboxBusinessActorUIProvider extends com.archimatetool.editor.ui.factory.elements.BusinessActorUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(BusinessActorFigure.class);
	}
}