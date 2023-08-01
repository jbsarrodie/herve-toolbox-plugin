package org.archicontribs.toolbox.uiprovider.relationships;

import org.archicontribs.toolbox.uiprovider.ToolboxArchimateRelationshipEditPart;
import org.eclipse.gef.EditPart;
import com.archimatetool.editor.diagram.figures.connections.CompositionConnectionFigure;

public class ToolboxCompositionRelationshipUIProvider extends com.archimatetool.editor.ui.factory.relationships.CompositionRelationshipUIProvider {
    @Override
    public EditPart createEditPart() {
        return new ToolboxArchimateRelationshipEditPart(CompositionConnectionFigure.class);
    }
}