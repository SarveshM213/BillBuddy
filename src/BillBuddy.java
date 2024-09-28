import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillBuddy {
    private Map<String, Group> groups;

    public BillBuddy() {
        groups = new HashMap<>();
    }

    public void createGroup(String groupName) {
        if (!groups.containsKey(groupName)) {
            groups.put(groupName, new Group(groupName));
        }
    }

    public void addUserToGroup(String groupName, String userName) {
        Group group = groups.get(groupName);
        if (group != null) {
            group.addUser(new User(userName));
        }
    }

    public List<String> getUsersInGroup(String groupName) {
        Group group = groups.get(groupName);
        if (group != null) {
            return group.getUserNames();
        }
        return new ArrayList<>();
    }

    public void addExpense(String groupName, String title, double amount, String paidBy) {
        Group group = groups.get(groupName);
        if (group != null) {
            group.addExpense(new Expense(title, amount, paidBy));
        }
    }

    public String showExpenses(String groupName) {
        Group group = groups.get(groupName);
        if (group != null) {
            return group.showExpenses();
        }
        return "No expenses found.";
    }

    public String distributeExpenses(String groupName) {
        Group group = groups.get(groupName);
        if (group != null) {
            return group.distributeExpenses();
        }
        return "No group found.";
    }

    // Method to edit a user's name in a group
    public void editUserInGroup(String groupName, String oldUserName, String newUserName) {
        Group group = groups.get(groupName);
        if (group != null) {
            group.editUser(oldUserName, newUserName);
        }
    }

    // Method to get a list of all group names
    public List<String> getGroups() {
        return new ArrayList<>(groups.keySet());
    }
}
