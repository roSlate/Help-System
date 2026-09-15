import Header from '../blocks/Header.jsx'
import BottomNav from '../blocks/BottomNav/BottomNav.jsx'

function Home() {
  return (
    <div className="app-shell">
      <main className="app-content">
        <Header />
        <p className="rubik-body">Post a question, or browse existing ones.</p>
      </main>
      <BottomNav />
    </div>
  )
}

export default Home
