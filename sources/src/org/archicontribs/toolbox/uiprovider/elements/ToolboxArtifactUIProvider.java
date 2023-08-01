package org.archicontribs.toolbox.uiprovider.elements;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateElementEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.elements.ArtifactFigure;
import com.archimatetool.editor.ui.factory.elements.ArtifactUIProvider;

public class ToolboxArtifactUIProvider extends com.archimatetool.editor.ui.factory.elements.ArtifactUIProvider {
	@Override
	public EditPart createEditPart() {
	    return new ToolboxArchimateElementEditPart(ArtifactFigure.class);
	}
}