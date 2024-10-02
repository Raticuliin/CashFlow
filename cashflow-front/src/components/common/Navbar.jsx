import {useEffect, useState} from "react";
import {NavItem} from "./NavItem.jsx";

import "../../assets/styles/common/navbar.css"
import {useLocation} from "react-router-dom";


const navItems = [
    { name: 'Inicio', path: '/', id: 'home' },
    { name: 'Cuentas', path: '/accounts', id: 'accounts' },
    { name: 'Inversiones', path: '/investments', id: 'investments' },
    { name: 'Movimientos', path: '/movements', id: 'movements' },
];

export function Navbar() {
    const [active, setActive] = useState('home');
    const location = useLocation();

    useEffect(() => {
        // Encuentra el elemento del navbar cuyo path coincide con el pathname actual
        const currentItem = navItems.find(item => item.path === location.pathname);
        if (currentItem) {
            setActive(currentItem.id);
        }
    }, [location.pathname]); // Ejecuta este efecto cada vez que cambie el pathname

    const handleClick = (item) => {
        setActive(item);
    };

    return (
        <nav className="navbar">
            {navItems.map(({ name, path, id }) => (
                <NavItem
                    key={id}
                    name={name}
                    path={path}
                    isActive={active === id}
                    onClick={() => handleClick(id)}
                />
            ))}
        </nav>
    );
}

