document.addEventListener("DOMContentLoaded", () => {
    const select = document.getElementById("filter-specialty");
    if (!select) return;

    select.addEventListener("change", async () => {
        const selected = select.value;

        const response = await fetch(`/filterApplicantsBySpecialty?specialtyName=${encodeURIComponent(selected)}`);
        if (!response.ok) {
            alert("Ошибка загрузки данных");
            return;
        }

        const html = await response.text();
        document.getElementById("applicant-report").innerHTML = html;
    });
});