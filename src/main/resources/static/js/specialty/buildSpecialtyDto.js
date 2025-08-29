

export function buildDto() {
    return {
        number: getVal("createSpecialtyNumber"),
        name: getVal("createSpecialtyName"),
        facultyId: getVal("createSpecialtyFacultySelector"),
        budgetPlaces: getValOrElseZero("createSpecialtyBudgetPlaces"),
        contractPlaces: getValOrElseZero("createSpecialtyContractPlaces"),
        subjectIds: getCheckedValuesByName("subjectIds")
    };
}

function getVal(id) {
    return document.getElementById(id).value.trim();
}

export function getCheckedValuesByName(name) {
    return Array.from(document.querySelectorAll(`input[name="${name}"]:checked`))
        .map(cb => parseInt(cb.value, 10))
        .filter(Number.isFinite);
}

function getValOrElseZero(id) {
    if(document.getElementById(id).value.trim())
        return document.getElementById(id).value;
    else
        return 0;
}