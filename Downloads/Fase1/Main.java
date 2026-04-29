import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemLapangan sistem = new SistemLapangan();

        System.out.println("Selamat Datang di Sistem Pemesanan Lapangan Olahraga");

        int pilihan = 0;

        while (pilihan != 6) {
            System.out.println("");
            System.out.println("Menu:");
            System.out.println("1. Lihat semua lapangan");
            System.out.println("2. Lihat lapangan tersedia");
            System.out.println("3. Buat pemesanan");
            System.out.println("4. Lihat semua pemesanan");
            System.out.println("5. Tambah lapangan");
            System.out.println("6. Keluar");
            System.out.print("Pilih: ");

            pilihan = sc.nextInt();
            sc.nextLine();

            if (pilihan == 1) {
                sistem.tampilkanSemuaLapangan();

            } else if (pilihan == 2) {
                sistem.tampilkanLapanganTersedia();

            } else if (pilihan == 3) {
                System.out.print("Nama pemesan: ");
                String nama = sc.nextLine();

                sistem.tampilkanLapanganTersedia();
                System.out.print("ID Lapangan: ");
                String idLapangan = sc.nextLine();

                System.out.print("Tanggal: ");
                String tanggal = sc.nextLine();

                System.out.print("Jam mulai: ");
                int jamMulai = sc.nextInt();
                sc.nextLine();

                System.out.print("Jam selesai: ");
                int jamSelesai = sc.nextInt();
                sc.nextLine();

                sistem.buatPemesanan(nama, idLapangan, tanggal, jamMulai, jamSelesai);

            } else if (pilihan == 4) {
                sistem.tampilkanSemuaPemesanan();

            } else if (pilihan == 5) {
                System.out.print("ID Lapangan: ");
                String id = sc.nextLine();
                System.out.print("Nama Lapangan: ");
                String nama = sc.nextLine();
                System.out.print("Jenis: ");
                String jenis = sc.nextLine();
                System.out.print("Harga per jam: ");
                double harga = sc.nextDouble();
                sc.nextLine();

                sistem.tambahLapangan(id, nama, jenis, harga);

            } else if (pilihan == 6) {
                System.out.println("Keluar...");

            } else {
                System.out.println("Pilihan salah");
            }
        }

        sc.close();
    }
}
