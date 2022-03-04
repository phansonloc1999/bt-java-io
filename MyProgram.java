import javax.imageio.*;
import java.io.*;

import Libs.Student;

public class MyProgram {

    public static void main(String[] args) throws Exception {
        Student student = new Student();
        student.setMHS("1712571");
        student.setTenHS("Phan Son Loc");
        student.setDiem(10);
        File f = new File("image.png");
        try {
            student.setHinhAnh(ImageIO.read(f));
        } catch (IOException e) {
            e.printStackTrace();
        }
        student.setDiaChi("09 Ba Trieu, thi tran Quang Phu, huyen Cum'gar, tinh Daklak, Viet Nam");
        student.setGhiChu("");

        FileOutputStream fos = new FileOutputStream("students.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(student);
        oos.flush();
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("students.bin");
        ObjectInput ois = new ObjectInputStream(fis);
        Student student1 = (Student) ois.readObject();
        ois.close();

        if (student.getMHS().equals(student1.getMHS())) {
            System.out.println("TRUE");
        }
    }
}