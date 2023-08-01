package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.PlateauFigure;
import com.archimatetool.editor.ui.factory.elements.PlateauUIProvider;

public class ToolboxPlateauUIProvider extends com.archimatetool.editor.ui.factory.elements.PlateauUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(PlateauFigure.class);
	}
}