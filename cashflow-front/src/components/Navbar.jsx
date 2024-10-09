import PropTypes from "prop-types";
import {NavbarItem} from "./NavbarItem.jsx";

import styles from "../assets/styles/navbar.module.css";

export function Navbar({navbarItems}) {

    return (
        <nav className={styles.navbar}>
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