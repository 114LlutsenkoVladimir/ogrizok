
export async function getUser() {
    const response = await fetch(`/users/getUser`)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.text();
}

export async function setUserOnPassword(password) {
    const response = await fetch('/users/setUserOnPassword', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ password }),
        credentials: 'same-origin'
    });

    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }

    return await response.text();
}

export async function setDefaultUser() {
    const response = await fetch(`/users/logout`)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.text();
}