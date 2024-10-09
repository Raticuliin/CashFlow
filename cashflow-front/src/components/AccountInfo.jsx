import styles from "../assets/styles/page.module.css";

const total =
    {
        name: "name",
        balance: 10,
    };

const accounts =
    [
        {
            name: "name",
            balance: 10,
        },
        {
            name: "name",
            balance: 10,
        },
        {
            name: "name",
            balance: 10,
        },
    ]

export function AccountInfo() {
    return (
        <aside className={styles.infoContainer}>aside</aside>
    );
}