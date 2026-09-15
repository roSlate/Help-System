import { ArrowLeft, ArrowRight } from 'lucide-react'
import './ticketqueue.css'

//isto vai ser substituido por uma chamada a API para pegar os tickets do banco de dados
const sampleTickets = [
  { id: 'HD-2841', title: 'Aero glass reflection not rendering correctly on client portal', requester: 'Marcus Vance (AeroCorp)', category: 'UI/Theme Glitch', time: '10m ago', status: 'Open' },
  { id: 'HD-2839', title: 'Leaf badge status indicators fail to sync with API database', requester: 'Elena Rostova (GreenTech)', category: 'Backend Sync', time: '42m ago', status: 'In Progress' },
  { id: 'HD-2832', title: 'Requesting installer file for legacy Windows Vista theme patch', requester: 'Tariq Johnson', category: 'Downloads', time: '1h ago', status: 'Resolved' },
  { id: 'HD-2819', title: 'Translucency effect causing heavy performance lag on older clients', requester: 'Sven Lindqvist', category: 'Performance Optimization', time: '3h ago', status: 'In Progress' },
  { id: 'HD-2802', title: 'Dewdrop splash screen animation does not dismiss upon startup completion', requester: 'Fiona Gallagher', category: 'Visual Effects', time: 'Yesterday', status: 'Resolved' },
]

const statusClass = {
  'Open': 'status-open',
  'In Progress': 'status-progress',
  'Resolved': 'status-resolved',
}

function TicketQueue({ tickets = sampleTickets, total = 38, page = 1, perPage = 7 }) {

  const pageCount = Math.max(1, Math.ceil(total / perPage))
  const pages = Array.from({ length: pageCount }, (_, i) => i + 1)

  return (
    <section className="queue">
      <header className="queue-header">
        <div>
          <h2 className="sora-brand queue-title">Active Ticket Queue</h2>
          <p className="rubik-text queue-count">Showing {tickets.length} of {total} unresolved assignments</p>
        </div>
        <div className="queue-actions">
          <button className="rubik-subtitle queue-button" type="button">Create Ticket</button>
          <button className="rubik-subtitle queue-button" type="button">All Statuses</button>
          <button className="rubik-subtitle queue-button" type="button">Filter</button>
        </div>
      </header>

      <ul className="queue-list">
        {tickets.map((ticket) => (
          <li className="queue-row" key={ticket.id}>
            <span className="sora-subtitle queue-id">#{ticket.id}</span>
            <span className="queue-main">
              <span className="rubik-subtitle">{ticket.title}</span>
              <span className="rubik-text queue-meta">{ticket.requester} • {ticket.category}</span>
            </span>
            <span className="rubik-text queue-time">{ticket.time}</span>
            <span className={`status ${statusClass[ticket.status]} queue-status`}>{ticket.status}</span>
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
    </section>
  )
}

export default TicketQueue
