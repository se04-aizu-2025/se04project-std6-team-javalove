const chk = document.getElementById('visualizeChk');
const delayBox = document.getElementById('delayBox');

function updateDelayVisibility() {
  delayBox.style.display = chk.checked ? 'inline-flex' : 'none';
}

chk.addEventListener('change', updateDelayVisibility);
updateDelayVisibility();
