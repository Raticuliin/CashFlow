import PropTypes from "prop-types";
import {NavbarItem} from "./NavbarItem.jsx";

export function Navbar(props) {

    const {navbarItems} = props;

    console.log(navbarItems);

    return (
        <nav>
            {navbarItems.map((navItem, index) => (
                <NavbarItem key={index} name={navItem.name} link={navItem.link} />
            ))}
        </nav>
    );
}

Navbar.propTypes = {
    navbarItems: PropTypes.arrayOf(PropTypes.shape({
        name: PropTypes.string.isRequired,
        link: PropTypes.string.isRequired,
    }))
}