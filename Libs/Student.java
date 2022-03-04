package Libs;

import java.awt.image.BufferedImage;

public class Student {
    private String MHS;

    private String TenHS;

    private float Diem;

    private BufferedImage HinhAnh = null;

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
}
