export function buildDto() {
    return {
        firstName: getVal("firstName"),
        lastName: getVal("lastName"),
        email: getVal("email"),
        phoneNumber: getVal("phoneNumber"),
        specialtyAndPriority: getSelectedSpecialtiesFromDOM(),
        benefitIds: getCheckedValues("benefitIds"),
        subjectAndScore: getSubjectsWithScores()
    };

}
function getVal(id) {
    return document.getElementById(id).value.trim();
}

function getCheckedValues(name) {
    return Array.from(document.querySelectorAll(`input[name="${name}"]:checked`))
        .map(cb => parseInt(cb.value));
}

function getSubjectsWithScores() {
    const inputs = document.querySelectorAll(".exam-result");
    const map = {};
    inputs.forEach(input => {
        const subjectId = parseInt(input.dataset.subjectId);
        const score = parseInt(input.value);
        if (!isNaN(subjectId) && !isNaN(score)) {
            map[subjectId] = score;
        }
    });
    return map;
}

export function getSelectedSpecialtiesFromDOM() {
    const selected = {};
    document.querySelectorAll('#available-specialties input[type="checkbox"]:checked')
        .forEach((checkbox, index) => {
        const id = parseInt(checkbox.dataset.id)
        const priority = index + 1;
        selected[id] = priority
    });

    console.log(selected);
    return selected;
}

