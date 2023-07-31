package org.archicontribs.toolbox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import javassist.CannotCompileException;
import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.tools.reflect.Loader;
import javassist.util.HotSwapper;

//import com.archimatetool.editor.views.tree.TreeModelViewer.ModelTreeViewerLabelProvider;

/**
 * The activator class controls the plug-in life cycle
 */
public class ToolboxActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "toolbox-plugin"; //$NON-NLS-1$

	// The shared instance
	private static ToolboxActivator plugin;

	/**
	 * The constructor
	 */
	public ToolboxActivator() {
		System.out.println("Toolbox plugin's Activator ...");
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		System.out.println("Toolbox plugin is starting.");
		
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
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		System.out.println("Toolbox plugin is stoping.");
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ToolboxActivator getDefault() {
		return plugin;
	}

}