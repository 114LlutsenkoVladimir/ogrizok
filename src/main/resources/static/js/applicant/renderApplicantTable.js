export function renderApplicantTable(report) {
    const container = document.getElementById("table-container");
    container.innerHTML = ""; // очищаем, но потом всё строим с нужными классами

    for (const [specialtyId, applicants] of Object.entries(report.report)) {

        // обёртка для каждой таблицы (чтобы сохранить стили)
        const block = document.createElement("div");
        block.className = "table-wrapper card-table mb-4";

        const title = document.createElement("h2");
        title.textContent = report.specialtyNames[specialtyId];
        block.appendChild(title);

        // сама таблица с bootstrap-классами
        const table = document.createElement("table");
        table.id = "applicants-table-" + specialtyId; // уникальный id, если нужно
        table.className = "table table-striped table-hover align-middle";

        // шапка таблицы
        const thead = document.createElement("thead");
        thead.className = "table-light";
        thead.innerHTML = `
            <tr>
                <th>id</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Телефон</th>
                <th>Email</th>
                <th>Середній бал</th>
                <th>Статус</th>
                ${report.subjectIdsBySpecialty[specialtyId].map(id => `
                    <th>${report.subjectNames[id]}</th>
                `).join('')}
            </tr>
        `;
        table.appendChild(thead);

        // тело таблицы
        const tbody = document.createElement("tbody");

        applicants.forEach(applicant => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${applicant.applicantId}</td>
                <td>${applicant.firstName}</td>
                <td>${applicant.lastName}</td>
                <td>${applicant.phoneNumber}</td>
                <td>${applicant.email}</td>
                <td>${applicant.averageScore}</td>
                <td>${applicant.status}</td>
                ${report.subjectIdsBySpecialty[specialtyId].map(id => `
                    <td>${applicant.subjectAndScore[id] ?? '-'}</td>
                `).join('')}
            `;
            tbody.appendChild(row);
        });

        table.appendChild(tbody);
        block.appendChild(table);
        container.appendChild(block);
    }
}
