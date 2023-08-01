package org.archicontribs.toolbox.uiprovider.relationships;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateRelationshipEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.connections.AggregationConnectionFigure;

public class ToolboxAggregationRelationshipUIProvider extends com.archimatetool.editor.ui.factory.relationships.AggregationRelationshipUIProvider {
    @Override
    public EditPart createEditPart() {
        return new ToolboxArchimateRelationshipEditPart(AggregationConnectionFigure.class);
    }
}