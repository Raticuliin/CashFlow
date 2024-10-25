import"../../assets/styles/page.css";
import {AccountInfoTitle} from "./AccountInfoTitle.jsx";
import {AccountInfoList} from "./AccountInfoList.jsx";

import accountsMock from '../../assets/data/accounts.json'
import accountsTotalMock from '../../assets/data/accountsTotal.json'

const total = accountsTotalMock;

const accounts = accountsMock.accounts;

export function AccountInfo() {
    return (
        <aside className="info_container">
            <AccountInfoTitle total={total} />
            <AccountInfoList accounts={accounts} />
        </aside>
    );
}