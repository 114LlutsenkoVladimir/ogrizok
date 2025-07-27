import {getFilteredSpecialtyList, getSpecialtyDtosBySpecialtyId} from "./api.js";
import {renderTable} from "./renderSpecialtyTable.js";
import {showError} from "../errorPopup/errorPopup.js";
import {clearFindSpecialtyForm} from "./clearSpecialtyForm.js";

export async function handleFilterSpecialtyTable() {
    try {
        const facultyId = document.getElementById("facultySelect").value
        const report = await getFilteredSpecialtyList(facultyId)
        renderTable(report)
    } catch (error) {
        showError(error.message)
    }
}

export async function handleFindSpecialty() {
    try {
        const specialtyId = document.getElementById("findSpecialtyId").value
        const report = await getSpecialtyDtosBySpecialtyId(specialtyId)
        renderTable(report)
        clearFindSpecialtyForm()
    } catch (error) {
        showError(error.message)
    }
}