
import {AccountInfo} from "../components/account/AccountInfo.jsx";
import LinearGraph from "../components/graph/LinearGraph.jsx";

import "./../assets/styles/page.css";


export function AccountPage() {

    return (
        <main className="main">
            <LinearGraph />
            <AccountInfo />
        </main>
    );

}