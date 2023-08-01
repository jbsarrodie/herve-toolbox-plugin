package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.figures.elements.WorkPackageFigure;

public class ToolboxWorkPackageUIProvider extends com.archimatetool.editor.ui.factory.elements.WorkPackageUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(WorkPackageFigure.class);
	}
}