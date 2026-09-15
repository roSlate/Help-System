import AppLayout from '../layouts/AppLayout/AppLayout.jsx'
import Header from '../blocks/Header.jsx'

function Home() {
  return (
    <AppLayout>
      <Header />
      <p className="rubik-body">Post a question, or browse existing ones.</p>
    </AppLayout>
  )
}

export default Home
