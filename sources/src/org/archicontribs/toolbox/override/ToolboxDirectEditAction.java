package org.archicontribs.toolbox.override;

import org.archicontribs.toolbox.Tools;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IEditorPart;

public class ToolboxDirectEditAction extends org.eclipse.gef.ui.actions.DirectEditAction {

	public ToolboxDirectEditAction(IEditorPart editor) {
		super(editor);
	}

	// we deactivate the direct edition if the object is protected
    @Override
    protected boolean calculateEnabled() {
    	if ( (getSelectedObjects().size() == 1) && (getSelectedObjects().get(0) instanceof EditPart editPart) && editPart.understandsRequest(getDirectEditRequest()) ) {
    		// we should not popup a message as it will conflict with contextual menu 
        	if ( Tools.isProtected(editPart) )
            	return false;
            return true;
        }
        return false;
    }
}
