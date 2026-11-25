import { useEffect, useState } from 'react'
import HomePage from './pages/HomePage.jsx'
import LoginPage from './pages/LoginPage.jsx'
import Page01 from './pages/Page01.jsx'

function currentPage() {
  return window.location.hash.replace(/^#\/?/, '')
}

export default function App() {
  const [page, setPage] = useState(currentPage)

  useEffect(() => {
    const onHashChange = () => setPage(currentPage())
    window.addEventListener('hashchange', onHashChange)
    return () => window.removeEventListener('hashchange', onHashChange)
  }, [])

  return (
    <div>
      <nav>
        <a href="#/">Home</a>
        {' | '}
        <a href="#/page01">Page 01</a>
        {' | '}
        <a href="#/login">Login</a>
      </nav>
      <hr />
      {page === 'page01' && <Page01 />}
      {page === 'login' && <LoginPage />}
      {page !== 'page01' && page !== 'login' && <HomePage />}
    </div>
  )
}
