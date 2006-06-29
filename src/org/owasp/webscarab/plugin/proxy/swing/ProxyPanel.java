/***********************************************************************
 *
 * $CVSHeader$
 *
 * This file is part of WebScarab, an Open Web Application Security
 * Project utility. For details, please see http://www.owasp.org/
 *
 * Copyright (c) 2002 - 2004 Rogan Dawes
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Getting Source
 * ==============
 *
 * Source for this application is maintained at Sourceforge.net, a
 * repository for free software projects.
 *
 * For details, please see http://www.sourceforge.net/projects/owasp
 *
 */

/*
 * ProxyPanel.java
 *
 * Created on July 25, 2003, 11:07 PM
 */

package org.owasp.webscarab.plugin.proxy.swing;

import org.owasp.webscarab.model.ConversationID;
import org.owasp.webscarab.model.HttpUrl;

import org.owasp.webscarab.ui.swing.SwingPluginUI;

import org.owasp.webscarab.plugin.proxy.Proxy;
import org.owasp.webscarab.plugin.proxy.ProxyUI;
import org.owasp.webscarab.util.swing.ColumnDataModel;
import org.owasp.webscarab.util.W32WinInet;

import java.util.ArrayList;
import java.util.Date;

import java.util.logging.Logger;

import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author  rdawes
 */
public class ProxyPanel extends javax.swing.JPanel implements SwingPluginUI, ProxyUI {
    
    private Proxy _proxy;
    private ListenerTableModel _ltm;
    private MessageTableModel _mtm;
    
    private ArrayList _plugins;
    private ProxyPluginUI[] _pluginArray = new ProxyPluginUI[0];
    
    private Logger _logger = Logger.getLogger(getClass().getName());
    
    /** Creates new form ProxyPanel */
    public ProxyPanel(Proxy proxy) {
        initComponents();
        
        _proxy = proxy;
        
        _ltm = new ListenerTableModel(_proxy);
        listenerTable.setModel(_ltm);
        listenerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        _mtm = new MessageTableModel();
        messageTable.setModel(_mtm);
        
        int[][] columnWidths = {
            {200, 200, 200}, 
            {50, 50, 50},
            {50, 50, 50},
            {250, 250, 250},
        };
        
        javax.swing.table.TableColumnModel columnModel = messageTable.getColumnModel();
        for (int i=0; i<columnWidths.length; i++) {
            columnModel.getColumn(i).setMinWidth(columnWidths[i][0]);
            columnModel.getColumn(i).setMaxWidth(columnWidths[i][1]);
            columnModel.getColumn(i).setPreferredWidth(columnWidths[i][2]);
        }
        
        networkComboBox.setModel(new DefaultComboBoxModel(_proxy.getSimulators()));
        networkComboBox.setSelectedItem("Unlimited");
        
        String[] keys = _proxy.getProxies();
        for (int i=0; i<keys.length; i++) _ltm.proxyAdded(keys[i]);
        
        proxy.setUI(this);
        
        if (!W32WinInet.isAvailable()) {
            primaryLabel.setEnabled(false);
            primaryCheckBox.setEnabled(false);
        }
    }
    
    public javax.swing.JPanel getPanel() {
        return this;
    }
    
    public String getPluginName() {
        return new String("Proxy");
    }
    
    public void addPlugin(ProxyPluginUI plugin) {
        if (_plugins == null) {
            _plugins = new ArrayList();
        }
        _plugins.add(plugin);
        _pluginArray = (ProxyPluginUI[]) _plugins.toArray(_pluginArray);
        mainTabbedPane.add(plugin.getPanel(), plugin.getPluginName());
        if (plugin instanceof ManualEditPanel)
            mainTabbedPane.setSelectedIndex(mainTabbedPane.getTabCount()-1);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        mainTabbedPane = new javax.swing.JTabbedPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        stopButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listenerTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        addressTextField = new javax.swing.JTextField();
        portTextField = new javax.swing.JTextField();
        baseTextField = new javax.swing.JTextField();
        pluginsCheckBox = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        networkComboBox = new javax.swing.JComboBox();
        primaryLabel = new javax.swing.JLabel();
        primaryCheckBox = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageTable = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.9);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(stopButton, gridBagConstraints);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(startButton, gridBagConstraints);

        listenerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(listenerTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel1.setText("Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel2.setText("Port");
        jLabel2.setMinimumSize(new java.awt.Dimension(23, 15));
        jLabel2.setPreferredSize(new java.awt.Dimension(23, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel3.setText("Base URL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel4.setText("Use Plugins?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel4, gridBagConstraints);

        addressTextField.setToolTipText("Blank or \"*\" means all interfaces");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(addressTextField, gridBagConstraints);

        portTextField.setToolTipText("integer between 0 and 65536");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        jPanel1.add(portTextField, gridBagConstraints);

        baseTextField.setToolTipText("Blank for a conventional proxy, or http://host:port for a reverse proxy.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(baseTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        jPanel1.add(pluginsCheckBox, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel5.setText("Speed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel5, gridBagConstraints);

        networkComboBox.setFont(new java.awt.Font("Dialog", 0, 12));
        networkComboBox.setMinimumSize(new java.awt.Dimension(32, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(networkComboBox, gridBagConstraints);

        primaryLabel.setFont(new java.awt.Font("Dialog", 0, 12));
        primaryLabel.setText("Primary?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(primaryLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        jPanel1.add(primaryCheckBox, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel3.setLayout(new java.awt.BorderLayout());

        messageTable.setBackground(new java.awt.Color(204, 204, 204));
        messageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "ID", "Method", "URL", "Status"
            }
        ));
        messageTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        messageTable.setShowHorizontalLines(false);
        messageTable.setShowVerticalLines(false);
        jScrollPane2.setViewportView(messageTable);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel3);

        mainTabbedPane.addTab("Listeners", jSplitPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(mainTabbedPane, gridBagConstraints);

    }//GEN-END:initComponents
    
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        try {
            String address = addressTextField.getText();
            int port = Integer.parseInt(portTextField.getText().trim());
            HttpUrl base = null;
            if (!baseTextField.getText().equals("")) {
                base = new HttpUrl(baseTextField.getText());
            }
            String simulator = (String) networkComboBox.getSelectedItem();
            boolean plugins = pluginsCheckBox.isSelected();
            boolean primary = primaryCheckBox.isSelected();
            _proxy.addListener(address, port, base, simulator, plugins, primary);
            addressTextField.setText("");
            portTextField.setText("");
            baseTextField.setText("");
        } catch (Exception e) {
            _logger.severe("Error starting the listener : " + e);
            JOptionPane.showMessageDialog(null, new String[] {"Error starting proxy listener: ", e.toString()}, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_startButtonActionPerformed
    
    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        String[] keys = _proxy.getProxies();
        int row = listenerTable.getSelectedRow();
        if (row<0) return;
        String key = _ltm.getKey(row);
        String address = _proxy.getAddress(key);
        String port = Integer.toString(_proxy.getPort(key));
        HttpUrl base = _proxy.getBase(key);
        String simulator = _proxy.getSimulator(key);
        boolean usePlugins = _proxy.usesPlugins(key);
        boolean primary = _proxy.isPrimaryProxy(key);
        if (!_proxy.removeListener(key)) {
            _logger.severe("Failed to stop " + key);
        } else {
            addressTextField.setText(address);
            portTextField.setText(port);
            baseTextField.setText(base == null ? "" : base.toString());
            if (simulator != null) {
                networkComboBox.setSelectedItem(simulator);
            } else {
                networkComboBox.setSelectedItem("Unlimited");
            }
            pluginsCheckBox.setSelected(usePlugins);
            primaryCheckBox.setSelected(primary);
        }
    }//GEN-LAST:event_stopButtonActionPerformed
    
    public javax.swing.Action[] getConversationActions() {
        return null;
    }
    
    public javax.swing.Action[] getUrlActions() {
        return null;
    }
    
    public void proxyAdded(final String key) {
        if (SwingUtilities.isEventDispatchThread()) {
            _ltm.proxyAdded(key);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    proxyAdded(key);
                }
            });
        }
    }
    
    public void proxyRemoved(final String key) {
        if (SwingUtilities.isEventDispatchThread()) {
            _ltm.proxyRemoved(key);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    proxyRemoved(key);
                }
            });
        }
    }
    
    public void proxyStarted(String key) {
    }
    
    public void proxyStopped(String key) {
    }
    
    public void requested(final ConversationID id, final String method, final HttpUrl url) {
        if (SwingUtilities.isEventDispatchThread()) {
            _mtm.addRow(id, method, url);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    requested(id, method, url);
                }
            });
        }
    }
    
    public void received(final ConversationID id, final String status) {
        if (SwingUtilities.isEventDispatchThread()) {
            _mtm.updateRow(id, status);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    received(id, status);
                }
            });
        }
    }
    
    public void aborted(final ConversationID id, final String reason) {
        if (SwingUtilities.isEventDispatchThread()) {
            _mtm.updateRow(id, reason);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    aborted(id, reason);
                }
            });
        }
    }
    
    public void setEnabled(final boolean enabled) {
        if (SwingUtilities.isEventDispatchThread()) {
            startButton.setEnabled(enabled);
            stopButton.setEnabled(enabled);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setEnabled(enabled);
                }
            });
        }
    }
    
    public ColumnDataModel[] getConversationColumns() {
        return null;
    }
    
    public ColumnDataModel[] getUrlColumns() {
        return null;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTextField;
    private javax.swing.JTextField baseTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable listenerTable;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JTable messageTable;
    private javax.swing.JComboBox networkComboBox;
    private javax.swing.JCheckBox pluginsCheckBox;
    private javax.swing.JTextField portTextField;
    private javax.swing.JCheckBox primaryCheckBox;
    private javax.swing.JLabel primaryLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
    
    private class MessageTableModel extends AbstractTableModel {
        
        private ArrayList _rows = new ArrayList();
        private Timer _timer = new Timer(true);
        
        private String[] _columns = new String [] {
            "Time", "ID", "Method", "URL", "Status"
        };
        
        public String getColumnName(int columnIndex) {
            return _columns[columnIndex];
        }
        
        public int getColumnCount() {
            return _columns.length;
        }
        
        public int getRowCount() {
            return _rows.size();
        }
        
        public Object getValueAt(int rowIndex, int columnIndex) {
            Object[] row = (Object[]) _rows.get(rowIndex);
            return row[columnIndex];
        }
        
        public void addRow(ConversationID id, String method, HttpUrl url) {
            Object[] row = new Object[] {new Date(), id, method, url, null};
            _rows.add(row);
            fireTableRowsInserted(_rows.size()-1, _rows.size()-1);
        }
        
        public void updateRow(final ConversationID id, String status) {
            for (int i=0; i<_rows.size(); i++) {
                Object[] row = (Object[]) _rows.get(i);
                if (row[1].equals(id)) {
                    row[4] = status;
                    fireTableCellUpdated(i, 4);
                    _timer.schedule(new TimerTask() {
                        public void run() {
                            removeRow(id);
                        }
                    }, 5000);
                    return;
                }
            }
        }
        
        public void removeRow(final ConversationID id) {
            if (SwingUtilities.isEventDispatchThread()) {
                for (int i=0; i<_rows.size(); i++) {
                    Object[] row = (Object[]) _rows.get(i);
                    if (row[1].equals(id)) {
                        _rows.remove(i);
                        fireTableRowsDeleted(i, i);
                        return;
                    }
                }
            } else {
                SwingUtilities.invokeLater(new Runnable() { 
                    public void run() {
                        removeRow(id);
                    }
                });
            }
        }
        
    }
    
}
