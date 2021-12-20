// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.plugins.openindoor;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.net.UnknownHostException;
import java.net.InetAddress;

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

import org.openstreetmap.josm.plugins.openindoor.OpenIndoorAction;
import org.openstreetmap.josm.plugins.openindoor.IndoorEqualAction;

import org.openstreetmap.josm.tools.Pair;
import org.openstreetmap.josm.tools.ResourceProvider;

import org.openstreetmap.josm.plugins.openindoor.OpenIndoorServer;
import org.openstreetmap.josm.tools.Logging;

// import fi.iki.elonen.NanoHTTPD;

/**
 * OpenIndoor plugin.
 */
public final class OpenIndoorPlugin extends Plugin {

    private static OpenIndoorPlugin instance;
    private static int PORT = 8432;

    public OpenIndoorPlugin(PluginInformation info) {
        super(info);
        if (instance == null) {
            instance = this;
            try {
                Logging.info("\n====================OpenIndoor Server Details====================");
                Logging.info("OpenIndoor Server Machine: " + InetAddress.getLocalHost().getCanonicalHostName());
                Logging.info("Port number: " + PORT);
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            }
            try {	
                OpenIndoorServer server = new OpenIndoorServer(PORT);
                server.start();
            } catch (IOException e) {
                Logging.error("Error occured:" + e.getMessage());
            }
            refreshMenu();
        } else {
            throw new IllegalStateException("Cannot instantiate plugin twice !");
        }
    }
    
    public static OpenIndoorPlugin getInstance() {
        return instance;
    }

    public static void refreshMenu() {
        JMenu menu = MainApplication.getMenu().moreToolsMenu;
        if (menu.isVisible())
            menu.addSeparator();
        else {
            menu.setVisible(true);
        }
        menu.add(new JMenuItem(new OpenIndoorAction()));
        menu.add(new JMenuItem(new IndoorEqualAction()));
        }
}
