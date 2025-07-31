const selectedSpecialties = [];

document.querySelectorAll('#specialties input[type="checkbox"]').forEach(checkbox => {
    checkbox.addEventListener('change', () => {
        const id = parseInt(checkbox.dataset.id);

        if (checkbox.checked) {
            selectedSpecialties.push({ id, priority: selectedSpecialties.length + 1 });
        } else {
            const index = selectedSpecialties.findIndex(s => s.id === id);
            if (index !== -1) {
                selectedSpecialties.splice(index, 1);
                // Пересчитываем приоритеты
                selectedSpecialties.forEach((s, i) => s.priority = i + 1);
            }
        }

        updatePriorityDisplays();
    });
});

function updatePriorityDisplays() {
    document.querySelectorAll('#specialties label').forEach(label => {
        const checkbox = label.querySelector('input[type="checkbox"]');
        const id = parseInt(checkbox.dataset.id);
        const span = label.querySelector('.priority-display');

        const specialty = selectedSpecialties.find(s => s.id === id);
        span.textContent = specialty ? `(приоритет: ${specialty.priority})` : '';
    });
}
