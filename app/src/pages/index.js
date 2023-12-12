import {Link} from 'react-router-dom';

const Home = () => {
    return (
    <div className="Home">
        <p>Welcome to the home page!</p>
        <Link to='/about'>Go To About?</Link>
    </div>
    );
}

export default Home;