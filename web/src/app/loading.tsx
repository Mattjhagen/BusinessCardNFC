export default function Loading() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-zinc-50 dark:bg-zinc-900">
      <div className="animate-pulse flex flex-col items-center">
        <div className="w-32 h-32 bg-zinc-300 dark:bg-zinc-700 rounded-full mb-6"></div>
        <div className="h-8 bg-zinc-300 dark:bg-zinc-700 rounded w-48 mb-4"></div>
        <div className="h-4 bg-zinc-300 dark:bg-zinc-700 rounded w-32 mb-8"></div>
        <div className="w-full max-w-md space-y-4 px-6">
          <div className="h-16 bg-zinc-300 dark:bg-zinc-700 rounded-xl w-full"></div>
          <div className="h-16 bg-zinc-300 dark:bg-zinc-700 rounded-xl w-full"></div>
        </div>
      </div>
    </div>
  )
}
