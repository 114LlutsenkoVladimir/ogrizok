import {setupSpecialtyChecker} from "./listener.js";
import {
    handleDelete,
    handleFindApplicant,
    handleSpecialtySelection,
    handleSubmit,
    handleUpdateStatus
} from "./handlers.js";
import {getUser} from "../user/api.js";

export async function initialize(initMap) {

    await initCommon(initMap);
    await initCommittee(initMap);

    const user = await getUser();
    if(user === "committee" || user === "admin")
        await initCommittee()

    document.addEventListener("DOMContentLoaded", () => {
        setupSpecialtyChecker();
    });
}

export async function initCommon(initMap) {
    initBenefits(initMap.allBenefits)
    await initSubjectScoreInputs(initMap.allSubjects)
    initSpecialtySelect(initMap.allSpecialties)

    document.getElementById("submit-btn").addEventListener("click", handleSubmit);

    document.getElementById("deleteApplicantForm")
        .querySelector("button").addEventListener("click", handleDelete);

    document.getElementById("findApplicantForm")
        .querySelector("button").addEventListener("click", handleFindApplicant);
}

export async function initCommittee(initMap) {
    initStatusSelect(initMap.allStatuses)

    document.getElementById("updateApplicantStatusForm")
        .querySelector("button").addEventListener("click", handleUpdateStatus);
}


function initBenefits(benefits) {
    const container = document.getElementById('benefitIds');

    benefits.forEach(benefit => {
        const wrapper = document.createElement('div');
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.name = "benefitIds";
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


function initStatusSelect(statuses) {
    const select = document.getElementById('applicantStatusSelect');

    statuses.forEach(status => {
        const option = document.createElement('option');
        option.value = status.code;
        option.textContent = status.name;
        select.appendChild(option);
    });
}

export function attachSpecialtyListeners() {
    document.querySelectorAll('#available-specialties input[type="checkbox"]').forEach(checkbox => {
        checkbox.addEventListener('change', handleSpecialtySelection);
    });
}