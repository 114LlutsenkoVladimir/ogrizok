export function buildFindSpecialtyQueryParams() {
    const id = document.getElementById("findSpecialtyId").value
    const name = document.getElementById("findSpecialtyName").value.trim();
    const number = document.getElementById("findSpecialtyNumber").value

    const params = new URLSearchParams();

    if (id) params.append("id", id);
    if (name) params.append("name", name);
    if (number) params.append("number", number);

    return params.toString();
}


export function buildUpdateSpecialtyPlacesQueryParams() {
    const id = document.getElementById("updatePlacesSpecialtyId").value
    const contractPlaces = document.getElementById("updatePlacesContractPlaces").value
    const budgetPlaces = document.getElementById("updatePlacesBudgetPlaces").value

    const params = new URLSearchParams();

    if (id) params.append("id", id);
    if (contractPlaces) params.append("contractPlaces", contractPlaces);
    if (budgetPlaces) params.append("budgetPlaces", budgetPlaces);

    return params.toString();
}