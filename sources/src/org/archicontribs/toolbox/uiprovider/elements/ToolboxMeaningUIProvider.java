package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.MeaningFigure;

public class ToolboxMeaningUIProvider extends com.archimatetool.editor.ui.factory.elements.MeaningUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(MeaningFigure.class);
	}
}