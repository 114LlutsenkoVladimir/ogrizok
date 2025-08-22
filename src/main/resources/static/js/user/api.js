
export async function getUser(password) {
    const response = await fetch(`/users/getUser/password=${password}`)
    if(!response.ok)

}