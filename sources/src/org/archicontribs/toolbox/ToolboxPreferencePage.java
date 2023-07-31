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
	
	// TODO: move as manual update in preference page
			/*
			String jarFilename = com.archimatetool.editor.views.tree.TreeViewpointFilterProvider.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			File oldJarFilename = new File(jarFilename + ".old");
			if ( oldJarFilename.exists() )
				System.out.println("   -> JAR file already patched");
			else {
				System.out.println("   -> getting classPool");
				ClassPool classPool = ClassPool.getDefault();
				ClassPath classPath = classPool.insertClassPath(jarFilename);
		
				System.out.println("   -> getting ctClass");
				CtClass ctClass = classPool.get("com.archimatetool.editor.views.tree.TreeViewpointFilterProvider");		
				System.out.println("      -> got "+ctClass.getName());
		
				System.out.println("   -> getting ctMethod");
				CtMethod ctMethod = ctClass.getDeclaredMethod("getTextColor");
		
				System.out.println("   -> setting method body");
				ctMethod.setBody("{ System.out.println(\"rouge\"); return new org.eclipse.swt.graphics.Color(255,0,0); }");	
		
				byte[] classBytes = ctClass.toBytecode();
				classPool.removeClassPath(classPath);   // need to remove the classpath to release connection to JAR file so we can update it.
				
				System.out.println("   -> Patching JAR file");
				JarHandler jarHandler = new JarHandler();
				jarHandler.replaceJarFile(jarFilename, classBytes, "com.archimatetool.editor.views.tree.TreeViewpointFilterProvider.class");
		
				//System.out.println("   -> Writing file " + ctClass.getClassFile().getName());
				//ctClass.writeFile(com.archimatetool.editor.views.tree.TreeViewpointFilterProvider.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			}
		}

		public class JarHandler{
			public void replaceJarFile(String jarPathAndName,byte[] fileByteCode,String fileName) throws IOException {
				File jarFile = new File(jarPathAndName);
				File tempJarFile = new File(jarPathAndName + ".tmp");
				File oldJarFile = new File(jarPathAndName + ".old");
				JarFile jar = new JarFile(jarFile);
				boolean jarWasUpdated = false;

				try {
					JarOutputStream tempJar =
							new JarOutputStream(new FileOutputStream(tempJarFile));

					// Allocate a buffer for reading entry data.

					byte[] buffer = new byte[1024];
					int bytesRead;

					try {
						// Open the given file.

						try {
							// Create a jar entry and add it to the temp jar.
							System.out.println(">>> Adding patched "+fileName);
							JarEntry entry = new JarEntry(fileName);
							tempJar.putNextEntry(entry);
							tempJar.write(fileByteCode);

						} catch (Exception ex) {
							System.out.println(ex);

							// Add a stub entry here, so that the jar will close without an
							// exception.

							tempJar.putNextEntry(new JarEntry("stub"));
						}


						// Loop through the jar entries and add them to the temp jar,
						// skipping the entry that was added to the temp jar already.
						InputStream entryStream = null;
						for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements(); ) {
							// Get the next entry.

							JarEntry entry = (JarEntry) entries.nextElement();

							// If the entry has not been added already, so add it.

							if (! entry.getName().equals(fileName)) {
								// Get an input stream for the entry.
								System.out.println(">>> Copying "+entry.getName());
								entryStream = jar.getInputStream(entry);
								tempJar.putNextEntry(entry);

								while ((bytesRead = entryStream.read(buffer)) != -1) {
									tempJar.write(buffer, 0, bytesRead);
								}
							}else
								System.out.println("Does Equal");
						}
						if(entryStream!=null)
							entryStream.close();
						jarWasUpdated = true;
					}
					catch (Exception ex) {
						System.out.println(ex);

						// IMportant so the jar will close without an
						// exception.

						tempJar.putNextEntry(new JarEntry("stub"));
					}
					finally {
						tempJar.close();
					}
				}
				finally {

					jar.close();

					if (! jarWasUpdated) {
						tempJarFile.delete();
					}
				}


				if (jarWasUpdated) {
					System.out.println("JAR has been updated ... restarting Archi ...");
					Display.getDefault().syncExec(new Runnable() {
						@Override public void run() {
							PlatformUI.getWorkbench().close();
							if ( jarFile.renameTo(oldJarFile) ) {
								System.out.println(jarFile.getName() + " renamed to " + oldJarFile.getName());
								if ( tempJarFile.renameTo(jarFile) )
									System.out.println(tempJarFile.getName() + " renamed to " + jarFile.getName());
								else
									System.err.println("FAILED TO RENAME "+ tempJarFile.getName() + " TO " + jarFile.getName());
							} else
								System.err.println("FAILED TO RENAME "+ jarFile.getName() + " TO " + oldJarFile.getName());
							try {
								Thread.sleep(5000);
							} catch (Exception x) {
								// do nothing
							}
							PlatformUI.getWorkbench().restart();
						}
					});
				}
			}
			*/
}
