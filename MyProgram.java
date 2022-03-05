import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

import Libs.Student;

public class MyProgram {
    private static void outputToFile(ArrayList<Student> students) throws IOException {
        FileOutputStream fos = new FileOutputStream("students.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeInt(students.size());
        for (int i = 0; i < students.size(); i++) {
            oos.writeObject(students.get(i));
        }
        oos.flush();
        oos.close();
        fos.close();
    }

    private static ArrayList<Student> inputFromFile() throws IOException {
        FileInputStream fis = new FileInputStream("students.bin");
        ObjectInput ois = new ObjectInputStream(fis);
        int num = ois.readInt();
        ArrayList<Student> students = new ArrayList<Student>();
        for (int i = 0; i < num; i++) {
            try {
                Student student = (Student) ois.readObject();
                students.add(student);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        ois.close();
        return students;
    }

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

        Student student1 = new Student();
        student1.setMHS("1712570");
        student1.setTenHS("Nguyen Manh Linh");
        student1.setDiem(10);
        File f1 = new File("image1.png");
        try {
            student1.setHinhAnh(ImageIO.read(f1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        student1.setDiaChi("10 Ba Trieu, thi tran Quang Phu, huyen Cum'gar, tinh Daklak, Viet Nam");
        student1.setGhiChu("");

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(student1);
        outputToFile(students);

        students.clear();
        students = inputFromFile();

        ImageIO.write(students.get(0).getHinhAnh(), "png", new File("out.png"));
        ImageIO.write(students.get(1).getHinhAnh(), "png", new File("out1.png"));
        return;
    }
}