package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.ProductFigure;
import com.archimatetool.editor.ui.factory.elements.ProductUIProvider;

public class ToolboxProductUIProvider extends com.archimatetool.editor.ui.factory.elements.ProductUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(ProductFigure.class);
	}
}