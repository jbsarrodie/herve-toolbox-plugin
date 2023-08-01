package org.archicontribs.toolbox.uiprovider.relationships;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateRelationshipEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.connections.RealizationConnectionFigure;

public class ToolboxRealizationRelationshipUIProvider extends com.archimatetool.editor.ui.factory.relationships.RealizationRelationshipUIProvider {
    @Override
    public EditPart createEditPart() {
        return new ToolboxArchimateRelationshipEditPart(RealizationConnectionFigure.class);
    }
}