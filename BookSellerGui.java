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
        panel.add(new JLabel("Book title:"));
        titleField = new JTextField(15);
        panel.add(titleField);
        panel.add(new JLabel("Price:"));
        priceField = new JTextField(15);
        panel.add(priceField);
        getContentPane().add(panel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
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
            public void windowClosing(WindowEvent event) {
                myAgent.doDelete();
            }
        });

        setResizable(false);
    }

    public void show() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 3;
        int centerY = (int) screenSize.getHeight() / 3;
        setLocation(centerX - getWidth() / 3, centerY - getHeight() / 3);
        super.show();
    }
}
