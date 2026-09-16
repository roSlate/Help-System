import { useEffect } from 'react'
import '../Login/login.css'
import './createticket.css'

function CreateTicket({ onClose, onSubmit }) {
  // Esc closes the overlay
  useEffect(() => {
    const handleKey = (e) => {
      if (e.key === 'Escape') onClose()
    }
    document.addEventListener('keydown', handleKey)
    return () => document.removeEventListener('keydown', handleKey)
  }, [onClose])

  return (
    <div className="create-ticket-overlay" onClick={onClose}>
      <form
        className="login-card create-ticket"
        role="dialog"
        aria-modal="true"
        aria-labelledby="create-ticket-title"
        onClick={(e) => e.stopPropagation()}
        onSubmit={onSubmit}
      >
        <h2 id="create-ticket-title" className="sora-brand create-ticket-title">Create a ticket</h2>

        <div className="login-field">
          <label className="rubik-subtitle" htmlFor="ticket-name">Ticket name</label>
          <div className="login-input">
            <input id="ticket-name" name="name" type="text" placeholder="ticket" required autoFocus />
          </div>
        </div>

        <div className="login-field">
          <label className="rubik-subtitle" htmlFor="ticket-description">Description</label>
          <div className="login-input">
            <textarea id="ticket-description" name="description" rows="6" placeholder="description" required />
          </div>
        </div>

        <button className="sora-brand login-submit" type="submit">Register</button>
      </form>
    </div>
  )
}

export default CreateTicket
