export async function sendApplicant(dto) {
    const response = await fetch("applicants/addApplicant", {
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

export async function deleteApplicant(id) {
    const response = await fetch(`applicants/deleteApplicant/${id}`, {
        method: "DELETE"
    })
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
}

export async function getApplicantInitDto() {
    const response = await fetch("applicants/initializeApplicantPage")
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.json();
}

export async function findApplicantByKeyAttributes(params) {
    const response = await fetch("applicants/findApplicant?" + params)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.json();
}

export async function updateApplicantStatus(params) {
    const response = await fetch("applicants/updateApplicantStatus?" + params)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.json();
}
