import javax.swing.*;

public interface Person {

    String Name ="";
    String SurName ="";
    String City ="";
    String Phone_NO ="";
    String gender ="";
    int age =0;

    void getinfo();
    void showinfo(JFrame frame);
    void setinfo(JFrame frame);
}
