import"../../assets/styles/accounts.css";
import PropTypes from "prop-types";

export function AccountInfoList({accounts}) {
    return (
        <ul className={"info_container__list"}>
            {accounts.map(account => (
                <li key={account.id} className={"info_container__list_item"}>
                    <button className={"info_container__list_button"}>
                        <span className={"info_container__list_name"}>{account.name}</span>
                        <span className={"info_container__list_balance"}>{account.balance}</span>
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