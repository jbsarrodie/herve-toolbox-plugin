package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.GapFigure;
import com.archimatetool.editor.ui.factory.elements.GapUIProvider;

public class ToolboxGapUIProvider extends com.archimatetool.editor.ui.factory.elements.GapUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(GapFigure.class);
	}
}