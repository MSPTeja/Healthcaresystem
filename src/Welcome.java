import com.sun.jdi.PathSearchingVirtualMachine;

import java.sql.*;
import java.util.*;
public class Welcome {
    public static void  main(String[] args) throws SQLException {
        System.out.println("Welcome to Health Application");
        System.out.println("Please enter  existing User or new User");
        System.out.println("0-Existing user 1-new User");
        Scanner sc = new Scanner(System.in);
        var input = sc.nextInt();
        if(input == 1)
        {
            Reg myForm = new Reg(null);
            User user = myForm.user;
            if (user != null) {
                System.out.println("Successful registration of: " + user.Name + " "+ user.Gender);
            }
            else
                System.out.println("Registration canceled");
        }
        if(input == 0) {
            System.out.println("Please enter your cardId");
            String CardId = sc.next();
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
            String s = "Select * from User.user where CardId = CardId ";
            ResultSet rs = stmt.executeQuery(s);
            rs.next();
            String dis = rs.getString("Diseases");
            String grp = rs.getString("Bloodgroup");
            String med = rs.getString("Medicines");
           // System.out.println(rs.getString("Name") + rs.getString("Diseases"));
            System.out.println("previous diseases :" + " " + dis);
            System.out.println("Bloodgroup is:" + " " + grp);
            System.out.println("Currently using Medicines:" + " "+ med);
          stmt.close();;
          conn.close();


        }

    }
}
