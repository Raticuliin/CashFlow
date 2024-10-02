import axios from "axios";

const API_URL = "http://localhost:8080";
const API_URL_ACCOUNTS = API_URL + "/account";

export const getAllAccounts = async () => {

    const response = await axios.get(API_URL_ACCOUNTS + "/all");

    if (response.status !== 200) {
        throw new Error("Error getting all the accounts");
    }

    return await response.data;
};