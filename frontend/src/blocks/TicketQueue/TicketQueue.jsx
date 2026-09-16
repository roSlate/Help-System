import { useState } from 'react'
import { Link } from 'react-router'
import { ArrowLeft, ArrowRight } from 'lucide-react'
import CreateTicket from '../CreateTicket/CreateTicket.jsx'
import { sampleTickets, statusClass } from './tickets.js'
import './ticketqueue.css'

function TicketQueue({ tickets = sampleTickets, total = 38, page = 1, perPage = 7 }) {

  const pageCount = Math.max(1, Math.ceil(total / perPage))
  const pages = Array.from({ length: pageCount }, (_, i) => i + 1)
  const [creating, setCreating] = useState(false)

  // ponytail: no API yet, submitting only closes the overlay
  function handleCreate(e) {
    e.preventDefault()
    setCreating(false)
  }

  return (
    <section className="queue">
      <header className="queue-header">
        <div>
          <h2 className="sora-brand queue-title">Active Ticket Queue</h2>
          <p className="rubik-text queue-count">Showing {tickets.length} of {total} unresolved assignments</p>
        </div>
        <div className="queue-actions">
          <button className="rubik-subtitle queue-button" type="button" onClick={() => setCreating(true)}>Create Ticket</button>
          <button className="rubik-subtitle queue-button" type="button">All Statuses</button>
          <button className="rubik-subtitle queue-button" type="button">Filter</button>
        </div>
      </header>

      <ul className="queue-list">
        {tickets.map((ticket) => (
          <li key={ticket.id}>
            <Link className="queue-row" to={`/tickets/${ticket.id}`}>
              <span className="sora-subtitle queue-id">#{ticket.id}</span>
              <span className="queue-main">
                <span className="rubik-subtitle">{ticket.title}</span>
                <span className="rubik-text queue-meta">{ticket.requester} • {ticket.category}</span>
              </span>
              <span className="rubik-text queue-time">{ticket.time}</span>
              <span className={`status ${statusClass[ticket.status]} queue-status`}>{ticket.status}</span>
            </Link>
          </li>
        ))}
      </ul>

      <nav className="queue-pagination" aria-label="Pages">
        <button className="queue-page queue-arrow" type="button" aria-label="Previous page">
          <ArrowLeft className="icon icon-dark" />
        </button>
        {pages.map((n) => (
          <button key={n} className="rubik-subtitle queue-page" type="button" aria-current={n === page ? 'page' : undefined}>
            {n}
          </button>
        ))}
        <button className="queue-page queue-arrow" type="button" aria-label="Next page">
          <ArrowRight className="icon icon-dark" />
        </button>
      </nav>

      {creating && <CreateTicket onClose={() => setCreating(false)} onSubmit={handleCreate} />}
    </section>
  )
}

export default TicketQueue
