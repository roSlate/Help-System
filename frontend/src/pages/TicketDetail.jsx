import { useParams } from 'react-router'
import AppLayout from '../layouts/AppLayout/AppLayout.jsx'
import TicketDetailBlock from '../blocks/TicketDetail/TicketDetail.jsx'
import { sampleTickets } from '../blocks/TicketQueue/tickets.js'
import NotFound from './NotFound.jsx'

function TicketDetail() {
  const { id } = useParams()

  // ponytail: looks up sample data until GET /api/tickets/:id exists
  const ticket = sampleTickets.find((t) => t.id === id)
  if (!ticket) return <NotFound />

  return (
    <AppLayout>
      <TicketDetailBlock ticket={ticket} />
    </AppLayout>
  )
}

export default TicketDetail
