import { getEnteredSubjectIds } from "./subjectCollector.js";
import { fetchAvailableSpecialties } from "./fetchAvailableSpecialties.js";
import { renderSpecialties, renderSpecialtyError } from "./render.js";

export function setupSpecialtyChecker() {
    document.querySelectorAll(".exam-result").forEach(input => {
        input.addEventListener("input", async () => {
            const subjectIds = getEnteredSubjectIds();

            if (subjectIds.length === 0) {
                renderSpecialties([], "available-specialties");
                return;
            }

            try {
                const specialties = await fetchAvailableSpecialties(subjectIds);
                renderSpecialties(specialties);
            } catch (e) {
                renderSpecialtyError(e.message);
            }
        });
    });
}
