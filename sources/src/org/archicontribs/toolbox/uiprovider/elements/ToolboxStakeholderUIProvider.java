package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.StakeholderFigure;
import com.archimatetool.editor.ui.factory.elements.StakeholderUIProvider;

public class ToolboxStakeholderUIProvider extends com.archimatetool.editor.ui.factory.elements.StakeholderUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(StakeholderFigure.class);
	}
}