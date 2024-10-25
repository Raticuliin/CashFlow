import "../../assets/styles/accounts.css";
import PropTypes from "prop-types";

export function AccountInfoTitle({total}) {

    return (
        <section className="info_container__title">
            <h2 className="info_container__title_name"> Cuentas </h2>
            <span className="info_container__title_balance">{total.balance}</span>
        </section>
    );
}

AccountInfoTitle.propTypes = {

    total: PropTypes.shape({
        balance: PropTypes.number.isRequired,
    })

}