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
        checkbox.dataset.id = spec.id; // важно для JS логики

        const prioritySpan = document.createElement("span");
        prioritySpan.className = "priority-display"; // для отображения приоритета

        label.appendChild(checkbox);
        label.append(" " + spec.name + " ");
        label.appendChild(prioritySpan);

        container.appendChild(label);
        container.appendChild(document.createElement("br"));
    });

    attachSpecialtyListeners();
}


export function renderPriorities(selected) {
    document.querySelectorAll('#available-specialties label').forEach(label => {
        const checkbox = label.querySelector('input[type="checkbox"]');
        const id = parseInt(checkbox.dataset.id);
        const span = label.querySelector('.priority-display');
        const item = selected[id];
        span.textContent = item ? `(приоритет: ${item})` : '';
    });
}
