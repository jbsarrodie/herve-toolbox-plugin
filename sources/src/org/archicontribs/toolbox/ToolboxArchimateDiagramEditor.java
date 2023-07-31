package org.archicontribs.toolbox;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;

import com.archimatetool.editor.diagram.actions.DeleteFromModelAction;

public class ToolboxArchimateDiagramEditor extends com.archimatetool.editor.diagram.ArchimateDiagramEditor {
	
    /**
     * Add some extra Actions - *after* the graphical viewer has been created
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void createActions(GraphicalViewer viewer) {
    	super.createActions(viewer);
    	System.out.println("Changing actions ...");
    	
    	ActionRegistry registry = getActionRegistry();
        IAction action;
    	
    	// we remove the former DeleteFrmmModelAction action
        action = new DeleteFromModelAction(this);
        registry.removeAction(action);
        getSelectionActions().remove(action.getId());
        
        // we add our ToolboxDeleteFromModelAction action instead
        action = new ToolboxDeleteFromModelAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
    }
}
