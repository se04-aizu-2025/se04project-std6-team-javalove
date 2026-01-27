const fileInput = document.getElementById('fileInput');
const textArea = document.getElementById('in');
fileInput.addEventListener('change', function() {
  const file = fileInput.files[0];
  if (!file) return;
  if (file.type && !file.type.startsWith('text')) {
    alert('Select a text file.');
    return;
  }
  const reader = new FileReader();
  reader.onload = function(e) {
    textArea.value = e.target.result;
  };
  reader.readAsText(file, 'UTF-8');
});
