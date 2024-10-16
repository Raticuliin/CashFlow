import styles from "../../assets/styles/page.module.css";
import PropTypes from "prop-types";

export function AccountInfoList({accounts}) {
    return (
        <ul className={styles.accountList}>
            {accounts.map(account => (
                <li key={account.id}>
                    <button className={styles.accountListButton}>
                        <span>{account.name}</span>
                        <span>{account.balance}</span>
                    </button>
                </li>
            ))}
        </ul>
    );
}

AccountInfoList.propTypes = {
    accounts: PropTypes.arrayOf(PropTypes.shape({
        id: PropTypes.number.isRequired,
        name: PropTypes.string.isRequired,
        balance: PropTypes.number.isRequired,
    }))
}