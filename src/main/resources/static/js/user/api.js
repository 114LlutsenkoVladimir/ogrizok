
export async function getUser() {
    const response = await fetch(`/users/getUser`)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.json();
}

export async function setUserOnPassword(password) {
    const response = await fetch(`/users/setUserOnPassword/password=${password}`)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.json();
}