package Change_pin_package;
import withdraw_package.withdraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Change_pin extends Component {
    public Change_pin(){
        JFrame j = new JFrame("WELCOME");
        j.setSize(1200, 800);
        j.setLayout(new BorderLayout(0,0));

        String url="jdbc:mysql://localhost:3306";
        String uname="root";
        String paswd="5361";
        String databaseName="bank";

        JPanel upperpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon icon = new ImageIcon("C:\\\\Users\\\\Tejaswini\\\\Desktop\\\\yashu\\\\images\\\\bank1.png");




        JLabel l1 = new JLabel(icon);
        JLabel l2 = new JLabel("BANK OF PEOPLE");

        upperpanel.setBounds(0,0,580,30);
        l2.setFont(new Font("Arial", Font.BOLD, 31));
        upperpanel.setBounds(0,0,1200,200);
        upperpanel.setBackground(Color.gray);

        l2.setPreferredSize(new Dimension(280,30));
        l1.setPreferredSize(new Dimension(200,200));
        upperpanel.add(l1);
        upperpanel.add(l2);

        JPanel lowerpanel = new JPanel(new GridLayout(5,3,10,30));
        lowerpanel.setBounds(0,300,1200,600);
        JLabel l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
        l3 = new JLabel("");
        l4 = new JLabel("CHANGE YOUR PIN",JLabel.CENTER);
        l4.setFont(new Font("Arial", Font.BOLD, 24));
        l5 = new JLabel("");
        l6 = new JLabel("");
        l7 = new JLabel("");
        l8 = new JLabel("");
        l9 = new JLabel("");
        l10 = new JLabel("ENTER THE OLD PIN",JLabel.CENTER);
        l11=new JLabel("ENTER THE NEW PIN",JLabel.CENTER);
        l12=new JLabel("CONFIRM THE NEW PIN",JLabel.CENTER);
        l10.setFont(new Font("Arial", Font.BOLD, 16));
        l11.setFont(new Font("Arial", Font.BOLD, 16));
        l12.setFont(new Font("Arial", Font.BOLD, 16));

        lowerpanel.setBackground(Color.LIGHT_GRAY);
        JButton b2,b4;

        JTextField t1,t2,t3;
        t1= new JTextField();
        t1.setHorizontalAlignment(JTextField.CENTER);
        t1.setFont(new Font("Arial", Font.BOLD, 18));
        t2= new JTextField();
        t2.setHorizontalAlignment(JTextField.CENTER);
        t2.setFont(new Font("Arial", Font.BOLD, 18));
        t3= new JTextField();
        t3.setHorizontalAlignment(JTextField.CENTER);
        t3.setFont(new Font("Arial", Font.BOLD, 18));



        b2= new JButton("CONFIRM");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, paswd);
                    Statement st = con.createStatement();
                    String q1 = "use " + databaseName + ";";
                    String q2 = "select * from users where pin=" + t1.getText() + ";";
                    st.executeUpdate(q1);
                    ResultSet rs = st.executeQuery(q2);
                    rs.next();
                    if(t2.getText().equals(t3.getText())){
                        String q3= "update users set pin="+t2.getText()+" where account="+(Integer)rs.getObject(4)+";";
                        st.executeUpdate(q3);
                        JOptionPane.showMessageDialog(Change_pin.this, "Pin Changed Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(Change_pin.this, "Incorrect pin", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    System.exit(0);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }}
        });

        b4 = new JButton("CANCEL");
        b2.setFont(new Font("Arial", Font.BOLD, 16));
        b4.setFont(new Font("Arial", Font.BOLD, 16));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        lowerpanel.add(l3); lowerpanel.add(l4);lowerpanel.add(l5);lowerpanel.add(l10);lowerpanel.add(t1); lowerpanel.add(b2);lowerpanel.add(l11);lowerpanel.add(t2);lowerpanel.add(b4);
        lowerpanel.add(l12);lowerpanel.add(t3);lowerpanel.add(l9);

        j.add(upperpanel,BorderLayout.NORTH);
        j.add(lowerpanel,BorderLayout.CENTER);



        j.setVisible(true);
        j.setLocationRelativeTo(null);
        j.setResizable(false);
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public static void main(String args[]){
        Change_pin c = new Change_pin();
    }
}