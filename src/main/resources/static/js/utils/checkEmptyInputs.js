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