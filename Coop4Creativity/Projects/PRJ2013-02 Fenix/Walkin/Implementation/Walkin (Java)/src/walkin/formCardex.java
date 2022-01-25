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
public class formCardex extends javax.swing.JFrame {

    /** Creates new form formNewCardex */
    public formCardex() {
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

        /* save customer information. */
        try {
            saveCustomer();
        } catch (Exception ex) {
            Logger.getLogger(formCardex.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* finally dispose the window. */
        dispose();
    }

    /**
     * Initialize values for buttons, combos, etc.
     */
    private void initSemantics() {

        /* Service Interface. */
        walkin.service.iServCountry servCountry = Main.getServiceLayer().getCountryService();
        walkin.service.iServPerson servPerson = Main.getServiceLayer().getPersonService();

        try {

            /*** Country Name Combo Box. */
            {
                javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel();
                java.util.Iterator itr = servCountry.getCountryList().iterator();
                while (itr.hasNext()) {
                    model.addElement(itr.next());
                }
                countryComboBox.setModel(model);
            }

            /*** Language Combo Box. */
            {
                javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel();
                java.util.Iterator itr = servCountry.getLanguageList().iterator();
                while (itr.hasNext()) {
                    model.addElement(itr.next());
                }
                languageComboBox.setModel(model);
            }

            /*** Identification Kind Combo Box. */
            {
                javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel();
                java.util.Iterator itr = servPerson.getPersonIdKindList().iterator();
                while (itr.hasNext()) {
                    model.addElement(itr.next());
                }
                idKindComboBox.setModel(model);
            }

            /*** Title Combo Box. */
            {
                javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel();
                java.util.Iterator itr = servPerson.getPersonTitleList().iterator();
                while (itr.hasNext()) {
                    model.addElement(itr.next());
                }
                titleComboBox.setModel(model);
            }

            /*** Profession Combo Box. */
            {
                javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel();
                java.util.Iterator itr = servPerson.getPersonProfessionList().iterator();
                while (itr.hasNext()) {
                    model.addElement(itr.next());
                }
                professionComboBox.setModel(model);
            }

        } catch (Exception ex) {
            Logger.getLogger(formCardex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Save customer information.
     */
    private void saveCustomer() throws Exception {

        /* get the customer service layer. */
        walkin.service.iServCustomer servCustomer = Main.getServiceLayer().getCustomerService();

        /* we need to create the customer structure. */
        walkin.model.bCustomer cust;
        cust = new walkin.model.bCustomer();

        /**** insert object into the structure. */

        /* tab - Personal. */
        cust.setTitle((walkin.model.bPersonTitle) titleComboBox.getSelectedItem());
        cust.setFirstName(nameTextField.getText());
        cust.setLastName(lastNameTextField.getText());

        /* date format string. */
        java.text.DateFormat dformat;
        dformat = new java.text.SimpleDateFormat("dd-MM-yyyy");
        cust.setDateOfBirth(dformat.parse(dateOfBirthTextField.getText()));

        cust.setNationality(nationalityTextField.getText());
        cust.setLanguage((walkin.model.bLanguage) languageComboBox.getSelectedItem());
        cust.setTaxNumber(taxNumberTextField.getText());
        cust.setIdKind((walkin.model.bPersonIdKind) idKindComboBox.getSelectedItem());
        cust.setIdNumber(idNumberTextField.getText());

        /* tab - Address. */
        cust.setAddress(addressTextField.getText());
        cust.setCity(cityTextField.getText());
        cust.setZipCode(zipCodeTextField.getText());
        cust.setCountry((walkin.model.bCountry) countryComboBox.getSelectedItem());

        /* tab - Professional. */
        cust.setProfession((walkin.model.bPersonProfession) professionComboBox.getSelectedItem());
        cust.setCompany(companyTextField.getText());
        cust.setContact(companyContactTextField.getText());

        /* tab - Contacts. */
        java.util.List<String> phone = new java.util.ArrayList<String>();
        java.util.List<String> mobile = new java.util.ArrayList<String>();
        java.util.List<String> email = new java.util.ArrayList<String>();

        phone.add(phone1TextField.getText());
        phone.add(phone2TextField.getText());

        mobile.add(mobile1TextField.getText());
        mobile.add(mobile2TextField.getText());

        email.add(email1TextField.getText());
        email.add(email2TextField.getText());

        /* tab - assurance. */
        walkin.model.bCustomerCreditCard ccCust;
        ccCust = new walkin.model.bCustomerCreditCard();

        ccCust.setName(nameCCTextField.getText());
        ccCust.setExpiresOn(dformat.parse(expireCCTextField.getText()));
        ccCust.setNumber(numberCCTextField.getText());

        /* tab - aditional. */
        cust.setObservations(obsTextArea.getText());

        /* add information. */
        try {
            /* add it. */
            servCustomer.insertCustomer(cust, phone, mobile, email);
        } catch (Exception ex) {
            Logger.getLogger(formCardex.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        savelButton = new javax.swing.JButton();
        newCustomerSymbolLabel = new javax.swing.JLabel();
        newCustomerDescriptionLabel = new javax.swing.JLabel();
        customerTabbedPane = new javax.swing.JTabbedPane();
        personalPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        titleComboBox = new javax.swing.JComboBox();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JFormattedTextField();
        lastNameLabel = new javax.swing.JLabel();
        lastNameTextField = new javax.swing.JFormattedTextField();
        dateOfBirthLabel = new javax.swing.JLabel();
        dateOfBirthTextField = new javax.swing.JFormattedTextField();
        dateOfBirthPickDateButton = new javax.swing.JButton();
        nationalityLabel = new javax.swing.JLabel();
        nationalityTextField = new javax.swing.JFormattedTextField();
        languageLabel = new javax.swing.JLabel();
        languageComboBox = new javax.swing.JComboBox();
        taxNumberLabel = new javax.swing.JLabel();
        taxNumberTextField = new javax.swing.JFormattedTextField();
        idKindLabel = new javax.swing.JLabel();
        idKindComboBox = new javax.swing.JComboBox();
        idNumberLabel = new javax.swing.JLabel();
        idNumberTextField = new javax.swing.JFormattedTextField();
        addressPanel = new javax.swing.JPanel();
        addressLabel = new javax.swing.JLabel();
        addressTextField = new javax.swing.JFormattedTextField();
        cityLabel = new javax.swing.JLabel();
        cityTextField = new javax.swing.JFormattedTextField();
        zipCodeLabel = new javax.swing.JLabel();
        zipCodeTextField = new javax.swing.JFormattedTextField();
        countryLabel = new javax.swing.JLabel();
        countryComboBox = new javax.swing.JComboBox();
        professionalPanel = new javax.swing.JPanel();
        professionLabel = new javax.swing.JLabel();
        professionComboBox = new javax.swing.JComboBox();
        companyLabel = new javax.swing.JLabel();
        companyTextField = new javax.swing.JFormattedTextField();
        companyContactLabel = new javax.swing.JLabel();
        companyContactTextField = new javax.swing.JFormattedTextField();
        contactPanel = new javax.swing.JPanel();
        phone1Label = new javax.swing.JLabel();
        phone1TextField = new javax.swing.JFormattedTextField();
        phone2Label = new javax.swing.JLabel();
        phone2TextField = new javax.swing.JFormattedTextField();
        mobile1Label = new javax.swing.JLabel();
        mobile1TextField = new javax.swing.JFormattedTextField();
        mobile2Label = new javax.swing.JLabel();
        mobile2TextField = new javax.swing.JFormattedTextField();
        email1Label = new javax.swing.JLabel();
        email1TextField = new javax.swing.JFormattedTextField();
        email2Label = new javax.swing.JLabel();
        email2TextField = new javax.swing.JFormattedTextField();
        assurancePanel = new javax.swing.JPanel();
        creditCardPanel = new javax.swing.JPanel();
        numberCCTextFieldLabel = new javax.swing.JLabel();
        numberCCTextField = new javax.swing.JFormattedTextField();
        expireCCTextFieldLabel = new javax.swing.JLabel();
        expireCCTextField = new javax.swing.JFormattedTextField();
        expireCCpickDateButton = new javax.swing.JButton();
        nameCCTextFieldLabel = new javax.swing.JLabel();
        nameCCTextField = new javax.swing.JFormattedTextField();
        additionalInfoPanel = new javax.swing.JPanel();
        obsLabel = new javax.swing.JLabel();
        obsScrolllPane = new javax.swing.JScrollPane();
        obsTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(walkin.Main.class).getContext().getResourceMap(formCardex.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(670, 590));
        setName("Form"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(walkin.Main.class).getContext().getActionMap(formCardex.class, this);
        cancelButton.setAction(actionMap.get("cancelButtonAction")); // NOI18N
        cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N

        savelButton.setAction(actionMap.get("saveButtonAction")); // NOI18N
        savelButton.setText(resourceMap.getString("savelButton.text")); // NOI18N
        savelButton.setName("savelButton"); // NOI18N

        newCustomerSymbolLabel.setIcon(resourceMap.getIcon("newCustomerSymbolLabel.icon")); // NOI18N
        newCustomerSymbolLabel.setText(resourceMap.getString("newCustomerSymbolLabel.text")); // NOI18N
        newCustomerSymbolLabel.setName("newCustomerSymbolLabel"); // NOI18N

        newCustomerDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newCustomerDescriptionLabel.setText(resourceMap.getString("newCustomerDescriptionLabel.text")); // NOI18N
        newCustomerDescriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        newCustomerDescriptionLabel.setName("newCustomerDescriptionLabel"); // NOI18N

        customerTabbedPane.setName("customerTabbedPane"); // NOI18N

        personalPanel.setBackground(resourceMap.getColor("personalPanel.background")); // NOI18N
        personalPanel.setName("personalPanel"); // NOI18N

        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        titleLabel.setLabelFor(titleComboBox);
        titleLabel.setText(resourceMap.getString("titleLabel.text")); // NOI18N
        titleLabel.setName("titleLabel"); // NOI18N

        titleComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        titleComboBox.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        titleComboBox.setName("titleComboBox"); // NOI18N

        nameLabel.setBackground(resourceMap.getColor("nameLabel.background")); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLabel.setLabelFor(nameTextField);
        nameLabel.setText(resourceMap.getString("nameLabel.text")); // NOI18N
        nameLabel.setName("nameLabel"); // NOI18N

        nameTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        nameTextField.setText(resourceMap.getString("nameTextField.text")); // NOI18N
        nameTextField.setName("nameTextField"); // NOI18N
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        lastNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastNameLabel.setText(resourceMap.getString("lastNameLabel.text")); // NOI18N
        lastNameLabel.setName("lastNameLabel"); // NOI18N

        lastNameTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lastNameTextField.setText(resourceMap.getString("lastNameTextField.text")); // NOI18N
        lastNameTextField.setName("lastNameTextField"); // NOI18N

        dateOfBirthLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateOfBirthLabel.setText(resourceMap.getString("dateOfBirthLabel.text")); // NOI18N
        dateOfBirthLabel.setName("dateOfBirthLabel"); // NOI18N

        dateOfBirthTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        dateOfBirthTextField.setText(resourceMap.getString("dateOfBirthTextField.text")); // NOI18N
        dateOfBirthTextField.setName("dateOfBirthTextField"); // NOI18N

        dateOfBirthPickDateButton.setIcon(resourceMap.getIcon("dateOfBirthPickDateButton.icon")); // NOI18N
        dateOfBirthPickDateButton.setText(resourceMap.getString("dateOfBirthPickDateButton.text")); // NOI18N
        dateOfBirthPickDateButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        dateOfBirthPickDateButton.setName("dateOfBirthPickDateButton"); // NOI18N

        nationalityLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nationalityLabel.setText(resourceMap.getString("nationalityLabel.text")); // NOI18N
        nationalityLabel.setName("nationalityLabel"); // NOI18N

        nationalityTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        nationalityTextField.setText(resourceMap.getString("nationalityTextField.text")); // NOI18N
        nationalityTextField.setName("nationalityTextField"); // NOI18N

        languageLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        languageLabel.setText(resourceMap.getString("languageLabel.text")); // NOI18N
        languageLabel.setName("languageLabel"); // NOI18N

        languageComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        languageComboBox.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        languageComboBox.setName("languageComboBox"); // NOI18N

        taxNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        taxNumberLabel.setText(resourceMap.getString("taxNumberLabel.text")); // NOI18N
        taxNumberLabel.setName("taxNumberLabel"); // NOI18N

        taxNumberTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        taxNumberTextField.setText(resourceMap.getString("taxNumberTextField.text")); // NOI18N
        taxNumberTextField.setName("taxNumberTextField"); // NOI18N

        idKindLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        idKindLabel.setText(resourceMap.getString("idKindLabel.text")); // NOI18N
        idKindLabel.setName("idKindLabel"); // NOI18N

        idKindComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        idKindComboBox.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        idKindComboBox.setName("idKindComboBox"); // NOI18N

        idNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        idNumberLabel.setText(resourceMap.getString("idNumberLabel.text")); // NOI18N
        idNumberLabel.setName("idNumberLabel"); // NOI18N

        idNumberTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        idNumberTextField.setText(resourceMap.getString("idNumberTextField.text")); // NOI18N
        idNumberTextField.setName("idNumberTextField"); // NOI18N

        javax.swing.GroupLayout personalPanelLayout = new javax.swing.GroupLayout(personalPanel);
        personalPanel.setLayout(personalPanelLayout);
        personalPanelLayout.setHorizontalGroup(
            personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(languageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(taxNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(idKindLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(dateOfBirthLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(lastNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(nationalityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleComboBox, 0, 435, Short.MAX_VALUE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addGroup(personalPanelLayout.createSequentialGroup()
                        .addComponent(dateOfBirthTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateOfBirthPickDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(languageComboBox, 0, 435, Short.MAX_VALUE)
                    .addComponent(taxNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addGroup(personalPanelLayout.createSequentialGroup()
                        .addComponent(idKindComboBox, 0, 82, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idNumberLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                    .addComponent(nationalityTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
                .addContainerGap())
        );
        personalPanelLayout.setVerticalGroup(
            personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleComboBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateOfBirthLabel)
                    .addComponent(dateOfBirthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateOfBirthPickDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nationalityLabel)
                    .addComponent(nationalityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(languageLabel)
                    .addComponent(languageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(taxNumberLabel)
                    .addComponent(taxNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idKindLabel)
                    .addComponent(idKindComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idNumberLabel)
                    .addComponent(idNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(176, 176, 176))
        );

        customerTabbedPane.addTab(resourceMap.getString("personalPanel.TabConstraints.tabTitle"), personalPanel); // NOI18N

        addressPanel.setBackground(resourceMap.getColor("addressPanel.background")); // NOI18N
        addressPanel.setName("addressPanel"); // NOI18N

        addressLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addressLabel.setText(resourceMap.getString("addressLabel.text")); // NOI18N
        addressLabel.setName("addressLabel"); // NOI18N

        addressTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        addressTextField.setText(resourceMap.getString("addressTextField.text")); // NOI18N
        addressTextField.setName("addressTextField"); // NOI18N

        cityLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cityLabel.setText(resourceMap.getString("cityLabel.text")); // NOI18N
        cityLabel.setName("cityLabel"); // NOI18N

        cityTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        cityTextField.setText(resourceMap.getString("cityTextField.text")); // NOI18N
        cityTextField.setName("cityTextField"); // NOI18N

        zipCodeLabel.setText(resourceMap.getString("zipCodeLabel.text")); // NOI18N
        zipCodeLabel.setName("zipCodeLabel"); // NOI18N

        zipCodeTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        zipCodeTextField.setText(resourceMap.getString("zipCodeTextField.text")); // NOI18N
        zipCodeTextField.setName("zipCodeTextField"); // NOI18N

        countryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        countryLabel.setText(resourceMap.getString("countryLabel.text")); // NOI18N
        countryLabel.setName("countryLabel"); // NOI18N

        countryComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        countryComboBox.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        countryComboBox.setName("countryComboBox"); // NOI18N

        javax.swing.GroupLayout addressPanelLayout = new javax.swing.GroupLayout(addressPanel);
        addressPanel.setLayout(addressPanelLayout);
        addressPanelLayout.setHorizontalGroup(
            addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(zipCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(countryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                    .addComponent(cityTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                    .addComponent(countryComboBox, 0, 472, Short.MAX_VALUE)
                    .addComponent(zipCodeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                .addContainerGap())
        );
        addressPanelLayout.setVerticalGroup(
            addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel)
                    .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zipCodeLabel)
                    .addComponent(zipCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countryLabel)
                    .addComponent(countryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(300, Short.MAX_VALUE))
        );

        customerTabbedPane.addTab(resourceMap.getString("addressPanel.TabConstraints.tabTitle"), addressPanel); // NOI18N

        professionalPanel.setBackground(resourceMap.getColor("professionalPanel.background")); // NOI18N
        professionalPanel.setName("professionalPanel"); // NOI18N

        professionLabel.setText(resourceMap.getString("professionLabel.text")); // NOI18N
        professionLabel.setName("professionLabel"); // NOI18N

        professionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        professionComboBox.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        professionComboBox.setName("professionComboBox"); // NOI18N

        companyLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        companyLabel.setText(resourceMap.getString("companyLabel.text")); // NOI18N
        companyLabel.setName("companyLabel"); // NOI18N

        companyTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        companyTextField.setName("companyTextField"); // NOI18N

        companyContactLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        companyContactLabel.setText(resourceMap.getString("companyContactLabel.text")); // NOI18N
        companyContactLabel.setName("companyContactLabel"); // NOI18N

        companyContactTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        companyContactTextField.setName("companyContactTextField"); // NOI18N

        javax.swing.GroupLayout professionalPanelLayout = new javax.swing.GroupLayout(professionalPanel);
        professionalPanel.setLayout(professionalPanelLayout);
        professionalPanelLayout.setHorizontalGroup(
            professionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(professionalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(professionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(professionalPanelLayout.createSequentialGroup()
                        .addComponent(professionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(professionComboBox, 0, 493, Short.MAX_VALUE))
                    .addGroup(professionalPanelLayout.createSequentialGroup()
                        .addGroup(professionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(companyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(companyContactLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(professionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(companyContactTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                            .addComponent(companyTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))))
                .addContainerGap())
        );
        professionalPanelLayout.setVerticalGroup(
            professionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(professionalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(professionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(professionLabel)
                    .addComponent(professionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(professionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyLabel)
                    .addComponent(companyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(professionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyContactLabel)
                    .addComponent(companyContactTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(326, Short.MAX_VALUE))
        );

        customerTabbedPane.addTab(resourceMap.getString("professionalPanel.TabConstraints.tabTitle"), professionalPanel); // NOI18N

        contactPanel.setBackground(resourceMap.getColor("contactPanel.background")); // NOI18N
        contactPanel.setName("contactPanel"); // NOI18N

        phone1Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        phone1Label.setText(resourceMap.getString("phone1Label.text")); // NOI18N
        phone1Label.setName("phone1Label"); // NOI18N

        phone1TextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        phone1TextField.setName("phone1TextField"); // NOI18N

        phone2Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        phone2Label.setText(resourceMap.getString("phone2Label.text")); // NOI18N
        phone2Label.setName("phone2Label"); // NOI18N

        phone2TextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        phone2TextField.setName("phone2TextField"); // NOI18N

        mobile1Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mobile1Label.setText(resourceMap.getString("mobile1Label.text")); // NOI18N
        mobile1Label.setName("mobile1Label"); // NOI18N

        mobile1TextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        mobile1TextField.setName("mobile1TextField"); // NOI18N

        mobile2Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mobile2Label.setText(resourceMap.getString("mobile2Label.text")); // NOI18N
        mobile2Label.setName("mobile2Label"); // NOI18N

        mobile2TextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        mobile2TextField.setName("mobile2TextField"); // NOI18N

        email1Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        email1Label.setText(resourceMap.getString("email1Label.text")); // NOI18N
        email1Label.setName("email1Label"); // NOI18N

        email1TextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        email1TextField.setName("email1TextField"); // NOI18N

        email2Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        email2Label.setText(resourceMap.getString("email2Label.text")); // NOI18N
        email2Label.setName("email2Label"); // NOI18N

        email2TextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        email2TextField.setName("email2TextField"); // NOI18N

        javax.swing.GroupLayout contactPanelLayout = new javax.swing.GroupLayout(contactPanel);
        contactPanel.setLayout(contactPanelLayout);
        contactPanelLayout.setHorizontalGroup(
            contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(email2Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(email1Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mobile2Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phone2Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phone1Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mobile1Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phone1TextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                    .addComponent(phone2TextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                    .addComponent(mobile1TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                    .addComponent(mobile2TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                    .addComponent(email1TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                    .addComponent(email2TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE))
                .addContainerGap())
        );
        contactPanelLayout.setVerticalGroup(
            contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phone1Label)
                    .addComponent(phone1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phone2Label)
                    .addComponent(phone2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mobile1Label)
                    .addComponent(mobile1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mobile2Label)
                    .addComponent(mobile2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email1Label)
                    .addComponent(email1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email2Label)
                    .addComponent(email2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(252, Short.MAX_VALUE))
        );

        customerTabbedPane.addTab(resourceMap.getString("contactPanel.TabConstraints.tabTitle"), contactPanel); // NOI18N

        assurancePanel.setBackground(resourceMap.getColor("assurancePanel.background")); // NOI18N
        assurancePanel.setName("assurancePanel"); // NOI18N

        creditCardPanel.setBackground(resourceMap.getColor("creditCardPanel.background")); // NOI18N
        creditCardPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), resourceMap.getString("creditCardPanel.border.title"))); // NOI18N
        creditCardPanel.setName("creditCardPanel"); // NOI18N

        numberCCTextFieldLabel.setText(resourceMap.getString("numberCCTextFieldLabel.text")); // NOI18N
        numberCCTextFieldLabel.setName("numberCCTextFieldLabel"); // NOI18N

        numberCCTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        numberCCTextField.setText(resourceMap.getString("numberCCTextField.text")); // NOI18N
        numberCCTextField.setName("numberCCTextField"); // NOI18N

        expireCCTextFieldLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        expireCCTextFieldLabel.setText(resourceMap.getString("expireCCTextFieldLabel.text")); // NOI18N
        expireCCTextFieldLabel.setName("expireCCTextFieldLabel"); // NOI18N

        expireCCTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        expireCCTextField.setText(resourceMap.getString("expireCCTextField.text")); // NOI18N
        expireCCTextField.setName("expireCCTextField"); // NOI18N

        expireCCpickDateButton.setIcon(resourceMap.getIcon("expireCCpickDateButton.icon")); // NOI18N
        expireCCpickDateButton.setText(resourceMap.getString("expireCCpickDateButton.text")); // NOI18N
        expireCCpickDateButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        expireCCpickDateButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
        expireCCpickDateButton.setName("expireCCpickDateButton"); // NOI18N

        nameCCTextFieldLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameCCTextFieldLabel.setText(resourceMap.getString("nameCCTextFieldLabel.text")); // NOI18N
        nameCCTextFieldLabel.setName("nameCCTextFieldLabel"); // NOI18N

        nameCCTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        nameCCTextField.setText(resourceMap.getString("nameCCTextField.text")); // NOI18N
        nameCCTextField.setName("nameCCTextField"); // NOI18N

        javax.swing.GroupLayout creditCardPanelLayout = new javax.swing.GroupLayout(creditCardPanel);
        creditCardPanel.setLayout(creditCardPanelLayout);
        creditCardPanelLayout.setHorizontalGroup(
            creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(creditCardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nameCCTextFieldLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(expireCCTextFieldLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numberCCTextFieldLabel, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numberCCTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, creditCardPanelLayout.createSequentialGroup()
                        .addComponent(expireCCTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expireCCpickDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nameCCTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                .addContainerGap())
        );
        creditCardPanelLayout.setVerticalGroup(
            creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(creditCardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberCCTextFieldLabel)
                    .addComponent(numberCCTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expireCCTextFieldLabel)
                    .addComponent(expireCCTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expireCCpickDateButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameCCTextFieldLabel)
                    .addComponent(nameCCTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout assurancePanelLayout = new javax.swing.GroupLayout(assurancePanel);
        assurancePanel.setLayout(assurancePanelLayout);
        assurancePanelLayout.setHorizontalGroup(
            assurancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assurancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(creditCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        assurancePanelLayout.setVerticalGroup(
            assurancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assurancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(creditCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(282, Short.MAX_VALUE))
        );

        customerTabbedPane.addTab("Garantia", assurancePanel);

        additionalInfoPanel.setBackground(resourceMap.getColor("additionalInfoPanel.background")); // NOI18N
        additionalInfoPanel.setName("additionalInfoPanel"); // NOI18N

        obsLabel.setText(resourceMap.getString("obsLabel.text")); // NOI18N
        obsLabel.setName("obsLabel"); // NOI18N

        obsScrolllPane.setName("obsScrolllPane"); // NOI18N

        obsTextArea.setColumns(20);
        obsTextArea.setRows(5);
        obsTextArea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        obsTextArea.setName("obsTextArea"); // NOI18N
        obsScrolllPane.setViewportView(obsTextArea);

        javax.swing.GroupLayout additionalInfoPanelLayout = new javax.swing.GroupLayout(additionalInfoPanel);
        additionalInfoPanel.setLayout(additionalInfoPanelLayout);
        additionalInfoPanelLayout.setHorizontalGroup(
            additionalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(additionalInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(obsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(obsScrolllPane, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );
        additionalInfoPanelLayout.setVerticalGroup(
            additionalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(additionalInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(additionalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(obsScrolllPane, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addComponent(obsLabel))
                .addContainerGap())
        );

        customerTabbedPane.addTab(resourceMap.getString("additionalInfoPanel.TabConstraints.tabTitle"), additionalInfoPanel); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(customerTabbedPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(savelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(newCustomerSymbolLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(newCustomerDescriptionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newCustomerSymbolLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newCustomerDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(savelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel additionalInfoPanel;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JPanel addressPanel;
    private javax.swing.JFormattedTextField addressTextField;
    private javax.swing.JPanel assurancePanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JFormattedTextField cityTextField;
    private javax.swing.JLabel companyContactLabel;
    private javax.swing.JFormattedTextField companyContactTextField;
    private javax.swing.JLabel companyLabel;
    private javax.swing.JFormattedTextField companyTextField;
    private javax.swing.JPanel contactPanel;
    private javax.swing.JComboBox countryComboBox;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JPanel creditCardPanel;
    private javax.swing.JTabbedPane customerTabbedPane;
    private javax.swing.JLabel dateOfBirthLabel;
    private javax.swing.JButton dateOfBirthPickDateButton;
    private javax.swing.JFormattedTextField dateOfBirthTextField;
    private javax.swing.JLabel email1Label;
    private javax.swing.JFormattedTextField email1TextField;
    private javax.swing.JLabel email2Label;
    private javax.swing.JFormattedTextField email2TextField;
    private javax.swing.JFormattedTextField expireCCTextField;
    private javax.swing.JLabel expireCCTextFieldLabel;
    private javax.swing.JButton expireCCpickDateButton;
    private javax.swing.JComboBox idKindComboBox;
    private javax.swing.JLabel idKindLabel;
    private javax.swing.JLabel idNumberLabel;
    private javax.swing.JFormattedTextField idNumberTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox languageComboBox;
    private javax.swing.JLabel languageLabel;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JFormattedTextField lastNameTextField;
    private javax.swing.JLabel mobile1Label;
    private javax.swing.JFormattedTextField mobile1TextField;
    private javax.swing.JLabel mobile2Label;
    private javax.swing.JFormattedTextField mobile2TextField;
    private javax.swing.JFormattedTextField nameCCTextField;
    private javax.swing.JLabel nameCCTextFieldLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JFormattedTextField nameTextField;
    private javax.swing.JLabel nationalityLabel;
    private javax.swing.JFormattedTextField nationalityTextField;
    private javax.swing.JLabel newCustomerDescriptionLabel;
    private javax.swing.JLabel newCustomerSymbolLabel;
    private javax.swing.JFormattedTextField numberCCTextField;
    private javax.swing.JLabel numberCCTextFieldLabel;
    private javax.swing.JLabel obsLabel;
    private javax.swing.JScrollPane obsScrolllPane;
    private javax.swing.JTextArea obsTextArea;
    private javax.swing.JPanel personalPanel;
    private javax.swing.JLabel phone1Label;
    private javax.swing.JFormattedTextField phone1TextField;
    private javax.swing.JLabel phone2Label;
    private javax.swing.JFormattedTextField phone2TextField;
    private javax.swing.JComboBox professionComboBox;
    private javax.swing.JLabel professionLabel;
    private javax.swing.JPanel professionalPanel;
    private javax.swing.JButton savelButton;
    private javax.swing.JLabel taxNumberLabel;
    private javax.swing.JFormattedTextField taxNumberTextField;
    private javax.swing.JComboBox titleComboBox;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel zipCodeLabel;
    private javax.swing.JFormattedTextField zipCodeTextField;
    // End of variables declaration//GEN-END:variables
}
