import styles from '../assets/styles/page.module.css';
import {AccountInfo} from "../components/account/AccountInfo.jsx";
import Graph from "../components/Graph.jsx";


export function AccountPage() {

    return (
        <main className={styles.main}>
            <Graph />
            <AccountInfo />
        </main>
    );

}