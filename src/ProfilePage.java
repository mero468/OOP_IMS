
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePage implements ActionListener {
    private JLabel Name_L = new JLabel();
    private JLabel Surname_L = new JLabel();
    private JLabel City_L = new JLabel();
    private JLabel Phone_NO_L = new JLabel();
    private JLabel gender_L = new JLabel();
    private JLabel age_L = new JLabel();
    private JFrame Frame;

    public ProfilePage(){

        //Setup The labels
        Name_L.setText("Name :");
        Name_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        Name_L.setBounds(300,50, 140,30);
        Name_L.setForeground(Color.white);

        Surname_L.setText("Surname :");
        Surname_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        Surname_L.setBounds(300,90, 140,30);
        Surname_L.setForeground(Color.white);

        City_L.setText("City :");
        City_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        City_L.setBounds(300,130, 140,30);
        City_L.setForeground(Color.white);

        Phone_NO_L.setText("Phone NO :");
        Phone_NO_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        Phone_NO_L.setBounds(300,170, 140,30);
        Phone_NO_L.setForeground(Color.white);

        gender_L.setText("Gender :");
        gender_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        gender_L.setBounds(300,220, 140,30);
        gender_L.setForeground(Color.white);

        age_L.setText("Age :");
        age_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        age_L.setBounds(300,270, 140,30);
        age_L.setForeground(Color.white);
    }


    //Manager
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //Create a new Frame
        Frame = new JFrame();
        Manager manager = new Manager();
        ImageIcon image = new ImageIcon("LOGO.png");
        Frame.setIconImage(image.getImage());
        JLabel Background=new JLabel(new ImageIcon("9.jpg"));
        Background.setBounds(0,0, 720,469);
        Frame.setDefaultCloseOperation(Frame.DISPOSE_ON_CLOSE);
        Frame.setResizable(false);
        Frame.setSize(720,469);
        Frame.setVisible(true);
        Frame.setLayout(null);
        Frame.setTitle("Manager Profile Page");
        Name_L.setBounds(300,50, 140,30);
        Surname_L.setBounds(300,90, 140,30);
        City_L.setBounds(300,130, 140,30);
        Phone_NO_L.setBounds(300,170, 140,30);
        gender_L.setBounds(300,220, 140,30);
        age_L.setBounds(300,270, 140,30);

        Name_L.setForeground(Color.WHITE);
        Surname_L.setForeground(Color.WHITE);
        City_L.setForeground(Color.WHITE);
        Phone_NO_L.setForeground(Color.WHITE);
        gender_L.setForeground(Color.WHITE);
        age_L.setForeground(Color.WHITE);

        Frame.add(Name_L);
        Frame.add(Surname_L);
        Frame.add(City_L);
        Frame.add(Phone_NO_L);
        Frame.add(gender_L);
        Frame.add(age_L);

        JButton source =(JButton) actionEvent.getSource();
        if( source.getText().equals("SHOW INFO")){
            manager.getinfo();
            manager.showinfo(Frame);
        }
        else if( source.getText().equals("UPDATE INFO")){
            manager.setinfo(Frame);
        }
        Frame.add(Background);

    }

    //supplier
    public void SupplierInfo(ActionEvent e) {
        //Create a new Frame
        Frame = new JFrame();
        Supplier supplier = new Supplier();
        ImageIcon image = new ImageIcon("LOGO.png");
        Frame.setIconImage(image.getImage());
        JLabel Background=new JLabel(new ImageIcon("11.jpg"));
        Background.setBounds(-350,0, 720,469);
        Frame.setDefaultCloseOperation(Frame.DISPOSE_ON_CLOSE);
        Frame.setResizable(false);
        Frame.setSize(720,469);
        Frame.setVisible(true);
        Frame.setLayout(null);
        Frame.setTitle("Supplier Profile Page");
        Name_L.setBounds(400,50, 100,30);
        Surname_L.setBounds(400,90, 100,30);
        City_L.setBounds(400,130, 100,30);
        Phone_NO_L.setBounds(400,170, 100,30);
        gender_L.setBounds(400,220, 100,30);
        age_L.setBounds(400,270, 100,30);

        Name_L.setForeground(Color.BLACK);
        Surname_L.setForeground(Color.BLACK);
        City_L.setForeground(Color.BLACK);
        Phone_NO_L.setForeground(Color.BLACK);
        gender_L.setForeground(Color.BLACK);
        age_L.setForeground(Color.BLACK);

        Frame.add(Name_L);
        Frame.add(Surname_L);
        Frame.add(City_L);
        Frame.add(Phone_NO_L);
        Frame.add(gender_L);
        Frame.add(age_L);

        JButton source =(JButton) e.getSource();
        if( source.getText().equals("SHOW INFO")){
            supplier.getinfo();
            supplier.showinfo(Frame);
        }
        else if( source.getText().equals("UPDATE INFO")){
            supplier.setinfo(Frame);
        }
        Frame.add(Background);

    }

}
