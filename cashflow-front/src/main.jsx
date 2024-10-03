import { createRoot } from 'react-dom/client'
import { App } from './App.jsx'

import "./assets/styles/common/global.css"
import {StrictMode} from "react";

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
