package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.DistributionNetworkFigure;
import com.archimatetool.editor.ui.factory.elements.DistributionNetworkUIProvider;

public class ToolboxDistributionNetworkUIProvider extends com.archimatetool.editor.ui.factory.elements.DistributionNetworkUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(DistributionNetworkFigure.class);
	}
}