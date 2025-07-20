export async function fetchAvailableSpecialties(subjectIds) {
    const response = await fetch("/availableSpecialties", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(subjectIds)
    });

    if (!response.ok) {
        throw new Error("Ошибка загрузки специальностей");
    }

    return await response.json();
}
