import {getApplicantsReport, getSpecialtiesReport} from "./api.js";
import {showError} from "../errorPopup/errorPopup.js";
import {renderApplicantTable} from "../applicant/renderApplicantTable.js";
import {renderSpecialtyTable} from "../specialty/renderSpecialtyTable.js";


export async function handleShowReport({
                                           buttonId,
                                           wrapId,
                                           getReportFunction,
                                           renderTableFunction
                                       }) {
    const btn = document.getElementById(buttonId);
    const wrap = document.getElementById(wrapId);

    if (wrap.style.display === "none" || wrap.style.display === "") {
        // показать
        wrap.style.display = "block";

        if (btn.dataset.loaded !== "true") {
            try {
                const report = await getReportFunction();
                renderTableFunction(report);
                btn.dataset.loaded = "true";
            } catch (err) {
                showError(err.message);
                wrap.style.display = "none"; // спрятать если ошибка
            }
        }
    } else {
        wrap.style.display = "none";
    }
}


export async function handleShowApplicantsReport() {
    await handleShowReport({
        buttonId:"applicantsReportBtn",
        wrapId: "applicantsReportWrap",
        getReportFunction: getApplicantsReport,
        renderTableFunction: renderApplicantTable})
}


export async function handleShowSpecialtiesReport() {
    await handleShowReport({
        buttonId:"specialtiesReportBtn",
        wrapId: "specialtiesReportWrap",
        getReportFunction: getSpecialtiesReport,
        renderTableFunction: renderSpecialtyTable})
}