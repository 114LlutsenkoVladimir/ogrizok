import {getFilteredSpecialtyList, getSpecialtyDtosBySpecialtyId} from "./api";
import {renderTable} from "./renderSpecialtyTable";
import {showError} from "../errorPopup/errorPopup";
import {clearFindSpecialtyForm} from "./clearSpecialtyForm";

export function handleFilterSpecialtyTable() {
    try {
        const facultyId = document.getElementById("facultySelect").value
        const report = getFilteredSpecialtyList(facultyId)
        renderTable(report)
    } catch (error) {
        showError(error.message)
    }
}

export function handleFindSpecialty() {
    try {
        const specialtyId = document.getElementById("specialtyId").value
        const report = getSpecialtyDtosBySpecialtyId(specialtyId)
        renderTable(report)
        clearFindSpecialtyForm()
    } catch (error) {
        showError(error.message)
    }
}