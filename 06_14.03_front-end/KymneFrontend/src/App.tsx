import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import { Contestant } from './models/Contestant'
import './App.css'
import { Result } from './models/Result'

function App() {
  const [count, setCount] = useState(0)
  const [contestant, setContestant] = useState<Contestant[]>([]);
  const [result, setResult] = useState<Result[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/contestant")
    .then(res=>res.json())
    .then(json=>setContestant(json))
  }, []);
  useEffect(() => {
    fetch("http://localhost:8080/result")
    .then(res=>res.json())
    .then(json=>setResult(json))
  }, []);
  return (
    <>
    {contestant.map(Contestant =>
       <div key={Contestant.name}>{Contestant.country} - {Contestant.name} ({Contestant.age}) </div>
       )}

    {result.map(Result => 
    <div>{Result.points}, {Result.sport}</div>
    )}
    </>
  )
}

export default App
