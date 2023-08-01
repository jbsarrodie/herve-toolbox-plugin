package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.ContractFigure;
import com.archimatetool.editor.ui.factory.elements.ContractUIProvider;

public class ToolboxContractUIProvider extends com.archimatetool.editor.ui.factory.elements.ContractUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(ContractFigure.class);
	}
}