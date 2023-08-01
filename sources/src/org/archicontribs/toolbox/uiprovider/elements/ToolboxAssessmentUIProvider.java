package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.AssessmentFigure;
import com.archimatetool.editor.ui.factory.elements.AssessmentUIProvider;

public class ToolboxAssessmentUIProvider extends com.archimatetool.editor.ui.factory.elements.AssessmentUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(AssessmentFigure.class);
	}
}