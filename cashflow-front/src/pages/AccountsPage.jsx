import { Graph } from "../components/common/Graph.jsx";
import { Data } from "../components/common/Data.jsx"
import PropTypes from "prop-types";

export function AccountsPage({totalBalanceAccounts, accountList}) {

    return (
        <>
            <Graph />
            <Data title={"Cuentas"} totalBalance={totalBalanceAccounts} dataList={accountList} />
        </>
    )
}

AccountsPage.propTypes = {
    totalBalanceAccounts: PropTypes.number,
    accountList: PropTypes.array,
}