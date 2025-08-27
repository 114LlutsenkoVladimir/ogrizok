import {
    handleCreateSpecialtyFromDto, handleDeleteSpecialtyById,
    handleFilterSpecialtyTable,
    handleFindSpecialty,
    handleUpdateSpecialtyPlaces
} from "./handlers.js";
import {getUser} from "../user/api.js";

export async function initialize(initMap) {

    await initCommon();
    const user = await getUser();

    if(user === "admin")
        await initAdmin();

}

export async function initCommon() {
    initFacultySelector(initMap.allFaculties, "facultySelect")
    initFacultySelector(initMap.allFaculties, "createSpecialtyFacultySelector")

    document.getElementById("facultySelect").addEventListener("change", handleFilterSpecialtyTable)
    document.getElementById("findSpecialtyBtn").addEventListener("click", handleFindSpecialty)
    document.getElementById("updateSpecialtyBtn").addEventListener("click", handleUpdateSpecialtyPlaces)

}

export async function initAdmin() {
    document.getElementById("addSpecialtyBtn").addEventListener("click", handleCreateSpecialtyFromDto)
    document.getElementById("deleteSpecialtyBtn").addEventListener("click", handleDeleteSpecialtyById)
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
