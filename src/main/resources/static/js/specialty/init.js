import {
    handleCreateSpecialtyFromDto, handleDeleteSpecialtyById,
    handleFilterSpecialtyTable,
    handleFindSpecialty,
    handleUpdateSpecialtyPlaces
} from "./handlers.js";
import {getUser} from "../user/api.js";

export async function initialize(initMap) {

    await initCommon(initMap);
    const user = await getUser();

    if(user === "admin")
        await initAdmin(initMap);

}

export async function initCommon(initMap) {
    initFacultySelector(initMap.allFaculties, "facultySelect")
    initFacultySelector(initMap.allFaculties, "createSpecialtyFacultySelector")

    document.getElementById("facultySelect").addEventListener("change", handleFilterSpecialtyTable)
    document.getElementById("findSpecialtyBtn").addEventListener("click", handleFindSpecialty)
    document.getElementById("updateSpecialtyBtn").addEventListener("click", handleUpdateSpecialtyPlaces)

}

export async function initAdmin(initMap) {
    initSubjects(initMap.allSubjects)
    document.getElementById("addSpecialtyBtn").addEventListener("click", handleCreateSpecialtyFromDto)
    document.getElementById("deleteSpecialtyBtn").addEventListener("click", handleDeleteSpecialtyById)
}


function initSubjects(subjects) {
    const container = document.getElementById('createSpecialtyRequiredSubjectsSelect');

    subjects.forEach(subject => {
        const wrapper = document.createElement('div');

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.name = "subjectIds";
        checkbox.value = subject.id;

        const label = document.createElement('span');
        label.textContent = subject.name;

        wrapper.appendChild(checkbox);
        wrapper.appendChild(label);
        container.appendChild(wrapper);
    });
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
