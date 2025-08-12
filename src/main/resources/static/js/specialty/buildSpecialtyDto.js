import {getSelectedSpecialtiesFromDOM} from "../applicant/buildDto";

export function buildDto() {
    return {
        number: getVal("createSpecialtyNumber"),
        name: getVal("createSpecialtyName"),

    };
}

function getVal(id) {
    return document.getElementById(id).value.trim();
}