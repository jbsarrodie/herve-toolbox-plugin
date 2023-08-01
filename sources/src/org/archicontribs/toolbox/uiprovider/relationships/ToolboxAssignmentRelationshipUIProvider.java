package org.archicontribs.toolbox.uiprovider.relationships;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateRelationshipEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.connections.AssignmentConnectionFigure;
import com.archimatetool.editor.ui.factory.relationships.AssignmentRelationshipUIProvider;

public class ToolboxAssignmentRelationshipUIProvider extends com.archimatetool.editor.ui.factory.relationships.AssignmentRelationshipUIProvider {
    @Override
    public EditPart createEditPart() {
        return new ToolboxArchimateRelationshipEditPart(AssignmentConnectionFigure.class);
    }
}