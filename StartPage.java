import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage {
    private JTextField bikeRentalRentYourTextField;
    private JButton viewBikesButton;
    private JButton createAnAcountButton;
    private JButton rentABikeButton;
    JPanel Main;

    public StartPage() {
    viewBikesButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame viewBikesFrame = new JFrame("View Bikes");
            viewBikesFrame.setPreferredSize(new Dimension(1800, 600));
            viewBikesFrame.setContentPane(new ViewBikesPage().Main);
            viewBikesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            viewBikesFrame.pack();
            viewBikesFrame.setVisible(true);

        }
    });
    createAnAcountButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame createAccountFrame = new JFrame("Client Registration");
            createAccountFrame.setPreferredSize(new Dimension(1800, 800));
            createAccountFrame.setContentPane(new ClientRegistrationPage().Main);
            createAccountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            createAccountFrame.pack();
            createAccountFrame.setVisible(true);
        }
    });
    rentABikeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame rentBikesFrame = new JFrame("Rent Bikes");
            rentBikesFrame.setPreferredSize(new Dimension(2000, 1500));
            rentBikesFrame.setContentPane(new RentBikesPage().Main);
            rentBikesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            rentBikesFrame.pack();
            rentBikesFrame.setVisible(true);
        }
    });
}
}
