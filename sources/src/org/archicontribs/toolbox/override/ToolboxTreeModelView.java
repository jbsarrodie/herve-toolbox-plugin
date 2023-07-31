package org.archicontribs.toolbox.override;

import java.lang.reflect.Field;

import org.archicontribs.toolbox.actions.ToolboxDeleteAction;
import org.archicontribs.toolbox.actions.ToolboxRenameAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;

public class ToolboxTreeModelView extends com.archimatetool.editor.views.tree.TreeModelView {
    
	public void doCreatePartControl(Composite parent) {
		super.doCreatePartControl(parent);
		System.out.println("ToolboxTreeModelView: Changing action handler ...");
		
		IAction deleteAction =  new ToolboxDeleteAction(getViewer());
		IAction renameAction = new ToolboxRenameAction(getViewer());
		
		// we use reflection to update the private variable super.fActionDelete
		try {
			Field field = this.getClass().getSuperclass().getDeclaredField("fActionDelete");
			field.setAccessible(true);
			field.set(this, deleteAction);
			field.setAccessible(false);			
			System.out.println("                 ...: fActionDelete updated using reflection...");
			
			field = this.getClass().getSuperclass().getDeclaredField("fActionRename");
			field.setAccessible(true);
			field.set(this, renameAction);
			field.setAccessible(false);			
			System.out.println("                 ...: fActionRename updated using reflection...");

		} catch (Exception err) {
			System.err.println("ToolboxTreeModelView: failed to update actions ...");
			System.err.println(err);
		}
		
		// we replace the actions by ours
		IActionBars actionBars = getViewSite().getActionBars();
		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteAction);
		actionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), renameAction);
		actionBars.updateActionBars();
	}
}