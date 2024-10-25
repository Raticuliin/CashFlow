import {NavLink} from "react-router-dom";
import PropTypes from "prop-types";

import "../../assets/styles/navbar.css";

export function NavbarItem({selected, name, path, onClick}) {

    return (
        <NavLink
            to={path}
            className={`navbar__item ${selected ? "navbar__item--selected" : ""}`}
            onClick={onClick}
        > {name}
        </NavLink>
    );
}

NavbarItem.propTypes = {
    selected: PropTypes.bool.isRequired,
    name: PropTypes.string.isRequired,
    path: PropTypes.string.isRequired,
    onClick: PropTypes.func,

}