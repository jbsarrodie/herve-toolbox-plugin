package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.EquipmentFigure;
import com.archimatetool.editor.ui.factory.elements.EquipmentUIProvider;

public class ToolboxEquipmentUIProvider extends com.archimatetool.editor.ui.factory.elements.EquipmentUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(EquipmentFigure.class);
	}
}