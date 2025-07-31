const selectedSpecialties = [];

document.querySelectorAll('#specialties input[type="checkbox"]').forEach(checkbox => {
    checkbox.addEventListener('change', (e) => {
        const id = parseInt(e.target.dataset.id);

        if (e.target.checked) {
            selectedSpecialties.push({ id, priority: selectedSpecialties.length + 1 });
        } else {
            const index = selectedSpecialties.findIndex(s => s.id === id);
            if (index !== -1) {
                selectedSpecialties.splice(index, 1);
                selectedSpecialties.forEach((s, i) => s.priority = i + 1);
            }
        }

        console.log("Выбрано:", selectedSpecialties);
    });
});
