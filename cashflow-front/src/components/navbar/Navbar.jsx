import PropTypes from "prop-types";
import {NavbarItem} from "./NavbarItem.jsx";

import "../../assets/styles/navbar.css";
import {useLocation} from "react-router-dom";
export function Navbar({navbarItems}) {

    const location = useLocation();

    return (
        <nav className="navbar">
            {navbarItems.map((navItem) => (
                <NavbarItem
                    key={navItem.id}
                    selected = {location.pathname === navItem.path}
                    name={navItem.name}
                    path={navItem.path}
                />
            ))}
        </nav>
    );
}

Navbar.propTypes = {
    navbarItems: PropTypes.arrayOf(PropTypes.shape({
        id: PropTypes.number.isRequired,
        name: PropTypes.string.isRequired,
        path: PropTypes.string.isRequired,
    }))
}