import javax.imageio.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Libs.Student;

public class MyProgram {
    private static void outputToBinFile(ArrayList<Student> students) throws IOException {
        FileOutputStream fos = new FileOutputStream("Data/students.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeInt(students.size());
        for (int i = 0; i < students.size(); i++) {
            oos.writeObject(students.get(i));
        }
        oos.flush();
        oos.close();
        fos.close();
    }

    private static ArrayList<Student> inputFromBinFile() throws IOException {
        FileInputStream fis = new FileInputStream("Data/students.bin");
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
        System.out.println("==================THEM HOC SINH==================");
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
        System.out.println("==================CHINH SUA HOC SINH==================");
        System.out.print("Nhap ma hoc sinh can chinh sua: ");
        Scanner scanner = new Scanner(System.in);
        String maHocSinh = scanner.nextLine();
        int i;
        if (students.size() == 0) {
            System.out.println("Khong co hoc sinh nao trong danh sach!\n");

            return;
        }

        for (i = 0; i < students.size(); i++) {
            if (students.get(i).getMHS().equals(maHocSinh)) {
                System.out.println("==================THONG TIN HOC SINH DA NHAP==================");
                System.out.println("Ma hoc sinh: " + students.get(i).getMHS());
                System.out.println("Ten hoc sinh: " + students.get(i).getTenHS());
                System.out.println("Diem: " + students.get(i).getDiem());
                System.out.println("Dia chi: " + students.get(i).getDiaChi());
                System.out.println("Ghi chu: " + students.get(i).getGhiChu());

                break;
            }
            if (i >= students.size() - 1) {
                System.out.println("Khong tim thay hoc sinh voi ma da nhap!");
                System.out.println("Bam bat ky phim nao de tiep tuc...");
                scanner.nextLine();
                return;
            }
        }

        Student modifiedStudent = new Student();
        System.out.println("==================NHAP THONG TIN HOC SINH CAN CHINH SUA==================");
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
        System.out.println("==================XOA HOC SINH==================");
        System.out.print("Nhap ma hoc sinh muon xoa: ");
        Scanner scanner = new Scanner(System.in);
        String maHocSinh = scanner.nextLine();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getMHS().equals(maHocSinh)) {
                students.remove(i);

                return;
            }
        }
        System.out.println("Khong tim thay hoc sinh voi ma da nhap!\n");

    }

    private static void listStudents(ArrayList<Student> students) {
        System.out.println("==================LIET KE THONG TIN HOC SINH==================");
        System.out.println("1. Ma hoc sinh tang dan");
        System.out.println("2. Ma hoc sinh giam dan");
        System.out.println("3. Diem tang dan");
        System.out.println("4. Diem giam dan");
        System.out.println("-1. Thoat");
        System.out.print("Nhap lua chon cua ban (bang so): ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case -1:
                return;

            case 1:
                sortAscendingMaHS(students);
                break;

            case 2:
                sortDescendingMaHS(students);
                break;

            case 3:
                sortAscendingDiem(students);
                break;

            case 4:
                sortDescendingDiem(students);
                break;

            default:
                break;
        }

        for (int i = 0; i < students.size(); i++) {
            System.out.println("Ma hoc sinh: " + students.get(i).getMHS());
            System.out.println("Ten hoc sinh: " + students.get(i).getTenHS());
            System.out.println("Diem: " + students.get(i).getDiem());
            System.out.println("Dia chi: " + students.get(i).getDiaChi());
            System.out.println("Ghi chu: " + students.get(i).getGhiChu());
            System.out.println();
        }

        System.out.print("Bam bat ky phim nao de tiep tuc...");
        scanner.nextLine();
    }

    private static ArrayList<Student> sortAscendingMaHS(ArrayList<Student> students) {
        int i, j;
        for (i = 0; i < students.size() - 1; i++) {
            for (j = 0; j < students.size() - i - 1; j++) {
                Long student1 = Long.parseLong(students.get(j).getMHS());
                Long student2 = Long.parseLong(students.get(j + 1).getMHS());
                if (student1 > student2) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        return students;
    }

    private static ArrayList<Student> sortDescendingMaHS(ArrayList<Student> students) {
        int i, j;
        for (i = 0; i < students.size() - 1; i++) {
            for (j = 0; j < students.size() - i - 1; j++) {
                Long student1 = Long.parseLong(students.get(j).getMHS());
                Long student2 = Long.parseLong(students.get(j + 1).getMHS());
                if (student1 < student2) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        return students;
    }

    private static ArrayList<Student> sortAscendingDiem(ArrayList<Student> students) {
        int i, j;
        for (i = 0; i < students.size() - 1; i++) {
            for (j = 0; j < students.size() - i - 1; j++) {
                Float student1 = students.get(j).getDiem();
                Float student2 = students.get(j + 1).getDiem();
                if (student1 > student2) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        return students;
    }

    private static ArrayList<Student> sortDescendingDiem(ArrayList<Student> students) {
        int i, j;
        for (i = 0; i < students.size() - 1; i++) {
            for (j = 0; j < students.size() - i - 1; j++) {
                Float student1 = students.get(j).getDiem();
                Float student2 = students.get(j + 1).getDiem();
                if (student1 < student2) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        return students;
    }

    private static ArrayList<Student> importFromCSV(ArrayList<Student> students) {
        try {
            students.clear();
            Scanner scanner = new Scanner(new File("Data/output.csv"));
            scanner.nextLine(); // Read first line containing column names
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals(""))
                    continue;

                String[] tokens = line.split(",", -1);
                Student student = new Student();

                student.setMHS(tokens[0]);
                student.setTenHS(tokens[1]);
                student.setDiem(Float.parseFloat(tokens[2]));
                student.setDiaChi(tokens[3]);
                student.setGhiChu(tokens[4]);

                students.add(student);
            }
            return students;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private static void exportToCSV(ArrayList<Student> students) {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter("Data/output.csv"));
            bWriter.write("MHS,TenHS,Diem,DiaChi,GhiChu\n");
            for (int i = 0; i < students.size(); i++) {
                Student std = students.get(i);
                bWriter.write(std.getMHS() + "," + std.getTenHS() + "," + std.getDiem() + "," + std.getDiaChi() + ","
                        + std.getGhiChu() + "\n");
            }
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() throws IOException {
        int option;
        ArrayList<Student> students = new ArrayList<>();
        do {
            System.out.println("==================MENU CHINH==================");
            System.out.println("1. Them hoc sinh");
            System.out.println("2. Cap nhat thong tin hoc sinh");
            System.out.println("3. Xoa hoc sinh");
            System.out.println("4. Liet ke danh sach hoc sinh");
            System.out.println("5. Load danh sach tu file nhi phan (Data/students.bin)");
            System.out.println("6. Ghi danh sach ra file nhi phan (Data/students.bin)");
            System.out.println("7. Import danh sach tu file csv (Data/output.csv)");
            System.out.println("8. Export danh sach ra file csv (Data/output.csv)");
            System.out.println("-1. Thoat");
            System.out.print("Nhap lua chon cua ban (bang chu so): ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addStudent(students);
                    break;
                case 2:
                    modifyStudent(students);
                    break;
                case 3:
                    removeStudent(students);
                    break;
                case 4:
                    listStudents(students);
                    break;
                case 5:
                    students = inputFromBinFile();
                    break;
                case 6:
                    outputToBinFile(students);
                    break;
                case 7:
                    importFromCSV(students);
                    break;
                case 8:
                    exportToCSV(students);
                    break;
                case -1:
                    System.out.println("GOODBYE!");
                    return;
                default:
                    break;
            }
        } while (option != -1);
    }

    public static void main(String[] args) throws Exception {
        showMenu();
    }
}