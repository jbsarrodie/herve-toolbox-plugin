package org.archicontribs.toolbox;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.Version;

import com.archimatetool.model.IArchimateConcept;
import com.archimatetool.model.IArchimateModelObject;
import com.archimatetool.model.IDiagramModelArchimateConnection;
import com.archimatetool.model.IDiagramModelArchimateObject;
import com.archimatetool.model.IProperty;

import lombok.Getter;

public class ToolboxPlugin extends AbstractUIPlugin {
	/** ID of the plugin */
	public static final String PLUGIN_ID = "org.archicontribs.toolbox";

	/** version of the plugin */
	public static final Version PLUGIN_VERSION = Platform.getBundle(PLUGIN_ID).getVersion();
	
	/** Name of the plugin */
	public static final String PLUGIN_NAME = "ToolboxPlugin";
	
	/** Title od the plugin's windows */
	public static final String PLUGIN_TITLE = "Toolbox plugin v" + PLUGIN_VERSION.toString();
	
	/** PreferenceStore allowing to store the plugin configuration */
	private static IPersistentPreferenceStore preferenceStore = null;
	
	/** keep the plugin instance */
	public static ToolboxPlugin INSTANCE;
	
	/** List of classes that needs to be patched when Archi shutdowns.<br><br>Use {@link ToolboxPlugin#toolboxPatchMethod} method to fill in this variable. */
	@Getter private static final ToolboxPatchedClasses patchedClasses = new ToolboxPatchedClasses();
	
	/** Logger */
	private ToolboxLogger logger;
	
	/**
	 * The ToolboxPlugin class is instanciated when the Toolbox plugin is loaded<b>
	 * It:<b>
	 *    1- configures the preference store
	 *    2- defines default values for standard options (in case they're not defined in the preference store) 
	 */
	//TODO: check if a new version is available on GitHub
	public ToolboxPlugin() {
		INSTANCE = this;
		
		// forcing UTF-8
		System.setProperty("client.encoding.override", "UTF-8");
		System.setProperty("file.encoding", "UTF-8");
		
		// setting the preference defaults
		preferenceStore = getPreferenceStore();
		preferenceStore.setDefault("loggerMode",		      "simple");
		preferenceStore.setDefault("loggerLevel",		      "trace");
		preferenceStore.setDefault("loggerFilename",	      System.getProperty("user.home") + File.separator + PLUGIN_NAME + ".log");
		preferenceStore.setDefault("loggerExpert",
		        "log4j.rootLogger                               = INFO, stdout, file\n"+
				"\n"+
				"log4j.appender.stdout                          = org.apache.log4j.ConsoleAppender\n"+
				"log4j.appender.stdout.Target                   = System.out\n"+
				"log4j.appender.stdout.layout                   = org.apache.log4j.PatternLayout\n"+
				"log4j.appender.stdout.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss} %-5p %4L:%-40.40C{1} %m%n\n"+
				"\n"+
				"log4j.appender.file                            = org.apache.log4j.FileAppender\n"+
				"log4j.appender.file.ImmediateFlush             = true\n"+
				"log4j.appender.file.Append                     = false\n"+
				"log4j.appender.file.Encoding                   = UTF-8\n"+
				"log4j.appender.file.File                       = "+(System.getProperty("user.home") + File.separator+PLUGIN_NAME + ".log").replace("\\", "\\\\")+"\n"+
				"log4j.appender.file.layout                     = org.apache.log4j.PatternLayout\n"+
				"log4j.appender.file.layout.ConversionPattern   = %d{yyyy-MM-dd HH:mm:ss} %-5p %4L:%-40.40C{1} %m%n");

		logger = new ToolboxLogger(ToolboxPlugin.class);
		System.out.println("Initialising "+PLUGIN_TITLE+" ...");
		logger.info("Initialising "+PLUGIN_TITLE+" ...");
	}
	
