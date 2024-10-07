import {AccountPage} from "./pages/AccountPage.jsx";
import {Navbar} from "./components/Navbar.jsx";
import {Route, Routes} from "react-router-dom";
import {HomePage} from "./pages/HomePage.jsx";
import {InvestmentPage} from "./pages/InvestmentPage.jsx";
import {MovementPage} from "./pages/MovementPage.jsx";

export function App() {

    const navbarItems =
        [
            {
                name: "Inicio",
                link: "/",
            },
            {
                name: "Cuentas",
                link: "/accounts",
            },
            {
                name: "Inversiones",
                link: "/investments",
            },
            {
                name: "Movimientos",
                link: "/movements",
            },
        ];

    console.log(navbarItems);

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
