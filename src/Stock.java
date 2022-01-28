import javax.swing.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class Stock {


    private Hashtable<Integer,Product> Stock = new Hashtable<>();
    private static int id;

    public Hashtable<Integer, Product> getStock() {
        return Stock;
    }

    public Stock(){
        try{
            Scanner scanner = new Scanner(new File("Stock.txt"));
            id = 100;
            while (scanner.hasNextLine()){
                String line[] = scanner.nextLine().split("\t");
                Product product = new Product(line[0],Integer.parseInt(line[1]),line[2],Integer.parseInt(line[3]));
                Stock.put(id,product);
                id++;
            }
        }catch (FileNotFoundException fe){
            System.out.println("Stock not Found!");
        }
    }

    public void addProduct(Product p){
        try {
            boolean flag = true;
            for (int i : Stock.keySet()) {
                Product p2 = Stock.get(i);
                if (p2.getProductName().equalsIgnoreCase(p.getProductName())) {
                    p.setQuantity(p2.getQuantity()+p.getQuantity());
                    writeStock();
                    return;
                }
            }
            id++;
            Stock.put(id, p);
            File file = new File("Stock.txt");
            FileWriter fr = new FileWriter(file, true);
            fr.write(p.getProductName() + "\t" + p.getPrice() + "\t" + p.getExpiryDate() + "\t" + p.getQuantity() + "\n");
            fr.close();
        }
        catch (IOException e){
            System.out.println("STOCK FILE NOT FOUND!");
        }
    }

    public void removeProduct(String productname){
        for (int i : Stock.keySet()) {
            if (Stock.get(i).getProductName().equalsIgnoreCase(productname)) {
                Stock.remove(i);
                writeStock();
                id--;
                return;
            }
        }
        JFrame f;
        f = new JFrame();
        JOptionPane.showMessageDialog(f, "Product not found!");
    }

    public void writeStock(){
        if(Stock.size()!=0){
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Stock.txt")));
                for (int i : Stock.keySet()) {
                    Product p = Stock.get(i);
                    writer.write(p.getProductName() + "\t" +p.getPrice()+"\t"+p.getExpiryDate()+"\t"+p.getQuantity()+"\n");
                }
                writer.close();
            }catch (IOException exception){
                System.out.println("Stock File not FOUND!");
            }
        }
    }
}
