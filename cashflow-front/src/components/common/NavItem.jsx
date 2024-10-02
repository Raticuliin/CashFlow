import {NavLink} from "react-router-dom";
import PropTypes from "prop-types";

export function NavItem ({ name, path, isActive, onClick }){
    return (
        <NavLink
            to={path}
            className={`navbar-item ${isActive ? 'navbar-item__selected' : ''}`}
            onClick={onClick}
        >
            {name}
        </NavLink>
    )
}

NavItem.propTypes = {
    name: PropTypes.string,
    path: PropTypes.string,
    isActive: PropTypes.bool,
    onClick: PropTypes.func
}