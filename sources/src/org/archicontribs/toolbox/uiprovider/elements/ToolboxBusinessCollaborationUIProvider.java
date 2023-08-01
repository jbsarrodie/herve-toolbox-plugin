package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.CollaborationFigure;

public class ToolboxBusinessCollaborationUIProvider extends com.archimatetool.editor.ui.factory.elements.BusinessCollaborationUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(CollaborationFigure.class);
	}
}