window.addEventListener("DOMContentLoaded", function () {
    const select = document.getElementById("specialty-select");
    if (!select) {
        console.error("❌ Элемент #specialty-select не найден в DOM");
        return;
    }

    select.addEventListener("change", function (event) {
        event.preventDefault();
        event.stopImmediatePropagation();

        const selectedValue = this.value;

        const url = "/filterApplicantsBySpecialty?specialtyId=" + encodeURIComponent(selectedValue);

        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error("⚠️ Ошибка ответа от сервера: " + response.status);
                }
                return response.json(); // ✅ теперь получаем JSON
            })
            .then(data => {
                renderTable(data); // ✅ рендерим таблицу вручную
            })
            .catch(error => {
                console.error("❌ Ошибка при fetch:", error);
            });
    });

    // ✅ функция для отрисовки таблицы на основе JSON
    function renderTable(report) {
        const container = document.getElementById("table-container");
        container.innerHTML = "";

        for (const [specialtyId, applicants] of Object.entries(report.report)) {
            const title = document.createElement("h2");
            title.textContent = report.specialtyNames[specialtyId];
            container.appendChild(title);

            const table = document.createElement("table");

            const thead = document.createElement("thead");
            thead.innerHTML = `
                <tr>
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>Телефон</th>
                    <th>Email</th>
                    ${report.subjectIdsBySpecialty[specialtyId].map(id => `
                        <th>${report.subjectNames[id]}</th>
                    `).join('')}
                </tr>
            `;
            table.appendChild(thead);

            const tbody = document.createElement("tbody");

            applicants.forEach(applicant => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${applicant.firstName}</td>
                    <td>${applicant.lastName}</td>
                    <td>${applicant.phoneNumber}</td>
                    <td>${applicant.email}</td>
                    ${report.subjectIdsBySpecialty[specialtyId].map(id => `
                        <td>${applicant.subjectAndScore[id] ?? '-'}</td>
                    `).join('')}
                `;
                tbody.appendChild(row);
            });

            table.appendChild(tbody);
            container.appendChild(table);
        }
    }
});