package org.archicontribs.toolbox.uiprovider.relationships;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateRelationshipEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.connections.ServingConnectionFigure;
import com.archimatetool.editor.ui.factory.relationships.ServingRelationshipUIProvider;

public class ToolboxServingRelationshipUIProvider extends com.archimatetool.editor.ui.factory.relationships.ServingRelationshipUIProvider {
    @Override
    public EditPart createEditPart() {
        return new ToolboxArchimateRelationshipEditPart(ServingConnectionFigure.class);
    }
}