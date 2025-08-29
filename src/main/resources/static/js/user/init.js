import {handleLogout, handleSetUserOnPassword} from "./handler.js";

export function init() {
    document.getElementById("loginBtn").addEventListener("click", handleSetUserOnPassword);
    document.getElementById("logoutBtn").addEventListener("click", handleLogout);
}