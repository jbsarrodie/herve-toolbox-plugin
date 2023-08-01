package org.archicontribs.toolbox.uiprovider.relationships;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateRelationshipEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.connections.AssociationConnectionFigure;
import com.archimatetool.editor.ui.factory.relationships.AssociationRelationshipUIProvider;

public class ToolboxAssociationRelationshipUIProvider extends com.archimatetool.editor.ui.factory.relationships.AssociationRelationshipUIProvider {
    @Override
    public EditPart createEditPart() {
        return new ToolboxArchimateRelationshipEditPart(AssociationConnectionFigure.class);
    }
}