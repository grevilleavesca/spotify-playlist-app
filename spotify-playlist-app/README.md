# Side A — Playlist Builder (Code Defense Report)

Tema: **Spotify Playlist** (dibungkus sebagai "liner notes" tracklist sebuah rekaman).

## 1. Diagram Pohon Komponen

```
App.jsx  (state: tracks[], formValues{title, artist, duration})
│
├── PlaylistHeader        props → trackCount
│
├── PlaylistForm          props → values, onFieldChange, onSubmit
│
└── TrackList             props → tracks, onRemoveTrack
        │
        └── TrackItem (× N)   props → index, track, onRemove
```

**Alur Props (Parent → Child):**
- `App` menyimpan seluruh state aplikasi: daftar lagu (`tracks`) dan nilai form (`formValues`).
- `PlaylistHeader` hanya menerima `trackCount` (angka) untuk ditampilkan — murni presentational.
- `PlaylistForm` menerima `values` (object) beserta dua *event callback*: `onFieldChange` (dipanggil setiap `onChange` input) dan `onSubmit` (dipanggil saat form disubmit). Form ini tidak menyimpan state sendiri — semua nilai input berasal dari `App`, sehingga statusnya benar-benar *controlled*.
- `TrackList` menerima array `tracks` dan callback `onRemoveTrack`. Ia melakukan `.map()` untuk merender satu `TrackItem` per lagu.
- `TrackItem` menerima `track`, `index`, dan sebuah fungsi `onRemove` yang sudah "dibungkus" oleh `TrackList` (`() => onRemoveTrack(track.id)`), sehingga `TrackItem` tidak perlu tahu apa-apa soal `id` — ia cukup memanggil `onRemove()` saat tombol × diklik.

Ini adalah pola *"data turun, event naik"*: data (tracks, values) mengalir turun lewat props, sedangkan aksi pengguna (klik, ketik) naik kembali ke `App` lewat callback, dan `App` yang mengubah state.

## 2. Bedah Code

### `useState` di App.jsx
```jsx
const [tracks, setTracks] = useState(STARTER_TRACKS)
const [formValues, setFormValues] = useState(EMPTY_FORM)
```
- `tracks` menyimpan array objek lagu `{ id, title, artist, duration }`. Ini adalah *single source of truth* untuk seluruh tracklist.
- `formValues` menyimpan nilai tiga input form (`title`, `artist`, `duration`) sebagai satu object. Karena form dikendalikan penuh dari parent, setiap keystroke di input manapun langsung memperbarui state ini lewat `setFormValues`.

### Fungsi handler
- **`handleFormChange(field, value)`** — dipanggil dari `PlaylistForm` setiap kali user mengetik. Menggunakan *computed property name* (`[field]: value`) supaya satu fungsi bisa menangani ketiga input sekaligus, lalu men-*spread* state lama (`...prev`) agar field lain tidak hilang.
- **`handleAddTrack(event)`** — dipanggil saat form disubmit. Memanggil `event.preventDefault()` supaya halaman tidak reload, memvalidasi bahwa `title` dan `artist` tidak kosong, membentuk objek `newTrack` baru dengan `id` dari `Date.now()`, menambahkannya ke `tracks` lewat *functional update* (`setTracks(prev => [...prev, newTrack])`), lalu mengosongkan form kembali ke `EMPTY_FORM`.
- **`handleRemoveTrack(id)`** — memfilter `tracks` agar lagu dengan `id` yang cocok dihapus dari array, menggunakan `Array.prototype.filter`.

Ketiga handler ini didefinisikan di `App.jsx` dan diteruskan sebagai props ke komponen anak — inilah mekanisme *event callback* yang diminta di brief.

## 3. Log Prompt AI

Prompt yang digunakan untuk membangun aplikasi ini (via Claude):

> "Buatkan Web App Interaktif Modular tema Spotify Playlist: wajib terpisah minimal 3 komponen modular di folder src/components/, managing state & controlled form input di Parent (App.jsx), props passing data & event callback dari Parent ke Child. Sertakan juga README.md code defense berisi diagram pohon komponen, bedah code useState & handler, dan log prompt AI ini."

*(Ganti/lengkapi bagian ini dengan prompt tambahan yang kamu gunakan sendiri saat mengembangkan atau menyesuaikan aplikasi, jika ada.)*

## Menjalankan proyek

```bash
npm install
npm run dev
```
