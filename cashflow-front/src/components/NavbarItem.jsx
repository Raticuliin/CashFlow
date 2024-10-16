import {NavLink} from "react-router-dom";
import PropTypes from "prop-types";



export function NavbarItem(props) {

    const {name, link} = props;

    return (
        <NavLink to={link}> {name} </NavLink>
    );
}

NavbarItem.propTypes = {

    name: PropTypes.string.isRequired,
    link: PropTypes.string.isRequired,

}