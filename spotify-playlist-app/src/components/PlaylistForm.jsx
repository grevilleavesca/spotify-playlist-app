// PlaylistForm is intentionally "dumb": it holds no state of its own.
// Every input's value comes from props, and every keystroke is reported
// back up to App.jsx via onFieldChange — that's what makes this a
// controlled form with the source of truth in the parent.
function PlaylistForm({ values, onFieldChange, onSubmit }) {
  return (
    <form className="track-form" onSubmit={onSubmit}>
      <label className="track-form__field">
        <span>Track title</span>
        <input
          type="text"
          value={values.title}
          onChange={(e) => onFieldChange('title', e.target.value)}
          placeholder="Redondo Beach"
          required
        />
      </label>

      <label className="track-form__field">
        <span>Artist</span>
        <input
          type="text"
          value={values.artist}
          onChange={(e) => onFieldChange('artist', e.target.value)}
          placeholder="Patti Smith"
          required
        />
      </label>

      <label className="track-form__field">
        <span>Runtime</span>
        <input
          type="text"
          value={values.duration}
          onChange={(e) => onFieldChange('duration', e.target.value)}
          placeholder="3:24"
        />
      </label>

      <button type="submit" className="track-form__submit">
        Add to Side A
      </button>
    </form>
  )
}

export default PlaylistForm
