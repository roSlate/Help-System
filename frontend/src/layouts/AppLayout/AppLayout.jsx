import Sidebar from '../../blocks/Sidebar/Sidebar.jsx'
import BottomNav from '../../blocks/BottomNav/BottomNav.jsx'
import { useAuth } from '../../context/AuthContext.jsx'
import './applayout.css'

function AppLayout({ children }) {
  const { user } = useAuth()

  return (
    <div className="app-shell">
      <main className="app-main">
        <div className="app-sidebar">
          <Sidebar />
        </div>
        <div className="app-body">{children}</div>
      </main>
      {/* role comes from the backend once it sends one */}
      <BottomNav userName={user?.name} userRole={user?.role} />
    </div>
  )
}

export default AppLayout
