import {handleShowApplicantsReport, handleShowSpecialtiesReport} from "./handler.js";

export async function initialize() {
    document.getElementById("applicantsReportBtn")
        .addEventListener("click", handleShowApplicantsReport);

    document.getElementById("specialtiesReportBtn")
        .addEventListener("click", handleShowSpecialtiesReport);
}