


function initBenefits(benefits) {
    const container = document.getElementById('benefitIds');

    benefits.forEach(benefit => {
        const wrapper = document.createElement('div');
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.value = benefit.id;

        const label = document.createElement('span');
        label.textContent = benefit.name;

        wrapper.appendChild(checkbox);
        wrapper.appendChild(label);
        container.appendChild(wrapper);
    });
}

function initSubjectScoreInputs(subjects) {
    const container = document.getElementById('subject-scores-container');

    subjects.forEach(subject => {
        const wrapper = document.createElement('div');

        const label = document.createElement('span');
        label.textContent = subject.name + ': ';

        const input = document.createElement('input');
        input.type = 'number';
        input.className = 'exam-result';
        input.name = `subjectAndScore[${subject.id}]`;
        input.setAttribute('data-subject-id', subject.id);

        wrapper.appendChild(label);
        wrapper.appendChild(input);

        container.appendChild(wrapper);
    });
}


function initSpecialtySelect(specialties) {
    const select = document.getElementById('specialty-select');

    specialties.forEach(specialty => {
        const option = document.createElement('option');
        option.value = specialty.id;
        option.textContent = specialty.name;
        select.appendChild(option);
    });
}