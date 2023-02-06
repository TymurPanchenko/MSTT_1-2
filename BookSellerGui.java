import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BookSellerGui extends JFrame {
    private BookSellerAgent myAgent;

    private JTextField titleField, priceField;

    BookSellerGui(BookSellerAgent agent) {
        super(agent.getLocalName());

        myAgent = agent;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Title:"));
        titleField = new JTextField(15);
        panel.add(titleField);
        panel.add(new JLabel("Book price:"));
        priceField = new JTextField(15);
        panel.add(priceField);
        getContentPane().add(panel, BorderLayout.CENTER);

        JButton addButton = new JButton("Put");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String title = titleField.getText().trim();
                    String price = priceField.getText().trim();
                    myAgent.updateCatalogue(title, Integer.parseInt(price));
                    titleField.setText("");
                    priceField.setText("");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(BookSellerGui.this, "Invalid values. " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel = new JPanel();
        panel.add(addButton);
        getContentPane().add(panel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        });

        setResizable(false);
    }

    public void show() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        super.show();
    }
}
