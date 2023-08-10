/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package org.archicontribs.toolbox.override;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.archicontribs.toolbox.ToolboxPlugin;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.archimatetool.editor.model.commands.SetProfileCommand;
import com.archimatetool.editor.propertysections.IObjectFilter;
import com.archimatetool.editor.propertysections.ITabbedLayoutConstants;
import com.archimatetool.editor.propertysections.ObjectFilter;
import com.archimatetool.editor.propertysections.SpecializationSection;
import com.archimatetool.editor.tools.ProfilesManagerDialog;
import com.archimatetool.model.IArchimateConcept;
import com.archimatetool.model.IArchimateFactory;
import com.archimatetool.model.IArchimateModel;
import com.archimatetool.model.IArchimateModelObject;
import com.archimatetool.model.IArchimatePackage;
import com.archimatetool.model.IProfile;
import com.archimatetool.model.util.ArchimateModelUtils;
import com.archimatetool.model.util.LightweightEContentAdapter;



/**
 * Property Section for a Specialization
 * 
 * @author Phillip Beauvoir
 */
public class ToolboxSpecializationSection extends com.archimatetool.editor.propertysections.SpecializationSection {
	Class<SpecializationSection> clazz = com.archimatetool.editor.propertysections.SpecializationSection.class;
	
	ComboViewer comboViewer = null;
	IProfile noneProfile = null;
	
    public static class Filter extends ObjectFilter {
        @Override
        public boolean isRequiredType(Object object) {
            return object instanceof IArchimateConcept;
        }

        @Override
        public Class<?> getAdaptableType() {
            return IArchimateConcept.class;
        }
    }
	
