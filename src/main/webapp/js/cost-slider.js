document.addEventListener("DOMContentLoaded", () => {
  const slider = document.getElementById("cost");
  const display = document.getElementById("costValue");
  updateDisplay(slider.value);
  slider.addEventListener("input", () => {
    updateDisplay(slider.value);
  });
  function updateDisplay(cost) {
    display.textContent = "Cost: " + cost;
  }
});
