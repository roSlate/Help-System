import { LifeBuoy, Mail, User, Building2, Lock } from 'lucide-react'
import '../Login/login.css'

function Register({ onSubmit, error }) {
  return (
    <form className="login-card" onSubmit={onSubmit}>
      <div className="login-brand">
        <span className="login-logo">
          <LifeBuoy className="icon" />
        </span>
        <span className="sora-brand">HelpDesk <span className="login-brand-accent">Pro</span></span>
      </div>

      <div className="login-field">
        <label className="rubik-subtitle" htmlFor="register-email">Email</label>
        <div className="login-input">
          <Mail className="icon icon-dark" />
          <input id="register-email" name="email" type="email" placeholder="name@company.com" required />
        </div>
      </div>

      <div className="login-field">
        <label className="rubik-subtitle" htmlFor="register-username">Username</label>
        <div className="login-input">
          <User className="icon icon-dark" />
          <input id="register-username" name="username" type="text" placeholder="John Doe" required />
        </div>
      </div>

      <div className="login-field">
        <label className="rubik-subtitle" htmlFor="register-department">Department</label>
        <div className="login-input">
          <Building2 className="icon icon-dark" />
          <input id="register-department" name="department" type="text" placeholder="Marketing" required />
        </div>
      </div>

      <div className="login-field">
        <label className="rubik-subtitle" htmlFor="register-password">Password</label>
        <div className="login-input">
          <Lock className="icon icon-dark" />
          <input id="register-password" name="password" type="password" placeholder="••••••••" required />
        </div>
      </div>

      <div className="login-field">
        <label className="rubik-subtitle" htmlFor="register-confirm">Confirm Password</label>
        <div className="login-input">
          <Lock className="icon icon-dark" />
          <input id="register-confirm" name="confirmPassword" type="password" placeholder="••••••••" required />
        </div>
      </div>

      {error && <p className="rubik-text login-error" role="alert">{error}</p>}

      <button className="sora-brand login-submit" type="submit">Register</button>

      <p className="rubik-text login-footer">
        <span>Already have an account?</span>
        <a href="/login">Log in</a>
      </p>
    </form>
  )
}

export default Register
