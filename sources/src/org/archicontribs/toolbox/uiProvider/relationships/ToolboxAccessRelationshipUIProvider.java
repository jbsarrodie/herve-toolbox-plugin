package org.archicontribs.toolbox.uiprovider.relationships;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateRelationshipEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.connections.AccessConnectionFigure;

public class ToolboxAccessRelationshipUIProvider extends com.archimatetool.editor.ui.factory.relationships.AccessRelationshipUIProvider {
    @Override
    public EditPart createEditPart() {
        return new ToolboxArchimateRelationshipEditPart(AccessConnectionFigure.class);
    }
}