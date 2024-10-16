import {LineChart} from "@mui/x-charts";
import styles from "../assets/styles/page.module.css";
import FilterBar from "./FilterBar.jsx";

const uData = [null, 2, 2, 4, 4];
const pData = [3, 5, 2, 6, 2];
const xLabels = [
    'Page A',
    'Page B',
    'Page C',
    'Page D',
    'Page E',
];

export default function Graph() {
    return (
        <section className={styles.graphContainer}>
            <FilterBar />
            <LineChart
                series={[
                    { curve: "monotoneX", data: pData, label: 'pv' },
                    { curve: "monotoneX", data: uData, label: 'uv' },
                ]}
                xAxis={[{ scaleType: 'point', data: xLabels }]}
            />
        </section>
    );
}