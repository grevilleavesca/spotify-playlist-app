// The leaf of the component tree. Purely presentational: it reads
// `track` and `index` from props, and calls `onRemove` — a callback
// handed down through TrackList — when the button is clicked.
function TrackItem({ index, track, onRemove }) {
  return (
    <li className="track-item">
      <span className="track-item__index">{String(index).padStart(2, '0')}</span>
      <span className="track-item__info">
        <span className="track-item__title">{track.title}</span>
        <span className="track-item__artist">{track.artist}</span>
      </span>
      <span className="track-item__duration">{track.duration}</span>
      <button
        type="button"
        className="track-item__remove"
        onClick={onRemove}
        aria-label={`Remove ${track.title}`}
      >
        ×
      </button>
    </li>
  )
}

export default TrackItem
