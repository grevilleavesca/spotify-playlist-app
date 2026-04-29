public class Pemesanan {
    public String idPemesanan;
    public String namaPemesan;
    public String idLapangan;
    public String namaLapangan;
    public String tanggal;
    public int jamMulai;
    public int jamSelesai;
    public double totalBiaya;

    public Pemesanan(String idPemesanan, String namaPemesan, String idLapangan, String namaLapangan, String tanggal, int jamMulai, int jamSelesai, double hargaPerJam) {
        this.idPemesanan = idPemesanan;
        this.namaPemesan = namaPemesan;
        this.idLapangan = idLapangan;
        this.namaLapangan = namaLapangan;
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        // hitung biaya
        int durasi = jamSelesai - jamMulai;
        totalBiaya = durasi * hargaPerJam;
    }

    public void tampilkanDetail() {
        System.out.println("ID Pemesanan: " + idPemesanan);
        System.out.println("Nama: " + namaPemesan);
        System.out.println("Lapangan: " + namaLapangan);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Jam: " + jamMulai + " - " + jamSelesai);
        System.out.println("Total: " + totalBiaya);
    }
}
