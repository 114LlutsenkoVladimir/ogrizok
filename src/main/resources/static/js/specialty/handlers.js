import {getFilteredSpecialtyList, getSpecialtyDtosBySpecialtyId, updateSpecialtyPlaces} from "./api.js";
import {renderTable} from "./renderSpecialtyTable.js";
import {showError} from "../errorPopup/errorPopup.js";
import {clearFindSpecialtyForm, clearUpdateSpecialtyPlacesForm} from "./clearSpecialtyForm.js";
import {checkFindSpecialtyForm, checkUpdateSpecialtyPlacesForm} from "./formValidation.js";
import {buildFindSpecialtyQueryParams, buildUpdateSpecialtyPlacesQueryParams} from "./buildSpecialtyQueryParams.js";

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
        checkFindSpecialtyForm()
        const params = buildFindSpecialtyQueryParams()
        const report = await getSpecialtyDtosBySpecialtyId(params)
        renderTable(report)
        clearFindSpecialtyForm()
    } catch (error) {
        showError(error.message)
    }
}

export async function handleUpdateSpecialtyPlaces() {
    try {
        checkUpdateSpecialtyPlacesForm()
        const params = buildUpdateSpecialtyPlacesQueryParams()
        const report = await updateSpecialtyPlaces(params)
        renderTable(report)
        clearUpdateSpecialtyPlacesForm()
    } catch (error) {
        showError(error.message)
    }
}
