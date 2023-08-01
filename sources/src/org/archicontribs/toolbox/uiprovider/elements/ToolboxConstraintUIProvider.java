package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.ConstraintFigure;
import com.archimatetool.editor.ui.factory.elements.ConstraintUIProvider;

public class ToolboxConstraintUIProvider extends com.archimatetool.editor.ui.factory.elements.ConstraintUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(ConstraintFigure.class);
	}
}