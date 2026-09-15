import { useNavigate } from 'react-router'
import LoginBlock from '../blocks/Login/Login.jsx'

function Login() {
  const navigate = useNavigate()

  // ponytail: no auth yet, any submit goes straight to the dashboard
  function handleSubmit(e) {
    e.preventDefault()
    navigate('/dashboard')
  }

  return (
    <main className="login-page">
      <LoginBlock onSubmit={handleSubmit} />
    </main>
  )
}

export default Login
