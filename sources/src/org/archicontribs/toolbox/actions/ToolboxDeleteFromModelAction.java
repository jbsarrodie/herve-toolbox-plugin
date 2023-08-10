package org.archicontribs.toolbox.actions;

import java.util.List;

import org.archicontribs.toolbox.ToolboxPlugin;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IWorkbenchPart;

import com.archimatetool.model.IArchimateElement;
import com.archimatetool.model.IArchimateRelationship;
import com.archimatetool.model.IDiagramModelArchimateComponent;
import com.archimatetool.model.IDiagramModelArchimateConnection;
import com.archimatetool.model.IDiagramModelArchimateObject;
import com.archimatetool.model.util.ArchimateModelUtils;

public class ToolboxDeleteFromModelAction extends com.archimatetool.editor.diagram.actions.DeleteFromModelAction {

	public ToolboxDeleteFromModelAction(IWorkbenchPart part) {
		super(part);
	}
	
	@Override
    public void run() {
		System.out.println("ToolboxDeleteFromModelAction: Delete or not delete ???");
		
		List<?> selection = getSelectedObjects();
        
        for(Object object : selection) {
            if(object instanceof EditPart editPart) {
                Object model = editPart.getModel();
                
                if(model instanceof IDiagramModelArchimateObject obj) {
                    IArchimateElement element = obj.getArchimateElement();
                    if ( ToolboxPlugin.isProtected(element, "Delete", "Cannot delete as one or more archimate concepts are protected.") )
                    	return;
                    
                    // Element's relationships
                    for(IArchimateRelationship relationship : ArchimateModelUtils.getAllRelationshipsForConcept(element)) {
                    	if ( ToolboxPlugin.isProtected(relationship, "Delete", "Cannot delete as one or more archimate concepts are protected.") )
                    		return;
                        // Relation's relationships
                        for(IArchimateRelationship r : ArchimateModelUtils.getAllRelationshipsForConcept(relationship)) {
                        	if ( ToolboxPlugin.isProtected(r, "Delete", "Cannot delete as one or more archimate concepts are protected.") )
                        		return;
                        }
                    }
                }
                else if(model instanceof IDiagramModelArchimateConnection con) {
                    IArchimateRelationship relationship = con.getArchimateRelationship();
                    if ( ToolboxPlugin.isProtected(relationship, "Delete", "Cannot delete as one or more archimate concepts are protected.") )
                		return;
                    
                    // Relation's relationships
                    for(IArchimateRelationship r : ArchimateModelUtils.getAllRelationshipsForConcept(relationship)) {
                    	if ( ToolboxPlugin.isProtected(r, "Delete", "Cannot delete as one or more archimate concepts are protected.") )
                    		return;
                    }
                }
            }
        }
        
        super.run();
	}
	
    @Override
    protected boolean calculateEnabled() {
        List<?> list = getSelectedObjects();
        
        if(list.isEmpty()) {
            return false;
        }
        
        boolean atLeastOneArchimateComponent = false;
        
        for(Object object : list) {
            if(object instanceof EditPart) {
                Object model = ((EditPart)object).getModel();
                if(model instanceof IDiagramModelArchimateComponent) {
                	if ( ToolboxPlugin.isProtected(model) )
                		return false;
                	atLeastOneArchimateComponent = true;
                }
            }
        }
        
        return atLeastOneArchimateComponent;
    }
}
