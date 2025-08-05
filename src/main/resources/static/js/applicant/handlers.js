import {buildDto, buildFindApplicantQueryParams, getSelectedSpecialtiesFromDOM} from "./buildDto.js";
import {deleteApplicant, findApplicantByKeyAttributes, sendApplicant} from "./api.js";
import {renderTable} from "./renderApplicantTable.js";
import {clearDeleteForm, clearFindForm, clearSendForm} from "./clearApplicantForm.js";
import {showError} from "../errorPopup/errorPopup.js";
import {renderPriorities} from "./render.js";
import {checkFindApplicantForm} from "./ckeckAppicantForm.js";

export async function handleSubmit() {
    try {
        const dto = buildDto();
        const result = await sendApplicant(dto);
        renderTable(result);
        clearSendForm();
    } catch (error) {
        showError(error.message);
    }
}

export async function handleDelete() {
    try {
        const id = document.getElementById("deleteApplicantInput").value;
        await deleteApplicant(id);
        clearDeleteForm();
    } catch (error) {
        showError(error.message);
    }
}


export function handleSpecialtySelection() {
    try {
        const selected = getSelectedSpecialtiesFromDOM();
        renderPriorities(selected);
    } catch (error) {
        showError(error.message);
    }
}


export async function handleFindApplicant() {
    try {
        checkFindApplicantForm()
        const params = buildFindApplicantQueryParams()
        const report = await findApplicantByKeyAttributes(params)
        renderTable(report)
        clearFindForm()
    } catch (error) {
        showError(error.message)
    }
}

