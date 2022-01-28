import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Hashtable;

public class StockPage implements ActionListener {

    private static StockPage instance = new StockPage();
    //make the constructor private so that this class cannot be
    //instantiated
    private StockPage(){}
    //Get the only object available
    public static StockPage getInstance(){
        return instance;
    }

    private JTextField  ProductName_TXT;
    //you need to check for the product name formate before going further
    private boolean access = false;
    private String productName;
    private String expiryDate;
    private int price;
    private int quantity;

    private int listcount=0;

    private DefaultListModel<String> grocery_products = new DefaultListModel();
    private JList<String> GL = new JList<>(grocery_products);

    private JFormattedTextField Price_txt;
    private JFormattedTextField Day_txt;
    private JFormattedTextField Month_txt;
    private JFormattedTextField Year_txt;
    private JFormattedTextField Quantity_txt;

    public void createPage(JPanel stockPage){

        stockPage.setLayout(null);
        stockPage.setBounds(20, 20, 1000, 650);

        //LABELS
        JLabel ProductName_L = new JLabel();
        JLabel price_L = new JLabel();
        JLabel Day_ED_L = new JLabel();
        JLabel Month_ED_L = new JLabel();
        JLabel Year_ED_L = new JLabel();
        JLabel Quantity_L = new JLabel();

        ProductName_L.setText("Product Name :");
        ProductName_L.setFont(new Font("SansSerif", Font.PLAIN, 15));
        ProductName_L.setBounds(20, 50, 140, 30);
        ProductName_L.setForeground(Color.black);

        price_L.setText("Product price :");
        price_L.setFont(new Font("SansSerif", Font.PLAIN, 15));
        price_L.setBounds(20, 90, 140, 30);
        price_L.setForeground(Color.black);

        Day_ED_L.setText("Day of ExpiryDate :");
        Day_ED_L.setFont(new Font("SansSerif", Font.PLAIN, 15));
        Day_ED_L.setBounds(20, 130, 140, 30);
        Day_ED_L.setForeground(Color.black);

        Month_ED_L.setText("Month of ExpiryDate :");
        Month_ED_L.setFont(new Font("SansSerif", Font.PLAIN, 15));
        Month_ED_L.setBounds(20, 170, 140, 30);
        Month_ED_L.setForeground(Color.black);

        Year_ED_L.setText("Year of ExpiryDate :");
        Year_ED_L.setFont(new Font("SansSerif", Font.PLAIN, 15));
        Year_ED_L.setBounds(20, 210, 140, 30);
        Year_ED_L.setForeground(Color.black);


        Quantity_L.setText("Product Quantity :");
        Quantity_L.setFont(new Font("SansSerif", Font.PLAIN, 15));
        Quantity_L.setBounds(20, 250, 140, 30);
        Quantity_L.setForeground(Color.black);

        //TEXT FIELDS
        ProductName_TXT = new JTextField();
        ProductName_TXT.setBounds(180,50,140,35);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatterPrice= new NumberFormatter(format);
        formatterPrice.setValueClass(Integer.class);
        formatterPrice.setMinimum(0);
        formatterPrice.setMaximum(150);
        formatterPrice.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatterPrice.setCommitsOnValidEdit(true);
        Price_txt = new JFormattedTextField(formatterPrice);
        Price_txt.setBounds(180,90,140,35);

        //Formats for our text fields
        NumberFormatter formatterDay = new NumberFormatter(format);
        formatterDay.setValueClass(Integer.class);
        formatterDay.setMinimum(1);
        formatterDay.setMaximum(31);
        formatterDay.setAllowsInvalid(false);

        // If you want the value to be committed on each keystroke instead of focus lost
        formatterDay.setCommitsOnValidEdit(true);
        Day_txt = new JFormattedTextField(formatterDay);
        Day_txt.setBounds(180,130,140,35);

        NumberFormatter formatterMonth = new NumberFormatter(format);
        formatterMonth.setValueClass(Integer.class);
        formatterMonth.setMinimum(1);
        formatterMonth.setMaximum(12);
        formatterMonth.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatterMonth.setCommitsOnValidEdit(true);
        Month_txt = new JFormattedTextField(formatterMonth);
        Month_txt.setBounds(180,170,140,35);

        NumberFormatter formatterYear = new NumberFormatter(format);
        formatterYear.setValueClass(Integer.class);
        formatterYear.setMinimum(0);
        formatterYear.setMaximum(9999);
        formatterYear.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatterYear.setCommitsOnValidEdit(true);
        Year_txt = new JFormattedTextField(formatterYear);
        Year_txt.setBounds(180,210,140,35);

        NumberFormatter formatterQuantity = new NumberFormatter(format);
        formatterQuantity.setValueClass(Integer.class);
        formatterQuantity.setMinimum(0);
        formatterQuantity.setMaximum(150);
        formatterQuantity.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatterQuantity.setCommitsOnValidEdit(true);
        Quantity_txt = new JFormattedTextField(formatterQuantity);
        Quantity_txt.setBounds(180,250,140,35);

        //Create the GroceryList

        GL.setBounds(600,70,300,300);
        GL.setBorder(BorderFactory.createLineBorder(Color.black));

        //BUTTONS

        JButton ADD = new JButton("ADD");
        ADD.setBounds(20, 300, 120, 70);
        ADD.setFocusable(false);
        ADD.addActionListener(this::AddtoStock);

        JButton remove = new JButton("remove");
        remove.setBounds(170, 300, 150, 70);
        remove.setFocusable(false);
        remove.addActionListener(this::RemoveFromStock);


        JButton ShowStock = new JButton("Show Stock");
        ShowStock.setBounds(20, 400, 300, 120);
        ShowStock.setFocusable(false);
        ShowStock.addActionListener(this::ShowStock);

        JButton Groceries = new JButton("Add to list >>>");
        Groceries.setBounds(350, 70, 150, 60);
        Groceries.setFocusable(false);
        Groceries.addActionListener(this::AddgroceryList);

        JButton RemoveG = new JButton("Remove From list >>>");
        RemoveG.setBounds(350, 150, 150, 60);
        RemoveG.setFocusable(false);
        RemoveG.addActionListener(this::RemoveGrocerry);

        JButton Submit = new JButton("Submit list >>>");
        Submit.setBounds(350, 230, 150, 60);
        Submit.setFocusable(false);
        Submit.addActionListener(this::SubmitList);

        JLabel introduction = new JLabel("To add to the Grocery list Please fill the Product name section");
        introduction.setFont(new Font("SansSerif", Font.BOLD, 15));
        introduction.setBounds(20, 0, 500, 60);

        JLabel Picture = new JLabel(new ImageIcon("Stockpic2.png"));
        Picture.setBounds(600, 380, 300, 250);

        stockPage.add(ProductName_L);
        stockPage.add(price_L);
        stockPage.add(Day_ED_L);
        stockPage.add(Month_ED_L);
        stockPage.add(Year_ED_L);
        stockPage.add(Quantity_L);
        stockPage.add(ProductName_TXT);
        stockPage.add(Price_txt);
        stockPage.add(Day_txt);
        stockPage.add(Month_txt);
        stockPage.add(Year_txt);
        stockPage.add(Quantity_txt);
        stockPage.add(ADD);
        stockPage.add(remove);
        stockPage.add(ShowStock);
        stockPage.add(Groceries);
        stockPage.add(GL);
        stockPage.add(introduction);
        stockPage.add(RemoveG);
        stockPage.add(Submit);
        stockPage.add(Picture);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private boolean check(){
        String[] arr = {"/", "(", ")", "\t", "^", "$", "!", "@", "#", "%", "&", "_", "=", ",", ";", ".", ">"
                , "<", "]" + "[", "'", "*", "-", "|", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (String s : arr) {
            if (ProductName_TXT.getText().contains(s) || ProductName_TXT.getText().equals("")) {
                ProductName_TXT.setText("");
                return false;
            }
        }
        productName = ProductName_TXT.getText();
        return true;
    }

    public void ShowStock(ActionEvent e){

        JFrame f = new JFrame();
        Manager manager = new Manager();
        ArrayQueue<String[]> products = manager.accessStock();
        String[] Columns ={"ID","Product Name","Price","Expiration Date","Quantity"};
        String[][] Rows = new String[manager.getRows()][Columns.length];
        int i = products.size()-1;
        while (!products.isEmpty()){
            Rows[i] = products.dequeue();
            i--;
        }
        JTable STOCK = new JTable(Rows,Columns);
        STOCK.setBounds(30,40,400,600);
        JScrollPane sp=new JScrollPane(STOCK);
        f.add(sp);
        f.setSize(600,800);
        f.setVisible(true);
    }

    public void AddtoStock(ActionEvent e){
        expiryDate = Day_txt.getText()+"/"+Month_txt.getText()+"/"+Year_txt.getText(). replaceAll(",", "");
        price = Integer.parseInt(Price_txt.getText()) ;
        quantity = Integer.parseInt(Quantity_txt.getText()) ;
        System.out.println(price+" "+expiryDate+" "+quantity);
        if(check() && price!=0 && !expiryDate.equals("0/0/0") && quantity!=0 ) {
            Stock stock = new Stock();
            boolean flag = true;
            Hashtable<Integer, Product> stk = stock.getStock();
            for (int i : stk.keySet()) {
                if (stk.get(i).getProductName().equalsIgnoreCase(productName)) {
                    JFrame f;
                    f = new JFrame();
                    JOptionPane.showMessageDialog(f, "Product is already in Stock");
                    flag=false;
                }
            }
            if(flag){
                Product p = new Product(productName,price,expiryDate,quantity);
                stock.addProduct(p);
            }
        }
        else{
            JFrame f;
            f = new JFrame();
            JOptionPane.showMessageDialog(f, "Product is InValid ");
        }
    }
    public void RemoveFromStock(ActionEvent e){
        if(check()) {
            Stock stock = new Stock();
            stock.removeProduct(productName);
            JFrame f;
            f = new JFrame();
            JOptionPane.showMessageDialog(f, "Product Removed and stock updated");
        }
        else{
            JFrame f;
            f = new JFrame();
            JOptionPane.showMessageDialog(f, "Product is InValid, Change the name of the product");
        }
    }

    public void AddgroceryList(ActionEvent e){
        if(check()){
            if(!grocery_products.contains(productName.toLowerCase())) {
                grocery_products.addElement(productName);
                listcount++;
            }
        }
        else{
            JFrame f;
            f = new JFrame();
            JOptionPane.showMessageDialog(f, "Product is InValid ");
        }

    }

    public void RemoveGrocerry(ActionEvent e){
        if(GL.getSelectedValue()!= null) {
            grocery_products.removeElement(GL.getSelectedValue());
            listcount--;
        }
    }

    public void SubmitList(ActionEvent e){
        try {
            File file = new File("Grocery.txt");
            FileWriter fr = new FileWriter(file, true);
            String[] arr = new String[listcount];
            grocery_products.copyInto(arr);
            for (String s: arr) {
                fr.write(s+"\n");
                grocery_products.removeElement(s);
            }
            fr.close();
        }
        catch (IOException exception){
            System.out.println("STOCK FILE NOT FOUND!");
        }
    }
}
