package financeProject;

public class Expenses {
    private String nameOfExpense;
    private int amount;
    private boolean active;
    private String user;

    public String getNameOfExpense() {
        return nameOfExpense;
    }

    public void setNameOfExpense(String nameOfExpense) {
        this.nameOfExpense = nameOfExpense;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Expenses(String nameOfExpense, int amount, boolean active, String user) {
        this.nameOfExpense = nameOfExpense;
        this.amount = amount;
        this.active = active;
        this.user = user;
    }

    public Expenses() {
    }

    @Override
    public String toString() {
        return user + " " + nameOfExpense + " " + amount;
    }
}
