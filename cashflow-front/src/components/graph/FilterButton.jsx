import PropTypes from "prop-types";

export default function FilterButton({title, selected, onClick}) {

    return (
        <button
            className={`filter-bar__button ${selected ? "filter-bar__button--selected" : ""}`}
            onClick={onClick}
        >
            {title}
        </button>
    );
}

FilterButton.propTypes = {
    title: PropTypes.string,
    selected: PropTypes.bool,
    onClick: PropTypes.func,
}