import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {Navbar} from "./components/common/Navbar.jsx";

import {HomePage} from "./pages/HomePage.jsx";
import {AccountsPage} from "./pages/AccountsPage.jsx";
import {MovementsPage} from "./pages/MovementsPage.jsx";
import {InvestmentsPage} from "./pages/InvestmentsPage.jsx";
import {useEffect, useState} from "react";
import {getAllAccounts} from "./services/accountsApi.js";

export function App() {

    const [accountList, setAccountList] = useState([]);
    const [accountBalance, setAccountBalance] = useState(0);

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchAccounts = async () => {
            try {
                const data = await getAllAccounts();

                setAccountList(data.accountList);
                setAccountBalance(data.totalBalance);

                console.log(data.accountList);
            } catch (error) {
                setError(error);
            } finally {
                setLoading(false);
            }
        };

        fetchAccounts();
    }, []);

    if (loading) return <p>Cargando...</p>
    if (error) return <p>Error: {error.message}</p>;

    return (
        <Router>
            <header>
               <Navbar />
            </header>
            <main>
                <Routes>
                    <Route path="/" exact element={<HomePage />} />
                    <Route path="/accounts" element={<AccountsPage totalBalanceAccounts={accountBalance} accountList={accountList} />}/>
                    <Route path="/investments" element={<InvestmentsPage />} />
                    <Route path="/movements" element={<MovementsPage />} />
                </Routes>
            </main>

        </Router>
    )
}
