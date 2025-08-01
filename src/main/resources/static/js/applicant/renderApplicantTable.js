export function renderTable(report) {
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
        <th>Имя</th><th>Фамилия</th><th>Телефон</th><th>Email</th>
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