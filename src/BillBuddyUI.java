import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

public class BillBuddyUI {
    private JFrame frame;
    private JTextArea outputArea;
    private JTextField groupNameField;
    private JTextField userNameField;
    private JTextField expenseTitleField;
    private JTextField expenseAmountField;
    private JComboBox<String> usersComboBox;
    private JComboBox<String> groupsComboBox;  // Combo box to switch groups

    private BillBuddy billBuddy;

    public BillBuddyUI() {
        billBuddy = new BillBuddy();
        frame = new JFrame("Bill Buddy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Increased size for better layout
        frame.setLayout(new GridLayout(7, 1)); // Changed grid layout to 7 rows

        // Group name and group switch
        JPanel groupPanel = new JPanel();
        groupNameField = new JTextField(15);
        JButton createGroupButton = new JButton("Create Group");
        createGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = groupNameField.getText();
                billBuddy.createGroup(groupName);
                groupNameField.setText("");
                updateGroupComboBox();
                displayMessage("Group created: " + groupName);
            }
        });
        groupPanel.add(new JLabel("Group Name:"));
        groupPanel.add(groupNameField);
        groupPanel.add(createGroupButton);

        // Group switch combo box
        groupsComboBox = new JComboBox<>();
        groupsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserComboBox();  // Update users when a group is switched
            }
        });
        groupPanel.add(new JLabel("Switch Group:"));
        groupPanel.add(groupsComboBox);
        frame.add(groupPanel);

        // User name and edit option
        JPanel userPanel = new JPanel();
        userNameField = new JTextField(15);
        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                String groupName = (String) groupsComboBox.getSelectedItem();
                billBuddy.addUserToGroup(groupName, userName);
                userNameField.setText("");
                updateUserComboBox();
                displayMessage("User added: " + userName + " to group: " + groupName);
            }
        });

        // Add user editing functionality
        JButton editUserButton = new JButton("Edit User");
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldUserName = (String) usersComboBox.getSelectedItem();
                String newUserName = userNameField.getText();
                String groupName = (String) groupsComboBox.getSelectedItem();
                if (oldUserName != null && !newUserName.isEmpty()) {
                    billBuddy.editUserInGroup(groupName, oldUserName, newUserName);
                    updateUserComboBox();
                    displayMessage("User " + oldUserName + " edited to: " + newUserName);
                } else {
                    displayMessage("No user selected or new user name is empty.");
                }
            }
        });

        userPanel.add(new JLabel("User Name:"));
        userPanel.add(userNameField);
        userPanel.add(addUserButton);
        userPanel.add(editUserButton);
        frame.add(userPanel);

        // Expense
        JPanel expensePanel = new JPanel();
        expenseTitleField = new JTextField(15);
        expenseAmountField = new JTextField(5);
        usersComboBox = new JComboBox<>();
        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = (String) groupsComboBox.getSelectedItem();
                String title = expenseTitleField.getText();
                double amount;
                try {
                    amount = Double.parseDouble(expenseAmountField.getText());
                } catch (NumberFormatException ex) {
                    displayMessage("Please enter a valid amount.");
                    return;
                }
                String paidBy = (String) usersComboBox.getSelectedItem();
                if (paidBy != null) {
                    billBuddy.addExpense(groupName, title, amount, paidBy);
                    displayMessage("Expense added: " + title + " - " + formatCurrency(amount) + " (Paid by: " + paidBy + ")");
                } else {
                    displayMessage("No user selected for 'Paid By'.");
                }
            }
        });
        expensePanel.add(new JLabel("Expense Title:"));
        expensePanel.add(expenseTitleField);
        expensePanel.add(new JLabel("Amount (INR):"));
        expensePanel.add(expenseAmountField);
        expensePanel.add(new JLabel("Paid By:"));
        expensePanel.add(usersComboBox);
        expensePanel.add(addExpenseButton);
        frame.add(expensePanel);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea));

        // Show expenses
        JButton showExpensesButton = new JButton("Show Expenses");
        showExpensesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = (String) groupsComboBox.getSelectedItem();
                String expenses = billBuddy.showExpenses(groupName);
                outputArea.setText(expenses);
            }
        });
        frame.add(showExpensesButton);

        // Distribute expenses
        JButton distributeExpensesButton = new JButton("Distribute Expenses");
        distributeExpensesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = (String) groupsComboBox.getSelectedItem();
                String distribution = billBuddy.distributeExpenses(groupName);
                outputArea.setText(distribution);
            }
        });
        frame.add(distributeExpensesButton);

        frame.setVisible(true);
        updateGroupComboBox();  // Initialize groups in combo box
    }

    // Method to update group combo box when new groups are added
    private void updateGroupComboBox() {
        groupsComboBox.removeAllItems();
        for (String group : billBuddy.getGroups()) {
            groupsComboBox.addItem(group);
        }
        updateUserComboBox();  // Also update users when group is changed
    }

    // Method to update users in the combo box for the selected group
    private void updateUserComboBox() {
        usersComboBox.removeAllItems();
        String groupName = (String) groupsComboBox.getSelectedItem();
        if (groupName != null) {
            for (String user : billBuddy.getUsersInGroup(groupName)) {
                usersComboBox.addItem(user);
            }
        }
    }

    private void displayMessage(String message) {
        outputArea.append(message + "\n");
    }

    private String formatCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("en-IN"));
        return formatter.format(amount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BillBuddyUI();
            }
        });
    }
}
