import { API_BASE } from '../api.js'

export default function LoginPage() {
  return (
    <main>
      <h1>Sign in required</h1>
      <p>The API is protected. Sign in to use the application.</p>
      <p>
        <a href={`${API_BASE}/login`}>Sign in</a>
      </p>
    </main>
  )
}
