import {buildDto, getSelectedSpecialtiesFromDOM } from "./buildDto.js";
import {deleteApplicant, sendApplicant} from "./api.js";
import {renderTable} from "./renderApplicantTable.js";
import {clearDeleteForm, clearSendForm} from "./clearApplicantForm.js";
import {showError} from "../errorPopup/errorPopup.js";
import {renderPriorities} from "./render.js";

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

