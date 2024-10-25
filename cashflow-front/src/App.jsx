import {AccountPage} from "./pages/AccountPage.jsx";
import {Navbar} from "./components/navbar/Navbar.jsx";
import {Route, Routes} from "react-router-dom";
import {HomePage} from "./pages/HomePage.jsx";
import {InvestmentPage} from "./pages/InvestmentPage.jsx";
import {MovementPage} from "./pages/MovementPage.jsx";

export function App() {

    const navbarItems =
        [
            {
                id: 0,
                name: "Inicio",
                path: "/",
            },
            {
                id: 1,
                name: "Cuentas",
                path: "/accounts",
            },
            {
                id: 2,
                name: "Inversiones",
                path: "/investments",
            },
            {
                id: 3,
                name: "Movimientos",
                path: "/movements",
            },
        ];

    return (
        <>
            <Navbar navbarItems={navbarItems}/>
            <Routes>
                <Route path="/" element={<HomePage />}/>
                <Route path="/accounts" element={<AccountPage />}/>
                <Route path="/investments" element={<InvestmentPage />}/>
                <Route path="/movements" element={<MovementPage />}/>
            </Routes>
        </>
    );

}
