import {getSpecialtyInitDto} from "./api.js";
import {initialize} from "./init.js";

document.addEventListener('DOMContentLoaded', async () => {
    const initMap = await getSpecialtyInitDto();
    await initialize(initMap);
});
