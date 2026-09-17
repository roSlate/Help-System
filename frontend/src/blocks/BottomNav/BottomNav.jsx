import { LifeBuoy, Search, User } from 'lucide-react'
import './bottomnav.css'

function BottomNav({ userName, userRole, onSearch }) {
  return (
    <nav className="bottomnav">
      <div className="bottomnav-brand">
        <span className="bottomnav-logo">
          <LifeBuoy className="icon" />
        </span>
        <span className="sora-brand">HelpDesk <span className="bottomnav-accent">Pro</span></span>
      </div>

      <div className="bottomnav-search">
        <Search className="icon icon-dark" />
        <input
          type="search"
          name="q"
          placeholder="Search tickets, customers, docs..."
          onChange={onSearch}
        />
      </div>

      <div className="bottomnav-user">
        <span className="bottomnav-user-text">
          <span className="rubik-subtitle">{userName}</span>
          <span className="rubik-text bottomnav-role">{userRole}</span>
        </span>
        <span className="bottomnav-avatar">
          <User className="icon icon-dark" />
        </span>
      </div>
    </nav>
  )
}

export default BottomNav
