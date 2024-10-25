import FilterButton from "./FilterButton.jsx";
import {useState} from "react";

const filterData = ["Semana", "Mes", "Año", "Max"];

export default function FilterBar() {

    const [selected, setSelected] = useState(filterData[0]);

    const handleClick = (title) => {
        setSelected(title);
    }

    return (
        <section className="filter-bar">
            {filterData.map((item, index) => (
                <FilterButton
                    key={index}
                    title={item}
                    selected={selected === item}
                    onClick={() => handleClick(item)}
                />
            ))}
        </section>
    );
}