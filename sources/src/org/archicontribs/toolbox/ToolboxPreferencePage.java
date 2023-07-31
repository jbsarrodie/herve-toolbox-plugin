package org.archicontribs.toolbox;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


public class ToolboxPreferencePage extends PreferencePage	implements IWorkbenchPreferencePage {

	/**
	 * Creates the preference page
	 */
	public ToolboxPreferencePage() {
		// nothing to do
	}

	/**
	 * Initialize the class
	 */
	@Override
	public void init(IWorkbench workbench) {
		// Nothing to do
		
	}

	/**
	 * Create the fields that will be shown in the preference page
	 */
	@Override
	protected Control createContents(Composite parent) {
		final TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		
		// Concepts protection tab
		TabItem cpTab = new TabItem(tabFolder, SWT.NONE, 0);
		cpTab.setText("Concepts protection");
		
		Composite cpPage = new Composite(tabFolder, SWT.NONE);
		cpPage.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		cpTab.setControl(cpPage);
		
		Label cpLabel = new Label(cpPage, SWT.NONE);
		cpLabel.setText("The concepts protection allows to define conditions that will forbit concepts deletion from the model");
		
		Group cpPropertiesGroup = new Group(cpPage, SWT.NULL);
		cpPropertiesGroup.setText("Properties");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		cpPropertiesGroup.setLayout(gridLayout);
		cpPropertiesGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		
		cpDisabledButton = new Button(cpPropertiesGroup, SWT.RADIO);
		cpDisabledButton.setText("Disabled");
		GridData gridData = new GridData();
		gridData.horizontalSpan = 4;
		cpDisabledButton.setLayoutData(gridData);
		
		cpPropertyButton = new Button(cpPropertiesGroup, SWT.RADIO);
		cpPropertyButton.setText("Using property value (visible)");
		gridData = new GridData();
		gridData.horizontalSpan = 4;
		cpPropertyButton.setLayoutData(gridData);
		
		cpFeatureButton = new Button(cpPropertiesGroup, SWT.RADIO);
		cpFeatureButton.setText("Using feature value (hidden)");
		gridData = new GridData();
		gridData.horizontalSpan = 4;
		cpFeatureButton.setLayoutData(gridData);
		
		cpNameLabel = new Label(cpPropertiesGroup, SWT.NONE);
		cpNameLabel.setText("Property name:");
		
		cpNameText = new Text(cpPropertiesGroup, SWT.NONE);
		cpNameText.setText("");
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		cpNameText.setLayoutData(gridData);
		
		cpValueLabel = new Label(cpPropertiesGroup, SWT.NONE);
		cpValueLabel.setText("Property value:");
		
		cpValueText = new Text(cpPropertiesGroup, SWT.NONE);
		cpValueText.setText("");
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		cpValueText.setLayoutData(gridData);
		
		cpDisabledButton.addSelectionListener(cpButtonSelected);
		cpPropertyButton.addSelectionListener(cpButtonSelected);
		cpFeatureButton.addSelectionListener(cpButtonSelected);
		
		Dialog.applyDialogFont(tabFolder);
		GridLayoutFactory.fillDefaults().margins(LayoutConstants.getSpacing()).generateLayout(cpPage);
		return tabFolder;
	}
	
	protected Button cpDisabledButton;
	protected Button cpPropertyButton;
	protected Button cpFeatureButton;
	protected Label cpNameLabel;
	protected Text cpNameText;
	protected Label cpValueLabel;
	protected Text cpValueText;
	
	final SelectionListener cpButtonSelected = new SelectionListener() {
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			if ( e.widget.equals(cpDisabledButton) ) {
				cpNameLabel.setVisible(false);
				cpNameText.setVisible(false);
				cpValueLabel.setVisible(false);
				cpValueText.setVisible(false);
			}
			if ( e.widget.equals(cpPropertyButton) ) {
				cpNameLabel.setVisible(true);
				cpNameLabel.setText("Property name:");
				cpNameText.setVisible(true);
				cpValueLabel.setVisible(true);
				cpValueLabel.setText("Property value:");
				cpValueText.setVisible(true);
			}
			if ( e.widget.equals(cpFeatureButton) ) {
				cpNameLabel.setVisible(true);
				cpNameLabel.setText("Feature name:");
				cpNameText.setVisible(true);
				cpValueLabel.setVisible(true);
				cpValueLabel.setText("Feature value:");
				cpValueText.setVisible(true);
			}
		}
	};
}
