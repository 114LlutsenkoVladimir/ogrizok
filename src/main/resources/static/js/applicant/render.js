export function renderSpecialties(specialties, containerId = "available-specialties") {
    const container = document.getElementById(containerId);
    container.innerHTML = "";

    if (specialties.length === 0) {
        container.innerHTML = "<p>Нет подходящих специальностей</p>";
        return;
    }

    specialties.forEach(spec => {
        const label = document.createElement("label");

        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.name = "specialtyIds";
        checkbox.value = spec.id;

        label.appendChild(checkbox);
        label.append(" " + spec.name);
        container.appendChild(label);
        container.appendChild(document.createElement("br"));
    });
}

export function renderSpecialtyError(message) {
    const container = document.getElementById("available-specialties");
    container.innerHTML = `<p style="color:red">${message}</p>`;
}
