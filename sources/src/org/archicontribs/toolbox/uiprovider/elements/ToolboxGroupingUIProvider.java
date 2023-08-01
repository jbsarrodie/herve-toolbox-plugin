package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.GroupingFigure;
import com.archimatetool.editor.ui.factory.elements.GroupingUIProvider;

public class ToolboxGroupingUIProvider extends com.archimatetool.editor.ui.factory.elements.GroupingUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(GroupingFigure.class);
	}
}