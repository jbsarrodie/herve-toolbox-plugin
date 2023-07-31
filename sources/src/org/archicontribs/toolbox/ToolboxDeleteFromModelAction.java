package org.archicontribs.toolbox;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.archimatetool.model.IArchimateConcept;
import com.archimatetool.model.IArchimateElement;
import com.archimatetool.model.IArchimateRelationship;
import com.archimatetool.model.IDiagramModelArchimateConnection;
import com.archimatetool.model.IDiagramModelArchimateObject;
import com.archimatetool.model.IProperty;
import com.archimatetool.model.util.ArchimateModelUtils;

public class ToolboxDeleteFromModelAction extends com.archimatetool.editor.diagram.actions.DeleteFromModelAction {
    public static final String ID = "com.archimatetool.editor.action.deleteFromModel"; //$NON-NLS-1$
    public static final String TEXT = "My Delete from model"; //$NON-NLS-1$

	public ToolboxDeleteFromModelAction(IWorkbenchPart part) {
		super(part);
        setText(TEXT);
        setId(ID);
        setActionDefinitionId(ID); // register key binding
	}
	
	@Override
    public void run() {
		System.out.println("Delete or not delete ???");
		
		List<?> selection = getSelectedObjects();
        Set<IArchimateConcept> archimateConcepts = new HashSet<IArchimateConcept>();
        
        // Gather referenced model concepts
        for(Object object : selection) {
            if(object instanceof EditPart) {
                Object model = ((EditPart)object).getModel();
                
                if(model instanceof IDiagramModelArchimateObject) {
                    IArchimateElement element = ((IDiagramModelArchimateObject)model).getArchimateElement();
                    archimateConcepts.add(element);
                    
                    // Element's relationships
                    for(IArchimateRelationship relation : ArchimateModelUtils.getAllRelationshipsForConcept(element)) {
                        archimateConcepts.add(relation);
                        // Relation's relationships
                        for(IArchimateRelationship r : ArchimateModelUtils.getAllRelationshipsForConcept(relation)) {
                            archimateConcepts.add(r);
                        }
                    }
                }
                else if(model instanceof IDiagramModelArchimateConnection) {
                    IArchimateRelationship relation = ((IDiagramModelArchimateConnection)model).getArchimateRelationship();
                    archimateConcepts.add(relation);
                    
                    // Relation's relationships
                    for(IArchimateRelationship r : ArchimateModelUtils.getAllRelationshipsForConcept(relation)) {
                        archimateConcepts.add(r);
                    }
                }
            }
        }
        
        // we check if one concept is protected
        if ( areProtected(archimateConcepts) ) {
        	MessageDialog.openError(Display.getDefault().getActiveShell(), "Delete from model", "Cannot delete as one or more archimate concepts are protected.");
        	return;
        }
        
        super.run();
	}
	
	protected boolean areProtected(Set<IArchimateConcept> archimateConcepts) {
		for ( IArchimateConcept concept: archimateConcepts)
			if ( isProtected(concept) )
				return true;
		return false;
	}
	
	protected boolean isProtected(IArchimateConcept concept) {
		EList<IProperty> properties = concept.getProperties();
		concept.getFeatures();
		if ( properties != null ) {
			for ( IProperty prop: properties ) {
				if ( prop.getKey().equals("protected") && prop.getValue().equals("yes") )
					return true;
			}
		}
		return false;
	}
}
