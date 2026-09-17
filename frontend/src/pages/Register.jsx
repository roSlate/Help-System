import { useState } from 'react'
import { useNavigate } from 'react-router'
import api from '../api.js'
import RegisterBlock from '../blocks/Register/Register.jsx'

function Register() {
  const navigate = useNavigate()
  const [error, setError] = useState('')

  async function handleSubmit(e) {
    
    e.preventDefault()
    setError('')

    const form = new FormData(e.target)

    if (form.get('password') !== form.get('confirmPassword')) {
      setError('Passwords do not match')
      return
    }

    const data = {
      name: form.get('username'),
      email: form.get('email'),
      password: form.get('password'),
      department: form.get('department'),
    }

    console.log('Registering:', { ...data, password: '********' })

    try {
      await api.post('/users/register', data)

      navigate('/login')
    } catch (err) {
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
      <RegisterBlock onSubmit={handleSubmit} error={error} />
    </main>
  )
}

export default Register
