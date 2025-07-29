import {checkEmptyInputs} from "../utils/checkEmptyInputs.js";

export function checkFindSpecialtyForm() {
    checkEmptyInputs("find-specialty")
}

export function checkUpdateSpecialtyPlacesForm() {
    checkEmptyInputs("updateSpecialtyPlaces")
    const contractPlacesValue = document.getElementById("updatePlacesContractPlaces").value
    const budgetPlacesValue = document.getElementById("updatePlacesBudgetPlaces").value

    if (contractPlacesValue.trim() !== "") {
        if (contractPlacesValue <= 0)
            throw new Error("Кіль-ть контрактних місць не может бути <= 0")
    }

    if (budgetPlacesValue.trim() !== "") {
        if (budgetPlacesValue < 0)
            throw new Error("Кіль-ть бюджетних місць не может бути < 0")
    }
}
