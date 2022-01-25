/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 */
public class formMain extends FrameView {

    /**
     * Initialize application main form.
     * @param app The application.
     */
    public formMain(SingleFrameApplication app) {
        super(app);

        /* build GUI components. */
        initComponents();

        /* add the planning char to the picture. */
        planningView = new panePlanning();
        planningContainer.add(planningView);

        /* status bar initialization:
         *       - message timeout,
         *       - idle icon and busy animation, etc
         */
        ResourceMap resourceMap = getResourceMap();

        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);

        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });

        /* after initializing, we show the login screen. */
        /**
        formLogin login = new formLogin(mainFrame, true);
        login.setLocationRelativeTo(mainFrame);
        Main.getApplication().show(login);
         **/
    }

    /** Action to show the about box. */
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            aboutBox = new formAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(aboutBox);
    }

    /** Action to show the hotel configuration frame. */
    @Action
    public void hotelConfigAction() {
        if (hotelConfigFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            hotelConfigFrame = new formHotelConfig();
            hotelConfigFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(hotelConfigFrame);
    }

    /** Action to show the system configuration frame. */
    @Action
    public void systemConfigAction() {
        if (systemConfigFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            systemConfigFrame = new formSystemConfig();
            systemConfigFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(systemConfigFrame);
    }

    /** Action to show the new cardex form. */
    @Action
    public void newCardexAction() {
        if (newCardexFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            newCardexFrame = new formCardex();
            newCardexFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(newCardexFrame);
    }

    /** Action to show the new cardex form. */
    @Action
    public void searchCardexAction() {
        if (searchCardexFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            searchCardexFrame = new formManageCardex();
            searchCardexFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(searchCardexFrame);
    }

    /** Action to show the new entity form. */
    @Action
    public void newEntityAction() {
        if (newEntityFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            newEntityFrame = new formEntity();
            newEntityFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(newEntityFrame);
    }

    /** Action to show the new cardex form. */
    @Action
    public void searchEntityAction() {
        if (searchEntityFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            searchEntityFrame = new formManageEntity();
            searchEntityFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(searchEntityFrame);
    }

    /** Action to show the new booking form. */
    @Action
    public void newBookingAction() {
        if (newBookingFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            newBookingFrame = new formBooking();
            newBookingFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(newBookingFrame);
    }

    /** Action to show the new booking form. */
    @Action
    public void searchBookingAction() {
        if (searchBookingFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            searchBookingFrame = new formManageBooking();
            searchBookingFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(searchBookingFrame);
    }

    /** Action to show the import hotel form. */
    @Action
    public void importHotelAction() {
        if (importHotelFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            importHotelFrame = new formImportHotel();
            importHotelFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(importHotelFrame);
    }

    /** Action to show the export hotel form. */
    @Action
    public void exportHotelAction() {
        if (exportHotelFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            exportHotelFrame = new formExportHotel();
            exportHotelFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(exportHotelFrame);
    }

    /** Action to show the check in form. */
    @Action
    public void checkInAction() {
        if (checkInFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            checkInFrame = new formCheckin();
            checkInFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(checkInFrame);
    }

    /** Action to show the check out form. */
    @Action
    public void checkOutAction() {
        if (checkOutFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            checkOutFrame = new formCheckout();
            checkOutFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(checkOutFrame);
    }

    /** Action to show the charge extra. */
    @Action
    public void chargeExtraAction() {
        if (chargeExtraFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            chargeExtraFrame = new formExtra();
            chargeExtraFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(chargeExtraFrame);
    }

    /** Action to show the payments form. */
    @Action
    public void paymentsAction() {
        if (paymentsFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            paymentsFrame = new formPayments();
            paymentsFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(paymentsFrame);
    }

    /** Action to show the hotel stats. */
    @Action
    public void hotelStatsAction() {
        if (hotelStatsFrame == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            hotelStatsFrame = new formStats();
            hotelStatsFrame.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(hotelStatsFrame);
    }

    /**
     *
     */
    private void initSemantics() {

        /*** Add planning view. */

    }

    /**
     * initComponents
     * Initialization/building of Swing components and layout.
     * WARNING: DD NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        goLeftButton = new javax.swing.JButton();
        goRightButton = new javax.swing.JButton();
        goUpButton = new javax.swing.JButton();
        goDownButton = new javax.swing.JButton();
        planningContainer = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        importMenuItem = new javax.swing.JMenuItem();
        exportMenuItem = new javax.swing.JMenuItem();
        sep4 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        entityMenu = new javax.swing.JMenu();
        newCustomerMenuItem = new javax.swing.JMenuItem();
        searchCustomerMenuItem = new javax.swing.JMenuItem();
        sep5 = new javax.swing.JPopupMenu.Separator();
        newEntityMenuItem = new javax.swing.JMenuItem();
        searchEntityMenuItem = new javax.swing.JMenuItem();
        receptionMenu = new javax.swing.JMenu();
        newBookingMenuItem = new javax.swing.JMenuItem();
        searchBookingMenuItem = new javax.swing.JMenuItem();
        bookingChartMenuItem = new javax.swing.JMenuItem();
        calendarMenuItem = new javax.swing.JMenuItem();
        sep7 = new javax.swing.JPopupMenu.Separator();
        checkInMenuItem = new javax.swing.JMenuItem();
        checkOutMenuItem = new javax.swing.JMenuItem();
        nightAuditMenuItem = new javax.swing.JMenuItem();
        sep8 = new javax.swing.JPopupMenu.Separator();
        chargeExtraMenuItem = new javax.swing.JMenuItem();
        paymentExtraMenuItem = new javax.swing.JMenuItem();
        toolMenu = new javax.swing.JMenu();
        configMenu = new javax.swing.JMenu();
        hotelConfigMenuItem = new javax.swing.JMenuItem();
        sep6 = new javax.swing.JPopupMenu.Separator();
        systemConfigMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        quickToolbar = new javax.swing.JToolBar();
        newCustomer = new javax.swing.JButton();
        searchCustomer = new javax.swing.JButton();
        newEntity = new javax.swing.JButton();
        searchEntity = new javax.swing.JButton();
        sep1 = new javax.swing.JToolBar.Separator();
        newBooking = new javax.swing.JButton();
        searchBooking = new javax.swing.JButton();
        sep2 = new javax.swing.JToolBar.Separator();
        checkIn = new javax.swing.JButton();
        checkOut = new javax.swing.JButton();
        sep3 = new javax.swing.JToolBar.Separator();
        chargeExtra = new javax.swing.JButton();
        paymentExtra = new javax.swing.JButton();
        sep9 = new javax.swing.JToolBar.Separator();
        stats = new javax.swing.JButton();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(walkin.Main.class).getContext().getResourceMap(formMain.class);
        goLeftButton.setText(resourceMap.getString("goLeftButton.text")); // NOI18N
        goLeftButton.setName("goLeftButton"); // NOI18N

        goRightButton.setText(resourceMap.getString("goRightButton.text")); // NOI18N
        goRightButton.setName("goRightButton"); // NOI18N

        goUpButton.setText(resourceMap.getString("goUpButton.text")); // NOI18N
        goUpButton.setName("goUpButton"); // NOI18N

        goDownButton.setText(resourceMap.getString("goDownButton.text")); // NOI18N
        goDownButton.setActionCommand(resourceMap.getString("goDownButton.actionCommand")); // NOI18N
        goDownButton.setName("goDownButton"); // NOI18N

        planningContainer.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        planningContainer.setName("planningContainer"); // NOI18N
        planningContainer.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(planningContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(goLeftButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(goRightButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
                        .addComponent(goUpButton))
                    .addComponent(goDownButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goLeftButton)
                    .addComponent(goRightButton)
                    .addComponent(goUpButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(planningContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goDownButton)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(walkin.Main.class).getContext().getActionMap(formMain.class, this);
        importMenuItem.setAction(actionMap.get("importHotelAction")); // NOI18N
        importMenuItem.setIcon(resourceMap.getIcon("importMenuItem.icon")); // NOI18N
        importMenuItem.setText(resourceMap.getString("importMenuItem.text")); // NOI18N
        importMenuItem.setName("importMenuItem"); // NOI18N
        fileMenu.add(importMenuItem);

        exportMenuItem.setAction(actionMap.get("exportHotelAction")); // NOI18N
        exportMenuItem.setIcon(resourceMap.getIcon("exportMenuItem.icon")); // NOI18N
        exportMenuItem.setText(resourceMap.getString("exportMenuItem.text")); // NOI18N
        exportMenuItem.setName("exportMenuItem"); // NOI18N
        fileMenu.add(exportMenuItem);

        sep4.setName("sep4"); // NOI18N
        fileMenu.add(sep4);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        entityMenu.setText(resourceMap.getString("entityMenu.text")); // NOI18N
        entityMenu.setName("entityMenu"); // NOI18N

        newCustomerMenuItem.setAction(actionMap.get("newCardexAction")); // NOI18N
        newCustomerMenuItem.setIcon(resourceMap.getIcon("newCustomerMenuItem.icon")); // NOI18N
        newCustomerMenuItem.setText(resourceMap.getString("newCustomerMenuItem.text")); // NOI18N
        newCustomerMenuItem.setName("newCustomerMenuItem"); // NOI18N
        entityMenu.add(newCustomerMenuItem);

        searchCustomerMenuItem.setAction(actionMap.get("searchCardexAction")); // NOI18N
        searchCustomerMenuItem.setIcon(resourceMap.getIcon("searchCustomerMenuItem.icon")); // NOI18N
        searchCustomerMenuItem.setText(resourceMap.getString("searchCustomerMenuItem.text")); // NOI18N
        searchCustomerMenuItem.setName("searchCustomerMenuItem"); // NOI18N
        entityMenu.add(searchCustomerMenuItem);

        sep5.setName("sep5"); // NOI18N
        entityMenu.add(sep5);

        newEntityMenuItem.setAction(actionMap.get("newEntityAction")); // NOI18N
        newEntityMenuItem.setIcon(resourceMap.getIcon("newEntityMenuItem.icon")); // NOI18N
        newEntityMenuItem.setText(resourceMap.getString("newEntityMenuItem.text")); // NOI18N
        newEntityMenuItem.setName("newEntityMenuItem"); // NOI18N
        entityMenu.add(newEntityMenuItem);

        searchEntityMenuItem.setAction(actionMap.get("searchEntityAction")); // NOI18N
        searchEntityMenuItem.setIcon(resourceMap.getIcon("searchEntityMenuItem.icon")); // NOI18N
        searchEntityMenuItem.setText(resourceMap.getString("searchEntityMenuItem.text")); // NOI18N
        searchEntityMenuItem.setName("searchEntityMenuItem"); // NOI18N
        entityMenu.add(searchEntityMenuItem);

        menuBar.add(entityMenu);

        receptionMenu.setText(resourceMap.getString("receptionMenu.text")); // NOI18N
        receptionMenu.setName("receptionMenu"); // NOI18N

        newBookingMenuItem.setAction(actionMap.get("newBookingAction")); // NOI18N
        newBookingMenuItem.setIcon(resourceMap.getIcon("newBookingMenuItem.icon")); // NOI18N
        newBookingMenuItem.setText(resourceMap.getString("newBookingMenuItem.text")); // NOI18N
        newBookingMenuItem.setName("newBookingMenuItem"); // NOI18N
        receptionMenu.add(newBookingMenuItem);

        searchBookingMenuItem.setAction(actionMap.get("searchBookingAction")); // NOI18N
        searchBookingMenuItem.setIcon(resourceMap.getIcon("searchBookingMenuItem.icon")); // NOI18N
        searchBookingMenuItem.setText(resourceMap.getString("searchBookingMenuItem.text")); // NOI18N
        searchBookingMenuItem.setName("searchBookingMenuItem"); // NOI18N
        receptionMenu.add(searchBookingMenuItem);

        bookingChartMenuItem.setText(resourceMap.getString("bookingChartMenuItem.text")); // NOI18N
        bookingChartMenuItem.setName("bookingChartMenuItem"); // NOI18N
        receptionMenu.add(bookingChartMenuItem);

        calendarMenuItem.setText(resourceMap.getString("calendarMenuItem.text")); // NOI18N
        calendarMenuItem.setName("calendarMenuItem"); // NOI18N
        receptionMenu.add(calendarMenuItem);

        sep7.setName("sep7"); // NOI18N
        receptionMenu.add(sep7);

        checkInMenuItem.setAction(actionMap.get("checkInAction")); // NOI18N
        checkInMenuItem.setIcon(resourceMap.getIcon("checkInMenuItem.icon")); // NOI18N
        checkInMenuItem.setText(resourceMap.getString("checkInMenuItem.text")); // NOI18N
        checkInMenuItem.setName("checkInMenuItem"); // NOI18N
        receptionMenu.add(checkInMenuItem);

        checkOutMenuItem.setAction(actionMap.get("checkOutAction")); // NOI18N
        checkOutMenuItem.setIcon(resourceMap.getIcon("checkOutMenuItem.icon")); // NOI18N
        checkOutMenuItem.setText(resourceMap.getString("checkOutMenuItem.text")); // NOI18N
        checkOutMenuItem.setName("checkOutMenuItem"); // NOI18N
        receptionMenu.add(checkOutMenuItem);

        nightAuditMenuItem.setText(resourceMap.getString("nightAuditMenuItem.text")); // NOI18N
        nightAuditMenuItem.setName("nightAuditMenuItem"); // NOI18N
        receptionMenu.add(nightAuditMenuItem);

        sep8.setName("sep8"); // NOI18N
        receptionMenu.add(sep8);

        chargeExtraMenuItem.setAction(actionMap.get("chargeExtraAction")); // NOI18N
        chargeExtraMenuItem.setIcon(resourceMap.getIcon("chargeExtraMenuItem.icon")); // NOI18N
        chargeExtraMenuItem.setText(resourceMap.getString("chargeExtraMenuItem.text")); // NOI18N
        chargeExtraMenuItem.setName("chargeExtraMenuItem"); // NOI18N
        receptionMenu.add(chargeExtraMenuItem);

        paymentExtraMenuItem.setAction(actionMap.get("paymentsAction")); // NOI18N
        paymentExtraMenuItem.setIcon(resourceMap.getIcon("paymentExtraMenuItem.icon")); // NOI18N
        paymentExtraMenuItem.setText(resourceMap.getString("paymentExtraMenuItem.text")); // NOI18N
        paymentExtraMenuItem.setName("paymentExtraMenuItem"); // NOI18N
        receptionMenu.add(paymentExtraMenuItem);

        menuBar.add(receptionMenu);

        toolMenu.setText(resourceMap.getString("toolMenu.text")); // NOI18N
        toolMenu.setName("toolMenu"); // NOI18N
        menuBar.add(toolMenu);

        configMenu.setText(resourceMap.getString("configMenu.text")); // NOI18N
        configMenu.setName("configMenu"); // NOI18N

        hotelConfigMenuItem.setAction(actionMap.get("hotelConfigAction")); // NOI18N
        hotelConfigMenuItem.setIcon(resourceMap.getIcon("hotelConfigMenuItem.icon")); // NOI18N
        hotelConfigMenuItem.setText(resourceMap.getString("hotelConfigMenuItem.text")); // NOI18N
        hotelConfigMenuItem.setName("hotelConfigMenuItem"); // NOI18N
        configMenu.add(hotelConfigMenuItem);

        sep6.setName("sep6"); // NOI18N
        configMenu.add(sep6);

        systemConfigMenuItem.setAction(actionMap.get("systemConfigAction")); // NOI18N
        systemConfigMenuItem.setIcon(resourceMap.getIcon("systemConfigMenuItem.icon")); // NOI18N
        systemConfigMenuItem.setText(resourceMap.getString("systemConfigMenuItem.text")); // NOI18N
        systemConfigMenuItem.setName("systemConfigMenuItem"); // NOI18N
        configMenu.add(systemConfigMenuItem);

        menuBar.add(configMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        helpMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        helpMenuItem.setText(resourceMap.getString("helpMenuItem.text")); // NOI18N
        helpMenuItem.setName("helpMenuItem"); // NOI18N
        helpMenu.add(helpMenuItem);

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 444, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        quickToolbar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quickToolbar.setRollover(true);
        quickToolbar.setName("quickToolbar"); // NOI18N

        newCustomer.setAction(actionMap.get("newCardexAction")); // NOI18N
        newCustomer.setIcon(resourceMap.getIcon("newCustomer.icon")); // NOI18N
        newCustomer.setText(resourceMap.getString("newCustomer.text")); // NOI18N
        newCustomer.setToolTipText(resourceMap.getString("newCustomer.toolTipText")); // NOI18N
        newCustomer.setFocusable(false);
        newCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newCustomer.setName("newCustomer"); // NOI18N
        newCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quickToolbar.add(newCustomer);

        searchCustomer.setAction(actionMap.get("searchCardexAction")); // NOI18N
        searchCustomer.setIcon(resourceMap.getIcon("searchCustomer.icon")); // NOI18N
        searchCustomer.setText(resourceMap.getString("searchCustomer.text")); // NOI18N
        searchCustomer.setToolTipText(resourceMap.getString("searchCustomer.toolTipText")); // NOI18N
        searchCustomer.setFocusable(false);
        searchCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchCustomer.setName("searchCustomer"); // NOI18N
        searchCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quickToolbar.add(searchCustomer);

        newEntity.setAction(actionMap.get("newEntityAction")); // NOI18N
        newEntity.setIcon(resourceMap.getIcon("newEntity.icon")); // NOI18N
        newEntity.setText(resourceMap.getString("newEntity.text")); // NOI18N
        newEntity.setToolTipText(resourceMap.getString("newEntity.toolTipText")); // NOI18N
        newEntity.setFocusable(false);
        newEntity.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newEntity.setName("newEntity"); // NOI18N
        newEntity.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quickToolbar.add(newEntity);

        searchEntity.setAction(actionMap.get("searchEntityAction")); // NOI18N
        searchEntity.setIcon(resourceMap.getIcon("searchEntity.icon")); // NOI18N
        searchEntity.setText(resourceMap.getString("searchEntity.text")); // NOI18N
        searchEntity.setToolTipText(resourceMap.getString("searchEntity.toolTipText")); // NOI18N
        searchEntity.setFocusable(false);
        searchEntity.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchEntity.setName("searchEntity"); // NOI18N
        searchEntity.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quickToolbar.add(searchEntity);

        sep1.setName("sep1"); // NOI18N
        quickToolbar.add(sep1);

        newBooking.setAction(actionMap.get("newBookingAction")); // NOI18N
        newBooking.setIcon(resourceMap.getIcon("newBooking.icon")); // NOI18N
        newBooking.setText(resourceMap.getString("newBooking.text")); // NOI18N
        newBooking.setToolTipText(resourceMap.getString("newBooking.toolTipText")); // NOI18N
        newBooking.setFocusable(false);
        newBooking.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newBooking.setName("newBooking"); // NOI18N
        newBooking.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quickToolbar.add(newBooking);

        searchBooking.setAction(actionMap.get("searchBookingAction")); // NOI18N
        searchBooking.setIcon(resourceMap.getIcon("searchBooking.icon")); // NOI18N
        searchBooking.setText(resourceMap.getString("searchBooking.text")); // NOI18N
        searchBooking.setToolTipText(resourceMap.getString("searchBooking.toolTipText")); // NOI18N
        searchBooking.setFocusable(false);
        searchBooking.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchBooking.setName("searchBooking"); // NOI18N
        searchBooking.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quickToolbar.add(searchBooking);

        sep2.setName("sep2"); // NOI18N
        quickToolbar.add(sep2);

        checkIn.setAction(actionMap.get("checkInAction")); // NOI18N
        checkIn.setIcon(resourceMap.getIcon("checkIn.icon")); // NOI18N
        checkIn.setText(resourceMap.getString("checkIn.text")); // NOI18N
        checkIn.setToolTipText(resourceMap.getString("checkIn.toolTipText")); // NOI18N
        checkIn.setFocusable(false);
        checkIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        checkIn.setName("checkIn"); // NOI18N
        checkIn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quickToolbar.add(checkIn);

        checkOut.setAction(actionMap.get("checkOutAction")); // NOI18N
        checkOut.setIcon(resourceMap.getIcon("checkOut.icon")); // NOI18N
        checkOut.setText(resourceMap.getString("checkOut.text")); // NOI18N
        checkOut.setToolTipText(resourceMap.getString("checkOut.toolTipText")); // NOI18N
        checkOut.setFocusable(false);
        checkOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        checkOut.setName("checkOut"); // NOI18N
        checkOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quickToolbar.add(checkOut);

        sep3.setName("sep3"); // NOI18N
        quickToolbar.add(sep3);

        chargeExtra.setAction(actionMap.get("chargeExtraAction")); // NOI18N
        chargeExtra.setIcon(resourceMap.getIcon("chargeExtra.icon")); // NOI18N
        chargeExtra.setText(resourceMap.getString("chargeExtra.text")); // NOI18N
        chargeExtra.setToolTipText(resourceMap.getString("chargeExtra.toolTipText")); // NOI18N
        chargeExtra.setFocusable(false);
        chargeExtra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chargeExtra.setName("chargeExtra"); // NOI18N
        chargeExtra.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quickToolbar.add(chargeExtra);

        paymentExtra.setAction(actionMap.get("paymentsAction")); // NOI18N
        paymentExtra.setIcon(resourceMap.getIcon("paymentExtra.icon")); // NOI18N
        paymentExtra.setText(resourceMap.getString("paymentExtra.text")); // NOI18N
        paymentExtra.setToolTipText(resourceMap.getString("paymentExtra.toolTipText")); // NOI18N
        paymentExtra.setFocusable(false);
        paymentExtra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        paymentExtra.setName("paymentExtra"); // NOI18N
        paymentExtra.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quickToolbar.add(paymentExtra);

        sep9.setName("sep9"); // NOI18N
        quickToolbar.add(sep9);

        stats.setAction(actionMap.get("hotelStatsAction")); // NOI18N
        stats.setIcon(resourceMap.getIcon("stats.icon")); // NOI18N
        stats.setText(resourceMap.getString("stats.text")); // NOI18N
        stats.setToolTipText(resourceMap.getString("stats.toolTipText")); // NOI18N
        stats.setFocusable(false);
        stats.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stats.setName("stats"); // NOI18N
        stats.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quickToolbar.add(stats);

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
        setToolBar(quickToolbar);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem bookingChartMenuItem;
    private javax.swing.JMenuItem calendarMenuItem;
    private javax.swing.JButton chargeExtra;
    private javax.swing.JMenuItem chargeExtraMenuItem;
    private javax.swing.JButton checkIn;
    private javax.swing.JMenuItem checkInMenuItem;
    private javax.swing.JButton checkOut;
    private javax.swing.JMenuItem checkOutMenuItem;
    private javax.swing.JMenu configMenu;
    private javax.swing.JMenu entityMenu;
    private javax.swing.JMenuItem exportMenuItem;
    private javax.swing.JButton goDownButton;
    private javax.swing.JButton goLeftButton;
    private javax.swing.JButton goRightButton;
    private javax.swing.JButton goUpButton;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JMenuItem hotelConfigMenuItem;
    private javax.swing.JMenuItem importMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton newBooking;
    private javax.swing.JMenuItem newBookingMenuItem;
    private javax.swing.JButton newCustomer;
    private javax.swing.JMenuItem newCustomerMenuItem;
    private javax.swing.JButton newEntity;
    private javax.swing.JMenuItem newEntityMenuItem;
    private javax.swing.JMenuItem nightAuditMenuItem;
    private javax.swing.JButton paymentExtra;
    private javax.swing.JMenuItem paymentExtraMenuItem;
    private javax.swing.JPanel planningContainer;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JToolBar quickToolbar;
    private javax.swing.JMenu receptionMenu;
    private javax.swing.JButton searchBooking;
    private javax.swing.JMenuItem searchBookingMenuItem;
    private javax.swing.JButton searchCustomer;
    private javax.swing.JMenuItem searchCustomerMenuItem;
    private javax.swing.JButton searchEntity;
    private javax.swing.JMenuItem searchEntityMenuItem;
    private javax.swing.JToolBar.Separator sep1;
    private javax.swing.JToolBar.Separator sep2;
    private javax.swing.JToolBar.Separator sep3;
    private javax.swing.JPopupMenu.Separator sep4;
    private javax.swing.JPopupMenu.Separator sep5;
    private javax.swing.JPopupMenu.Separator sep6;
    private javax.swing.JPopupMenu.Separator sep7;
    private javax.swing.JPopupMenu.Separator sep8;
    private javax.swing.JToolBar.Separator sep9;
    private javax.swing.JButton stats;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JMenuItem systemConfigMenuItem;
    private javax.swing.JMenu toolMenu;
    // End of variables declaration//GEN-END:variables

    /* domain private fields. */
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
    private JFrame hotelConfigFrame;
    private JFrame systemConfigFrame;
    private JFrame newCardexFrame;
    private JFrame searchCardexFrame;
    private JFrame newEntityFrame;
    private JFrame searchEntityFrame;
    private JFrame newBookingFrame;
    private JFrame searchBookingFrame;
    private JFrame importHotelFrame;
    private JFrame exportHotelFrame;
    private JFrame checkInFrame;
    private JFrame checkOutFrame;
    private JFrame chargeExtraFrame;
    private JFrame paymentsFrame;
    private JFrame hotelStatsFrame;
    private panePlanning planningView;
}
