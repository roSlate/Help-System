import { Link } from 'react-router'
import { ArrowUp } from 'lucide-react'
import { statusClass } from '../TicketQueue/tickets.js'
import '../TicketQueue/ticketqueue.css'
import './ticketdetail.css'

// ponytail: sample question and answers until the API returns them with the ticket
const sampleThread = {
  votes: 12,
  question: "The glass effect appears broken on the login screen. I've tried clearing cache and checking browser versions, but the issue persists. The reflection layer is missing, and the portal background appears flat.",
  answers: [
    { id: 1, author: 'John Doe', time: '5m ago', votes: 4, text: "Can you confirm whether you're using the latest theme bundle? There was a regression in the reflection shader that should be fixed in v1.2.3." },
    { id: 2, author: 'Elena Rostova', time: '2m ago', votes: 2, text: 'Try disabling hardware acceleration in the portal settings. If that resolves it, we may need to add a fallback for older GPUs.' },
  ],
}

function Vote({ count, row = false }) {
  return (
    <span className={row ? 'detail-vote detail-vote-row' : 'detail-vote'}>
      <button className="detail-vote-button" type="button" aria-label="Upvote">
        <ArrowUp className="icon icon-dark" />
      </button>
      <span className="rubik-subtitle">{count}</span>
    </span>
  )
}

function TicketDetail({ ticket, thread = sampleThread }) {
  const { answers } = thread

  // ponytail: no API yet, posting does nothing
  function handleReply(e) {
    e.preventDefault()
  }

  return (
    <section className="detail">
      <nav className="rubik-text detail-breadcrumb" aria-label="Breadcrumb">
        <Link to="/tickets">Tickets</Link>
        <span aria-hidden="true">&gt;</span>
        <span className="rubik-subtitle" aria-current="page">Ticket #{ticket.id}</span>
      </nav>

      <header className="detail-header">
        <Vote count={thread.votes} />
        <div>
          <h2 className="sora-brand detail-title">{ticket.title}</h2>
          <p className="rubik-text detail-meta">{ticket.requester} • {ticket.time}</p>
        </div>
        <div className="detail-actions">
          <button className="rubik-subtitle queue-button" type="button">Edit ticket</button>
          <button className="rubik-subtitle queue-button" type="button">Close ticket</button>
          <span className={`status ${statusClass[ticket.status]} queue-status`}>{ticket.status}</span>
        </div>
      </header>

      <section className="detail-section">
        <h3 className="rubik-subtitle detail-subheading">Question</h3>
        <p className="rubik-text detail-text">{thread.question}</p>
      </section>

      <section className="detail-section">
        <h3 className="sora-brand detail-subheading">
          {answers.length} {answers.length === 1 ? 'Answer' : 'Answers'}
        </h3>
        <ul className="detail-answers">
          {answers.map((answer) => (
            <li className="detail-answer" key={answer.id}>
              <div className="detail-answer-head">
                <span className="rubik-subtitle">{answer.author}</span>
                <span className="rubik-text detail-meta">• {answer.time}</span>
                <Vote count={answer.votes} row />
              </div>
              <p className="rubik-text detail-text">{answer.text}</p>
            </li>
          ))}
        </ul>
      </section>

      <form className="detail-section" onSubmit={handleReply}>
        <label className="rubik-subtitle detail-subheading" htmlFor="reply">Reply</label>
        <div className="detail-reply"> 
          <input id="reply" name="reply" className="rubik-text detail-reply-input" placeholder="Write your answer..." required />
          <button className="rubik-subtitle queue-button detail-post" type="submit">Post Answer</button>
        </div>
      </form>
    </section>
  )
}

export default TicketDetail
