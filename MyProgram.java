import javax.imageio.*;
import java.io.File;
import java.io.IOException;

import Libs.Student;

public class MyProgram {
    public static void main(String[] args) {
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
    }
}