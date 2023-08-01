package org.archicontribs.toolbox.uiprovider.relationships;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateRelationshipEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.connections.SpecializationConnectionFigure;
import com.archimatetool.editor.ui.factory.relationships.SpecializationRelationshipUIProvider;

public class ToolboxSpecializationRelationshipUIProvider extends com.archimatetool.editor.ui.factory.relationships.SpecializationRelationshipUIProvider {
    @Override
    public EditPart createEditPart() {
        return new ToolboxArchimateRelationshipEditPart(SpecializationConnectionFigure.class);
    }
}