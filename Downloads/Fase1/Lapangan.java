public class Lapangan {
    public String idLapangan;
    public String namaLapangan;
    public String jenisOlahraga;
    public double hargaPerJam;
    public boolean tersedia;

    public Lapangan(String idLapangan, String namaLapangan, String jenisOlahraga, double hargaPerJam) {
        this.idLapangan = idLapangan;
        this.namaLapangan = namaLapangan;
        this.jenisOlahraga = jenisOlahraga;
        this.hargaPerJam = hargaPerJam;
        this.tersedia = true;
    }

    public void tampilkanInfo() {
        System.out.println("ID: " + idLapangan + " | Nama: " + namaLapangan + " | Jenis: " + jenisOlahraga + " | Harga/Jam: " + hargaPerJam + " | Tersedia: " + tersedia);
    }
}
