package financeProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
    private String name;
    private int balance;
    private boolean active;

    public User() {

    }

    public User(String name, int balance, boolean active) {
        this.name = name;
        this.balance = balance;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", active=" + active +
                '}';
    }

    public static void creatingNewStudentINTheDB() {
        ConnectionClass conn = new ConnectionClass();
        User user = new User();
        System.out.println("Enter the name of new user: ");
        Scanner in = new Scanner(System.in);
        user.setName(in.next());
        System.out.println("Enter the balance the user has: ");
        user.setBalance(in.nextInt());
        System.out.println("Is he/she active or not? true/false ");
        user.setActive(in.nextBoolean());
        conn.insertNewUser(user.getName(), user.getBalance(), user.isActive());

    }

    public static void getAction() {
        System.out.println("Welcome to our Application! Enter the name of your account");
        ConnectionClass conn = new ConnectionClass();
        HashMap<String, User> hashMap = conn.selectUser();
        User actionUser = User.getUserFromHashMap(hashMap);
        System.out.println("What do you want to do? 1 - make a transaction" + "\t" + "2 - top up the balance" + "\t" + "3 - View all the expenses" + " 4 - Show balance");
        Scanner in = new Scanner(System.in);
        int switcher = in.nextInt();

            switch (switcher) {
                case 1:
                    actionUser = User.transaction(actionUser);
                    break;
                case 2:
                    actionUser = User.topUpBalance(actionUser);
                    break;
                case 3:
                    ArrayList<Expenses> exp = new ArrayList<>();
                    exp = User.viewAllExpenses(actionUser);
                    break;
                case 4:
                    System.out.println("Your balance is " + actionUser.getBalance());
                    break;

                default:
                    getAction();
            }

    }

    public static User getUserFromHashMap(HashMap<String, User> hashMap) {
        User user = new User();
        Scanner in = new Scanner(System.in);
        System.out.println("Login please: ");
        String nameTmp = in.nextLine();
        if (hashMap.keySet().contains(nameTmp)) {
            user = hashMap.get(nameTmp);
            System.out.println("SUCCESS");
        } else if (!hashMap.keySet().contains(nameTmp)) {
            System.out.println("We don't have this user! Create a new one now!");
            ConnectionClass conn = new ConnectionClass();
            System.out.println("Enter your unique name: ");
            user.setName(in.next());
            System.out.println("Enter your balance: ");
            user.setBalance(in.nextInt());
            user.setActive(true);
            hashMap.put(user.getName(), user);
            conn.insertNewUser(user.getName(), user.getBalance(), user.isActive());
            getUserFromHashMap(hashMap);
        }
        return user;

    }

    public static User transaction(User user) throws InputMismatchException {
        ConnectionClass conn = new ConnectionClass();
        Scanner in = new Scanner(System.in);
        int money = user.getBalance();
        System.out.println("Input the direction of transaction: ");
        String expenseName = in.nextLine();
        System.out.println("How much money do you want to send?");

        int amount = in.nextInt();
        try {
            if (amount > money) {
                System.out.println("Not enough money! Try again!");
                return user;
            }
        } catch (Exception e) {
            System.out.println("Not enough money! Try again!");
        }
        user.setActive(true);

        int userBalanceResult = user.getBalance() - amount;
        user.setBalance(userBalanceResult);


        conn.updateUser(user.getName(), user.getBalance(), user.isActive());
        conn.insertNewExpense(expenseName, amount, user.isActive(), user.getName());

        return user;
    }

    public static User topUpBalance(User user) {
        ConnectionClass conn = new ConnectionClass();
        System.out.println("You have " + user.getBalance() + " money in your account, top it up!");
        Scanner in = new Scanner(System.in);
        System.out.println("How much money do you want to put?");
        int money = in.nextInt();
        money = user.getBalance() + money;
        user.setBalance(money);
        conn.updateUser(user.getName(), user.getBalance(), user.isActive());
        return user;
    }

    public static ArrayList<Expenses> viewAllExpenses(User user) {
        ConnectionClass conn = new ConnectionClass();
        ArrayList<Expenses> exp = conn.expenses();
        ArrayList<Expenses> users = new ArrayList<>();
        for (Expenses e : exp) {
            if (e.getUser().equals(user.getName())) {
                users.add(e);
            }
        }
        for (Expenses a : users) {
            System.out.println(a);
        }
        System.out.println("Do you want to see a sum of your expenses? y/Y - yes, n/N - no");
        Scanner in = new Scanner(System.in);
        String yesOrNo = in.next();
        if (yesOrNo.equals("y") | yesOrNo.equals("Y")) {
            int result = 0;
            for (Expenses a : users) {
                result = a.getAmount() + result;
            }
            System.out.println("Oh you spent so much money bro! " + result);
        } else if (yesOrNo.equals("n") | yesOrNo.equals("N")) {
            System.out.println("Bye Bye! ");
            return users;
        }
        return users;
    }




}
