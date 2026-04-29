public class SistemLapangan {
    Lapangan[] daftarLapangan = new Lapangan[10];
    Pemesanan[] daftarPemesanan = new Pemesanan[50];
    int jumlahLapangan = 0;
    int jumlahPemesanan = 0;

    public SistemLapangan() {
        // tambah data awal manual
        daftarLapangan[0] = new Lapangan("L001", "Lapangan Badminton A", "Badminton", 30000);
        daftarLapangan[1] = new Lapangan("L002", "Lapangan Badminton B", "Badminton", 30000);
        daftarLapangan[2] = new Lapangan("L003", "Lapangan Futsal Utama", "Futsal", 100000);
        daftarLapangan[3] = new Lapangan("L004", "Lapangan Basket Indoor", "Basket", 80000);
        jumlahLapangan = 4;
    }

    public void tampilkanSemuaLapangan() {
        System.out.println("Daftar Lapangan:");
        for (int i = 0; i < jumlahLapangan; i++) {
            daftarLapangan[i].tampilkanInfo();
        }
    }

    public void tampilkanLapanganTersedia() {
        System.out.println("Lapangan yang tersedia:");
        for (int i = 0; i < jumlahLapangan; i++) {
            if (daftarLapangan[i].tersedia == true) {
                daftarLapangan[i].tampilkanInfo();
            }
        }
    }

    public void buatPemesanan(String namaPemesan, String idLapangan, String tanggal, int jamMulai, int jamSelesai) {
        // cari lapangan
        int indexLapangan = -1;
        for (int i = 0; i < jumlahLapangan; i++) {
            if (daftarLapangan[i].idLapangan.equals(idLapangan)) {
                indexLapangan = i;
            }
        }

        if (indexLapangan == -1) {
            System.out.println("Lapangan tidak ditemukan");
            return;
        }

        if (daftarLapangan[indexLapangan].tersedia == false) {
            System.out.println("Lapangan tidak tersedia");
            return;
        }

        if (jamMulai > jamSelesai) {
            System.out.println("Jam salah");
            return;
        }

        String idPemesanan = "PES" + jumlahPemesanan;
        double harga = daftarLapangan[indexLapangan].hargaPerJam;
        String namaLapangan = daftarLapangan[indexLapangan].namaLapangan;

        Pemesanan p = new Pemesanan(idPemesanan, namaPemesan, idLapangan, namaLapangan, tanggal, jamMulai, jamSelesai, harga);
        daftarPemesanan[jumlahPemesanan] = p;
        jumlahPemesanan++;

        System.out.println("Pemesanan berhasil!");
        p.tampilkanDetail();
    }

    public void tampilkanSemuaPemesanan() {
        if (jumlahPemesanan == 0) {
            System.out.println("Belum ada pemesanan");
            return;
        }
        System.out.println("Semua Pemesanan:");
        for (int i = 0; i < jumlahPemesanan; i++) {
            daftarPemesanan[i].tampilkanDetail();
            System.out.println("---");
        }
    }

    public void tambahLapangan(String id, String nama, String jenis, double harga) {
        if (jumlahLapangan >= 10) {
            System.out.println("Lapangan penuh");
            return;
        }
        daftarLapangan[jumlahLapangan] = new Lapangan(id, nama, jenis, harga);
        jumlahLapangan++;
        System.out.println("Lapangan berhasil ditambahkan");
    }

    public void cariPemesanan(String nama) {
        System.out.println("Pemesanan atas nama " + nama + ":");
        for (int i = 0; i < jumlahPemesanan; i++) {
            if (daftarPemesanan[i].namaPemesan == nama) {
                daftarPemesanan[i].tampilkanDetail();
            }
        }
    }
}
