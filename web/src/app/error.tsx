'use client'
 
import { useEffect } from 'react'
 
export default function Error({
  error,
  reset,
}: {
  error: Error & { digest?: string }
  reset: () => void
}) {
  useEffect(() => {
    console.error(error)
  }, [error])
 
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-zinc-50 dark:bg-zinc-900 text-zinc-900 dark:text-zinc-100 p-6">
      <h2 className="text-3xl font-bold mb-4">Something went wrong!</h2>
      <p className="text-zinc-600 dark:text-zinc-400 mb-8 text-center max-w-md">
        An unexpected error occurred while loading this profile.
      </p>
      <button
        onClick={() => reset()}
        className="px-6 py-3 bg-zinc-900 dark:bg-zinc-100 text-white dark:text-zinc-900 rounded-xl font-semibold hover:opacity-90 transition-opacity"
      >
        Try again
      </button>
    </div>
  )
}
