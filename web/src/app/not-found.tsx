import Link from 'next/link'

export default function NotFound() {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-zinc-50 dark:bg-zinc-900 text-zinc-900 dark:text-zinc-100 p-6">
      <h2 className="text-4xl font-bold mb-4">Profile Not Found</h2>
      <p className="text-lg text-zinc-600 dark:text-zinc-400 mb-8 text-center max-w-md">
        We couldn&apos;t find the digital business card you were looking for. It may have been removed or made private.
      </p>
      <Link href="/" className="px-6 py-3 bg-zinc-900 dark:bg-zinc-100 text-white dark:text-zinc-900 rounded-xl font-semibold hover:opacity-90 transition-opacity">
        Create Your TapCard
      </Link>
    </div>
  )
}
