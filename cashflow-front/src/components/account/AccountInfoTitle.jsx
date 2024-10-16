import styles from "../../assets/styles/page.module.css";
import PropTypes from "prop-types";

export function AccountInfoTitle({total}) {

    return (
        <section className={styles.infoTitle}>
            <h2> Cuentas </h2>
            <span>{total.balance}</span>
        </section>
    );
}

AccountInfoTitle.propTypes = {

    total: PropTypes.shape({
        balance: PropTypes.number.isRequired,
    })

}