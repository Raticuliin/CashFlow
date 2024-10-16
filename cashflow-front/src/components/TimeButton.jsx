import PropTypes from "prop-types";
import styles from "../assets/styles/filter.module.css";

export default function TimeButton({title}) {
    return (
        <button className={styles.timeButton}> {title} </button>
    );
}

TimeButton.propTypes = {
    title: PropTypes.string,
}