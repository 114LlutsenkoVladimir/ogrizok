export async function getFilteredSpecialtyList(facultyId) {
    const response = await fetch(`/specialties/filterSpecialtiesByFaculty/${facultyId}`)
    if(!response.ok) {
        const err = await response.json();
        throw new Error(err.message)
    }

    return await response.json()
}

export async function getSpecialtyDtosBySpecialtyId(specialtyId) {
    const response = await fetch(`/specialties/findSpecialty/${specialtyId}`)
    if(!response.ok) {
        const err = await response.json();
        throw new Error(err.message)
    }
    return await response.json()
}