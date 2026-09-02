// A small presentational component: it receives the track count as a prop
// and renders it. No state, no logic — just a view of data owned by App.
function PlaylistHeader({ trackCount }) {
  return (
    <div className="tracklist-header">
      <h2>Tracklist</h2>
      <p>
        {trackCount} {trackCount === 1 ? 'cut' : 'cuts'} on this side
      </p>
    </div>
  )
}

export default PlaylistHeader
