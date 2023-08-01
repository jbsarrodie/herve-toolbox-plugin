package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.CollaborationFigure;
import com.archimatetool.editor.ui.factory.elements.TechnologyCollaborationUIProvider;

public class ToolboxTechnologyCollaborationUIProvider extends com.archimatetool.editor.ui.factory.elements.TechnologyCollaborationUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(CollaborationFigure.class);
	}
}