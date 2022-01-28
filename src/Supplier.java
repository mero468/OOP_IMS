import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.Scanner;

public class Supplier implements Person {

    private String Name ="";
    private String SurName ="";
    private String City ="";
    private String Phone_NO ="";
    private String gender ="";
    private int age =0;

    @Override
    public void getinfo() {
        File file = new File("Supplier.txt");
        try {
            Scanner scan = new Scanner(file);
            if(scan.hasNextLine()) {
                String[] managerInfo = scan.nextLine().split(" ");
                Name = managerInfo[0];
                SurName = managerInfo[1];
                City = managerInfo[2];
                Phone_NO = managerInfo[3];
                gender = managerInfo[4];
                age = Integer.parseInt(managerInfo[5]);
            }
        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("File not found!");
        }
    }

    @Override
    public void showinfo(JFrame frame) {
        JLabel Name_L = new JLabel();
        JLabel Surname_L = new JLabel();
        JLabel City_L = new JLabel();
        JLabel Phone_NO_L = new JLabel();
        JLabel gender_L = new JLabel();
        JLabel age_L = new JLabel();
        Name_L.setText(Name);
        Name_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        Name_L.setBounds(520,50, 140,30);
        Name_L.setForeground(Color.BLACK);

        Surname_L.setText(SurName);
        Surname_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        Surname_L.setBounds(520,90, 140,30);
        Surname_L.setForeground(Color.BLACK);

        City_L.setText(City);
        City_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        City_L.setForeground(Color.BLACK);
        City_L.setBounds(520,130, 140,30);

        Phone_NO_L.setText(Phone_NO);
        Phone_NO_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        Phone_NO_L.setBounds(520,170, 140,30);
        Phone_NO_L.setForeground(Color.BLACK);

        gender_L.setText(gender);
        gender_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        gender_L.setBounds(520,220, 140,30);
        gender_L.setForeground(Color.BLACK);

        age_L.setText(" "+age);
        age_L.setFont(new Font("SansSerif", Font.BOLD, 18));
        age_L.setBounds(520,270, 140,30);
        age_L.setForeground(Color.BLACK);

        frame.add(Name_L);
        frame.add(Surname_L);
        frame.add(City_L);
        frame.add(Phone_NO_L);
        frame.add(gender_L);
        frame.add(age_L);
    }

    @Override
    public void setinfo(JFrame frame) {
        JTextField Name_text = new JTextField();
        JTextField SurName_text = new JTextField();
        JTextField City_text = new JTextField();
        String[] genders = {"Male","Female","Others"};
        JComboBox gender_box = new JComboBox(genders);

        //Set a format for the Numbers we are accepting in age field
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(150);
        formatter.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField Age_text = new JFormattedTextField(formatter);
        NumberFormatter formatter2 = new NumberFormatter(format);
        formatter2.setValueClass(Integer.class);
        formatter2.setMinimum(0);
        formatter2.setMaximum(Integer.MAX_VALUE);
        formatter2.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter2.setCommitsOnValidEdit(true);
        JFormattedTextField PhoneNO_text = new JFormattedTextField(formatter2);

        Name_text.setBounds(520,50, 140,30);
        SurName_text.setBounds(520,90, 140,30);
        City_text.setBounds(520,130, 140,30);
        PhoneNO_text.setBounds(520,170, 140,30);
        gender_box.setBounds(520,220, 140,30);
        Age_text.setBounds(520,270, 140,30);

        JButton submit = new JButton("Submit");
        submit.setBounds(520,350, 140,40);
        submit.setFocusable(false);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean acces = true;
                String[] arr = {"/", "(" , ")" , " " ,"^" , "$" , "!" ,"@","#","%","&","_","=",",",";",".",">"
                        ,"<","]"+"[","'","*","-","|","0","1","2","3","4","5","6","7","8","9"};
                for (int i =0;i<arr.length;i++) {
                    if (Name_text.getText().contains(arr[i])) {
                        Name_text.setText(" ");
                        acces=false;
                    }
                    if (SurName_text.getText().contains(arr[i])) {
                        SurName_text.setText(" ");
                        acces=false;
                    }
                    if (City_text.getText().contains(arr[i])) {
                        City_text.setText(" ");
                        acces=false;
                    }
                }
                if( acces){
                    try{
                        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Supplier.txt")));
                        writer.write(Name_text.getText()+" " +SurName_text.getText()
                                +" " +City_text.getText()+" " +PhoneNO_text.getText()
                                +" " +gender_box.getSelectedItem()+" " +Age_text.getText());
                        writer.close();
                        frame.dispose();
                    }
                    catch (IOException fe) {
                        System.out.println("BIG CHUNGUS!");
                    }
                }
            }
        });
        frame.add(Name_text);
        frame.add(SurName_text);
        frame.add(City_text);
        frame.add(PhoneNO_text);
        frame.add(gender_box);
        frame.add(Age_text);
        frame.add(submit);
    }

    private ArrayQueue<String> Ordered_Products = new ArrayQueue<>(100);
    private Hashtable<String,Product> Supplier_DB = new Hashtable<>();

    public ArrayQueue<String> getOrdered_Products() {
        return Ordered_Products;
    }

    public Hashtable<String, Product> getSupplier_DB() {
        return Supplier_DB;
    }

    public void read_Orders(){
        try {
            Scanner scan1 = new Scanner(new File("SupplierDB.txt"));
            Scanner scan2 = new Scanner(new File("Grocery.txt"));
            while (scan1.hasNextLine()){
                String[] Line  = scan1.nextLine().split("\t");
                Supplier_DB.put(Line[0],new Product(Line[0],Integer.parseInt(Line[1]),Line[2],Integer.parseInt(Line[3])));
            }
            while (scan2.hasNextLine()){
                Ordered_Products.enqueue(scan2.nextLine());
            }
        }catch (FileNotFoundException e){
            System.out.println("DataBase OR Grocery files Not found!");
        }
    }


    public void writeStock(){
        if(Supplier_DB.size()!=0){
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("SupplierDB.txt")));
                for (String s : Supplier_DB.keySet()) {
                    Product p = Supplier_DB.get(s);
                    writer.write(p.getProductName() + "\t" +p.getPrice()+"\t"+p.getExpiryDate()+"\t"+p.getQuantity()+"\n");
                }
                writer.close();
            }catch (IOException exception){
                System.out.println("Stock File not FOUND!");
            }
        }
    }

}
