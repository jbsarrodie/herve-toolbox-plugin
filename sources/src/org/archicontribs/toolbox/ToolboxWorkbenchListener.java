package org.archicontribs.toolbox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import java.nio.file.Path;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;

import javassist.CannotCompileException;
import javassist.CtClass;
import java.nio.file.InvalidPathException;

public class ToolboxWorkbenchListener implements IWorkbenchListener {
	private static final ToolboxLogger logger = new ToolboxLogger(ToolboxWorkbenchListener.class);

	@Override
	public void postShutdown(IWorkbench workbench) {
		byte[] buffer = new byte[4096];
		int bytesRead;

		logger.debug("Archi is shutting down");

		ToolboxPatchedClasses patchedClasses = ToolboxPlugin.getPatchedClasses();

		for ( String archiJarFilename: patchedClasses.getJarFilenames() ) {
			String toolboxJarFilename = archiJarFilename + ".toolbox";

			try {
				Files.deleteIfExists(Path.of(toolboxJarFilename));
			} catch (IOException|InvalidPathException err) {
				logger.error("Cannot delete old version of " + toolboxJarFilename, err);
				continue;
			}

			logger.debug("Patching JAR file " + archiJarFilename);
			try (JarFile inputJar = new JarFile(archiJarFilename) ) {
				try ( JarOutputStream outputStream = new JarOutputStream(new FileOutputStream(toolboxJarFilename)) ) {
					// Loop through the jar entries and add them to the new patched jar, replacing the patched classes
					Enumeration<JarEntry> jarEntries = inputJar.entries();
					while ( jarEntries.hasMoreElements() ) {
						JarEntry jarEntry = jarEntries.nextElement();

						// if the jar entry is a class that has been patched, we replace its code by the patched version
						CtClass ctClass = patchedClasses.getPatchedClass(archiJarFilename, jarEntry.getName());
						if ( ctClass != null ) {
							logger.debug("Replacing class " + jarEntry.getName());
							outputStream.putNextEntry(jarEntry);
							outputStream.write(ctClass.toBytecode());
						} else {
							//logger.trace("Copying entry " + jarEntry.getName());
							try ( InputStream inputStream = inputJar.getInputStream(jarEntry) ) {
								outputStream.putNextEntry(jarEntry);
								while ((bytesRead = inputStream.read(buffer)) != -1)
									outputStream.write(buffer, 0, bytesRead);
							}
						}
					}
				} catch (CannotCompileException e1) {
					logger.error("Failed to create patched JAR file " + archiJarFilename, e1);
				}
				
				//TODO : find a way to move files despite the fact that they are locked by Windows
				/*
        		try {
					Files.move( new File(archiJarFilename).toPath(), new File(archiJarFilename+".old").toPath());
					System.out.println("JAR file successfully renamed to .old");
					try {
						System.out.println("Renaming toolbox JAR file ...");
						Files.move( new File(archiJarFilename+".toolbox").toPath(), new File(archiJarFilename).toPath());
						System.out.println("TMP file successfully renamed to .jar");
					} catch (Exception err) {
						System.out.println("Failed to rename TMP file to .jar : "+err.getMessage());
						err.printStackTrace();
					}
				} catch (Exception err) {
					System.out.println("Failed to rename JAR file to .old : "+err.getMessage());
					err.printStackTrace();
				}
				 */
			} catch (IOException e2) {
				logger.error("Failed to read JAR file " + archiJarFilename, e2);
			}
			
			try {
				logger.debug("sleeping 30 seconds");
				Thread.sleep(30000);
			} catch (Exception e) {
				logger.error("Cannot sleep", e);
			}
		}
	}

	@Override
	public boolean preShutdown(IWorkbench workbench, boolean forced) {
		return true;
	}
}
