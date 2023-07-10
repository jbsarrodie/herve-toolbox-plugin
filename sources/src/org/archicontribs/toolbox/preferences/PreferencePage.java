package org.archicontribs.toolbox.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class PreferencePage extends FieldEditorPreferencePage	implements IWorkbenchPreferencePage {
	/**
	 * Creates the preference page
	 */
	public PreferencePage() {
		super(FieldEditorPreferencePage.GRID);
	}

	/**
	 * Initialize the class
	 */
	@Override
	public void init(IWorkbench workbench) {
		// Nothing to do
		
	}

	/**
	 * Create the fields that will be whown in the preference page
	 */
	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		
	}
}
