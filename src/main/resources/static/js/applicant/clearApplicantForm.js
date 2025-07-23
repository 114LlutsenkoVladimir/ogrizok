import {clearForm} from "../utils/clearForm.js";

export function clearSendForm() {
    clearForm("addApplicantForm");
    document.getElementById("available-specialties").innerHTML="";
}

export function clearDeleteForm() {
    clearForm("deleteApplicantForm");
}