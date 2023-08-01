package org.archicontribs.toolbox.uiprovider.relationships;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateRelationshipEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.connections.FlowConnectionFigure;
import com.archimatetool.editor.ui.factory.relationships.FlowRelationshipUIProvider;

public class ToolboxFlowRelationshipUIProvider extends com.archimatetool.editor.ui.factory.relationships.FlowRelationshipUIProvider {
    @Override
    public EditPart createEditPart() {
        return new ToolboxArchimateRelationshipEditPart(FlowConnectionFigure.class);
    }
}