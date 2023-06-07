import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class Reg extends JDialog {
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField3;
    private JComboBox comboBox3;
    private JTextField textField4;
    private JButton submitButton;
    private JTextField REGISTRATIONFORMTextField;
    private JButton cancelButton;
    private JPanel RegistrationPannel;

    public Reg(JFrame parent) {
        super(parent);
        setTitle("Create a new account");
        setContentPane(RegistrationPannel);
        setMinimumSize(new Dimension(550, 574));
        setModal(true);
        setLocationRelativeTo(parent);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registeruser();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registeruser() throws SQLException {
        String Name = textField1.getText();
        String Age = textField2.getText();
        String Gender = (String)comboBox1.getSelectedItem();
        String Bloodgroup = (String)comboBox2.getSelectedItem();
        String CardId = textField3.getText();
        String Diseases = (String)comboBox3.getSelectedItem();
        String Medicines = textField4.getText();

        if (Name.isEmpty() || Age.isEmpty() || Gender.isEmpty() || Bloodgroup.isEmpty() || CardId.isEmpty()
                || Diseases.isEmpty() || Medicines.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addtoDatabase(Name, Age, Gender, Bloodgroup, CardId, Diseases, Medicines);
        if (user != null) {
            	dispose();
            	}
        	else {
            	JOptionPane.showMessageDialog(this,
                    	"Failed to register new user",
                    	"Try again",
                    	JOptionPane.ERROR_MESSAGE);
            }
        	}
        public User user;
        private User addtoDatabase (String Name, String Age, String Gender, String Bloodgroup, String CardId, String
        Diseases, String Medicines) throws SQLException {

            String DB_url = "jdbc:mysql://root@localhost:3306/User";
            final String USERNAME = "root";
            final String PASSWORD = "teja";
            Connection conn;
            try {
                conn = DriverManager.getConnection(DB_url, USERNAME, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO User.user (Name, Age, Gender, Bloodgroup, CardId,Diseases,Medicines) " +
                    "VALUES (?, ?, ?, ?, ?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, Age);
            preparedStatement.setString(3, Gender);
            preparedStatement.setString(4, Bloodgroup);
            preparedStatement.setString(5, CardId);
            preparedStatement.setString(6, Diseases);
            preparedStatement.setString(7, Medicines);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.Name = Name;
                user.Age = Age;
                user.Gender = Gender;
                user.Bloodgroup = Bloodgroup;
                user.CarId = CardId;
                user.Diseases = Diseases;
                user.Medicines = Medicines;
            }
            stmt.close();
            conn.close();
            return user;

        }

//    public static void main(String[] args) {
//        Reg myForm = new Reg(null);
//        User user = myForm.user;
//        if (user != null) {
//            System.out.println("Successful registration of: " + user.Name + user.Gender);
//        }
//        else
//            System.out.println("Registration canceled");
//
//    }
}

