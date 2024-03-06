import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class GUI extends JFrame {
    private JButton button;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label1;
    private JLabel label2;

    public GUI() {
        // Initializing components
        button = new JButton("Submit");
        textField1 = new JTextField(20);
        label1 = new JLabel("Enter your reference code:");
        textField2 = new JTextField(20);
        label2 = new JLabel("Enter your name:");

        // Set up the layout manager
        this.setLayout(new FlowLayout());

        // Create two panels to hold the label and text fields
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Add components to the panel
        panel1.add(label1);
        panel1.add(textField1);
        panel2.add(label2);
        panel2.add(textField2);

        // Set up the main layout manager
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add a panel to the window
        this.add(panel1);
        this.add(panel2);
        this.add(button);

        ConcurrentHashMap<String,Passenger> plist = CheckMachine.readPassenger("PassengerList.csv");
        ConcurrentHashMap<String,Flight> flist = CheckMachine.readFlight("FlightList.csv");
        // Add a button click event
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scanedreferencecode = textField1.getText();
                String scanedname = textField2.getText();
                try {
                    CheckMachine.checkInformation(scanedreferencecode,scanedname,plist,flist);
                } catch (FormatException ex) {

                }
            }
        });

        // Sets the window's properties
        this.setTitle("Airport check-in software");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.pack(); // Automatic Size
        this.setVisible(true); // display window

        // Add a window listener
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Here you add the code you want to execute when the window closes
                performOnClose(flist);

                // Close Windows and applications
                dispose();
                System.exit(0);
            }
        });
    }

    private void performOnClose(ConcurrentHashMap<String,Flight> flist) {
        // Iterate over the values of the ConcurrentHashMap
        System.out.println("\n");

        System.out.println("Flight List:\n");
        for (Flight flight : flist.values()) {
            System.out.println("\n");

            // Call the toString() method on each Flight object
            System.out.println(flight.toString());
        }
    }

    public static void main(String[] args) {
        // Creating GUI instances
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}