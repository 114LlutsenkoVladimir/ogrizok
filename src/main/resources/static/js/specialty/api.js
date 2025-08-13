export async function getFilteredSpecialtyList(facultyId) {
    const response = await fetch(`/specialties/filterSpecialtiesByFaculty/${facultyId}`)
    if(!response.ok) {
        const err = await response.json();
        throw new Error(err.message)
    }

    return await response.json()
}

export async function getSpecialtyDtosBySpecialtyId(paramsToStr) {
    const url = "/specialties/findSpecialty?" + paramsToStr
    const response = await fetch(url)
    if(!response.ok) {
        const err = await response.json();
        throw new Error(err.message)
    }
    return await response.json()
}

export async function updateSpecialtyPlaces(paramsToStr) {
    const url = "/specialties/updateSpecialtyPlaces?" + paramsToStr
    const response = await fetch(url)
    if(!response.ok) {
        const err = await response.json();
        throw new Error(err.message)
    }
    return await response.json()
}

export async function getSpecialtyInitDto() {

    const response = await fetch(`/specialties/initializeSpecialtyPage`)
    if(!response.ok) {
        const err = await response.json();
        throw new Error(err.message)
    }
    return await response.json()
}

export async function createSpecialtyFromDto(dto) {
    const response = await fetch("/specialties/createSpecialty", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dto)
    });

    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }

    return await response.json();
}

