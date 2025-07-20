export function getEnteredSubjectIds() {
    return Array.from(document.querySelectorAll(".exam-result"))
        .filter(el => el.value.trim())
        .map(el => Number(el.getAttribute("data-subject-id")));
}
