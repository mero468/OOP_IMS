import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;

public class Pages extends JFrame implements ActionListener {


    private static Pages instance = new Pages();
    //make the constructor private so that this class cannot be
    //instantiated
    private Pages(){}
    //Get the only object available
    public static Pages getInstance(){
        return instance;
    }

    //Login Page labels and FIELDS
    private JTextField userName_text;
    private JPasswordField password_text;
    private JLabel user_label, password_label, message,SIGN;
    private static String password_GL;
    private static String username_GL;

    //Pages and Frames
    private JFrame loginPage;
    private JFrame mainPage;
    private JPanel profilePage;
    private JPanel stockPage;
    private JPanel orderPage;
    private JFrame signUp_Frame;

    //Signup Page labels and FIELDS
    private JTextField userName_text2;
    private JPasswordField password_text2;
    private JPasswordField Confirm_password_text2;
    private JLabel user_label2, password_label2,message2,SIGNUP,Confirm_password_label2;

    //tabs
    private  JTabbedPane Tabs;

    //Sign up and Login Pages:
    private void Sign_up(ActionEvent ae) {
        signUp_Frame = new JFrame();

        //Labels and images For FRAME
        SIGNUP = new JLabel();
        JLabel sideImage=new JLabel(new ImageIcon("login_sideImage.jpg"));
        sideImage.setBounds(0,0, 300,499);
        SIGNUP.setText("SIGN UP");
        SIGNUP.setFont(new Font("Serif", Font.PLAIN, 30));
        SIGNUP.setBounds(450,0, 140,50);
        message2 = new JLabel();
        message2.setFont(new Font("SansSerif", Font.BOLD, 15));
        message2.setBounds(350,380, 250,20);

        //Username Label FOR FRAME
        user_label2 = new JLabel();
        user_label2.setText("User Name :");
        user_label2.setFont(new Font("SansSerif", Font.BOLD, 18));
        user_label2.setBounds(350,70, 140,30);
        userName_text2 = new JTextField();
        userName_text2.setBounds(350,100, 200,30);

        // Password Labels FOR FRAME
        password_label2 = new JLabel();
        password_label2.setText("Password :");
        password_label2.setFont(new Font("SansSerif", Font.BOLD, 18));
        password_label2.setBounds(350,150, 140,30);
        password_text2 = new JPasswordField();
        password_text2.setBounds(350,180, 200,30);
        Confirm_password_label2 = new JLabel();
        Confirm_password_label2.setText("Confirm Password :");
        Confirm_password_label2.setFont(new Font("SansSerif", Font.BOLD, 18));
        Confirm_password_label2.setBounds(350,220, 240,30);
        Confirm_password_text2 = new JPasswordField();
        Confirm_password_text2.setBounds(350,250, 200,30);

        //Buttons FOR FRAME
        JButton submit = new JButton("SIGN UP");
        submit.setBounds(350,300, 300,50);
        submit.setFocusable(false);
        submit.addActionListener(this::SIGNUP_Act);

        //ADD LABELS & BUTTONS & FIELDS TO FRAME
        signUp_Frame.setTitle("INVENTORY MANAGEMENT CLIENT");
        signUp_Frame.setDefaultCloseOperation(signUp_Frame.DISPOSE_ON_CLOSE);
        signUp_Frame.setResizable(false);
        signUp_Frame.setSize(700,500);
        signUp_Frame.setVisible(true);
        signUp_Frame.setLayout(null);
        signUp_Frame.add(submit);
        signUp_Frame.add(SIGNUP);
        signUp_Frame.add(message2);
        signUp_Frame.add(user_label2);
        signUp_Frame.add(userName_text2);
        signUp_Frame.add(password_label2);
        signUp_Frame.add(password_text2);
        signUp_Frame.add(Confirm_password_label2);
        signUp_Frame.add(Confirm_password_text2);
        signUp_Frame.add(sideImage);
        //set an icon for the frame
        ImageIcon image = new ImageIcon("LOGO.png");
        signUp_Frame.setIconImage(image.getImage());
        signUp_Frame.getContentPane().setBackground(new Color(255,255,255));
    }

