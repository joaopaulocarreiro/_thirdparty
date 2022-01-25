/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin;

import org.jdesktop.application.Action;

/**
 */
public class formManageCardex extends javax.swing.JFrame {

    /** Creates new form formBooking */
    public formManageCardex() {
        initComponents();
    }

    /** Action to quit form. */
    @Action
    public void cancelButtonAction() {
        dispose();
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

        formSymbolLabel = new javax.swing.JLabel();
        formDescriptionLabel = new javax.swing.JLabel();
        formTitleLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(walkin.Main.class).getContext().getResourceMap(formManageCardex.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N

        formSymbolLabel.setIcon(resourceMap.getIcon("formSymbolLabel.icon")); // NOI18N
        formSymbolLabel.setName("formSymbolLabel"); // NOI18N

        formDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        formDescriptionLabel.setText(resourceMap.getString("formDescriptionLabel.text")); // NOI18N
        formDescriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        formDescriptionLabel.setName("formDescriptionLabel"); // NOI18N

        formTitleLabel.setFont(resourceMap.getFont("formTitleLabel.font")); // NOI18N
        formTitleLabel.setText(resourceMap.getString("formTitleLabel.text")); // NOI18N
        formTitleLabel.setName("formTitleLabel"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(walkin.Main.class).getContext().getActionMap(formManageCardex.class, this);
        cancelButton.setAction(actionMap.get("cancelButtonAction")); // NOI18N
        cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N

        okButton.setText(resourceMap.getString("okButton.text")); // NOI18N
        okButton.setName("okButton"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(formSymbolLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(334, Short.MAX_VALUE)
                .addComponent(okButton)
                .addGap(5, 5, 5)
                .addComponent(cancelButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(formTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(formSymbolLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 328, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel formDescriptionLabel;
    private javax.swing.JLabel formSymbolLabel;
    private javax.swing.JLabel formTitleLabel;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
