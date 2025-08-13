export function checkEmptyInputs(formName) {
    let allEmpty = 1;
    document.getElementById(formName).querySelectorAll("input")
        .forEach(input => {
        if(input.value.trim())
            allEmpty = 0;
        }
    )
    if (allEmpty)
        throw new Error("Заповніть хоча б 1 поле")
}

export function checkNegativeInputs(ids) {
    for (const id of ids) {
        const el = document.getElementById(id);

        if (!el) {
            throw new Error(`Элемент с id="${id}" не найден`);
        }

        const value = parseFloat(el.value);

        if (!Number.isNaN(value) && value < 0) {
            throw new Error(`отрицательное значение (${value})`);
        }
    }
}
