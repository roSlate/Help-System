import { createContext, useContext, useState } from 'react'

// The shared box every component can read. AuthProvider fills it.
const AuthContext = createContext(null)

// Read the user saved in the browser, so a page refresh doesn't log you out
function loadSavedUser() {
  try {
    return JSON.parse(sessionStorage.getItem('user'))
  } catch {
    return null
  }
}

export function AuthProvider({ children }) {
  const [user, setUser] = useState(loadSavedUser)

  // Put the user in the box and save it in the browser
  function login(userData) {
    setUser(userData)
    sessionStorage.setItem('user', JSON.stringify(userData))
  }

  // Empty the box and forget the saved user
  function logout() {
    setUser(null)
    sessionStorage.removeItem('user')
  }

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

// Any component calls useAuth() to get { user, login, logout }
export function useAuth() {
  return useContext(AuthContext)
}
