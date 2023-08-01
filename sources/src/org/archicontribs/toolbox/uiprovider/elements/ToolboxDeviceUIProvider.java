package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.DeviceFigure;
import com.archimatetool.editor.ui.factory.elements.DeviceUIProvider;

public class ToolboxDeviceUIProvider extends com.archimatetool.editor.ui.factory.elements.DeviceUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(DeviceFigure.class);
	}
}