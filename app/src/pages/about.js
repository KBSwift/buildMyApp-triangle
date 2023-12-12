import {Link} from 'react-router-dom';

const About = () => {
    return (
    <div className="About">
        <p>Welcome to the about page!</p>
        <Link to='/'>Go to home?</Link>
    </div>
    );
}

export default About;