import { useState } from 'react'
import { API_BASE } from '../api.js'

export default function HomePage() {
  const [result, setResult] = useState('')
  const [loading, setLoading] = useState(false)

  async function loadElement() {
    setLoading(true)
    setResult('')

    try {
      const response = await fetch(`${API_BASE}/elements/1`, {
        credentials: 'include',
        headers: { Accept: 'application/json' },
      })
      const contentType = response.headers.get('content-type') || ''

      if (response.redirected || !contentType.includes('application/json')) {
        setResult('Not signed in. Use the Login link first.')
        return
      }

      const element = await response.json()
      setResult(JSON.stringify(element, null, 2))
    } catch (err) {
      setResult(`Error: ${err.message}`)
    } finally {
      setLoading(false)
    }
  }

  return (
    <main>
      <h1>Wavelenght</h1>
      <p>Hello myall</p>
      <p>
        <a href="#/page01">Go to page 01</a>
      </p>

      <button type="button" onClick={loadElement} disabled={loading}>
        {loading ? 'Loading...' : 'Click Me'}
      </button>

      {result && <pre>{result}</pre>}
    </main>
  )
}
