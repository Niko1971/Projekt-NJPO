package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Interface extends javax.swing.JFrame {

    //Query which will execute
    String Query = "";
    int Counter = 0;

    //Variables used to prepare query
    Boolean IDQueryBool = false;
    int IDQuery = 0;
    String IDQueryString = "";
    Boolean FirstNameQueryBool = false;
    String FirstNameQuery = "";
    String FirstNameQueryString = "";
    Boolean LastNameQueryBool = false;
    String LastNameQuery = "";
    String LastNameQueryString = "";
    Boolean EmailQueryBool = false;
    String EmailQuery = "";
    String EmailQueryString = "";
    Boolean PhoneNumberQueryBool = false;
    String PhoneNumberQuery = "";
    String PhoneNumberQueryString = "";
    Boolean JobIDQueryBool = false;
    String JobIDQuery = "";
    String JobIDQueryString = "";
    Boolean SalaryQueryBool = false;
    String SalaryQuery = "";
    String SalaryQueryString = "";
    Boolean ManagerIDQueryBool = false;
    int ManagerIDQuery = 0;
    String ManagerIDQueryString = "";
    Boolean DepartmentIDQueryBool = false;
    int DepartmentIDQuery = 0;
    String DepartmentIDQueryString = "";
    Boolean AllQueryBool = false;
    String AllQuery = "";
    String AllQueryString = "";

    Boolean isValidCheck = true;
    Boolean isValidAmmountCheck = true;
    Boolean insertIntoIsReady = false;
    Boolean dateIsValid = true;
    Boolean insertValid = false;

    public static final int UNIQUE_CONSTRAINT_VIOLATION = 1;

    //Select query part

    public void cleanTextArea() {
        //To clean Text ares to show just last query
        IDQueryString = "";
        FirstNameQueryString = "";
        LastNameQueryString = "";
        EmailQueryString = "";
        PhoneNumberQueryString = "";
        JobIDQueryString = "";
        SalaryQueryString = "";
        ManagerIDQueryString = "";
        DepartmentIDQueryString = "";
        Query = "";
    }

    public void unselect() {
        //Unselect buttons
        Query = "";
        IDQueryBool = false;
        FirstNameQueryBool = false;
        LastNameQueryBool = false;
        EmailQueryBool = false;
        PhoneNumberQueryBool = false;
        JobIDQueryBool = false;
        SalaryQueryBool = false;
        ManagerIDQueryBool = false;
        DepartmentIDQueryBool = false;
        AllQueryBool = false;
    }

    public void setSelected(String name) {
        //Makes valid query
        if (Counter == 0) {
            Query += name + " ";
            Counter++;
        } else {
            Query += "," + name + " ";
        }
    }

    public void prepareToExecute() {
        //Makes query
        unselect();
        jRadioButton1.setSelected(true);
        Counter = 0;
        Query = "SELECT ";

        if (ID.isSelected()) {
            IDQueryBool = true;
            setSelected("employee_id");
        }
        if (FirstName.isSelected()) {
            FirstNameQueryBool = true;
            setSelected("first_name");
        }
        if (LastName.isSelected()) {
            LastNameQueryBool = true;
            setSelected("last_name");
        }
        if (PhoneNumber.isSelected()) {
            PhoneNumberQueryBool = true;
            setSelected("phone_number");
        }
        if (Email.isSelected()) {
            EmailQueryBool = true;
            setSelected("email");
        }
        if (JobID.isSelected()) {
            JobIDQueryBool = true;
            setSelected("job_id");
        }
        if (Salary.isSelected()) {
            SalaryQueryBool = true;
            setSelected("salary");
        }
        if (DepartmentID.isSelected()) {
            DepartmentIDQueryBool = true;
            setSelected("department_id");
        }
        if (ManagerID.isSelected()) {
            ManagerIDQueryBool = true;
            setSelected("manager_id");
        }
        if (AllButton.isSelected()) {
            IDQueryBool = true;
            setSelected("employee_id");
            FirstNameQueryBool = true;
            setSelected("first_name");
            LastNameQueryBool = true;
            setSelected("last_name");
            PhoneNumberQueryBool = true;
            setSelected("phone_number");
            EmailQueryBool = true;
            setSelected("email");
            JobIDQueryBool = true;
            setSelected("job_id");
            SalaryQueryBool = true;
            setSelected("salary");
            DepartmentIDQueryBool = true;
            setSelected("department_id");
            ManagerIDQueryBool = true;
            setSelected("manager_id");
        }
        Query += " FROM employees ";
    }

    public void Conditionals() {
        //Makes query with conditionals
        if (IDConditional.isSelected()) {
            Query += "where employee_id ";
            Marks();
            Query += IDCombo.getSelectedItem();
        }
        if (FirstNameConditional.isSelected()) {
            Query += "where first_name ";
            Marks();
            Query += "'" + FirstNameCombo.getSelectedItem() + "'";
        }
        if (LastNameConditional.isSelected()) {
            Query += "where last_name ";
            Marks();
            Query += "'" + LastNameCombo.getSelectedItem() + "'";
        }
        if (PhoneNumberConditional.isSelected()) {
            Query += "where phone_number ";
            Marks();
            Query += "'" + PhoneNumberCombo.getSelectedItem() + "'";
        }
        if (EmailConditional.isSelected()) {
            Query += "where email ";
            Marks();
            Query += "'" + EmailCombo.getSelectedItem() + "'";
        }
        if (JobIDConditional.isSelected()) {
            Query += "where job_id ";
            Marks();
            Query += "'" + JobIDCombo.getSelectedItem() + "'";
        }
        if (SalaryConditional.isSelected()) {
            Query += "where salary ";
            Marks();
            Query += "'" + SalaryCombo.getSelectedItem() + "'";
        }
        if (DepartmentIDConditional.isSelected()) {
            Query += "where department_id ";
            Marks();
            Query += "'" + DepartmentIDCombo.getSelectedItem() + "'";
        }
        if (ManagerIDConditional.isSelected()) {
            Query += "where manager_id ";
            Marks();
            Query += "'" + ManagerIDCombo.getSelectedItem() + "'";
        }
    }

    public void Marks() {
        //Adds marks to query
        if (jRadioButton1.isSelected()) {
            Query += "= ";
        }
        if (jRadioButton2.isSelected()) {
            Query += ">= ";
        }
        if (jRadioButton3.isSelected()) {
            Query += "<= ";
        }
        if (jRadioButton4.isSelected()) {
            Query += "> ";
        }
        if (jRadioButton5.isSelected()) {
            Query += "< ";
        }
        if (jRadioButton6.isSelected()) {
            Query += "<>";
        }
    }

    //Update Query part
    public void Update1() {
        //Prepare query for update
        Query = "";
        Query = "UPDATE employees set ";
        if (ID.isSelected()) {
            Query += "employee_id = " + ConditionalTextField.getText();
        }
        if (FirstName.isSelected()) {
            Query += "first_name= '" + ConditionalTextField.getText() + "' where ";
        }
        if (LastName.isSelected()) {
            Query += "last_name= '" + ConditionalTextField.getText() + "' where ";
        }
        if (PhoneNumber.isSelected()) {
            Query += "phone_number '" + ConditionalTextField.getText() + "' where ";
        }
        if (Email.isSelected()) {
            Query += "email= '" + ConditionalTextField.getText() + "' where ";
        }
        if (JobID.isSelected()) {
            Query += "job_id= '" + ConditionalTextField.getText() + "' where ";
        }
        if (Salary.isSelected()) {
            Query += "salary= " + ConditionalTextField.getText() + " where ";
        }
        if (DepartmentID.isSelected()) {
            Query += "department_id= " + ConditionalTextField.getText() + " where ";
        }
        if (ManagerID.isSelected()) {
            Query += "manager_id= " + ConditionalTextField.getText() + " where ";
        }
        if (IDConditional.isSelected()) {
            Query += "employee_id = " + IDCombo.getSelectedItem();
        }
        if (FirstNameConditional.isSelected()) {
            Query += "first_name= '" + FirstNameCombo.getSelectedItem() + "'";
        }
        if (LastNameConditional.isSelected()) {
            Query += "last_name= '" + LastNameCombo.getSelectedItem() + "'";
        }
        if (PhoneNumberConditional.isSelected()) {
            Query += "phone_number= '" + PhoneNumberCombo.getSelectedItem() + "'";
        }
        if (EmailConditional.isSelected()) {
            Query += "email= '" + EmailCombo.getSelectedItem() + "'";
        }
        if (JobIDConditional.isSelected()) {
            Query += "job_id = " + IDCombo.getSelectedItem();
        }
        if (SalaryConditional.isSelected()) {
            Query += "salary = " + IDCombo.getSelectedItem();
        }
        if (DepartmentIDConditional.isSelected()) {
            Query += "department_id= " + IDCombo.getSelectedItem();
        }
        if (ManagerIDConditional.isSelected()) {
            Query += "manager_id = " + IDCombo.getSelectedItem();
        }
    }

    public void setDisabledWhenAllButtonSelected() {
        //Disable buttons
        ID.setSelected(false);
        FirstName.setSelected(false);
        LastName.setSelected(false);
        Email.setSelected(false);
        PhoneNumber.setSelected(false);
        JobID.setSelected(false);
        Salary.setSelected(false);
        ManagerID.setSelected(false);
        DepartmentID.setSelected(false);
    }

    public void checkButtonIsValid() {
        //Checks ammount of selected buttons
        int CheckCounter = 0;
        isValidAmmountCheck = true;
        if (InsertButton.isSelected() || UpdateButton.isSelected() || DeleteButton.isSelected()) {
            if (ID.isSelected()) {
                CheckCounter++;
            }
            if (FirstName.isSelected()) {
                CheckCounter++;
            }
            if (LastName.isSelected()) {
                CheckCounter++;
            }
            if (Email.isSelected()) {
                CheckCounter++;
            }
            if (PhoneNumber.isSelected()) {
                CheckCounter++;
            }
            if (JobID.isSelected()) {
                CheckCounter++;
            }
            if (Salary.isSelected()) {
                CheckCounter++;
            }
            if (ManagerID.isSelected()) {
                CheckCounter++;
            }
            if (DepartmentID.isSelected()) {
                CheckCounter++;
            }
            if (CheckCounter > 1) {
                String TooMuchIsSelected = "Select just one to make query";
                JOptionPane.showMessageDialog(null, TooMuchIsSelected);
                isValidAmmountCheck = false;
            }
        }
    }

    public void checkConditionalButtonIsValid() {
        //Checks ammount of conditional buttons selected
        int CheckCounter = 0;
        isValidAmmountCheck = true;
        if (DeleteButton.isSelected()) {
            if (IDConditional.isSelected()) {
                CheckCounter++;
            }
            if (FirstNameConditional.isSelected()) {
                CheckCounter++;
            }
            if (LastNameConditional.isSelected()) {
                CheckCounter++;
            }
            if (EmailConditional.isSelected()) {
                CheckCounter++;
            }
            if (PhoneNumberConditional.isSelected()) {
                CheckCounter++;
            }
            if (JobIDConditional.isSelected()) {
                CheckCounter++;
            }
            if (SalaryConditional.isSelected()) {
                CheckCounter++;
            }
            if (ManagerIDConditional.isSelected()) {
                CheckCounter++;
            }
            if (DepartmentIDConditional.isSelected()) {
                CheckCounter++;
            }
            if (CheckCounter != 1) {
                String TooMuchIsSelected = "Select just one conditional to make query";
                JOptionPane.showMessageDialog(null, TooMuchIsSelected);
                isValidAmmountCheck = false;
            }
        }
    }

    public void checkInsertQuery() {
        //Checks insert values query are valid
        String ConditionalTextFieldString = "";
        char CheckCharAt = ' ';
        dateIsValid = true;

        if (InsertButton.isSelected()) {
            ConditionalTextFieldString = jTextField1.getText();
            for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                CheckCharAt = ConditionalTextFieldString.charAt(i);
                if (CheckCharAt < 48 || CheckCharAt > 57) {
                    dateIsValid = false;
                }
            }

            ConditionalTextFieldString = jTextField2.getText();
            for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                CheckCharAt = ConditionalTextFieldString.charAt(i);
                if (CheckCharAt >= 65 && CheckCharAt <= 90 || CheckCharAt >= 97 && CheckCharAt <= 122) {
                } else {
                    dateIsValid = false;
                }
            }

            ConditionalTextFieldString = jTextField3.getText();
            for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                CheckCharAt = ConditionalTextFieldString.charAt(i);
                if (CheckCharAt >= 65 && CheckCharAt <= 90 || CheckCharAt >= 97 && CheckCharAt <= 122) {
                } else {
                    dateIsValid = false;
                }
            }

            ConditionalTextFieldString = jTextField4.getText();
            int emailCheck = 0;
            for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                CheckCharAt = ConditionalTextFieldString.charAt(i);
                if (CheckCharAt == 64) {
                    emailCheck++;
                }
                if (emailCheck == 1) {
                    dateIsValid = true;
                } else {
                    dateIsValid = false;
                }
            }

            ConditionalTextFieldString = jTextField5.getText();
            for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                CheckCharAt = ConditionalTextFieldString.charAt(i);
                if (CheckCharAt < 48 && CheckCharAt != 46 || CheckCharAt > 57 && CheckCharAt != 46) {
                    dateIsValid = false;
                }
            }

            ConditionalTextFieldString = jTextField7.getText();
            for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                CheckCharAt = ConditionalTextFieldString.charAt(i);
                if (CheckCharAt < 48 || CheckCharAt > 57) {
                    dateIsValid = false;
                }
            }
            if (dateIsValid == false) {
                JOptionPane.showMessageDialog(null, "You inserted wrong dates. Check it!");
                insertValid = false;
            } else {
                JOptionPane.showMessageDialog(null, "Dates are right");
                insertValid = true;
            }
        }
    }

    public void checkInsertIntoQueryIsValid() {
        //Checks insert into text fields are valid
        insertIntoIsReady = false;
        String ConditionalTextFieldString = ConditionalTextField.getText();
        char CheckCharAt = ' ';
        isValidCheck = false;
        String isValid = "Value is valid";
        String isInvalidNumber = "Value is invalid. Use numbers without space and letters.";
        String isInvalidString = "Value is invalid. Use letters.";
        String isInvalidSpace = "Value contains space. Use numbers only!";
        String isInvalidEmail = "Value doesn't contain @";
        String isInvalidPhoneNumber = "Value must contains numbers and dots";
        String isInvalidJobID = "Value must contains letters and _";
        String isInvalidAll = "All button cannot be selected.";
        Boolean finalValidString = true;
        Boolean finalValidNumber = true;
        Boolean finalValidEmail = true;
        Boolean finalValidPhoneNumber = true;
        Boolean finalValidJobID = true;
        Boolean finalValidAll = true;

        if (InsertButton.isSelected() || UpdateButton.isSelected()) {
            if (ID.isSelected()) {
                for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                    CheckCharAt = ConditionalTextFieldString.charAt(i);
                    if (CheckCharAt < 48 || CheckCharAt > 57) {
                        finalValidNumber = false;
                    }
                }
            }
            if (FirstName.isSelected()) {
                for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                    CheckCharAt = ConditionalTextFieldString.charAt(i);
                    if (CheckCharAt >= 65 && CheckCharAt <= 90 || CheckCharAt >= 97 && CheckCharAt <= 122) {
                    } else {
                        finalValidString = false;
                    }
                }
            }
            if (LastName.isSelected()) {
                for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                    CheckCharAt = ConditionalTextFieldString.charAt(i);
                    if (CheckCharAt >= 65 && CheckCharAt <= 90 || CheckCharAt >= 97 && CheckCharAt <= 122) {
                    } else {
                        finalValidString = false;
                    }
                }
            }
            if (Email.isSelected()) {
                int emailCheck = 0;
                for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                    CheckCharAt = ConditionalTextFieldString.charAt(i);
                    if (CheckCharAt == 64) {
                        emailCheck++;
                    }
                }
                if (emailCheck == 1) {
                    finalValidEmail = true;
                } else {
                    finalValidEmail = false;
                }
            }
            if (PhoneNumber.isSelected()) {
                for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                    CheckCharAt = ConditionalTextFieldString.charAt(i);
                    if (CheckCharAt < 48 && CheckCharAt != 46 || CheckCharAt > 57 && CheckCharAt != 46) {
                        finalValidPhoneNumber = false;
                    }
                }
            }
            if (JobID.isSelected()) {
                int JobIDCheck = 0;
                for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                    CheckCharAt = ConditionalTextFieldString.charAt(i);
                    if (CheckCharAt == 95) {
                        JobIDCheck++;
                    }
                }
                if (JobIDCheck == 1) {
                    finalValidJobID = true;
                } else {
                    finalValidJobID = false;
                }
            }
            if (Salary.isSelected()) {
                for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                    CheckCharAt = ConditionalTextFieldString.charAt(i);
                    if (CheckCharAt < 48 || CheckCharAt > 57) {
                        finalValidNumber = false;
                    }
                }
            }
            if (DepartmentID.isSelected()) {
                for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                    CheckCharAt = ConditionalTextFieldString.charAt(i);
                    if (CheckCharAt < 48 || CheckCharAt > 57) {
                        finalValidNumber = false;
                    }
                }
            }
            if (ManagerID.isSelected()) {
                for (int i = 0; i < ConditionalTextFieldString.length(); i++) {
                    CheckCharAt = ConditionalTextFieldString.charAt(i);
                    if (CheckCharAt < 48 || CheckCharAt > 57) {
                        finalValidNumber = false;
                    }
                }
            }
            if (AllButton.isSelected()) {
                finalValidAll = false;
            }
            if (finalValidString == false) {
                JOptionPane.showMessageDialog(null, isInvalidString);
                isValidCheck = false;
            } else if (finalValidNumber == false) {
                JOptionPane.showMessageDialog(null, isInvalidNumber);
                isValidCheck = false;
            } else if (finalValidEmail == false) {
                JOptionPane.showMessageDialog(null, isInvalidEmail);
                isValidCheck = false;
            } else if (finalValidPhoneNumber == false) {
                JOptionPane.showMessageDialog(null, isInvalidPhoneNumber);
                isValidCheck = false;
            } else if (finalValidJobID == false) {
                JOptionPane.showMessageDialog(null, isInvalidJobID);
                isValidCheck = false;
            } else if (finalValidAll == false) {
                JOptionPane.showMessageDialog(null, isInvalidAll);
                isValidCheck = false;
            } else {
                JOptionPane.showMessageDialog(null, isValid);
                insertIntoIsReady = true;
            }
        }
    }

    public void setDisabledAllButton() {
        //disabled all button for select query
        AllButton.setSelected(false);
    }

    //Delete query part
    public void prepareToDelete() {
        //Makes delete query
        Query = "";
        Query = "DELETE FROM employees where ";

        if (IDConditional.isSelected()) {
            Query += "employee_id ";
        }
        if (FirstNameConditional.isSelected()) {
            Query += "first_name ";
        }
        if (LastNameConditional.isSelected()) {
            Query += "last_name ";
        }
        if (PhoneNumberConditional.isSelected()) {
            Query += "phone_number ";
        }
        if (EmailConditional.isSelected()) {
            Query += "email  ";
        }
        if (JobIDConditional.isSelected()) {
            Query += "job_id ";
        }
        if (SalaryConditional.isSelected()) {
            Query += "salary ";
        }
        if (DepartmentIDConditional.isSelected()) {
            Query += "department_id ";
        }
        if (ManagerIDConditional.isSelected()) {
            Query += "manager_id ";
        }
        if (jRadioButton1.isSelected()) {
            Query += "=";
        }
        if (jRadioButton2.isSelected()) {
            Query += "=>";
        }
        if (jRadioButton3.isSelected()) {
            Query += "<=";
        }
        if (jRadioButton4.isSelected()) {
            Query += ">";
        }
        if (jRadioButton5.isSelected()) {
            Query += "<";
        }
        if (jRadioButton6.isSelected()) {
            Query += "<>";
        }
        if (IDConditional.isSelected()) {
            Query += IDCombo.getSelectedItem();
        }
        if (FirstNameConditional.isSelected()) {
            Query += FirstNameCombo.getSelectedItem();
        }
        if (LastNameConditional.isSelected()) {
            Query += LastNameCombo.getSelectedItem();
        }
        if (PhoneNumberConditional.isSelected()) {
            Query += PhoneNumberCombo.getSelectedItem();
        }
        if (EmailConditional.isSelected()) {
            Query += EmailCombo.getSelectedItem();
        }
        if (JobIDConditional.isSelected()) {
            Query += JobIDCombo.getSelectedItem();
        }
        if (SalaryConditional.isSelected()) {
            Query += SalaryCombo.getSelectedItem();
        }
        if (DepartmentIDConditional.isSelected()) {
            Query += DepartmentIDCombo.getSelectedItem();
        }
        if (ManagerIDConditional.isSelected()) {
            Query += ManagerIDCombo.getSelectedItem();
        }
    }

    public void setInsert() {
        //Makes insert into query
        Query = "INSERT INTO employees (employee_id,first_name,last_name,email,phone_number,job_id,salary,manager_id,hire_date) values (";
        Query += jTextField1.getText() + ",'" + jTextField2.getText() + "','" + jTextField3.getText() + "','" + jTextField4.getText() + "','" + jTextField5.getText();
        Query += "','AD_VP','" + jTextField7.getText() + "',100,SYSDATE)";
    }

    /**
     * Creates new form ContactEditor
     */
    public Interface() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        buttonGroup11 = new javax.swing.ButtonGroup();
        buttonGroup12 = new javax.swing.ButtonGroup();
        buttonGroup13 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jFrame1 = new javax.swing.JFrame();
        ExitButton = new javax.swing.JButton();
        ExecuteButton = new javax.swing.JButton();
        InsertButton = new javax.swing.JRadioButton();
        SelectButton = new javax.swing.JRadioButton();
        UpdateButton = new javax.swing.JRadioButton();
        DeleteButton = new javax.swing.JRadioButton();
        ID = new javax.swing.JRadioButton();
        FirstName = new javax.swing.JRadioButton();
        LastName = new javax.swing.JRadioButton();
        Email = new javax.swing.JRadioButton();
        PhoneNumber = new javax.swing.JRadioButton();
        JobID = new javax.swing.JRadioButton();
        Salary = new javax.swing.JRadioButton();
        ManagerID = new javax.swing.JRadioButton();
        DepartmentID = new javax.swing.JRadioButton();
        AllButton = new javax.swing.JRadioButton();
        ManagerIDConditional = new javax.swing.JRadioButton();
        DepartmentIDConditional = new javax.swing.JRadioButton();
        FirstNameConditional = new javax.swing.JRadioButton();
        LastNameConditional = new javax.swing.JRadioButton();
        EmailConditional = new javax.swing.JRadioButton();
        PhoneNumberConditional = new javax.swing.JRadioButton();
        IDConditional = new javax.swing.JRadioButton();
        JobIDConditional = new javax.swing.JRadioButton();
        SalaryConditional = new javax.swing.JRadioButton();
        IDCombo = new javax.swing.JComboBox();
        FirstNameCombo = new javax.swing.JComboBox();
        LastNameCombo = new javax.swing.JComboBox();
        EmailCombo = new javax.swing.JComboBox();
        PhoneNumberCombo = new javax.swing.JComboBox();
        JobIDCombo = new javax.swing.JComboBox();
        SalaryCombo = new javax.swing.JComboBox();
        ManagerIDCombo = new javax.swing.JComboBox();
        DepartmentIDCombo = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        IDTextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        FirstNameTextArea = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        LastNameTextArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        EmailTextArea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        PhoneNumberTextArea = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        JobIDTextArea = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        SalaryTextArea = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        ManagerIDTextArea = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        DepartmentIDTextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        ConditionalTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        CheckButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();

        org.jdesktop.layout.GroupLayout jDialog1Layout = new org.jdesktop.layout.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jFrame1Layout = new org.jdesktop.layout.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employees");
        setFocusTraversalPolicyProvider(true);

        ExitButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        ExecuteButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        ExecuteButton.setText("Execute");
        ExecuteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExecuteButtonMouseClicked(evt);
            }
        });
        ExecuteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExecuteButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(InsertButton);
        InsertButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        InsertButton.setText("Insert Into");
        InsertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(SelectButton);
        SelectButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        SelectButton.setSelected(true);
        SelectButton.setText("Select");

        buttonGroup1.add(UpdateButton);
        UpdateButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        UpdateButton.setText("Update");

        buttonGroup1.add(DeleteButton);
        DeleteButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        DeleteButton.setText("Delete");

        ID.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        ID.setText("ID");
        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });

        FirstName.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        FirstName.setText("First Name");
        FirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FirstNameActionPerformed(evt);
            }
        });

        LastName.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        LastName.setText("Last Name");
        LastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LastNameActionPerformed(evt);
            }
        });

        Email.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        Email.setText("Email");
        Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailActionPerformed(evt);
            }
        });

        PhoneNumber.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        PhoneNumber.setText("Phone Number");
        PhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneNumberActionPerformed(evt);
            }
        });

        JobID.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        JobID.setText("Job ID");
        JobID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JobIDActionPerformed(evt);
            }
        });

        Salary.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        Salary.setText("Salary");
        Salary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalaryActionPerformed(evt);
            }
        });

        ManagerID.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        ManagerID.setText("Manager ID");
        ManagerID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManagerIDActionPerformed(evt);
            }
        });

        DepartmentID.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        DepartmentID.setText("Department ID");
        DepartmentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentIDActionPerformed(evt);
            }
        });

        AllButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        AllButton.setText("All");
        AllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllButtonActionPerformed(evt);
            }
        });

        buttonGroup2.add(ManagerIDConditional);
        ManagerIDConditional.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        ManagerIDConditional.setText("Manager ID");
        ManagerIDConditional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManagerIDConditionalActionPerformed(evt);
            }
        });

        buttonGroup2.add(DepartmentIDConditional);
        DepartmentIDConditional.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        DepartmentIDConditional.setText("Department ID");
        DepartmentIDConditional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentIDConditionalActionPerformed(evt);
            }
        });

        buttonGroup2.add(FirstNameConditional);
        FirstNameConditional.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        FirstNameConditional.setText("First Name");

        buttonGroup2.add(LastNameConditional);
        LastNameConditional.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        LastNameConditional.setText("Last Name");

        buttonGroup2.add(EmailConditional);
        EmailConditional.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        EmailConditional.setText("Email");

        buttonGroup2.add(PhoneNumberConditional);
        PhoneNumberConditional.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        PhoneNumberConditional.setText("Phone Number");
        PhoneNumberConditional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneNumberConditionalActionPerformed(evt);
            }
        });

        buttonGroup2.add(IDConditional);
        IDConditional.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        IDConditional.setText("ID");

        buttonGroup2.add(JobIDConditional);
        JobIDConditional.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        JobIDConditional.setText("Job ID");

        buttonGroup2.add(SalaryConditional);
        SalaryConditional.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        SalaryConditional.setText("Salary");

        IDCombo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        IDCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "101", "100", "102", "103", "124", "149", "200", "201", "205", "300", "389", "422", "533", "333", "64" }));
        IDCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IDComboMouseClicked(evt);
            }
        });
        IDCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDComboActionPerformed(evt);
            }
        });

        FirstNameCombo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        FirstNameCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alexander", "Eleni", "Lex", "Jennifer", "Kevin", "Michael", "Shelley", "Tomek" }));

        LastNameCombo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        LastNameCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "De Haan", "Hartstein", "Higgins", "Hunold", "Kochhar", "MATI", "Mourgos", "Whalen", "Zlotkey" }));
        LastNameCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LastNameComboActionPerformed(evt);
            }
        });

        EmailCombo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        EmailCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AHUNOLD", "EZLOTKEY", "JWHALEN", "KMOURGOS", "LDEHAAN", "MHARTSTE", "NKOCHHAR", "SHIGGINS", "SKING" }));

        PhoneNumberCombo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        PhoneNumberCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "011.44.1344.429018", "515.123.4567", "515.123.4568", "515.123.4569", "515.123.4444", "515.123.5555", "515.123.8080", "590.423.4567", "650.123.5234", "23" }));

        JobIDCombo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        JobIDCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AD_ASST", "AC_MGR", "AD_PRES", "AD_VP", "AD_VP", "IT_PROG", "MK_MAN", "SA_MAN", "ST_MAN" }));

        SalaryCombo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        SalaryCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1000", "4400", "5800", "9000", "10500", "12000", "13000", "17000" }));

        ManagerIDCombo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        ManagerIDCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100", "101", "102" }));

        DepartmentIDCombo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        DepartmentIDCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "50", "60", "80", "90", "110" }));

        IDTextArea.setColumns(20);
        IDTextArea.setRows(5);
        IDTextArea.setMaximumSize(new java.awt.Dimension(120, 60));
        IDTextArea.setPreferredSize(new java.awt.Dimension(100, 60));
        jScrollPane2.setViewportView(IDTextArea);

        FirstNameTextArea.setColumns(20);
        FirstNameTextArea.setRows(5);
        FirstNameTextArea.setMaximumSize(new java.awt.Dimension(120, 60));
        FirstNameTextArea.setPreferredSize(new java.awt.Dimension(100, 60));
        jScrollPane3.setViewportView(FirstNameTextArea);

        LastNameTextArea.setColumns(20);
        LastNameTextArea.setRows(5);
        LastNameTextArea.setMaximumSize(new java.awt.Dimension(120, 60));
        LastNameTextArea.setPreferredSize(new java.awt.Dimension(100, 60));
        jScrollPane1.setViewportView(LastNameTextArea);

        EmailTextArea.setColumns(20);
        EmailTextArea.setRows(5);
        EmailTextArea.setMaximumSize(new java.awt.Dimension(120, 60));
        EmailTextArea.setPreferredSize(new java.awt.Dimension(100, 60));
        jScrollPane4.setViewportView(EmailTextArea);

        PhoneNumberTextArea.setColumns(20);
        PhoneNumberTextArea.setRows(5);
        PhoneNumberTextArea.setMaximumSize(new java.awt.Dimension(120, 60));
        PhoneNumberTextArea.setPreferredSize(new java.awt.Dimension(106, 60));
        jScrollPane5.setViewportView(PhoneNumberTextArea);

        JobIDTextArea.setColumns(20);
        JobIDTextArea.setRows(5);
        JobIDTextArea.setMaximumSize(new java.awt.Dimension(120, 60));
        JobIDTextArea.setPreferredSize(new java.awt.Dimension(100, 60));
        jScrollPane6.setViewportView(JobIDTextArea);

        SalaryTextArea.setColumns(20);
        SalaryTextArea.setRows(5);
        SalaryTextArea.setMaximumSize(new java.awt.Dimension(120, 60));
        SalaryTextArea.setPreferredSize(new java.awt.Dimension(100, 60));
        jScrollPane7.setViewportView(SalaryTextArea);

        ManagerIDTextArea.setColumns(20);
        ManagerIDTextArea.setRows(5);
        ManagerIDTextArea.setMaximumSize(new java.awt.Dimension(120, 60));
        ManagerIDTextArea.setPreferredSize(new java.awt.Dimension(100, 60));
        jScrollPane8.setViewportView(ManagerIDTextArea);

        jScrollPane9.setMaximumSize(new java.awt.Dimension(120, 60));

        DepartmentIDTextArea.setColumns(20);
        DepartmentIDTextArea.setRows(5);
        DepartmentIDTextArea.setPreferredSize(new java.awt.Dimension(100, 60));
        jScrollPane9.setViewportView(DepartmentIDTextArea);

        jLabel2.setText("ID");

        jLabel3.setText("First Name");

        jLabel4.setText("Last Name");

        jLabel5.setText("Email");

        jLabel6.setText("Phone Number");

        jLabel7.setText("Job ID");

        jLabel8.setText("Salary");

        jLabel9.setText("Manager ID");

        jLabel10.setText("Department ID");

        buttonGroup3.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jRadioButton1.setText("=");

        buttonGroup3.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jRadioButton2.setText(">=");

        buttonGroup3.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jRadioButton3.setText("<=");

        buttonGroup3.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jRadioButton4.setText(">");

        buttonGroup3.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jRadioButton5.setText("<");

        buttonGroup3.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jRadioButton6.setText("<>");

        ConditionalTextField.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N

        jLabel1.setText("Examples");

        jLabel11.setText("Select, ID(Query), ID(Conditional),=,100  == SELECT id FROM employees WHERE employee_id=100;");

        jLabel12.setText("Query");

        jLabel13.setText("Conditional");

        jLabel14.setText("Insert Into Button, insert values in Insert Into Panel, use check then execute");

        CheckButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        CheckButton.setText("Check");
        CheckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckButtonActionPerformed(evt);
            }
        });

        jLabel15.setText("Use Check Button to check Insert Into or Update query is valid");

        jLabel16.setText("Update,Salary(Query),value from text field, ID(Conditional),=,101 == UPDATE employees SET salary=value WHERE employee_id=101;");

        jLabel17.setText("Insert Into Panel");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jTextField1.setText("ID");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jTextField2.setText("First Name");

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jTextField3.setText("Last Name");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jTextField4.setText("Email");

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jTextField5.setText("Phone Number");

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jTextField7.setText("Salary");

        jLabel18.setText("Delete Button, ID(Conditional),=,101 == DELETE FROM employees WHERE employee_id=101;");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSeparator1)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(13, 13, 13)
                                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(layout.createSequentialGroup()
                                        .add(50, 50, 50)
                                        .add(jLabel2)
                                        .add(64, 64, 64)
                                        .add(jLabel3)))
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(layout.createSequentialGroup()
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                                    .add(layout.createSequentialGroup()
                                        .add(26, 26, 26)
                                        .add(jLabel4)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(jLabel5)
                                        .add(43, 43, 43)))
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(layout.createSequentialGroup()
                                        .add(8, 8, 8)
                                        .add(jLabel6)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                        .add(jLabel7)
                                        .add(37, 37, 37)))
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(layout.createSequentialGroup()
                                        .add(41, 41, 41)
                                        .add(jLabel8)
                                        .add(51, 51, 51)
                                        .add(jLabel9)
                                        .add(43, 43, 43)
                                        .add(jLabel10)
                                        .add(16, 16, 16))
                                    .add(layout.createSequentialGroup()
                                        .add(6, 6, 6)
                                        .add(jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(jScrollPane8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jScrollPane9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                            .add(jLabel1)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(UpdateButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                .add(DeleteButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jLabel11)
                            .add(jLabel15)
                            .add(jLabel16)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(InsertButton)
                                        .add(SelectButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(18, 18, 18)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, AllButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, ManagerID, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, DepartmentID, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                        .add(layout.createSequentialGroup()
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(jLabel12)
                                                .add(Salary, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, LastName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, FirstName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, ID, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, JobID, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, PhoneNumber, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, Email, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(ConditionalTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(layout.createSequentialGroup()
                                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                                        .add(FirstNameConditional, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                                        .add(LastNameConditional, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .add(IDConditional, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .add(18, 18, 18)
                                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                        .add(jRadioButton2)
                                                        .add(jRadioButton1)))
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                                    .add(layout.createSequentialGroup()
                                                        .add(JobIDConditional, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                        .add(18, 18, 18)
                                                        .add(jRadioButton5))
                                                    .add(layout.createSequentialGroup()
                                                        .add(PhoneNumberConditional, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                        .add(18, 18, 18)
                                                        .add(jRadioButton4)))
                                                .add(layout.createSequentialGroup()
                                                    .add(105, 105, 105)
                                                    .add(jRadioButton3))
                                                .add(EmailConditional, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(DepartmentIDConditional))
                                            .add(40, 40, 40)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, DepartmentIDCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, ManagerIDCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, SalaryCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, JobIDCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, IDCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, EmailCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, LastNameCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, FirstNameCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, PhoneNumberCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 114, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                            .add(28, 28, 28)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(layout.createSequentialGroup()
                                                    .add(ExitButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .add(42, 42, 42)
                                                    .add(jTextField7))
                                                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                        .add(ExecuteButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .add(CheckButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 42, Short.MAX_VALUE)
                                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                        .add(jLabel17)
                                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                            .add(jTextField5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                                            .add(jTextField4)
                                                            .add(jTextField3)
                                                            .add(jTextField2)
                                                            .add(jTextField1)))))
                                            .add(291, 291, 291))
                                        .add(layout.createSequentialGroup()
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(jLabel13)
                                                .add(layout.createSequentialGroup()
                                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                                        .add(org.jdesktop.layout.GroupLayout.LEADING, ManagerIDConditional, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                                        .add(org.jdesktop.layout.GroupLayout.LEADING, SalaryConditional, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .add(18, 18, 18)
                                                    .add(jRadioButton6)))
                                            .add(0, 0, Short.MAX_VALUE))))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel14)
                                    .add(141, 141, 141))))
                        .add(42, 42, 42))
                    .add(layout.createSequentialGroup()
                        .add(jLabel18)
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        layout.linkSize(new java.awt.Component[] {ExecuteButton, ExitButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel11)
                .add(4, 4, 4)
                .add(jLabel15)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel16)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel14)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel18)
                .add(29, 29, 29)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel12)
                    .add(jLabel13)
                    .add(jLabel17))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(ID)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(IDConditional)
                        .add(IDCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(SelectButton)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(FirstNameConditional)
                    .add(FirstName)
                    .add(FirstNameCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(InsertButton)
                    .add(jRadioButton1)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(LastNameConditional)
                    .add(LastName)
                    .add(UpdateButton)
                    .add(LastNameCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jRadioButton2)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(EmailConditional)
                        .add(Email)
                        .add(DeleteButton)
                        .add(EmailCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jRadioButton3)
                        .add(CheckButton)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(PhoneNumber)
                        .add(ConditionalTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(PhoneNumberConditional)
                        .add(jRadioButton4)
                        .add(PhoneNumberCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(ExecuteButton)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(JobID)
                    .add(JobIDConditional)
                    .add(jRadioButton5)
                    .add(JobIDCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(ExitButton)
                    .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(Salary, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(SalaryConditional))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(ManagerID)
                            .add(ManagerIDConditional))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(DepartmentID, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(DepartmentIDConditional, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jRadioButton6)
                            .add(SalaryCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(ManagerIDCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(DepartmentIDCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(AllButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jLabel4)
                    .add(jLabel3)
                    .add(jLabel5)
                    .add(jLabel6)
                    .add(jLabel7)
                    .add(jLabel8)
                    .add(jLabel9)
                    .add(jLabel10))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InsertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertButtonActionPerformed

    }//GEN-LAST:event_InsertButtonActionPerformed

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDActionPerformed

    }//GEN-LAST:event_IDActionPerformed

    private void IDComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDComboActionPerformed

    private void IDComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDComboMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_IDComboMouseClicked

    private void ExecuteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExecuteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExecuteButtonActionPerformed

    private void ExecuteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExecuteButtonMouseClicked

        //Execute button send query to date base
        //Insert query
        if (InsertButton.isSelected()) {
            if (insertValid == true) {
                setInsert();
                Singleton singleton1 = new Singleton();
                try {
                    int update = singleton1.executeUpdate(Query);
                    String insertDone = "Insert query is done";
                    JOptionPane.showMessageDialog(null, insertDone);
                } catch (SQLException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return;
            }
        } //Select query 
        else if (SelectButton.isSelected()) {
            cleanTextArea();
            prepareToExecute();
            Conditionals();
            try {
                cleanTextArea();
                prepareToExecute();
                Conditionals();
                Singleton singleton = new Singleton();
                
                ResultSet IDShow = singleton.executeSelect(Query);
                if (IDQueryBool == true) {
                    while (IDShow.next()) {
                        int IDQuery = IDShow.getInt("employee_id");
                        IDQueryString += IDQuery + "\n";
                    }
                }
                IDTextArea.setText(IDQueryString);

                ResultSet FirstNameShow = singleton.executeSelect(Query);
                if (FirstNameQueryBool == true) {
                    while (FirstNameShow.next()) {
                        String FirstNameQuery = FirstNameShow.getString("first_name");
                        FirstNameQueryString += FirstNameQuery + "\n";
                    }
                }
                FirstNameTextArea.setText(FirstNameQueryString);

                ResultSet LastNameShow = singleton.executeSelect(Query);
                if (LastNameQueryBool == true) {
                    while (LastNameShow.next()) {
                        String LastNameQuery = LastNameShow.getString("last_name");
                        LastNameQueryString += LastNameQuery + "\n";
                    }
                }
                LastNameTextArea.setText(LastNameQueryString);

                ResultSet EmailShow = singleton.executeSelect(Query);
                if (EmailQueryBool == true) {
                    while (EmailShow.next()) {
                        String EmailQuery = EmailShow.getString("email");
                        EmailQueryString += EmailQuery + "\n";
                    }
                }
                EmailTextArea.setText(EmailQueryString);

                ResultSet PhoneNumberShow = singleton.executeSelect(Query);
                if (PhoneNumberQueryBool == true) {
                    while (PhoneNumberShow.next()) {
                        String PhoneNumberQuery = PhoneNumberShow.getString("phone_number");
                        PhoneNumberQueryString += PhoneNumberQuery + "\n";
                    }
                }
                PhoneNumberTextArea.setText(PhoneNumberQueryString);

                ResultSet JobIDShow = singleton.executeSelect(Query);
                if (JobIDQueryBool == true) {
                    while (JobIDShow.next()) {
                        String JobIDQuery = JobIDShow.getString("job_id");
                        JobIDQueryString += JobIDQuery + "\n";
                    }
                }
                JobIDTextArea.setText(JobIDQueryString);

                ResultSet SalaryShow = singleton.executeSelect(Query);
                if (SalaryQueryBool == true) {
                    while (SalaryShow.next()) {
                        String SalaryQuery = SalaryShow.getString("salary");
                        SalaryQueryString += SalaryQuery + "\n";
                    }
                }
                SalaryTextArea.setText(SalaryQueryString);

                ResultSet ManagerIDShow = singleton.executeSelect(Query);

                if (ManagerIDQueryBool == true) {
                    while (ManagerIDShow.next()) {
                        String ManagerIDQuery = ManagerIDShow.getString("manager_id");
                        ManagerIDQueryString += ManagerIDQuery + "\n";
                    }
                }
                ManagerIDTextArea.setText(ManagerIDQueryString);

                ResultSet DepartmentIDShow = singleton.executeSelect(Query);
                if (DepartmentIDQueryBool == true) {
                    while (DepartmentIDShow.next()) {
                        String DepartmentIDQuery = DepartmentIDShow.getString("department_id");
                        DepartmentIDQueryString += DepartmentIDQuery + "\n";
                    }
                }
                DepartmentIDTextArea.setText(DepartmentIDQueryString);

            } catch (SQLException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }

        } //Update query
        else if (UpdateButton.isSelected()) {
            checkButtonIsValid();
            if (insertIntoIsReady == true) {
                Update1();
                Singleton singleton1 = new Singleton();
                try {
                    int update = singleton1.executeUpdate(Query);
                    String updateDone = "Update query is done";
                    JOptionPane.showMessageDialog(null, updateDone);
                } catch (SQLException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return;
            }
        } //Delete query
        else if (DeleteButton.isSelected()) {
            checkConditionalButtonIsValid();
            Singleton singleton1 = new Singleton();
            if (isValidAmmountCheck == true) {
                prepareToDelete();
                try {
                    int update = singleton1.executeDelete(Query);
                    String deleteDone = "Delete query is done";
                    JOptionPane.showMessageDialog(null, deleteDone);

                } catch (java.sql.SQLIntegrityConstraintViolationException e) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, e);

                    if (UNIQUE_CONSTRAINT_VIOLATION == e.getErrorCode()) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, e);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                return;
            }
        }
    }//GEN-LAST:event_ExecuteButtonMouseClicked

    private void AllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllButtonActionPerformed
        setDisabledWhenAllButtonSelected();
    }//GEN-LAST:event_AllButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void FirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstNameActionPerformed
        setDisabledAllButton();
    }//GEN-LAST:event_FirstNameActionPerformed

    private void LastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastNameActionPerformed
        setDisabledAllButton();
    }//GEN-LAST:event_LastNameActionPerformed

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        setDisabledAllButton();
    }//GEN-LAST:event_EmailActionPerformed

    private void PhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneNumberActionPerformed
        setDisabledAllButton();
    }//GEN-LAST:event_PhoneNumberActionPerformed

    private void JobIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JobIDActionPerformed
        setDisabledAllButton();
    }//GEN-LAST:event_JobIDActionPerformed

    private void SalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalaryActionPerformed
        setDisabledAllButton();
    }//GEN-LAST:event_SalaryActionPerformed

    private void ManagerIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManagerIDActionPerformed
        setDisabledAllButton();
    }//GEN-LAST:event_ManagerIDActionPerformed

    private void DepartmentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentIDActionPerformed
        setDisabledAllButton();
    }//GEN-LAST:event_DepartmentIDActionPerformed

    private void CheckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckButtonActionPerformed
        //Checks values are valid
        if (UpdateButton.isSelected()) {
            checkButtonIsValid();
            String emptyCTF = ConditionalTextField.getText();
            if ("".equals(emptyCTF)) {
                isValidAmmountCheck = false;
            }
            if (isValidAmmountCheck == true) {
                checkInsertIntoQueryIsValid();
                IDConditional.setSelected(true);
                jRadioButton1.setSelected(true);
            }
        }
        if (InsertButton.isSelected()) {
            checkInsertQuery();
        }
    }//GEN-LAST:event_CheckButtonActionPerformed

    private void LastNameComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastNameComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LastNameComboActionPerformed

    private void ManagerIDConditionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManagerIDConditionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ManagerIDConditionalActionPerformed

    private void DepartmentIDConditionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentIDConditionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DepartmentIDConditionalActionPerformed

    private void PhoneNumberConditionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneNumberConditionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneNumberConditionalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static Connection dateBaseConnection() {
        //Makes date base connections
        try {
            String DB_URL = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";
            String USER = "ziibd1";
            String PASS = "4420152";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        } catch (SQLException e) {
            System.out.println("SQL error" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver error" + e.getMessage());
        }
        return null;
    }

    public static void main(String args[]) throws SQLException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels = javax.swing.UIManager.getInstalledLookAndFeels();
            for (int idx = 0; idx < installedLookAndFeels.length; idx++) {
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton AllButton;
    private javax.swing.JButton CheckButton;
    private javax.swing.JTextField ConditionalTextField;
    private javax.swing.JRadioButton DeleteButton;
    private javax.swing.JRadioButton DepartmentID;
    private javax.swing.JComboBox DepartmentIDCombo;
    private javax.swing.JRadioButton DepartmentIDConditional;
    private javax.swing.JTextArea DepartmentIDTextArea;
    private javax.swing.JRadioButton Email;
    private javax.swing.JComboBox EmailCombo;
    private javax.swing.JRadioButton EmailConditional;
    private javax.swing.JTextArea EmailTextArea;
    private javax.swing.JButton ExecuteButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JRadioButton FirstName;
    private javax.swing.JComboBox FirstNameCombo;
    private javax.swing.JRadioButton FirstNameConditional;
    private javax.swing.JTextArea FirstNameTextArea;
    private javax.swing.JRadioButton ID;
    private javax.swing.JComboBox IDCombo;
    private javax.swing.JRadioButton IDConditional;
    private javax.swing.JTextArea IDTextArea;
    private javax.swing.JRadioButton InsertButton;
    private javax.swing.JRadioButton JobID;
    private javax.swing.JComboBox JobIDCombo;
    private javax.swing.JRadioButton JobIDConditional;
    private javax.swing.JTextArea JobIDTextArea;
    private javax.swing.JRadioButton LastName;
    private javax.swing.JComboBox LastNameCombo;
    private javax.swing.JRadioButton LastNameConditional;
    private javax.swing.JTextArea LastNameTextArea;
    private javax.swing.JRadioButton ManagerID;
    private javax.swing.JComboBox ManagerIDCombo;
    private javax.swing.JRadioButton ManagerIDConditional;
    private javax.swing.JTextArea ManagerIDTextArea;
    private javax.swing.JRadioButton PhoneNumber;
    private javax.swing.JComboBox PhoneNumberCombo;
    private javax.swing.JRadioButton PhoneNumberConditional;
    private javax.swing.JTextArea PhoneNumberTextArea;
    private javax.swing.JRadioButton Salary;
    private javax.swing.JComboBox SalaryCombo;
    private javax.swing.JRadioButton SalaryConditional;
    private javax.swing.JTextArea SalaryTextArea;
    private javax.swing.JRadioButton SelectButton;
    private javax.swing.JRadioButton UpdateButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
    private javax.swing.ButtonGroup buttonGroup12;
    private javax.swing.ButtonGroup buttonGroup13;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

}
