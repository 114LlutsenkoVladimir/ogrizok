import {showError} from "../errorPopup/errorPopup.js";
import {setDefaultUser, setUserOnPassword} from "./api.js";
import {clearForm} from "../utils/clearForm.js";

export async function handleSetUserOnPassword() {
    try {
        const password = document.getElementById("passwordField").value
        await setUserOnPassword(password)
        clearForm("authorizationForm")
    } catch (error) {
        showError(error.message)
    }
}

export async function handleLogout() {
    try {
        await setDefaultUser()
    } catch (error) {
        showError(error.message)
    }
}