const btn = document.getElementById('checkSpecialties');
btn.addEventListener('click', async () => {
    // 1️⃣ Собираем ID предметов, у которых есть значение
    const subjectIds = [];
    document.querySelectorAll('.exam-result').forEach(el => {
        const value = el.value.trim();
        if (value) {
            const subjectId = el.getAttribute('data-subject-id');
            subjectIds.push(Number(subjectId));
        }
    });
    if (subjectIds.length === 0) {
        alert("Введите хотя бы один результат экзамена");
        return;
    }

    // 2️⃣ Отправляем только список subjectId
    const response = await fetch("/availableSpecialties", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(subjectIds)
    });
    if (!response.ok) {
        alert("Не удалось получить список доступных специальностей");
        return;
    }

    const availableSpecialties = await response.json();

    // 3️⃣ Отображаем результат
    const container = document.getElementById("available-specialties");
    container.innerHTML = "";

    if (availableSpecialties.length === 0) {
        container.innerHTML = "<p>Нет доступных специальностей для введённых результатов</p>";
    } else {
        const ul = document.createElement("ul");
        availableSpecialties.forEach(spec => {
            const li = document.createElement("li");
            li.textContent = spec.name;
            ul.appendChild(li);
        });
        container.appendChild(ul);
    }
});
