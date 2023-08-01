package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.SystemSoftwareFigure;
import com.archimatetool.editor.ui.factory.elements.SystemSoftwareUIProvider;

public class ToolboxSystemSoftwareUIProvider extends com.archimatetool.editor.ui.factory.elements.SystemSoftwareUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(SystemSoftwareFigure.class);
	}
}