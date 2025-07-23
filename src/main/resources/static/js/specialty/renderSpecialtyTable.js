export function renderTable(report) {
    const container = document.getElementById("specialtiesTable");
    container.innerHTML = ""; // Очистка контейнера перед рендерингом

    for (const [facultyId, specialties] of Object.entries(report.report)) {
        const title = document.createElement("h2");
        title.textContent = report.facultyNames[facultyId] || "Невідомий факультет";
        container.appendChild(title);

        const table = document.createElement("table");
        table.border = "1";

        const thead = document.createElement("thead");
        thead.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Назва спеціальності</th>
                <th>Код спеціальності</th>
                <th>Бюджетні місця</th>
                <th>Контрактні місця</th>
                <th>Всього місць</th>
            </tr>
        `;
        table.appendChild(thead);

        const tbody = document.createElement("tbody");
        specialties.forEach(specialty => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${specialty.id}</td>
                <td>${specialty.name}</td>
                <td>${specialty.number}</td>
                <td>${specialty.numberOfBudgetPlaces ?? ''}</td>
                <td>${specialty.numberOfContractPlaces ?? ''}</td>
                <td>${specialty.sumOfPlaces ?? ''}</td>
            `;
            tbody.appendChild(row);
        });

        table.appendChild(tbody);
        container.appendChild(table);
        container.appendChild(document.createElement("br"));
    }
}
