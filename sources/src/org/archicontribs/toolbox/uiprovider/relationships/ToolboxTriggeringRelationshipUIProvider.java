package org.archicontribs.toolbox.uiprovider.relationships;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateRelationshipEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.connections.TriggeringConnectionFigure;

public class ToolboxTriggeringRelationshipUIProvider extends com.archimatetool.editor.ui.factory.relationships.TriggeringRelationshipUIProvider {
    @Override
    public EditPart createEditPart() {
        return new ToolboxArchimateRelationshipEditPart(TriggeringConnectionFigure.class);
    }
}
