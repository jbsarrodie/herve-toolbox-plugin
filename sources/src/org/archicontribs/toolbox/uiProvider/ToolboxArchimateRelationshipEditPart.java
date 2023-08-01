package org.archicontribs.toolbox.uiprovider;

import org.archicontribs.toolbox.Tools;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.LocationRequest;

import com.archimatetool.model.ILockable;

public class ToolboxArchimateRelationshipEditPart extends com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart {
    public ToolboxArchimateRelationshipEditPart(Class<?> figureClass) {
        super(figureClass);
    }

    @Override
    public void performRequest(Request request) {
        // REQ_DIRECT_EDIT is Single-click when already selected or a Rename command
        // REQ_OPEN is Double-click
        if(request.getType() == RequestConstants.REQ_DIRECT_EDIT || request.getType() == RequestConstants.REQ_OPEN) {
            if(request instanceof LocationRequest) {
                // Edit the text control if we clicked on it
                if(!(getModel() instanceof ILockable && ((ILockable)getModel()).isLocked()) && getFigure().didClickConnectionLabel(((LocationRequest)request).getLocation().getCopy())) {
                	if ( !Tools.isProtected(getFigure().getModelConnection()) )
                		createDirectEditManager().show();
                }
                // Else open Properties View on double-click
                else if(request.getType() == RequestConstants.REQ_OPEN){
                    showPropertiesView();
                }
            }
            else {
                createDirectEditManager().show();
            }
        }
    }
}