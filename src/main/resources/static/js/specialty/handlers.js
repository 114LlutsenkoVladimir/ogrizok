import {
    createSpecialtyFromDto, deleteSpecialty,
    getFilteredSpecialtyList,
    getSpecialtyDtosBySpecialtyId,
    updateSpecialtyPlaces
} from "./api.js";
import {renderSpecialtyTable} from "./renderSpecialtyTable.js";
import {showError} from "../errorPopup/errorPopup.js";
import {
    clearCreateSpecialtyForm, clearDeleteSpecialtyForm,
    clearFindSpecialtyForm,
    clearUpdateSpecialtyPlacesForm
} from "./clearSpecialtyForm.js";
import {
    checkCreateSpecialtyFromDtoForm,
    checkFindSpecialtyForm,
    checkUpdateSpecialtyPlacesForm
} from "./formValidation.js";
import {buildFindSpecialtyQueryParams, buildUpdateSpecialtyPlacesQueryParams} from "./buildSpecialtyQueryParams.js";
import {buildDto} from "./buildSpecialtyDto.js";

export async function handleFilterSpecialtyTable() {
    try {
        const facultyId = document.getElementById("facultySelect").value
        const report = await getFilteredSpecialtyList(facultyId)
        renderSpecialtyTable(report)
    } catch (error) {
        showError(error.message)
    }
}

export async function handleFindSpecialty() {
    try {
        checkFindSpecialtyForm()
        const params = buildFindSpecialtyQueryParams()
        const report = await getSpecialtyDtosBySpecialtyId(params)
        renderSpecialtyTable(report)
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
        renderSpecialtyTable(report)
        clearUpdateSpecialtyPlacesForm()
    } catch (error) {
        showError(error.message)
    }
}

export async function handleCreateSpecialtyFromDto() {
    try {
        checkCreateSpecialtyFromDtoForm()
        const dto = buildDto()
        const report = await createSpecialtyFromDto(dto)
        renderSpecialtyTable(report)
        clearCreateSpecialtyForm()
    } catch (error) {
        showError(error.message)
    }
}

export async function handleDeleteSpecialtyById() {
    try {
        const id = document.getElementById("deleteSpecialtyId").value
        await deleteSpecialty(id)
        clearDeleteSpecialtyForm()
    } catch (error) {
        showError(error.message)
    }
}
