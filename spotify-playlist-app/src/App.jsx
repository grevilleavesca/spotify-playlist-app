import { useState } from 'react'
import PlaylistHeader from './components/PlaylistHeader.jsx'
import PlaylistForm from './components/PlaylistForm.jsx'
import TrackList from './components/TrackList.jsx'
import './App.css'

const STARTER_TRACKS = [
  { id: 1, title: 'Redondo Beach', artist: 'Patti Smith', duration: '3:24' },
  { id: 2, title: 'Solsbury Hill', artist: 'Peter Gabriel', duration: '4:21' },
  { id: 3, title: 'Harvest Moon', artist: 'Neil Young', duration: '5:04' },
]

const EMPTY_FORM = { title: '', artist: '', duration: '' }

function App() {
  // Playlist data lives here so both the form and the list can read/update it.
  const [tracks, setTracks] = useState(STARTER_TRACKS)

  // Controlled form input state is managed in the parent, per the brief —
  // PlaylistForm only renders values and reports change/submit events upward.
  const [formValues, setFormValues] = useState(EMPTY_FORM)

  function handleFormChange(field, value) {
    setFormValues((prev) => ({ ...prev, [field]: value }))
  }

  function handleAddTrack(event) {
    event.preventDefault()
    if (!formValues.title.trim() || !formValues.artist.trim()) return

    const newTrack = {
      id: Date.now(),
      title: formValues.title.trim(),
      artist: formValues.artist.trim(),
      duration: formValues.duration.trim() || '—',
    }

    setTracks((prev) => [...prev, newTrack])
    setFormValues(EMPTY_FORM)
  }

  function handleRemoveTrack(id) {
    setTracks((prev) => prev.filter((track) => track.id !== id))
  }

  return (
    <div className="sleeve">
      <div className="sleeve__grid">
        <section className="sleeve__panel" aria-label="Add a track">
          <p className="eyebrow">Now cutting</p>
          <h1 className="sleeve__title">Side A</h1>
          <p className="sleeve__subtitle">
            Build the tracklist one cut at a time. Title and artist required —
            leave the runtime blank if you're not sure yet.
          </p>
          <PlaylistForm
            values={formValues}
            onFieldChange={handleFormChange}
            onSubmit={handleAddTrack}
          />
        </section>

        <section className="sleeve__panel sleeve__panel--dark" aria-label="Tracklist">
          <PlaylistHeader trackCount={tracks.length} />
          <TrackList tracks={tracks} onRemoveTrack={handleRemoveTrack} />
        </section>
      </div>
    </div>
  )
}

export default App
