import { useState } from 'react'
import { useNavigate } from 'react-router'
import api from '../api.js'
import { useAuth } from '../context/AuthContext.jsx'
import LoginBlock from '../blocks/Login/Login.jsx'

function Login() {
  const navigate = useNavigate()
  const { login } = useAuth()
  const [error, setError] = useState('')

  async function handleSubmit(e) {
    // 1. Stop the browser from reloading the page
    e.preventDefault()
    setError('')

    // 2. Read the email and password typed in the form
    const form = new FormData(e.target)
    const data = {
      email: form.get('email'),
      password: form.get('password'),
    }

    try {
      // 3. Ask the backend if this email and password are correct
      const response = await api.post('/users/login', data)

      // 4. Correct: the backend sends back the user (id, name, email), never the password
      console.log('Logged in:', response.data)

      // 5. Put the user in the shared box so every page knows who is logged in
      login(response.data)
      navigate('/dashboard')
    } catch (err) {
      // 6. Wrong: show the backend's message, or a general one
      if (!err.response) {
        setError('Could not reach the server')
      } else if (typeof err.response.data === 'string') {
        setError(err.response.data)
      } else {
        setError('Something went wrong, please try again')
      }
    }
  }

  return (
    <main className="login-page">
      <LoginBlock onSubmit={handleSubmit} error={error} />
    </main>
  )
}

export default Login
