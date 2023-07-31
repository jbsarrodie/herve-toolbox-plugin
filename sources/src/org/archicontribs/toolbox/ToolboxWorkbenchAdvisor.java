package org.archicontribs.toolbox;

import org.eclipse.ui.application.WorkbenchAdvisor;

public class ToolboxWorkbenchAdvisor extends WorkbenchAdvisor {

	@Override
	public String getInitialWindowPerspectiveId() {
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		return null;
	}

}
