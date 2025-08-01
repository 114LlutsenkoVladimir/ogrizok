export function buildDto() {
    return {
        firstName: getVal("firstName"),
        lastName: getVal("lastName"),
        email: getVal("email"),
        phoneNumber: getVal("phoneNumber"),
        specialtyIds: getSelectedSpecialtiesFromDOM(),
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
        const subjectId = parseInt(input.dataset.subjectId); // например <input data-subject-id="3" ...>
        const score = parseInt(input.value);
        if (!isNaN(subjectId) && !isNaN(score)) {
            map[subjectId] = score;
        }
    });
    return map;
}

export function getSelectedSpecialtiesFromDOM() {
    const selected = [];
    document.querySelectorAll('#specialties input[type="checkbox"]:checked')
        .forEach((checkbox, index) => {
        selected.push({
            id: parseInt(checkbox.dataset.id),
            priority: index + 1
        });
    });
    return selected;
}

