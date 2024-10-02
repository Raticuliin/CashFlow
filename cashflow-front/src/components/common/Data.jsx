import {DataItem} from "./DataItem.jsx";
import PropTypes from "prop-types";

export function Data({title, totalBalance, dataList}) {

    return (
        <aside className="data">
            <DataItem title={title} totalBalance={totalBalance} dataList={dataList}/>
        </aside>

    )
}

Data.propTypes = {
    title: PropTypes.string,
    totalBalance: PropTypes.number,
    dataList: PropTypes.array,
}