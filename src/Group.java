import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Group {
    private String groupName;
    private List<User> users;
    private List<Expense> expenses;

    public Group(String groupName) {
        this.groupName = groupName;
        this.users = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<String> getUserNames() {
        List<String> userNames = new ArrayList<>();
        for (User user : users) {
            userNames.add(user.getName());
        }
        return userNames;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public String showExpenses() {
        StringBuilder result = new StringBuilder("Expenses for group: " + groupName + "\n");
        for (Expense expense : expenses) {
            result.append(expense.getTitle()).append(" - ")
                  .append(expense.getAmount()).append(" paid by ")
                  .append(expense.getPaidBy()).append("\n");
        }
        return result.toString();
    }

    public String distributeExpenses() {
        if (expenses.isEmpty()) {
            return "No expenses to distribute.";
        }

        double totalExpenses = 0;
        Map<String, Double> userPayments = new HashMap<>();
        for (Expense expense : expenses) {
            totalExpenses += expense.getAmount();
            userPayments.put(expense.getPaidBy(), userPayments.getOrDefault(expense.getPaidBy(), 0.0) + expense.getAmount());
        }

        double amountPerUser = totalExpenses / users.size();
        Map<String, Double> userBalances = new HashMap<>();
        for (User user : users) {
            String userName = user.getName();
            double paid = userPayments.getOrDefault(userName, 0.0);
            double balance = paid - amountPerUser;
            userBalances.put(userName, balance);
        }

        StringBuilder distributionResult = new StringBuilder();
        distributionResult.append("Expense distribution for group: ").append(groupName).append("\n");
        for (Map.Entry<String, Double> entry : userBalances.entrySet()) {
            String userName = entry.getKey();
            double balance = entry.getValue();
            if (balance > 0) {
                distributionResult.append(userName).append(" is owed ").append(balance).append("\n");
            } else if (balance < 0) {
                distributionResult.append(userName).append(" owes ").append(-balance).append("\n");
            } else {
                distributionResult.append(userName).append(" is settled.\n");
            }
        }

        return distributionResult.toString();
    }

    public void editUser(String oldUserName, String newUserName) {
        for (User user : users) {
            if (user.getName().equals(oldUserName)) {
                user.setName(newUserName);
                break;
            }
        }
    }
}
