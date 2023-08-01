package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.CourseOfActionFigure;
import com.archimatetool.editor.ui.factory.elements.CourseOfActionUIProvider;

public class ToolboxCourseOfActionUIProvider extends com.archimatetool.editor.ui.factory.elements.CourseOfActionUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(CourseOfActionFigure.class);
	}
}