	@Override
	public IPersistentPreferenceStore getPreferenceStore() {
		if (preferenceStore == null) {
			preferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID);
		}
		return preferenceStore;
	}
	
	////////////////////////////////////////
	// Below, some static helper classes   /
	////////////////////////////////////////
	
    /**
     * Calculates the full name of an Archi component
     * @param obj The Archi component
     * @return A string composed of the class and component names of the component, seperated by a colon 
     */
    public static String getFullName(IArchimateModelObject obj) {
        if ( obj == null )
            return "null";

        return obj.getClass().getSimpleName() + ":\"" + obj.getName() + "\"";
    }

    /**
     * Calculates the debug name of an Archi component
     * @param obj the Archi component
     * @return A sting composed of the full name (@see getFullName) followed by the internal component ID surrounded by parenthesis
     */
    public static String getDebugName(IArchimateModelObject obj) {
    	if ( obj == null )
            return "null";
    	
        return getFullName(obj) + "(" + obj.getId() + ")";
    }
	
	/**
	 * Checks if an Archimate concept is protected.<br>
	 * <br>
	 * So far, an archimate concept is protected if it has got a <b>protected</b> property set as <b>yes</b>
	 * 
	 * @param object The Archimate object to check (in case of a view object, checks the underlying Archimate concept)
	 * @return True if the object is protected, else false
	 */
	static public boolean isProtected(Object object) {
		return isProtected(object, null, null);
	}
	
	/**
	 * Checks if an Archimate concept is protected and opens a popup windows in case it is.<br>
	 * <br>
	 * So far, an archimate concept is protected if it has got a <b>protected</b> property set as <b>yes</b>
	 * 
	 * @param object The Archimate object to check (in case of a view object, checks the underlying Archimate concept)
	 * @param title Title of the popup window (null for no popup window)
	 * @param message Message to print in the popup window
	 * @return True if the object is protected, else false
	 */
	static public boolean isProtected(Object object, String title, String message) {
		// we get the underlying Archimate concept under the object parameter
		if ( object instanceof EditPart editPart)
			object = editPart.getModel();
		
		if ( object instanceof IDiagramModelArchimateObject obj )
			object = obj.getArchimateElement();
		else if ( object instanceof IDiagramModelArchimateConnection con)
			object = con.getArchimateRelationship();
		
		if ( object instanceof IArchimateConcept archimateConcept ) {
			// the archimate concept is protected if it has got a "protected" property set as "yes"
			//TODO: allow to change this using properties
			EList<IProperty> properties = archimateConcept.getProperties();
			if ( properties != null ) {
				for ( IProperty prop: properties ) {
					if ( prop.getKey().equals("protected") && prop.getValue().equals("yes") ) {
						ToolboxPlugin.INSTANCE.logger.debug(getFullName(archimateConcept) + " is protected");
						if ( title != null )
							MessageDialog.openError(Display.getDefault().getActiveShell(), title, message);
						return true;
					}
				}
			}
		}
		
		return false;
	}
	

	

	
	static public <T> Object getFieldWithReflection(Class<T> clazz, Object instance, String fieldName) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			Object value = field.get(instance);
			System.out.println("getFieldWithReflection: "+clazz.getName()+"."+fieldName+" = "+Objects.toString(value));
			field.setAccessible(false);
			return value;
		} catch (Exception err) {
			System.err.println("getFieldWithReflection: Failed to get field "+fieldName+" from class "+clazz.getName());
			System.err.println(Thread.currentThread().getStackTrace());
			return err;
		}
	}
	
	static public <T> Object setFieldWithReflection(Class<T> clazz, Object instance, String fieldName, Object value) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(instance,  value);
			System.out.println("setFieldWithReflection: "+clazz.getName()+"."+fieldName+" = "+Objects.toString(value));
			field.setAccessible(false);
			return value;
		} catch (Exception err) {
			System.err.println("setFieldWithReflection: Failed to set field "+fieldName+" in class "+clazz.getName());
			System.err.println(Thread.currentThread().getStackTrace());
			return err;
		}
	}
	
	static public <T> Object executeMethodWithReflection(Class<T> clazz, Object instance, String methodName, Object... args) {
		try {
			Method method = clazz.getDeclaredMethod(methodName);
			method.setAccessible(true);
			Object result = method.invoke(instance, args);
			System.out.println("setFieldWithReflection: "+clazz.getName()+"."+methodName+" returned "+Objects.toString(result));
			method.setAccessible(false);
			return result;
		} catch (Exception err) {
			System.err.println("setFieldWithReflection: Failed to invoke method "+methodName+" in class "+clazz.getName());
			System.err.println(Thread.currentThread().getStackTrace());
			return err;
		}
	}
}
