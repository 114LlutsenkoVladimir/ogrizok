import {showError} from "../errorPopup/errorPopup";
import {setUserOnPassword} from "./api";

export async function handleSetUserOnPassword() {
    try {
        const password = document.getElementById("passwordField").value
        const response = await setUserOnPassword(password)
        if (!response.ok) {
            const err = await response.json();
            throw new Error(err.message);
        }
    } catch (error) {
        showError(error.message)
    }
}