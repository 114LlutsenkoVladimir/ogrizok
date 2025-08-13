export function buildDto() {
    return {
        number: getVal("createSpecialtyNumber"),
        name: getVal("createSpecialtyName"),
        facultyId: getVal("createSpecialtyFacultySelector"),
        budgetPlaces: getValOrElseZero("createSpecialtyBudgetPlaces"),
        contractPlaces: getValOrElseZero("createSpecialtyContractPlaces")
    };
}

function getVal(id) {
    return document.getElementById(id).value.trim();
}

function getValOrElseZero(id) {
    if(document.getElementById(id).value.trim())
        return document.getElementById(id).value;
    else
        return 0;
}