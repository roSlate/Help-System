import { LifeBuoy, Mail, Lock } from 'lucide-react'
import './login.css'

function Login({ onSubmit, error }) {
  return (
    <form className="login-card" onSubmit={onSubmit}>
      <div className="login-brand">
        <span className="login-logo">
          <LifeBuoy className="icon" />
        </span>
        <span className="sora-brand">HelpDesk <span className="login-brand-accent">Pro</span></span>
      </div>

      <div className="login-field">
        <label className="rubik-subtitle" htmlFor="login-email">Email</label>
        <div className="login-input">
          <Mail className="icon icon-dark" />
          <input id="login-email" name="email" type="email" placeholder="name@company.com" required />
        </div>
      </div>

      <div className="login-field">
        <label className="rubik-subtitle" htmlFor="login-password">Password</label>
        <div className="login-input">
          <Lock className="icon icon-dark" />
          <input id="login-password" name="password" type="password" placeholder="••••••••" required />
        </div>
        <a className="rubik-subtitle login-forgot" href="/forgot-password">Forgot password?</a>
      </div>

      {error && <p className="rubik-text login-error" role="alert">{error}</p>}

      <button className="sora-brand login-submit" type="submit">Log In</button>

      <p className="rubik-text login-footer">
        <span>Don&apos;t have an account?</span>
        <a href="/register">Sign up</a>
      </p>
    </form>
  )
}

export default Login
