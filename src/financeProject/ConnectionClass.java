package financeProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConnectionClass {
    public Connection connect() throws SQLException {
        String url = "jdbc:sqlite:/Users/zhakhongir/dataBases/financingDatabase.db";
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }

    public void insertNewUser(String userName, int balance, boolean active) {
        String sql = "INSERT INTO user(username, balance, active) VALUES(?,?,?)";
        int checkTrueFalse;
        try {
            Connection conn = this.connect();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            pstm.setInt(2, balance);
            if (active == true) {
                checkTrueFalse = 1;
            } else {
                checkTrueFalse = 0;
            }
            pstm.setInt(3, checkTrueFalse);
            pstm.executeUpdate();
            conn.close();


        } catch (Exception e) {

        }
    }

    public void insertNewExpense(String expenseName, int amount, boolean active, String nameOfUser) {
        String sql = "INSERT INTO Expenses(name_of_expense,amount,active,username_user) VALUES(?,?,?,?)";
        int checkTrueOrFalse;
        try {
            Connection conn = this.connect();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, expenseName);
            pstm.setInt(2, amount);
            if (active == true) {
                checkTrueOrFalse = 1;
            } else {
                checkTrueOrFalse = 0;
            }
            pstm.setInt(3, checkTrueOrFalse);
            pstm.setString(4, nameOfUser);
            pstm.executeUpdate();
            conn.close();


        } catch (Exception e) {

        }

    }

    public HashMap<String, User> selectUser() {
        String sql = "SELECT * FROM user";
        HashMap<String, User> hashMap = new HashMap<>();

        try {
            Connection conn = this.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                User usertmp = new User();
                usertmp.setName(rs.getString(1));
                usertmp.setBalance(rs.getInt(2));
                int tmp = rs.getInt(3);
                if (tmp == 1) {
                    usertmp.setActive(true);
                } else {
                    usertmp.setActive(false);
                }
                hashMap.put(usertmp.getName(), usertmp);
                //System.out.println(usertmp);
            }


        } catch (Exception e) {

        }
        return hashMap;
    }

    public void updateUser(String name, int balance, boolean active) {
        String sql = "UPDATE user SET balance = ?, active = ? WHERE username = ?";
        try {
            Connection conn = this.connect();
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, balance);
            int tmp;
            if (active == true) {
                tmp = 1;
            } else {
                tmp = 0;
            }
            pstm.setInt(2, tmp);
            pstm.setString(3, name);
            pstm.executeUpdate();
            conn.close();


        } catch (Exception e) {

        }

    }

    public ArrayList<Expenses> expenses() {
        ArrayList<Expenses> expensesList = new ArrayList<>();
        String sql = "SELECT * FROM Expenses";
        try {
            Connection conn = this.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Expenses usertmp = new Expenses();
                usertmp.setUser(rs.getString(5));
                usertmp.setNameOfExpense(rs.getString(2));
                usertmp.setAmount(rs.getInt(3));
                usertmp.setActive(true);
                expensesList.add(usertmp);
            }


        } catch (Exception e) {

        }


        return expensesList;
    }

   /* public void deleteLastExpense(int a){
        String sql = "DELETE FROM Expenses WHERE active = 0";
        Expenses ex = new Expenses();
        *//*int tmp = 0;
        if(active==true){
            tmp = 1;
        }else{
            tmp = 0;
        }*//*

        try{
            Connection conn = this.connect();
            PreparedStatement pstm = conn.prepareStatement(sql);

        }catch (Exception e){

        }

    }*/


}
