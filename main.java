import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class main {
    public static JFrame Startframe;
    private static Connection connection;
    private static PreparedStatement prepareStatement;

    public static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bikerental", "root", "");
            System.out.println("Successfully connected to the database!");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static void table_load(String name, JTable table) {
        try {
            prepareStatement = connection.prepareStatement("select * from " + name);
            ResultSet resultSet = prepareStatement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connect();

        Startframe = new JFrame("Start Page");
        Startframe.setPreferredSize(new Dimension(560, 200));
        Startframe.setContentPane(new StartPage().Main);
        Startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Startframe.pack();
        Startframe.setVisible(true);
    }

    public static Connection getConnection() {
        return connection;
    }
}