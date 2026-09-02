import TrackItem from './TrackItem.jsx'

// TrackList's only job is to turn the tracks array into a list of
// TrackItem children, passing each track's data plus a remove callback
// down as props. It owns no state of its own.
function TrackList({ tracks, onRemoveTrack }) {
  if (tracks.length === 0) {
    return <p className="tracklist-empty">No cuts yet — add the first one on the left.</p>
  }

  return (
    <ol className="tracklist">
      {tracks.map((track, index) => (
        <TrackItem
          key={track.id}
          index={index + 1}
          track={track}
          onRemove={() => onRemoveTrack(track.id)}
        />
      ))}
    </ol>
  )
}

export default TrackList
