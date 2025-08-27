import {handleSetUserOnPassword} from "./handler.js";

export function init() {
    document.getElementById("loginBtn").addEventListener("click", handleSetUserOnPassword);
}