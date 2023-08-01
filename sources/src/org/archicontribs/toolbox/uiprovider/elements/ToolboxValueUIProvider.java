package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.ValueFigure;
import com.archimatetool.editor.ui.factory.elements.ValueUIProvider;

public class ToolboxValueUIProvider extends com.archimatetool.editor.ui.factory.elements.ValueUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(ValueFigure.class);
	}
}
