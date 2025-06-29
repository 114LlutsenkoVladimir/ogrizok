async function checkAvailableSpecialties() {
    const subjectIds = [];
    document.querySelectorAll('.exam-result').forEach(el => {
        const value = el.value.trim();
        if (value) {
            const subjectId = el.getAttribute('data-subject-id');
            subjectIds.push(Number(subjectId));
        }
    });

    const container = document.getElementById("available-specialties");
    container.innerHTML = "";

    if (subjectIds.length === 0) {
        return;
    }

    try {
        const response = await fetch("/availableSpecialties", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(subjectIds)
        });

        if (!response.ok) {
            container.innerHTML = "<p style='color:red'>Ошибка загрузки</p>";
            return;
        }

        const specialties = await response.json();

        if (specialties.length === 0) {
            container.innerHTML = "<p>Нет подходящих специальностей</p>";
        } else {
            specialties.forEach(spec => {
                const label = document.createElement("label");

                const checkbox = document.createElement("input");
                checkbox.type = "checkbox";
                checkbox.name = "specialtyIds"; // важно!
                checkbox.value = spec.id;

                label.appendChild(checkbox);
                label.appendChild(document.createTextNode(" " + spec.name));
                container.appendChild(label);
                container.appendChild(document.createElement("br"));
            });
        }
    } catch (err) {
        container.innerHTML = "<p style='color:red'>Сетевая ошибка</p>";
    }
}

document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".exam-result").forEach(input => {
        input.addEventListener("input", checkAvailableSpecialties);
    });
});
