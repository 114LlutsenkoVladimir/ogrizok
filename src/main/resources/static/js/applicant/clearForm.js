function clearForm(formName) {
    document.getElementById(formName).querySelectorAll("input").forEach(input => {
        if (input.type === "checkbox" || input.type === "radio") {
            input.checked = false;
        } else {
            input.value = "";
        }
    })
}

export function clearSendForm() {
    clearForm("addApplicantForm");
    document.getElementById("available-specialties").innerHTML="";
}

export function clearDeleteForm() {
    clearForm("deleteApplicantForm");
}