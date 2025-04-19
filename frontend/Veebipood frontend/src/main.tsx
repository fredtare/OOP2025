import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.min.css'; //className="accordion"
import './index.css'
import App from './App.tsx'
import { BrowserRouter } from 'react-router-dom'


//navigeerimiseks urlide vahetaamiseks
//1. npm i react-router-dom
//2. importida browserouter ja ümbritseda <APP/> tag sel;lega, vt all.
//3. teha seosed failide ja urlide vahel app.tsxis
//localhost:5173/cart --> cart.tsx

//bootstrap npm install react-bootstrap bootstrap


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BrowserRouter>
    <App />
    </BrowserRouter>

  </StrictMode>,
)
