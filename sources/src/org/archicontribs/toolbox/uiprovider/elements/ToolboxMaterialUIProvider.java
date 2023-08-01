package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.MaterialFigure;

public class ToolboxMaterialUIProvider extends com.archimatetool.editor.ui.factory.elements.MaterialUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(MaterialFigure.class);
	}
}