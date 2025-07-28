import {handleFilterSpecialtyTable, handleFindSpecialty, handleUpdateSpecialtyPlaces} from "./handlers.js";

export async function initialize(initMap) {
    initFacultySelector(initMap.allFaculties)

    document.getElementById("facultySelect").addEventListener("change", handleFilterSpecialtyTable)
    document.getElementById("findSpecialtyBtn").addEventListener("click", handleFindSpecialty)
    document.getElementById("updateSpecialtyBtn").addEventListener("click", handleUpdateSpecialtyPlaces)
}

function initFacultySelector(faculties) {
    const select = document.getElementById('facultySelect');

    faculties.forEach(faculty => {
        const option = document.createElement('option');
        option.value = faculty.id;
        option.textContent = faculty.name;
        select.appendChild(option);
    });
}
