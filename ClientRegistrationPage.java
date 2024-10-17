import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.*;

public class ClientRegistrationPage {
    public JPanel Main;
    private JTextField textLastName;
    private JTextField textFirstName;
    private JTextField textEmail;
    private JTextField textPhoneNumber;
    private JButton saveButton;
    private JTable table;
    private JButton updateButton;
    private JButton deleteButton;
    private JScrollPane clients_table;
    private JButton searchButton;
    private JTextField textID;
    private JLabel TitleLabel;

    private PreparedStatement prepareStatement;

    private String tableName= "clients";

    public ClientRegistrationPage() {
        main.table_load(tableName, table);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String last_name, first_name, email, phone_number;

                last_name=textLastName.getText();
                first_name=textFirstName.getText();
                email=textEmail.getText();
                phone_number=textPhoneNumber.getText();

                try{
                    prepareStatement = main.getConnection().prepareStatement("insert into clients(ID,last_name,first_name,email,phone_number)values(null,?,?,?,?)");
                    prepareStatement.setString(1,last_name);
                    prepareStatement.setString(2,first_name);
                    prepareStatement.setString(3,email);
                    prepareStatement.setString(4,phone_number);

                    prepareStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Added!");
                    main.table_load(tableName, table);

                    textLastName.setText("");
                    textFirstName.setText("");
                    textEmail.setText("");
                    textPhoneNumber.setText("");

                    textLastName.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    String id=textID.getText();

                    prepareStatement = main.getConnection().prepareStatement("select last_name, first_name, email, phone_number from clients where id = ?");
                    prepareStatement.setString(1,id);
                    ResultSet resultSet= prepareStatement.executeQuery();

                    if(resultSet.next()==true){
                        String searched_last_name=resultSet.getString(1);
                        String searched_first_name = resultSet.getString(2);
                        String searched_email = resultSet.getString(3);
                        String searched_phone_number = resultSet.getString(4);

                        textLastName.setText(searched_last_name);
                        textFirstName.setText(searched_first_name);
                        textEmail.setText(searched_email);
                        textPhoneNumber.setText(searched_phone_number);
                    }else{
                        textLastName.setText("");
                        textFirstName.setText("");
                        textEmail.setText("");
                        textPhoneNumber.setText("");

                        JOptionPane.showMessageDialog(null,"Invalid client ID!");
                    }
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id,last_name, first_name, email, phone_number;

                last_name=textLastName.getText();
                first_name=textFirstName.getText();
                email=textEmail.getText();
                phone_number=textPhoneNumber.getText();
                id=textID.getText();

                try{
                    prepareStatement = main.getConnection().prepareStatement("update clients set last_name = ?, first_name = ?, email = ?, phone_number = ? where id = ?");
                    prepareStatement.setString(1,last_name);
                    prepareStatement.setString(2,first_name);
                    prepareStatement.setString(3,email);
                    prepareStatement.setString(4,phone_number);
                    prepareStatement.setString(5,id);

                    prepareStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record has been updated!");
                    main.table_load(tableName, table);

                    textLastName.setText("");
                    textFirstName.setText("");
                    textEmail.setText("");
                    textPhoneNumber.setText("");
                    textID.setText("");

                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id;

                id=textID.getText();

                try{
                    prepareStatement = main.getConnection().prepareStatement("delete from clients where id = ?");

                    prepareStatement.setString(1,id);

                    prepareStatement.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Record with id = "+id+" has been deleted!");
                    main.table_load(tableName, table);

                    textLastName.setText("");
                    textFirstName.setText("");
                    textEmail.setText("");
                    textPhoneNumber.setText("");
                    textID.setText("");

                } catch (MySQLIntegrityConstraintViolationException ex){
                    JOptionPane.showMessageDialog(null, "Acest client a avut cel putin o inchiriere! Nu poate fi sters! Foreign key constraint!");
                    textLastName.setText("");
                    textFirstName.setText("");
                    textEmail.setText("");
                    textPhoneNumber.setText("");
                    textID.setText("");
                } catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
    }

    public JTextField getTextLastName() {
        return textLastName;
    }

    public void setTextLastName(JTextField textLastName) {
        this.textLastName = textLastName;
    }

    public JTextField getTextFirstName() {
        return textFirstName;
    }

    public void setTextFirstName(JTextField textFirstName) {
        this.textFirstName = textFirstName;
    }

    public JTextField getTextEmail() {
        return textEmail;
    }

    public void setTextEmail(JTextField textEmail) {
        this.textEmail = textEmail;
    }

    public JTextField getTextPhoneNumber() {
        return textPhoneNumber;
    }

    public void setTextPhoneNumber(JTextField textPhoneNumber) {
        this.textPhoneNumber = textPhoneNumber;
    }
}
