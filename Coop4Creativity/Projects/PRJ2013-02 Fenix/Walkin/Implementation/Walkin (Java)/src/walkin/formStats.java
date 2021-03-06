/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;

/**
 */
public class formStats extends javax.swing.JFrame {

    /** Creates new form formNewCardex */
    public formStats() {
        initComponents();
        initSemantics();
    }

    /** Action to quit form. */
    @Action
    public void cancelButtonAction() {
        dispose();
    }

    /** Action to save the form. */
    @Action
    public void saveButtonAction() {
        dispose();
    }

    /**
     * Initialize values for buttons, combos, etc.
     */
    private void initSemantics() {
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

        cancelButton = new javax.swing.JButton();
        OkButton = new javax.swing.JButton();
        statsSymbolLabel = new javax.swing.JLabel();
        statsDescriptionLabel = new javax.swing.JLabel();
        statsTitleLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(walkin.Main.class).getContext().getResourceMap(formStats.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setAlwaysOnTop(true);
        setName("Form"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(walkin.Main.class).getContext().getActionMap(formStats.class, this);
        cancelButton.setAction(actionMap.get("cancelButtonAction")); // NOI18N
        cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N

        OkButton.setAction(actionMap.get("saveButtonAction")); // NOI18N
        OkButton.setText(resourceMap.getString("OkButton.text")); // NOI18N
        OkButton.setName("OkButton"); // NOI18N

        statsSymbolLabel.setIcon(resourceMap.getIcon("statsSymbolLabel.icon")); // NOI18N
        statsSymbolLabel.setText(resourceMap.getString("statsSymbolLabel.text")); // NOI18N
        statsSymbolLabel.setName("statsSymbolLabel"); // NOI18N

        statsDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statsDescriptionLabel.setText(resourceMap.getString("statsDescriptionLabel.text")); // NOI18N
        statsDescriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        statsDescriptionLabel.setName("statsDescriptionLabel"); // NOI18N

        statsTitleLabel1.setFont(resourceMap.getFont("statsTitleLabel1.font")); // NOI18N
        statsTitleLabel1.setText(resourceMap.getString("statsTitleLabel1.text")); // NOI18N
        statsTitleLabel1.setName("statsTitleLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(statsSymbolLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(statsTitleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(statsDescriptionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(OkButton))
                .addGap(6, 6, 6)
                .addComponent(cancelButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(statsTitleLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statsSymbolLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statsDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 404, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(OkButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OkButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel statsDescriptionLabel;
    private javax.swing.JLabel statsSymbolLabel;
    private javax.swing.JLabel statsTitleLabel1;
    // End of variables declaration//GEN-END:variables
}