    public void loginpage() {
        loginPage = new JFrame();
        try {
            Scanner scan = new Scanner(new File("User.txt"));
            String userDet[] = scan.nextLine().split(" ");
            username_GL = userDet[0];
            password_GL = userDet[1];
        }
        catch (FileNotFoundException e){
            System.out.println("NON EXISTENT FILE!");
        }
        //Labels and images For FRAME
        SIGN = new JLabel();
        JLabel sideImage=new JLabel(new ImageIcon("login_sideImage.jpg"));
        sideImage.setBounds(0,0, 300,499);
        SIGN.setText("SIGN IN");
        SIGN.setFont(new Font("Serif", Font.PLAIN, 30));
        SIGN.setBounds(450,0, 140,50);
        message = new JLabel();
        message.setFont(new Font("SansSerif", Font.BOLD, 15));
        message.setBounds(350,230, 140,20);

        //Username Label FOR FRAME
        user_label = new JLabel();
        user_label.setText("User Name :");
        user_label.setFont(new Font("SansSerif", Font.BOLD, 18));
        user_label.setBounds(350,70, 140,30);
        userName_text = new JTextField();
        userName_text.setBounds(350,100, 200,30);

        // Password Labels FOR FRAME
        password_label = new JLabel();
        password_label.setText("Password :");
        password_label.setFont(new Font("SansSerif", Font.BOLD, 18));
        password_label.setBounds(350,150, 140,30);
        password_text = new JPasswordField();
        password_text.setBounds(350,180, 200,30);

        //Buttons FOR FRAME
        JButton submit = new JButton("Submit");
        submit.setBounds(350,300, 300,50);
        submit.setFocusable(false);
        submit.addActionListener(this::actionPerformed);
        JButton sign_up = new JButton("SIGN UP");
        sign_up.setBounds(350,400, 300,50);
        sign_up.setFocusable(false);
        sign_up.addActionListener(this::Sign_up);

        //ADD LABELS & BUTTONS & FIELDS TO FRAME
        loginPage.setTitle("INVENTORY MANAGEMENT CLIENT");
        loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPage.setResizable(false);
        loginPage.setSize(700,500);
        loginPage.setVisible(true);
        loginPage.setLayout(null);
        loginPage.add(submit);
        loginPage.add(SIGN);
        loginPage.add(message);
        loginPage.add(user_label);
        loginPage.add(userName_text);
        loginPage.add(password_label);
        loginPage.add(password_text);
        loginPage.add(sideImage);
        loginPage.add(sign_up);
        //set an icon for the frame
        ImageIcon image = new ImageIcon("LOGO.png");
        loginPage.setIconImage(image.getImage());
        loginPage.getContentPane().setBackground(new Color(255,255,255));

    }

