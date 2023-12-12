import {
    BrowserRouter as Router,
    Routes,
    Route,
} from "react-router-dom";
import Home from './pages'
import About from './pages/about'

export const Routing = () => {
    return (
            <Router>
                <Routes>
                    <Route exact path="/" element={<Home />} />
                    <Route path="/about" element={<About />} />
                </Routes>
            </Router>
    )
}