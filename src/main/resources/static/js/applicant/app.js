import {getApplicantInitDto} from "./api.js";
import {initialize} from "./init.js";
import {setupSpecialtyChecker} from "../specialty/listener.js";


document.addEventListener('DOMContentLoaded', async () => {
    const initMap = await getApplicantInitDto();
    await initialize(initMap);
    setupSpecialtyChecker();
});



