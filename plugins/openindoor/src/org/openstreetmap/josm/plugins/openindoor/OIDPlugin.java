// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.plugins.openindoor;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.openstreetmap.josm.actions.ExtensionFileFilter;
import org.openstreetmap.josm.gui.MainApplication;
import org.openstreetmap.josm.gui.MainFrame;
import org.openstreetmap.josm.gui.MainMenu;
import org.openstreetmap.josm.gui.MapFrame;
import org.openstreetmap.josm.gui.MenuScroller;
import org.openstreetmap.josm.gui.preferences.PreferenceSetting;
import org.openstreetmap.josm.gui.util.GuiHelper;
import org.openstreetmap.josm.io.session.SessionReader;
import org.openstreetmap.josm.io.session.SessionWriter;
import org.openstreetmap.josm.plugins.Plugin;
import org.openstreetmap.josm.plugins.PluginInformation;
import org.openstreetmap.josm.tools.Pair;
import org.openstreetmap.josm.tools.ResourceProvider;

/**
 * OpenIndoor plugin.
 */
public final class OIDPlugin extends Plugin {

    private static OIDPlugin instance;

    public OIDPlugin(PluginInformation info) {
        super(info);
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Cannot instantiate plugin twice !");
        }
    }
    
    public static OIDPlugin getInstance() {
        return instance;
    }
}
