import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Hashtable;


public class OrderPage implements ActionListener {
    private static OrderPage instance = new OrderPage();
    //make the constructor private so that this class cannot be instantiated
    private OrderPage(){}
    //Get the only object available
    public static OrderPage getInstance(){
        return instance;
    }
    private Supplier supplier = new Supplier();
    private Hashtable<String,Product> SUPDB;
    private String selectedProduct;
    private int total=0;
    private int rows=0;
    private JPanel OrderPage;

    DefaultListModel<String> grocery_products = new DefaultListModel();
    JList<String> GL = new JList<>(grocery_products);
    private JFormattedTextField Quantity_txt;

    ArrayList<String> ProductNames = new ArrayList<>();
    ArrayList<String> ProductQuantities  = new ArrayList<>();
    ArrayList<String> ProductPrices = new ArrayList<>();

    public void createPage(JPanel panel){
        OrderPage = panel;
        OrderPage.setLayout(null);
        OrderPage.setBounds(20, 20, 1000, 550);

        JLabel Quantity_L = new JLabel();
        Quantity_L.setText("Product Quantity :");
        Quantity_L.setFont(new Font("SansSerif", Font.BOLD, 20));
        Quantity_L.setBounds(20, 400, 200, 30);
        Quantity_L.setForeground(Color.black);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatterQuantity = new NumberFormatter(format);
        formatterQuantity.setValueClass(Integer.class);
        formatterQuantity.setMinimum(0);
        formatterQuantity.setMaximum(150);
        formatterQuantity.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatterQuantity.setCommitsOnValidEdit(true);
        Quantity_txt = new JFormattedTextField(formatterQuantity);
        Quantity_txt.setBounds(20,460,280,35);
        Quantity_txt.setText("0");


        supplier.read_Orders();
        ArrayQueue<String> products = supplier.getOrdered_Products();
        while (!products.isEmpty()){
            grocery_products.addElement(products.dequeue());
        }
        GL.setBounds(20,50,300,300);
        GL.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel Picture = new JLabel(new ImageIcon("OrderPagePIC2.jpg"));
        Picture.setBounds(600, 0, 500, 700);
        JLabel Picture2 = new JLabel(new ImageIcon("copy.png"));
        Picture2.setBounds(300, 300, 300, 300);

        JButton GiveOrder = new JButton("MAKE ORDERS >>>");
        GiveOrder.setBounds(370, 60, 150, 60);
        GiveOrder.setFocusable(false);
        GiveOrder.addActionListener(this::ManageGroceries);

        JLabel Info = new JLabel();
        Info.setText("This page includes The grocery list that is used to make orders, you should put a viable Quantity:");
        Info.setFont(new Font("SansSerif", Font.BOLD, 12));
        Info.setBounds(5, 2, 700, 30);
        Info.setForeground(Color.black);

        JButton Invoice = new JButton("Print Invoice");
        Invoice.setBounds(370, 220, 150, 60);
        Invoice.setFocusable(false);
        Invoice.addActionListener(this::invoice_job);

        OrderPage.add(GiveOrder);
        OrderPage.add(GL);
        OrderPage.add(Quantity_L);
        OrderPage.add(Quantity_txt);
        OrderPage.add(Invoice);
        OrderPage.add(Info);
        OrderPage.add(Picture);
        OrderPage.add(Picture2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void ManageGroceries(ActionEvent e) {
        SUPDB = supplier.getSupplier_DB();
        selectedProduct = GL.getSelectedValue();
        int quantity = Integer.parseInt(Quantity_txt.getText());
        if(quantity!=0) {
            Stock stock = new Stock();
            for (String s : SUPDB.keySet()) {
                if (s.equalsIgnoreCase(selectedProduct) && SUPDB.get(s).getQuantity() >= quantity) {
                    grocery_products.removeElement(selectedProduct);
                    int previousQuantity = SUPDB.get(s).getQuantity();
                    SUPDB.get(s).setQuantity(previousQuantity - quantity);
                    supplier.writeStock();
                    Product product;
                    product = SUPDB.get(s);
                    product.setQuantity(quantity);
                    stock.addProduct(product);
                    ProductNames.add(selectedProduct);
                    ProductQuantities.add(quantity+"");
                    ProductPrices.add(product.getPrice()+"");
                    total+=product.getPrice();
                    rows++;
                    updateGrocery();

                    JFrame f;
                    f = new JFrame();
                    JOptionPane.showMessageDialog(f, "Successful Order!");
                    return;
                }
            }
        }
        JFrame f;
        f = new JFrame();
        JOptionPane.showMessageDialog(f, "ERROR IN PRODUCT DETAILS");

    }

    private void updateGrocery(){
        if(grocery_products.size()!=0){
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Grocery.txt")));
                for (int i =0; i<grocery_products.size();i++){
                    writer.write(grocery_products.get(i) +"\n");
                }
                writer.close();
            }catch (IOException exception){
                System.out.println("Stock File not FOUND!");
            }
        }
    }

    public void invoice_job(ActionEvent e)
    {
        JTextPane textPane = new JTextPane();
        try
        {

            int rows=this.rows;
            String head = String.format("%30s\n\n", "   Invoice Reciept   ");
            String s = String.format("%-15s %5s %10s\n", "Item",        "Qty",        "Price");
            String s1 = String.format("%-15s %5s %10s\n","---------","--------"," ----------");
            String output = head + s + s1;
            for(int row = 0; row<rows; row++)

            {

                String Names = ProductNames.get(row);
                String Quanti = ProductQuantities.get(row);
                String Prices = ProductPrices.get(row);

                String line = String.format("%-15s %5s %5s\n",Names,Quanti,Prices);
                output += line;
                String lin2 = String.format("%-15s\n","-------------------------------------");
                output += lin2;



            }

            String Total = String.format("%37s\n","Total");
            String total = String.format("%37s\n\n",this.total);
            String footer = String.format("%-15s\n", "Transaction Complete");
            output += Total + total + footer;
            textPane.setText(output);

            JOptionPane.showMessageDialog(null, output);


            PrinterJob printerJob = PrinterJob.getPrinterJob();
            PageFormat pageFormat = printerJob.defaultPage();
            Paper paper = new Paper();
            paper.setSize(180.0, (double) (paper.getHeight() + rows * 10.0));
            paper.setImageableArea(rows, rows, paper.getWidth() - rows * 2, paper.getHeight() - rows * 2);
            pageFormat.setPaper(paper);
            pageFormat.setOrientation(PageFormat.PORTRAIT);
            printerJob.setPrintable(textPane.getPrintable(null, null), pageFormat);
            printerJob.print();

        }
        catch ( PrinterAbortException sheesh )
        {
            System.out.println("Printing Aborted");
        }
        catch (PrinterException p){
            System.out.println("No file selected");
        }
    }

}
