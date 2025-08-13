import {
    handleCreateSpecialtyFromDto,
    handleFilterSpecialtyTable,
    handleFindSpecialty,
    handleUpdateSpecialtyPlaces
} from "./handlers.js";

export async function initialize(initMap) {
    initFacultySelector(initMap.allFaculties, "facultySelect")
    initFacultySelector(initMap.allFaculties, "createSpecialtyFacultySelector")

    document.getElementById("facultySelect").addEventListener("change", handleFilterSpecialtyTable)
    document.getElementById("findSpecialtyBtn").addEventListener("click", handleFindSpecialty)
    document.getElementById("updateSpecialtyBtn").addEventListener("click", handleUpdateSpecialtyPlaces)
    document.getElementById("addSpecialtyBtn").addEventListener("click", handleCreateSpecialtyFromDto)
}

function initFacultySelector(faculties, selectorName) {
    const select = document.getElementById(selectorName);

    faculties.forEach(faculty => {
        const option = document.createElement('option');
        option.value = faculty.id;
        option.textContent = faculty.name;
        select.appendChild(option);
    });
}
