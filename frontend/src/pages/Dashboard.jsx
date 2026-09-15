import Sidebar from '../blocks/Sidebar/Sidebar.jsx'
import BottomNav from '../blocks/BottomNav/BottomNav.jsx'

function Dashboard() {
  return (
    <div className="app-shell">
      <main className="app-content app-main">
        <Sidebar />
        <section className="scroll-area" />
      </main>
      <BottomNav />
    </div>
  )
}

export default Dashboard
