/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */

package org.archicontribs.toolbox;

import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

/**
 * This class is used when the user right-click on a graphical object to add entries to the contextual menu
 *
 * @author Herve Jouin
 */
public class ToolboxMenu extends ExtensionContributionFactory {
    
    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
        // nothing to do
    }
}
