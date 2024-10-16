import TimeButton from "./TimeButton.jsx";
import styles from "../assets/styles/filter.module.css";

export default function FilterBar() {
    return (
        <section className={styles.filterBar}>
            <TimeButton title={"Semana"}/>
            <TimeButton title={"Mes"}/>
            <TimeButton title={"Año"}/>
            <TimeButton title={"Máx."}/>
        </section>
    );
}