import { Link } from 'react-router'

function NotFound() {
  return (
    <main className="login-page">
      <div className="login-card login-brand">
        <span className="sora-title">404</span>
        <p className="rubik-body">This page doesn&apos;t exist yet.</p>
        <Link className="rubik-subtitle" to="/login">Back to login</Link>
      </div>
    </main>
  )
}

export default NotFound
