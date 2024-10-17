import com.mysql.jdbc.MysqlDataTruncation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RentBikesPage {
    public JPanel Main;
    private JTable table;
    private JScrollPane rentalsTable;
    private JLabel TitleLabel;
    private JTextField textClientID;
    private JTextField textBikeID;
    private JTextField textRentalDate;
    private JTextField textReturnDate;
    private JButton saveNewRentalButton;
    private JButton updateRentalButton;
    private JButton searchButton;
    private JTextField textID;
    private JButton deleteRentalButton;
    private JTextPane a24DirtBike29TextPane;
    private JTextPane a23DirtBike29TextPane;
    private JTextPane a32CityBike29TextPane;
    private JTextPane a31CityBike29TextPane;
    private JTextPane a30CityBike27TextPane;
    private JTextPane a29CityBike27TextPane;
    private JTextPane a28CityBike26TextPane;
    private JTextPane a27CityBike26TextPane;
    private JTextPane a26CityBike24TextPane;
    private JTextPane a25CityBike24TextPane;
    private JTextPane a22DirtBike27TextPane;
    private JTextPane a21DirtBike27TextPane;
    private JTextPane a20DirtBike26TextPane;
    private JTextPane a19DirtBike26TextPane;
    private JTextPane a18DirtBike24TextPane;
    private JTextPane a17DirtBike24TextPane;
    private JTextPane a16ElectricBike29TextPane;
    private JTextPane a15ElectricBike29TextPane;
    private JTextPane a14ElectricBike27TextPane;
    private JTextPane a13ElectricBike27TextPane;
    private JTextPane a12ElectricBike26TextPane;
    private JTextPane a11ElectricBike26TextPane;
    private JTextPane a10ElectricBike24TextPane;
    private JTextPane a9ElectricBike24TextPane;
    private JTextPane a8MountainBike29TextPane;
    private JTextPane a7MountainBike29TextPane;
    private JTextPane a6MountainBike27TextPane;
    private JTextPane a5MountainBike27TextPane;
    private JTextPane a4MountainBike26TextPane;
    private JTextPane a3MountainBike26TextPane;
    private JTextPane a2MountainBike24TextPane;
    private JTextPane a1MountainBike24TextPane;

    private PreparedStatement prepareStatement;

    private String tableName="rentals";

    public RentBikesPage(){
        main.table_load(tableName,table);
        saveNewRentalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String client_id, bike_id, rental_date="", return_date="";

                client_id= textClientID.getText();
                bike_id=textBikeID.getText();

                if(textRentalDate.getText().length()!=10 || textRentalDate.getText().charAt(4)!='-' || textRentalDate.getText().charAt(7)!='-') {
                    JOptionPane.showMessageDialog(null, "Invalid Rental Date!");
                    client_id="";
                    bike_id="";
                    textClientID.setText("");
                    textBikeID.setText("");
                    textRentalDate.setText("");
                    textReturnDate.setText("");
                }else{
                    rental_date = textRentalDate.getText();//este o data valida
                }

                if(textReturnDate.getText()!=null) {//am introdus o data
                    if (textReturnDate.getText().length() != 10 || textReturnDate.getText().charAt(4) != '-' || textReturnDate.getText().charAt(7) != '-') {
                        JOptionPane.showMessageDialog(null, "Invalid Return Date!");
                        client_id = "";
                        bike_id = "";
                        textClientID.setText("");
                        textBikeID.setText("");
                        textRentalDate.setText("");
                        textReturnDate.setText("");
                    } else {
                        return_date = textReturnDate.getText();//este o data valida
                    }

                    //verific ca data de retur sa fie mai mare decat cea de inchiriere
                    LocalDate inchiriere=LocalDate.parse(rental_date);
                    LocalDate returnare=LocalDate.parse(return_date);
                    if(returnare.getYear()<inchiriere.getYear()){
                        JOptionPane.showMessageDialog(null, "The Return Date cannot be lower than the Rental Date!");
                        client_id = "";
                        bike_id = "";
                        textClientID.setText("");
                        textBikeID.setText("");
                        textRentalDate.setText("");
                        textReturnDate.setText("");
                    }
                    if(returnare.getMonthValue()<inchiriere.getMonthValue() && returnare.getYear()==inchiriere.getYear()){
                        JOptionPane.showMessageDialog(null, "The Return Date cannot be lower than the Rental Date!");
                        client_id = "";
                        bike_id = "";
                        textClientID.setText("");
                        textBikeID.setText("");
                        textRentalDate.setText("");
                        textReturnDate.setText("");
                    }
                    if(returnare.getMonthValue()==inchiriere.getMonthValue() && returnare.getDayOfMonth()<inchiriere.getDayOfMonth()){
                        JOptionPane.showMessageDialog(null, "The Return Date cannot be lower than the Rental Date!");
                        client_id = "";
                        bike_id = "";
                        textClientID.setText("");
                        textBikeID.setText("");
                        textRentalDate.setText("");
                        textReturnDate.setText("");
                    }
                }
                try{
                    prepareStatement = main.getConnection().prepareStatement("insert into rentals(ID,client,bike,rental_date,return_date)values(null,?,?,?,?)");
                    prepareStatement.setString(1,client_id);
                    prepareStatement.setString(2,bike_id);
                    prepareStatement.setString(3,rental_date);
                    prepareStatement.setString(4,return_date);

                    prepareStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Added!");
                    main.table_load(tableName, table);

                    textClientID.setText("");
                    textBikeID.setText("");
                    textRentalDate.setText("");
                    textReturnDate.setText("");

                    textClientID.requestFocus();
                }catch (MysqlDataTruncation ex){
                    ex.printStackTrace();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String id=textID.getText();

                    prepareStatement = main.getConnection().prepareStatement("select client,bike,rental_date,return_date from rentals where id = ?");
                    prepareStatement.setString(1,id);
                    ResultSet resultSet= prepareStatement.executeQuery();

                    if(resultSet.next()==true){
                        String searched_client_id=resultSet.getString(1);
                        String searched_bik_id = resultSet.getString(2);
                        String searched_rental_date = resultSet.getString(3);
                        String searched_return_date = resultSet.getString(4);

                        textClientID.setText(searched_client_id);
                        textBikeID.setText(searched_bik_id);
                        textRentalDate.setText(searched_rental_date);
                        textReturnDate.setText(searched_return_date);
                    }else{
                        textClientID.setText("");
                        textBikeID.setText("");
                        textRentalDate.setText("");
                        textReturnDate.setText("");

                        JOptionPane.showMessageDialog(null,"Invalid rental ID!");
                    }
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
    }
}