    private void SIGNUP_Act(ActionEvent ae) {
        String userName = userName_text2.getText();
        String password = password_text2.getText();
        String password2 = Confirm_password_text2.getText();
        if (password.trim().equals(password2)) {
            message2.setText(" Hello " + userName + "");
            username_GL = userName;
            password_GL = password;
            try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("User.txt")));
            writer.write(userName+" " +password);
            writer.close();
            }
            catch (IOException fe){
                System.out.println("BIG CHUNGUS!");
            }

            signUp_Frame.dispose();
        } else {
            message2.setText(" Error with password confirmation.. ");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = password_text.getText();
        if (userName.trim().equals(username_GL) && password.trim().equals(password_GL)) {
            message.setText(" Hello " + userName + "");
            loginPage.dispose();
            Mainpage();

        } else {
            message.setText(" Invalid user.. ");
        }
    }

    private void PP(){
        JLabel info = new JLabel("<html><p>Please click on one of the two pictures to preview/update the information of the <br/> the Owner on the left Or the supplier on the right</p></html>");
        info.setFont(new Font("SansSerif", Font.BOLD, 15));
        info.setBounds(150,10, 600,40);
        //Setup the Profile Page

        JButton manager = new JButton(new ImageIcon("manager.png"));
        manager.setBorderPainted(false);
        manager.setFocusPainted(false);
        manager.setContentAreaFilled(false);
        manager.setBounds(20,100, 451,451);
        manager.addActionListener(this::deletepanelM);
        JButton supplier = new JButton(new ImageIcon("supplier.png"));
        supplier.setBorderPainted(false);
        supplier.setFocusPainted(false);
        supplier.setContentAreaFilled(false);
        supplier.setBounds(520,100, 451,451);
        supplier.addActionListener(this::deletepanelS);
        profilePage.add(manager);

        profilePage.add(supplier);
        profilePage.add(info);
    }

    private void Mainpage(){

        //Set up the main page

        mainPage = new JFrame();
        mainPage.setBounds(0,0, 1000,1000);
        mainPage.setTitle("INVENTORY MANAGEMENT SYSTEM");
        mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPage.setResizable(false);
        mainPage.setSize(1050,700);
        mainPage.setVisible(true);
        mainPage.setLayout(null);
        ImageIcon image = new ImageIcon("LOGO.png");
        mainPage.setIconImage(image.getImage());

        //Setup the Tabs and the panel
        Tabs = new JTabbedPane();
        profilePage = new JPanel();
        stockPage = new JPanel();
        orderPage = new JPanel();
        profilePage.setBounds(100,100,1000,650);
        Tabs.setBounds(0,0, 1000,650);
        profilePage.setBackground(Color.white);
        Tabs.add("ProfilePage",profilePage);
        Tabs.add("StockPage", stockPage);
        Tabs.add("OrderPage", orderPage);
        Tabs.setFont(new Font("SansSerif", Font.BOLD, 12));

        profilePage.setLayout(null);
        PP();
        StockPage();
        OrderPage();
        mainPage.add(Tabs);

    }
    //The action listener for the supplier profile
    private void deletepanelS(ActionEvent ae) {
        profilePage.removeAll();
        profilePage.repaint();
        ProfilePage PG = new ProfilePage();
        JButton AddInfo = new JButton("UPDATE INFO");
        AddInfo.setBounds(20,50, 360,200);
        AddInfo.setFocusable(false);
        AddInfo.addActionListener(PG::SupplierInfo);

        JButton Showinfo = new JButton("SHOW INFO");
        Showinfo.setBounds(440,50, 360,200);
        Showinfo.setFocusable(false);
        Showinfo.addActionListener(PG::SupplierInfo);

        JButton Goback = new JButton("Goback");
        Goback.setBounds(20,350, 780,200);
        Goback.setFocusable(false);
        Goback.addActionListener(this::goback);

        profilePage.add(AddInfo);
        profilePage.add(Showinfo);
        profilePage.add(Goback);


    }

    //The action listener for the Manager profile
    private void deletepanelM(ActionEvent ae) {

        profilePage.removeAll();
        profilePage.repaint();
        ProfilePage PG = new ProfilePage();
        JButton AddInfo = new JButton("UPDATE INFO");
        AddInfo.setBounds(20,50, 360,200);
        AddInfo.setFocusable(false);
        AddInfo.addActionListener(PG::actionPerformed);

        JButton Showinfo = new JButton("SHOW INFO");
        Showinfo.setBounds(440,50, 360,200);
        Showinfo.setFocusable(false);
        Showinfo.addActionListener(PG::actionPerformed);

        JButton Goback = new JButton("Goback");
        Goback.setBounds(20,350, 780,200);
        Goback.setFocusable(false);
        Goback.addActionListener(this::goback);

        profilePage.add(AddInfo);
        profilePage.add(Showinfo);
        profilePage.add(Goback);
    }
    private void goback(ActionEvent ae){
        mainPage.dispose();
        Mainpage();
    }

    private void StockPage() {
        StockPage SP = StockPage.getInstance();
        SP.createPage(stockPage);

    }

    private void OrderPage(){
        OrderPage OP = OrderPage.getInstance();
        OP.createPage(orderPage);
    }

}
