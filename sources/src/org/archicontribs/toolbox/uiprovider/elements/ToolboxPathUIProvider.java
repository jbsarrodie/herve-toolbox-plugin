package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.PathFigure;
import com.archimatetool.editor.ui.factory.elements.PathUIProvider;

public class ToolboxPathUIProvider extends com.archimatetool.editor.ui.factory.elements.PathUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(PathFigure.class);
	}
}