import './App.scss';
import { HashRouter as Router, Route, Routes } from 'react-router-dom';
import { HomePage } from './pages/HomePage';
import { GoogleBooksPage } from './pages/GoogleBooksPage';
import { ITunesSongsPage } from './pages/ITunesSongsPage';


function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<HomePage />} />
        </Routes>
        <Routes>
          <Route path="/books/:search" element={<GoogleBooksPage />} />
        </Routes>
        <Routes>
          <Route path="/songs/:search" element={<ITunesSongsPage />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
