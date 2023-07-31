package org.archicontribs.toolbox.override;

import org.archicontribs.toolbox.actions.ToolboxDeleteFromModelAction;
import org.archicontribs.toolbox.actions.ToolboxRenameAction;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.actions.ActionFactory;

import com.archimatetool.editor.diagram.Messages;
import com.archimatetool.editor.diagram.actions.DeleteFromModelAction;
import com.archimatetool.editor.views.tree.actions.RenameAction;

public class ToolboxArchimateDiagramEditor extends com.archimatetool.editor.diagram.ArchimateDiagramEditor {
	
    /**
     * Add some extra Actions - *after* the graphical viewer has been created
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void createActions(GraphicalViewer viewer) {
    	super.createActions(viewer);
    	System.out.println("ToolboxArchimateDiagramEditor: Changing actions ...");
    	
    	ActionRegistry registry = getActionRegistry();
        IAction action;
    	
    	// we remove the former DeleteFromModelAction action
        // -------------------------------------------------
        action = registry.getAction(ActionFactory.DELETE.getId());
        registry.removeAction(action);
        getSelectionActions().remove(action.getId());
        getUpdateCommandStackActions().remove((UpdateAction)action);
        
        	// and add our ToolboxDeleteFromModelAction action instead
        action = new ToolboxDeleteFromModelAction(this);
        action.setId(ActionFactory.DELETE.getId());
        action.setText("&Delete from view");
        action.setToolTipText(action.getText());
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        getUpdateCommandStackActions().add((UpdateAction)action);
        
        // we remove the former RenameFromModelAction action
        // -------------------------------------------------
        action = registry.getAction(ActionFactory.RENAME.getId());
        registry.removeAction(action);
        getSelectionActions().remove(action.getId());
        getUpdateCommandStackActions().remove((UpdateAction)action);
        
        	// and add our ToolboxDeleteFromModelAction action instead
        action = new ToolboxDirectEditAction(this);
        action.setId(ActionFactory.RENAME.getId());
        action.setText("Rena&me");
        action.setToolTipText("Rename");
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        getUpdateCommandStackActions().add((UpdateAction)action);
    }
}
