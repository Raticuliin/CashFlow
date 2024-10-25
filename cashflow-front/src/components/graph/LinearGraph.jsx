import {LineChart} from "@mui/x-charts";
import { createTheme, ThemeProvider } from '@mui/material/styles';

import FilterBar from "./FilterBar.jsx";

import "./../../assets/styles/graph.css";

import accountHistory from "../../assets/data/accountHistory.json";

const total = accountHistory.total;
const accounts = accountHistory.accounts;
const dates = accountHistory.dates;

const slotProps = {
    legend: {
        hidden: true,
    }
}

const theme = createTheme({
    palette: {
        mode: "dark"
    },
    typography: {
        fontFamily: [
            'Roboto',
            'sans-serif',
        ].join(','),
    },
});

export default function LinearGraph() {
    return (
        <section className="graph_container">
            <FilterBar />
            <ThemeProvider theme={theme}>
                <LineChart
                    slotProps={slotProps}
                    series={[
                        { curve: "monotoneX", data: total, label: 'total'},
                        ...accounts.map((account) => (
                            {curve: "monotoneX", data: account.historicBalance, label: account.account}
                        )),
                    ]}
                    xAxis={[{ scaleType: 'band', data: dates }]}
                    yAxis={[{min: -2000, max: 6000}]}

                />
            </ThemeProvider>
        </section>
    );
}