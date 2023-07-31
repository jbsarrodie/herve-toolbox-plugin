package org.archicontribs.toolbox.actions;

import org.archicontribs.toolbox.Tools;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import com.archimatetool.editor.views.tree.TreeModelViewer;
import com.archimatetool.model.IArchimateConcept;
import com.archimatetool.model.IDiagramModel;
import com.archimatetool.model.IFolder;


public class ToolboxDeleteAction extends com.archimatetool.editor.views.tree.actions.DeleteAction {
    
    public ToolboxDeleteAction(TreeModelViewer selectionProvider) {
        super(selectionProvider);
    }
    
    @Override
    public void run() {
    	System.out.println("ToolboxDeleteAction: Delete or not delete ???");
    	
    	IStructuredSelection selection = getSelection();
        if(selection == null || selection.isEmpty()) {
            return;
        }
        
        // Gather referenced model concepts (required as we also need to delete its relationships in cascade)
        for(Object object : selection.toArray()) {
        	if(object instanceof IFolder folder) {
                for(EObject element : folder.getElements())
                    if ( Tools.isProtected(element, "Delete", "Cannot delete as one or more archimate concepts are protected.") )
                    	return;

                for(IFolder f : ((IFolder)object).getFolders())
                	if ( Tools.isProtected(f, "Delete", "Cannot delete as one or more archimate concepts are protected.") )
                    	return;
            }
            
            // Concept
            if( (object instanceof IArchimateConcept concept) && Tools.isProtected(concept, "Delete", "Cannot delete as one or more archimate concepts are protected.") )
                return;
            
            // Diagram Model Reference
            if( (object instanceof IDiagramModel diagramModel) && Tools.isProtected(diagramModel, "Delete", "Cannot delete as one or more archimate concepts are protected.") )
                return;
        }
        
        super.run();
    }
}
