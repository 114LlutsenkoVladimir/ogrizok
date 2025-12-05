export function renderApplicantTable(report) {
    const container = document.getElementById("table-container");
    container.innerHTML = "";

    for (const [specialtyId, applicants] of Object.entries(report.report)) {

        const block = document.createElement("div");
        block.className = "table-wrapper card-table mb-4";

        const title = document.createElement("h2");
        title.textContent = report.specialtyNames[specialtyId];
        block.appendChild(title);

        const table = document.createElement("table");
        table.id = "applicants-table-" + specialtyId;
        table.className = "table table-striped table-hover align-middle";

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

        const tbody = document.createElement("tbody");

        applicants.forEach(applicant => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${applicant.applicantId}</td>
                <td>${applicant.firstName}</td>
                <td>${applicant.lastName}</td>
                <td>${applicant.phoneNumber}</td>
                <td>${applicant.email}</td>
                <td>${formatScore(applicant.averageScore)}</td>
                <td>${applicant.status}</td>
                ${report.subjectIdsBySpecialty[specialtyId].map(id => `
                    <td>${formatScore(applicant.subjectAndScore[id] ?? '-')}</td>
                `).join('')}
            `;
            tbody.appendChild(row);
        });

        table.appendChild(tbody);
        block.appendChild(table);
        container.appendChild(block);
    }
}

function formatScore(value) {
    if (value === null || value === undefined || value === '-') return '-';
    const num = Number(value);
    if (isNaN(num)) return value;
    return num.toFixed(2);
}