    @Override
    protected void createControls(Composite parent) {
        noneProfile = IArchimateFactory.eINSTANCE.createProfile();
        noneProfile.setName("(none)");
    	ToolboxPlugin.setFieldWithReflection(clazz, this, "NONE_PROFILE", noneProfile);
        //TODO: check if result is instanceof Exception
    	
        createLabel(parent, "My Specialization:", ITabbedLayoutConstants.STANDARD_LABEL_WIDTH, SWT.CENTER);
        
        Composite comp = createComposite(parent, 2);
        comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        comboViewer = new ComboViewer(new Combo(comp, SWT.READ_ONLY | SWT.BORDER));
        ToolboxPlugin.setFieldWithReflection(clazz, this, "fComboViewer", comboViewer);
        //TODO: check if result is instanceof Exception
        comboViewer.getCombo().setVisibleItemCount(12);
        comboViewer.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        getWidgetFactory().adapt(comboViewer.getControl(), true, true);
        
        comboViewer.addSelectionChangedListener(event -> {
            if( (boolean)ToolboxPlugin.getFieldWithReflection(clazz, this, "fIsRefreshing") ) { // A Viewer will get a selectionChanged event when setting it
                return;
            }

            if ( isProtected()) {
            	update();
            	return;
            }

            IProfile profile = (IProfile)((IStructuredSelection)event.getSelection()).getFirstElement();
            if(profile != null) {
                // None Profile is null
                if(profile == noneProfile) {
                    profile = null;
                }
                
                CompoundCommand result = new CompoundCommand();

                for(EObject object : getEObjects()) {
                    if(isAlive(object)) {
                        Command cmd = new SetProfileCommand((IArchimateConcept)object, profile);
                        if(cmd.canExecute()) {
                            result.add(cmd);
                        }
                    }
                }

                executeCommand(result.unwrap());
            }
        });
        
        comboViewer.setContentProvider(new IStructuredContentProvider() {
            /**
             * Return a list of suitable Profiles in the model given the concept type of the first selected object
             */
            @Override
            public Object[] getElements(Object inputElement) {
                IArchimateConcept firstSelected = (IArchimateConcept)getFirstSelectedObject();
                
                if(firstSelected == null) {
                    return new Object[0];
                }
                if ( isProtected()) {
                	List<IProfile> l = new ArrayList<>();
                	if ( firstSelected.getPrimaryProfile() == null )
                		l.add(noneProfile);
                	else
                		l.add(firstSelected.getPrimaryProfile());
                	return l.toArray();
                }
                
                List<IProfile> list = ArchimateModelUtils.findProfilesForConceptType(firstSelected.getArchimateModel(), firstSelected.eClass());
                
                // Sort the Profiles by name
                Collections.sort(list, new Comparator<IProfile>() {
                    @Override
                    public int compare(IProfile p1, IProfile p2) {
                        return p1.getName().compareToIgnoreCase(p2.getName());
                    }
                });

                // Add the "none" Profile at the top
                list.add(0, noneProfile);
                
                return list.toArray();
            }
        });
        
        comboViewer.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                return ((IProfile)element).getName();
            }
        });
        
        comboViewer.setInput(""); //$NON-NLS-1$
        
        // Open Profiles Manager Dialog button
        Button button = getWidgetFactory().createButton(comp, null, SWT.PUSH);
        button.setText(" ... "); //$NON-NLS-1$
        button.setToolTipText("Open Specializations Manager...");
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                IArchimateModelObject selected = getFirstSelectedObject();
                if(selected != null && selected.getArchimateModel() != null) {
                    ProfilesManagerDialog dialog = new ProfilesManagerDialog(getPart().getSite().getShell(), selected.getArchimateModel());
                    dialog.setDefaultClass(selected.eClass());
                    dialog.open();
                }
            }
        });
        
        // Help ID
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "com.archimatetool.help.elementPropertySection");
    }

    @Override
    protected void notifyChanged(Notification msg) {
        Object feature = msg.getFeature();
        
        // If model profiles changed or this concept's profile changed
        if(feature == IArchimatePackage.Literals.ARCHIMATE_MODEL__PROFILES
                || feature == IArchimatePackage.Literals.PROFILES__PROFILES
                || msg.getNotifier() instanceof IProfile) {
            update();
        }
    }

    @Override
    protected void update() {
        IArchimateModelObject firstSelected = getFirstSelectedObject();

        // Check also if the selected object has been deleted in case the Properties View is still showing the object if it has the focus
        if(fIsExecutingCommand || !isAlive(getFirstSelectedObject())) {
            return;
        }
        
        comboViewer.refresh();
        
        if(firstSelected instanceof IArchimateConcept) {
            //fIsRefreshing = true; // A Viewer will get a selectionChanged event when setting it
        	ToolboxPlugin.setFieldWithReflection(clazz, this, "fIsRefreshing", true);
            
            EList<IProfile> profiles = ((IArchimateConcept)firstSelected).getProfiles();
            
            if(!profiles.isEmpty()) {
            	comboViewer.setSelection(new StructuredSelection(profiles.get(0)));
            }
            else {
            	comboViewer.setSelection(new StructuredSelection(noneProfile));
            }

            //fIsRefreshing = false;
            ToolboxPlugin.setFieldWithReflection(clazz, this, "fIsRefreshing", false);
        }
    }

    @Override
    protected IObjectFilter getFilter() {
        return new Filter();
    }
    
    @Override
    protected void addAdapter() {
        super.addAdapter();
        
        // Add our adapter to the parent model to listen to its profile changes so we can update the combo
        IArchimateModelObject selected = getFirstSelectedObject();
        LightweightEContentAdapter adapter = (LightweightEContentAdapter)ToolboxPlugin.getFieldWithReflection(clazz, this, "eAdapter");
        if(selected != null && selected.getArchimateModel() != null && !selected.getArchimateModel().eAdapters().contains(adapter)) {
        	IArchimateModel model = selected.getArchimateModel();
        	ToolboxPlugin.setFieldWithReflection(clazz, this, "fModel", model);
            model.eAdapters().add(adapter);
        }
    }
    
    @Override
    protected void removeAdapter() {
        super.removeAdapter();
        
        IArchimateModel model = (IArchimateModel)ToolboxPlugin.getFieldWithReflection(clazz, this, "fModel");
        // Remove our adapter from the model
        if(model != null) {
        	LightweightEContentAdapter adapter = (LightweightEContentAdapter)ToolboxPlugin.getFieldWithReflection(clazz, this, "eAdapter");
            model.eAdapters().remove(adapter);
            ToolboxPlugin.setFieldWithReflection(clazz, this, "fModel", null);
        }
    }
    
    @Override
    public void dispose() {
        super.dispose(); // super first
        ToolboxPlugin.setFieldWithReflection(clazz, this, "eAdapter", null);
        ToolboxPlugin.setFieldWithReflection(clazz, this, "fModel", null);
    }
    
    boolean isProtected() {
    	return false;
    }
}
