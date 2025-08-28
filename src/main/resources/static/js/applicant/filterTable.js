import {renderTable} from "./renderApplicantTable.js";

const selector = document.getElementById("specialty-select")
selector.addEventListener("change", filterTable)

export async function filterTable() {
    const specialtyId = selector.value
    const response = await fetch(`filterApplicantsBySpecialty/${specialtyId}`)
    const data = await response.json()
    renderTable(data)
}