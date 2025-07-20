import { setupSpecialtyChecker } from "../specialty/listener.js";
import {handleDelete, handleSubmit} from "./handlers.js";




document.getElementById("submit-btn").addEventListener("click", handleSubmit);

document.getElementById("deleteApplicantForm")
    .querySelector("button").addEventListener("click", handleDelete);

document.addEventListener("DOMContentLoaded", () => {
    setupSpecialtyChecker();
});