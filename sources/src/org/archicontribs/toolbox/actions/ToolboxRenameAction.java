package org.archicontribs.toolbox.actions;

import org.archicontribs.toolbox.Tools;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import com.archimatetool.editor.views.tree.TreeModelViewer;
import com.archimatetool.model.IArchimateConcept;
import com.archimatetool.model.IDiagramModel;
import com.archimatetool.model.IFolder;


public class ToolboxRenameAction extends com.archimatetool.editor.views.tree.actions.RenameAction {
	static String title = "Rename";
	static String messageSingle = "Cannot rename as the Archimate concept is protected.";
	static String messageMulti = "Cannot rename as one or more archimate concepts are protected.";
    
    public ToolboxRenameAction(TreeModelViewer selectionProvider) {
        super(selectionProvider);
    }
    
    @Override
    public void run() {
    	System.out.println("ToolboxRenameAction: Rename or not rename ???");
    	
    	IStructuredSelection selection = getSelection();
        if(selection == null || selection.isEmpty()) {
            return;
        }
        
        String message = selection.size()==1 ? ToolboxRenameAction.messageSingle : ToolboxRenameAction.messageMulti;
        
        // Gather referenced model concepts (required as we also need to delete its relationships in cascade)
        for(Object object : selection.toArray()) {
        	if(object instanceof IFolder folder) {
                for(EObject element : folder.getElements())
                    if ( Tools.isProtected(element, ToolboxRenameAction.title, message) )
                    	return;

                for(IFolder f : ((IFolder)object).getFolders())
                	if ( Tools.isProtected(f, ToolboxRenameAction.title, message) )
                    	return;
            }
            
            // Concept
            if( (object instanceof IArchimateConcept concept) && Tools.isProtected(concept, ToolboxRenameAction.title, message) )
                return;
            
            // Diagram Model Reference
            if( (object instanceof IDiagramModel diagramModel) && Tools.isProtected(diagramModel, ToolboxRenameAction.title, message) )
                	return;
        }
        
        super.run();
    }
}
