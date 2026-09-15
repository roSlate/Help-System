import Sidebar from '../../blocks/Sidebar/Sidebar.jsx'
import BottomNav from '../../blocks/BottomNav/BottomNav.jsx'
import './applayout.css'

function AppLayout({ children }) {
  return (
    <div className="app-shell">
      <main className="app-main">
        <div className="app-sidebar">
          <Sidebar />
        </div>
        <div className="app-body">{children}</div>
      </main>
      <BottomNav />
    </div>
  )
}

export default AppLayout
