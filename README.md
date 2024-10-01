---

# BillBuddy - Bill Splitting System

BillBuddy is a simple Java-based bill-splitting application that helps users manage and split expenses among group members. The app allows users to create groups, add and remove members, track expenses, and automatically calculate the amount each member owes.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Installation and Setup](#installation-and-setup)
- [How to Run](#how-to-run)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features
- Create and manage groups.
- Add and remove users from groups.
- simultaneously calculate expenses for multiple groups.
- get which user owes which user and by how much.
- Add and track expenses for a group.
- Automatically distribute expenses among users.
- Simple and user-friendly command-line interface.

## Project Structure

```bash
BillBuddy-v1.0.0/
├── src/
│   ├── BillBuddy.java         # Main logic for handling groups and expenses
│   ├── BillBuddyUI.java       # User interface for interacting with BillBuddy
│   ├── Group.java             # Group management including users and expenses
│   ├── Expense.java           # Class to represent expenses
│   ├── User.java              # Class representing a user in a group
├── README.md                  # Project documentation
└── .gitignore                 # Ignored files for Git
```

## Installation and Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/SarveshM213/BillBuddy-v1.0.0.git
   ```
2. **Navigate to the project directory:**
   ```bash
   cd src
   ```

3. **Ensure you have Java installed:**
   - Install [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) if it's not installed.

4. **Open the project in your IDE (like VS Code or IntelliJ):**
   - If using VS Code, ensure the **Java Extension Pack** is installed for easy project management.

## How to Run

To compile and run the application, follow these steps:

1. **Compile the project:**
   ```bash
   javac *.java
   ```

2. **Run the application:**
   ```bash
   java BillBuddyUI
   ```

Once the program starts, you'll be prompted to enter your choices for creating groups, adding/removing users, and managing expenses.

## Usage

### Creating a Group
1. Enter the group name to create a new group.
   
### Adding Users to a Group
1. Enter the group name.
2. Enter the username to add the user to that group.

### Removing Users from a Group
1. Enter the group name.
2. Enter the username to remove the user from the group.

### Adding Expenses
1. Enter the group name.
2. Enter the expense details (description, amount, payer).
3. The app will automatically distribute the expense among group members.

### Viewing Group Balances
1. The app will display how much each user owes or is owed.

## Contributing

Feel free to fork this project, submit issues, and contribute via pull requests!

To contribute:
1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Feel free to customize it further depending on your project structure and specific use cases!
