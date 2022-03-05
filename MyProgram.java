import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    private static void addStudent(ArrayList<Student> students) throws IOException {
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ma hoc sinh: ");
        student.setMHS(scanner.nextLine());
        System.out.print("Ten hoc sinh: ");
        student.setTenHS(scanner.nextLine());
        System.out.print("Diem: ");
        student.setDiem(scanner.nextFloat());
        scanner.nextLine();
        System.out.print("Duong dan tuyet doi den hinh anh: ");
        String path = scanner.nextLine();
        student.setHinhAnh(ImageIO.read(new File(path)));
        System.out.print("Dia chi: ");
        student.setDiaChi(scanner.nextLine());
        System.out.print("Ghi chu: ");
        student.setGhiChu(scanner.nextLine());
        scanner.close();
        students.add(student);
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Student> students = new ArrayList<Student>();
        students = inputFromFile();

        addStudent(students);

        outputToFile(students);
    }
}