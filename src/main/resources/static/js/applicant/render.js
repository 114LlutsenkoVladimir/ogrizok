import {attachSpecialtyListeners} from "./init.js";

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

    attachSpecialtyListeners();
}

export function renderSpecialtyError(message) {
    const container = document.getElementById("available-specialties");
    container.innerHTML = `<p style="color:red">${message}</p>`;
}


export function renderPriorities(selected) {
    document.querySelectorAll('#specialties label').forEach(label => {
        const checkbox = label.querySelector('input[type="checkbox"]');
        const id = parseInt(checkbox.dataset.id);
        const span = label.querySelector('.priority-display');
        const item = selected.find(s => s.id === id);
        span.textContent = item ? `(приоритет: ${item.priority})` : '';
    });
}
