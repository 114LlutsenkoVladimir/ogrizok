import { getEnteredSubjectIds } from "./subjectCollector.js";
import { fetchAvailableSpecialties } from "./fetchAvailableSpecialties.js";
import { renderSpecialties} from "./render.js";

export function setupSpecialtyChecker() {
    document.addEventListener("input", async (event) => {
        if (!event.target.classList.contains("exam-result")) return;

        const subjectIds = getEnteredSubjectIds();

        if (subjectIds.length === 0) {
            renderSpecialties([], "available-specialties");
            return;
        }

        try {
            const specialties = await fetchAvailableSpecialties(subjectIds);
            renderSpecialties(specialties);
        } catch (e) {
            console.log(e.message);
        }
    });
}

