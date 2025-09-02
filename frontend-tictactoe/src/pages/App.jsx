import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Home from './Home/Home.jsx'
import Match from './Match/Match.jsx';
import styles from './App.module.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/match/:id" element={<Match />} />
        <Route path="/home" element={<Home />} />
        <Route path="/" element={<Home />} />
      </Routes>
    </BrowserRouter>
  );
}
export default App
