import styles from '../assets/styles/page.module.css';
import {AccountInfo} from "../components/AccountInfo.jsx";


export function AccountPage() {

    return (
        <main className={styles.main}>
            <section className={styles.graphContainer}>section</section>
            <AccountInfo />
        </main>
    );

}