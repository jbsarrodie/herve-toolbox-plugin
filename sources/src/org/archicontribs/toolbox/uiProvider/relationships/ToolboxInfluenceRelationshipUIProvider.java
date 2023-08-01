package org.archicontribs.toolbox.uiprovider.relationships;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateRelationshipEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.connections.InfluenceConnectionFigure;

public class ToolboxInfluenceRelationshipUIProvider extends com.archimatetool.editor.ui.factory.relationships.InfluenceRelationshipUIProvider {
    @Override
    public EditPart createEditPart() {
        return new ToolboxArchimateRelationshipEditPart(InfluenceConnectionFigure.class);
    }
}