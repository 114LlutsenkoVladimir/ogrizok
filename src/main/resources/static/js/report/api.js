
export async function getApplicantsReport() {
    const response = await fetch("applicantsReport")
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.json();
}


export async function getSpecialtiesReport() {
    const response = await fetch("specialtiesReport")
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.json();
}