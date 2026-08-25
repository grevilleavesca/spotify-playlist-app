# Student Hub — Grevillea Vesca (2505551070)

Web personal *Student Hub* untuk mahasiswa Teknologi Informasi (Sistem
Informasi) Universitas Udayana, dibangun dengan **React + Vite**. Desain
mengangkat tema *system schematic / blueprint* — garis putus-putus dan node
penghubung antar-bagian — untuk merepresentasikan identitas Sistem Informasi
sebagai perancang alur sistem.

---

## 1. Langkah-Langkah (Step-by-Step)

Berikut runtutan perintah terminal dari awal setup proyek hingga menjalankan
`npm run dev`.

```bash
# 1. Buat folder proyek dan masuk ke dalamnya
mkdir student-hub
cd student-hub

# 2. Inisialisasi proyek Vite + React
#    (jika membuat dari template resmi Vite)
npm create vite@latest . -- --template react

# 3. Pasang seluruh dependency yang tercantum di package.json
npm install

# 4. Tempatkan/timpa file src/App.jsx, src/index.css, dan src/main.jsx
#    dengan isi Student Hub ini

# 5. Jalankan server pengembangan (development server)
npm run dev
#    -> Vite akan menampilkan URL lokal, contoh: http://localhost:5173

# 6. (Opsional) Build untuk produksi
npm run build

# 7. (Opsional) Pratinjau hasil build produksi secara lokal
npm run preview
```

Setelah `npm run dev` berjalan, buka URL yang ditampilkan di terminal pada
browser untuk melihat halaman Student Hub secara langsung. Perubahan pada
file di dalam `src/` akan otomatis ter-refresh berkat Hot Module Replacement
(HMR) bawaan Vite.

---

## 2. AI Prompt Log

Berikut prompt yang digunakan selama proses pengembangan dengan bantuan AI
(Claude):

1. *"Buatkan Web Student Hub Personal Mahasiswa TI Unud: Header & Navbar
   Semantik (Nama, NIM, 1 dari 4 Bidang Minat Kurikulum 2026), Hero Profile
   (Bio ringkas & Target Profil Lulusan PL-01/PL-02/PL-03), Interactive
   Counter (useState) untuk Stat Card 'Project Selesai' yang bisa bertambah
   nilainya saat tombol diklik, dan Showcase 3 Card Project berisi ide
   proyek impian selama kuliah."*
2. Data kurikulum diberikan secara manual oleh pengguna: 4 pilihan Bidang
   Minat Kurikulum 2026 (Data Sains dan Sistem Cerdas, Tata Kelola dan
   Bisnis TI, Sistem Informasi, IoT dan Jaringan) beserta deskripsi lengkap
   tiga Profil Lulusan (PL-01, PL-02, PL-03).
3. *"Saya Sistem Informasi dan PL-01."*
4. *"Nama: Grevillea Vesca, NIM 2505551070. Carikan 3 ide [proyek
   impian] tersebut."* — AI kemudian menyusun tiga ide proyek (SIMDESA,
   EcoTrack Bali, UdayanaConnect) yang relevan dengan konsentrasi Sistem
   Informasi dan konteks lokal Bali/Udayana.

Dari prompt-prompt tersebut, AI menyusun struktur komponen React
(`App.jsx`), styling (`index.css` bertema *blueprint/schematic*), serta
laporan ini (`README.md`).

---

## 3. Trace Alur Eksekusi

Alur eksekusi aplikasi dari file pertama yang dimuat browser hingga UI
tampil di layar:

```
index.html
   │  Browser membuka index.html sebagai entry point.
   │  Di dalamnya terdapat <div id="root"></div> sebagai wadah kosong
   │  dan <script type="module" src="/src/main.jsx">.
   ▼
main.jsx
   │  Vite memuat main.jsx sebagai modul JavaScript utama.
   │  File ini memanggil ReactDOM.createRoot() pada elemen #root,
   │  lalu me-render komponen <App /> yang diimpor dari App.jsx.
   ▼
App.jsx
   │  Komponen App merender struktur halaman: <Navbar />,
   │  <HeroProfile />, <ProjectCounter />, dan <ProjectShowcase />.
   │  React membangun Virtual DOM dari komponen-komponen ini,
   │  lalu mem-flush-nya ke DOM asli di dalam <div id="root">.
   ▼
Browser (UI Tampil)
      Halaman Student Hub lengkap dengan Header/Navbar, Hero Profile,
      Interactive Counter, dan Showcase 3 Project ditampilkan ke pengguna.
```

Setiap interaksi pengguna selanjutnya (misalnya klik tombol "+ Tambah
Project") memicu `setCompleted()` di dalam `App.jsx`, yang mengubah state
komponen `ProjectCounter`. React lalu me-render ulang (re-render) hanya
bagian UI yang terpengaruh oleh state tersebut, tanpa memuat ulang
`index.html` atau `main.jsx`.

---

## 4. Bedah & HTML5 Semantik

`App.jsx` disusun menggunakan elemen semantik HTML5 alih-alih `<div>` generik
di setiap bagian, dengan alasan berikut:

| Elemen         | Digunakan untuk                          | Alasan Pemilihan |
|----------------|-------------------------------------------|-------------------|
| `<header>`     | Pembungkus Navbar                         | Menandakan area identitas/branding di bagian atas halaman, terpisah dari konten utama. |
| `<nav>`        | Daftar tautan navigasi (Profil, Progres, Proyek) | Memberi tahu browser dan pembaca layar (screen reader) bahwa elemen ini adalah kumpulan tautan navigasi, bukan sekadar daftar teks. |
| `<main>`       | Pembungkus seluruh konten inti halaman    | Hanya boleh ada satu per halaman; membantu teknologi aksesibilitas melompat langsung ke konten utama. |
| `<section>`    | Blok Hero Profile, Interactive Counter, dan Showcase | Setiap `<section>` merepresentasikan satu bagian tematik berdiri sendiri (masing-masing punya `id` dan `aria-labelledby`), sehingga dapat ditautkan langsung dari Navbar. |
| `<article>`    | Panel Hero Profile & setiap kartu proyek  | Konten yang secara mandiri dapat berdiri sendiri/didistribusikan ulang — profil mahasiswa dan setiap ide proyek adalah unit informasi yang lengkap dengan sendirinya. |
| `<aside>`      | Stat Card 'Project Selesai'               | Merupakan informasi pelengkap (statistik progres) yang menyertai konten utama, bukan bagian dari alur naratif utama halaman. |
| `<footer>`     | Penutup halaman                           | Menandai informasi metadata (hak cipta, identitas) yang relevan di seluruh halaman. |

Selain elemen struktural, atribut aksesibilitas seperti `aria-label`,
`aria-labelledby`, dan `aria-live="polite"` pada angka counter juga
digunakan agar pembaca layar dapat mengumumkan perubahan angka "Project
Selesai" secara otomatis setiap kali bertambah — sejalan dengan prinsip
HTML5 semantik yang menekankan makna konten, bukan sekadar tampilan visual.

---

## Struktur Proyek

```
student-hub/
├── index.html
├── package.json
├── vite.config.js
├── src/
│   ├── main.jsx
│   ├── App.jsx
│   └── index.css
└── README.md
```
