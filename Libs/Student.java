package Libs;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String MHS;

    private String TenHS;

    private float Diem;

    private transient BufferedImage HinhAnh = null;

    private String DiaChi;

    private String GhiChu;

    public Student(String mHS, String tenHS, float diem, BufferedImage hinhAnh, String diaChi, String ghiChu) {
        MHS = mHS;
        TenHS = tenHS;
        Diem = diem;
        HinhAnh = hinhAnh;
        DiaChi = diaChi;
        GhiChu = ghiChu;
    }

    public Student() {
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getMHS() {
        return MHS;
    }

    public BufferedImage getHinhAnh() {
        return HinhAnh;
    }

    public float getDiem() {
        return Diem;
    }

    public String getTenHS() {
        return TenHS;
    }

    public void setDiem(float diem) {
        this.Diem = diem;
    }

    public void setTenHS(String tenHS) {
        this.TenHS = tenHS;
    }

    public void setMHS(String mHS) {
        this.MHS = mHS;
    }

    public void setHinhAnh(BufferedImage hinhAnh) {
        this.HinhAnh = hinhAnh;
    }

    public void setGhiChu(String ghiChu) {
        this.GhiChu = ghiChu;
    }

    public void setDiaChi(String diaChi) {
        this.DiaChi = diaChi;
    }

    public void printInfo() {
        System.out.println("Ma hoc sinh: " + MHS);
        System.out.println("Ten hoc sinh: " + TenHS);
        System.out.println("Diem: " + Diem);
        System.out.println("Dia chi: " + DiaChi);
        System.out.println("Ghi chu: " + GhiChu);
    }

    private void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.defaultWriteObject();
        ImageIO.write(HinhAnh, "png", oos);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        HinhAnh = ImageIO.read(in);
    }
}
