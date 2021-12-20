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

import org.openstreetmap.josm.plugins.openindoor.OIDAction;
import org.openstreetmap.josm.plugins.openindoor.IndoorEqualAction;

import org.openstreetmap.josm.tools.Pair;
import org.openstreetmap.josm.tools.ResourceProvider;

import org.openstreetmap.josm.plugins.openindoor.Server;

// import fi.iki.elonen.NanoHTTPD;

/**
 * OpenIndoor plugin.
 */
public final class OIDPlugin extends Plugin {

    private static OIDPlugin instance;
    private static int PORT = 8432;

    public OIDPlugin(PluginInformation info) {
        super(info);
        if (instance == null) {
            instance = this;
            try {
                System.out.println("\n====================Server Details====================");
                System.out.println("Server Machine: " + InetAddress.getLocalHost().getCanonicalHostName());
                System.out.println("Port number: " + PORT);
                System.out.println();
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            //Here one instance of server is started..	
            try {	
                Server server = new Server(PORT);
                server.start();
                // server.start();
            } catch (IOException e) {
                System.err.println("Error occured:" + e.getMessage());
                System.exit(0);
            }
            refreshMenu();
        } else {
            throw new IllegalStateException("Cannot instantiate plugin twice !");
        }
    }
    
    public static OIDPlugin getInstance() {
        return instance;
    }

    public static void refreshMenu() {
        JMenu menu = MainApplication.getMenu().moreToolsMenu;
        if (menu.isVisible())
            menu.addSeparator();
        else {
            menu.setVisible(true);
        }
        menu.add(new JMenuItem(new OIDAction()));
        menu.add(new JMenuItem(new IndoorEqualAction()));
        }
}
