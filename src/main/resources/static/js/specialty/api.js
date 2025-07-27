export async function getFilteredSpecialtyList(facultyId) {
    const response = await fetch(`/specialties/filterSpecialtiesByFaculty/${facultyId}`)
    if(!response.ok) {
        const err = await response.json();
        throw new Error(err.message)
    }

    return await response.json()
}

export async function getSpecialtyDtosBySpecialtyId(id) {
    const response = await fetch(`/specialties/findSpecialty/${id}`)
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