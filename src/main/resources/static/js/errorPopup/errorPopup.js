document.addEventListener("DOMContentLoaded", () => {
    const closeBtn = document.getElementById("error-close-btn");
    if (closeBtn) {
        closeBtn.addEventListener("click", closeErrorModal);
    }
})
export function closeErrorModal() {
    const modal = document.getElementById("error-modal");
    if (modal) modal.style.display = "none";
}

export function showError(message, autoHide = false) {
    const modal = document.getElementById("error-modal");
    const text = document.getElementById("error-message-text");

    if (text) {
        text.textContent = message;
    }
    if (modal) {
        modal.style.display = "block";
    }

    if (autoHide) {
        setTimeout(() => {
            closeErrorModal();
        }, 5000);
    }
}
