import { NavLink } from 'react-router'
import {
  LayoutDashboard, Ticket, BookOpen, ChartColumn, Settings,
  Flag, Layers, CircleCheck,
} from 'lucide-react'
import './sidebar.css'

const navigation = [
  { to: '/dashboard', label: 'Dashboard', icon: LayoutDashboard },
  { to: '/tickets', label: 'Tickets', icon: Ticket },
  { to: '/knowledge-base', label: 'Knowledge Base', icon: BookOpen },
  { to: '/reports', label: 'Reports', icon: ChartColumn },
  { to: '/settings', label: 'Settings', icon: Settings },
]

const queues = [
  { to: '/queues/high-priority', label: 'High Priority', icon: Flag },
  { to: '/queues/aero-glass', label: 'Aero Glass Issues', icon: Layers },
  { to: '/queues/resolved-today', label: 'Resolved (Today)', icon: CircleCheck },
]

function SidebarLink({ to, label, icon: Icon }) {
  return (
    <li>
      <NavLink className="rubik-subtitle sidebar-link" to={to}>
        <Icon className="icon icon-dark" />
        {label}
      </NavLink>
    </li>
  )
}

function Sidebar({ status = 'Closed' }) {
  return (
    <aside className="sidebar">
      <nav>
        <h2 className="sora-subtitle sidebar-heading">Navigation</h2>
        <ul className="sidebar-list">
          {navigation.map((item) => <SidebarLink key={item.to} {...item} />)}
        </ul>
      </nav>

      <hr className="sidebar-divider" />

      <nav>
        <h2 className="sora-subtitle sidebar-heading">My Queues</h2>
        <ul className="sidebar-list">
          {queues.map((item) => <SidebarLink key={item.to} {...item} />)}
        </ul>
      </nav>

      <p className="rubik-subtitle sidebar-status">System Status: {status}</p>
    </aside>
  )
}

export default Sidebar
