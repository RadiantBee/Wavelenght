import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// The production build is served by Spring Boot under the /wavelenght context path.
// During local development Vite serves the app from / and proxies API calls to the backend.
export default defineConfig(({ command }) => ({
  plugins: [react()],
  base: command === 'build' ? '/wavelenght/' : '/',
  build: {
    // Gradle adds build/frontend to the Spring Boot resources, so the output
    // must live under static/ to be served from the classpath.
    outDir: '../build/frontend/static',
    emptyOutDir: true,
  },
  server: {
    port: 5173,
    proxy: {
      '/wavelenght': {
        target: 'http://localhost:8099',
        changeOrigin: true,
      },
    },
  },
}))
