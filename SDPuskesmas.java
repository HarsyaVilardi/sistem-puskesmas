package sdpuskesmas;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Scanner;

public class SDPuskesmas {

    public static class Search<T> {
        public T search(T[] array, String criteria) {
            for (T item : array) {
                if (item instanceof dokter) {
                    dokter dokterItem = (dokter) item;
                    if (Objects.equals(dokterItem.nama(), criteria)) {
                        return item;
                    }
                }
            }
            return null;
        }
    }

    public record pasien(String nama, int umur, int nik, String penyakit, String kategori) {}
    public record dokter(String nama, String poli, String ruangan, String jam) {}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Daftar pasien yang akan berobat");
        pasien person1 = new pasien("kiara", 4, 32416, "demam", "4.balita");
        pasien person2 = new pasien("neneng", 60, 32427, "jantung", "2.lansia");
        pasien person3 = new pasien("siti", 25, 32418, "melahirkan", "1.umum");
        pasien person4 = new pasien("zaky", 21, 32425, "sakit gigi", "4.bpjs");

        System.out.println("Nama : " + person1.nama() + ", Umur: " + person1.umur() + ", Nik: " + person1.nik() + ", Penyakit: " + person1.penyakit());
        System.out.println("Nama : " + person2.nama() + ", Umur: " + person2.umur() + ", Nik: " + person2.nik() + ", Penyakit: " + person2.penyakit());
        System.out.println("Nama : " + person3.nama() + ", Umur: " + person3.umur() + ", Nik: " + person3.nik() + ", Penyakit: " + person3.penyakit());
        System.out.println("Nama : " + person4.nama() + ", Umur: " + person4.umur() + ", Nik: " + person4.nik() + ", Penyakit: " + person4.penyakit() + "\n");

        dokter[] d = new dokter[5];
        d[0] = new dokter("dr lily", "umum", "gedung A", "11-12");
        d[1] = new dokter("dr xena", "speasalis gigi", "gedung B", "13-14");
        d[2] = new dokter("dr rara", "spesialis jantung", "gedung C", "9-11");
        d[3] = new dokter("dr farid", "spesialis kandungan", "gedung E", "24");
        d[4] = new dokter("dr satria", "spesialis mata", "gedung D", "7-10");

        Search<dokter> listdokter = new Search<>();

        System.out.print("Masukkan nama dokter yang ingin dicari: ");
        String namaDokterCari = scanner.nextLine();

        dokter searchedDokter = listdokter.search(d, namaDokterCari);

        if (searchedDokter != null) {
            System.out.println("Dokter yang ditemukan: " + searchedDokter.nama() +
                    ", Poli: " + searchedDokter.poli() +
                    ", Ruangan: " + searchedDokter.ruangan() +
                    ", Jam: " + searchedDokter.jam());
        } else {
            System.out.println("Dokter tidak ditemukan.");
        }

        System.out.println("Dokter yang mengobati:");
        for (dokter currentDokter : d) {
            System.out.println("nama: " + currentDokter.nama() +
                    ", Poli: " + currentDokter.poli() +
                    ", Ruangan: " + currentDokter.ruangan() +
                    ", Jam: " + currentDokter.jam());
        }
        System.out.println(" ");

        PriorityQueue<pasien> borrowingQueue = new PriorityQueue<>(Comparator.comparing(pasien::kategori));
        borrowingQueue.add(person1);
        borrowingQueue.add(person2);
        borrowingQueue.add(person3);
        borrowingQueue.add(person4);

        System.out.println("Urutan Antrian Berdasarkan Kategori :");
        while (!borrowingQueue.isEmpty()) {
            pasien pa = borrowingQueue.poll();
            System.out.println(pa.nama() + " Telah selesai di periksa");
        }
        System.out.println(" ");

        scanner.close();
    }
}