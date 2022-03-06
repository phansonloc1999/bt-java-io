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
        String path = "";
        path = scanner.nextLine();
        if (path.equals("")) {
            student.setHinhAnh(null);
        } else {
            student.setHinhAnh(ImageIO.read(new File(path)));
        }
        System.out.print("Dia chi: ");
        student.setDiaChi(scanner.nextLine());
        System.out.print("Ghi chu: ");
        student.setGhiChu(scanner.nextLine());
        //
        students.add(student);
    }

    private static void modifyStudent(ArrayList<Student> students) throws IOException {
        System.out.print("Nhap ma hoc sinh can chinh sua: ");
        Scanner scanner = new Scanner(System.in);
        String maHocSinh = scanner.nextLine();
        int i;
        if (students.size() == 0) {
            System.out.println("Khong co hoc sinh nao trong danh sach!");

            return;
        }

        for (i = 0; i < students.size(); i++) {
            if (students.get(i).getMHS().equals(maHocSinh)) {
                System.out.println("THONG TIN HOC SINH");
                System.out.println("Ma hoc sinh: " + students.get(i).getMHS());
                System.out.println("Ten hoc sinh: " + students.get(i).getTenHS());
                System.out.println("Diem: " + students.get(i).getDiem());
                System.out.println("Dia chi: " + students.get(i).getDiaChi());
                System.out.println("Ghi chu: " + students.get(i).getGhiChu());

                break;
            }
            if (i >= students.size() - 1) {
                System.out.println("Khong tim thay hoc sinh voi ma da nhap!");
                //
                return;
            }
        }

        Student modifiedStudent = new Student();
        System.out.println("NHAP THONG TIN HOC SINH CAN CHINH SUA");
        System.out.print("Ma hoc sinh: ");
        modifiedStudent.setMHS(scanner.nextLine());
        System.out.print("Ten hoc sinh: ");
        modifiedStudent.setTenHS(scanner.nextLine());
        System.out.print("Diem: ");
        modifiedStudent.setDiem(scanner.nextFloat());
        scanner.nextLine();
        System.out.print("Duong dan tuyet doi den hinh anh: ");
        String path = "";
        path = scanner.nextLine();
        if (path.equals("")) {
            modifiedStudent.setHinhAnh(null);
        } else {
            modifiedStudent.setHinhAnh(ImageIO.read(new File(path)));
        }
        System.out.print("Dia chi: ");
        modifiedStudent.setDiaChi(scanner.nextLine());
        System.out.print("Ghi chu: ");
        modifiedStudent.setGhiChu(scanner.nextLine());

        students.set(i, modifiedStudent);

    }

    private static void removeStudent(ArrayList<Student> students) {
        System.out.print("Nhap ma hoc sinh muon xoa: ");
        Scanner scanner = new Scanner(System.in);
        String maHocSinh = scanner.nextLine();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getMHS().equals(maHocSinh)) {
                students.remove(i);

                return;
            }
        }
        System.out.println("Khong tim thay hoc sinh voi ma da nhap!");

    }

    private static void listStudents(ArrayList<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            System.out.println("THONG TIN HOC SINH");
            System.out.println("Ma hoc sinh: " + students.get(i).getMHS());
            System.out.println("Ten hoc sinh: " + students.get(i).getTenHS());
            System.out.println("Diem: " + students.get(i).getDiem());
            System.out.println("Dia chi: " + students.get(i).getDiaChi());
            System.out.println("Ghi chu: " + students.get(i).getGhiChu());
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Student> students = new ArrayList<Student>();
        students = inputFromFile();

        addStudent(students);

        modifyStudent(students);

        removeStudent(students);

        listStudents(students);

        outputToFile(students);
    }
}