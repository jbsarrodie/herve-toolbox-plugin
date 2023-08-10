/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package org.archicontribs.toolbox.uiprovider;

import org.archicontribs.toolbox.ToolboxPlugin;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.LocationRequest;

/**
 * Archimate Element Edit Part
 * 
 * @author Phillip Beauvoir
 */
public class ToolboxArchimateElementEditPart extends com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart {
	public ToolboxArchimateElementEditPart() {
		super();
    }

    public ToolboxArchimateElementEditPart(Class<?> figureClass) {
        super(figureClass);
    }
    
    @Override
    public void performRequest(Request request) {
        // REQ_DIRECT_EDIT is Single-click when already selected or a Rename command
        // REQ_OPEN is Double-click
        if(request.getType() == RequestConstants.REQ_DIRECT_EDIT || request.getType() == RequestConstants.REQ_OPEN) {
            if(request instanceof LocationRequest) {
                // Edit the text control if we clicked on it
                if(getFigure().didClickTextControl(((LocationRequest)request).getLocation().getCopy())) {
                    if ( !ToolboxPlugin.isProtected(getFigure().getDiagramModelObject()) )
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