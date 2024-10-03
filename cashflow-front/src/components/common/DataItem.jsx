import "../../assets/styles/common/data.css"
import PropTypes from "prop-types";

export function DataItem({title, totalBalance, dataList}) {

    return (
        <section className="data-item">
            <header className="data-item__header">
                <h3 className="data-item__title">{title}</h3>
            </header>
            {/* Total mostrado con etiquetas semánticas adecuadas */}
            <section className="data-item__total">
                <strong>Total:</strong> <span>{totalBalance}€</span>
            </section>

            {/* Lista de ítems de la sección */}
            <ul className={"data-item__list"}>

                {dataList.map((item, index) => (
                    <li key={index} className={"data-item__list-item"}>
                        {item.name}:
                        <span>{item.balance}€</span>
                    </li>
                ))}
            </ul>
        </section>
    )
}

DataItem.propTypes = {
    totalBalance: PropTypes.number,
    title: PropTypes.string,
    dataList: PropTypes.array,
}