package org.archicontribs.toolbox;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.ui.PlatformUI;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class ToolboxPatchedClasses {
	private static final ToolboxLogger logger = new ToolboxLogger(ToolboxPatchedClasses.class);
	
	/**
	 * Contains all the patched classes
	 *     Key = jar filename
	 *     value = set of patched classes in the jar file 
	 */
	private static HashMap<String, HashSet<CtClass>> patchedClasses = new HashMap<>();
	
	public ToolboxPatchedClasses() {
		// nothing to do
	}
	
	/** 
	 * Register a method patch.<br>
	 * <br>
	 * The corresponding patched JAR file will be generated when Archi is shutdown. 
	 * 
	 * @param className The name of the class
	 * @param methodName The name of the method in tht specified class
	 * @param methodBody The new method body (must be enclosed by '{' and '}'
	 * @throws ToolboxException in case of error. The getCause() method of the exception allows to have more details about the error.
	 */
	public void registerPatchedMethod(String className, String methodName, String methodBody) throws ToolboxException {
		CtClass ctClass = null;
		String jarFilename;
		try {
			jarFilename = Class.forName(className).getProtectionDomain().getCodeSource().getLocation().getPath();
			// on windows, we remove the disk drive and the colon
			int i = jarFilename.indexOf(':');
			if ( i > 0 )
				jarFilename = jarFilename.substring(i+1);
		} catch (ClassNotFoundException err) {
			logger.error("Cannot update class " + className, err);
			throw new ToolboxException(err);
		}
		
		// we search if the class has already been patched, ie if the ctclass corresponding to the className already exists in the patchedClasses set.
		HashSet<CtClass> ctClasses = patchedClasses.get(jarFilename);
		if ( ctClasses != null ) {
			for ( CtClass clazz: ctClasses) {
				if ( className.equals(clazz.getName()) ) {
					ctClass = clazz;
					break;
				}
			}
		} else
			ctClasses = new HashSet<>();
		
		// if the ctClass has not been found, then we generate it from the className 
		if ( ctClass == null ) {
			try {
				logger.trace("Getting class " + className);
				ctClass = ClassPool.getDefault().get(className);
			} catch (NotFoundException|SecurityException err) {
				logger.error("Cannot update class " + className, err);
				throw new ToolboxException(err);
			}
		}
		
		
		CtMethod ctMethod;
		try {
			logger.trace("Getting method " + className + "$" + methodName);
			ctMethod = ctClass.getDeclaredMethod(methodName);
			
			logger.trace("Setting new method body");
			ctMethod.setBody(methodBody);
		} catch (NotFoundException | CannotCompileException err) {
			logger.error("Cannot patch method " + className + "$" + methodName, err);
			throw new ToolboxException(err);
		}
		
		// we reference the class and JAR file to patch
		ctClasses.add(ctClass);
		patchedClasses.put(jarFilename, ctClasses);
		
		// We then register a workbench listener to create patched JAR files when Archi is shutdown
		PlatformUI.getWorkbench().addWorkbenchListener(new ToolboxWorkbenchListener());
	}
	
	/**
	 * Get all the JAR filenames corresponding to the classes that have been patched
	 * @return A Set containing all the jar filenames
	 */
	public Set<String> getJarFilenames() {
		return patchedClasses.keySet();
	}
	
	/**
	 * Get all the patched classes in the specified jar file
	 * @param jarFilename The jar filename
	 * @return A Set with all the patched classes
	 */
	public Set<CtClass> getPatchedClasses(String jarFilename) {
		return patchedClasses.get(jarFilename);
	}
	
	public CtClass getPatchedClass(String jarFilename, String className) {
		className = convertClassPathToClassName(className);
		//logger.trace("Searching for class "+className+" in "+jarFilename);
		
		HashSet<CtClass> ctClasses = patchedClasses.get(jarFilename);
		if ( ctClasses == null ) {
			logger.error("***** jar file "+jarFilename+" not found *****");
			return null;
		}
		
		// we loop on the ctClasses to check if it contains the class
		for ( CtClass ctClass: ctClasses ) {
			//logger.trace("     ? " + ctClass.getName()+".class");
			if ( className.equals(ctClass.getName()+".class") )
				return ctClass;
		}
		
		// if the class has not been found
		return null;
	}
	
	/**
	 * Concerts a class name in class path (ie replace all the dots by slashes, except the last one in order to keep the .class file extension.
	 * @param className The class name (containing dots)
	 * @return The class path (containing slashes)
	 */
	public String convertClassNameToClassPath(String className) {
		return className.replace("/[.](?=.*[.])/g", "/");
	}
	
	/**
	 * Concerts a class path in class name (ie replace all the shashes by dots)
	 * @param classPath The class path (containing slashes)
	 * @return The class name (containing dots)
	 */
	public String convertClassPathToClassName(String classPath) {
		return classPath.replace("/", ".");
	}
}
