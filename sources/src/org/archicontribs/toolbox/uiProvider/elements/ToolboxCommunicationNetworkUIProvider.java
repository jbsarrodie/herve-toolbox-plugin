package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.CommunicationNetworkFigure;

public class ToolboxCommunicationNetworkUIProvider extends com.archimatetool.editor.ui.factory.elements.CommunicationNetworkUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(CommunicationNetworkFigure.class);
	}
}