function generateRandom(count, min, max) {
  const arr = [];
  for (let i = 0; i < count; i++) {
    arr.push(randomInt(min, max));
  }
  return arr.join(' ');
}

function randomInt(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

document.getElementById('generateBtn').addEventListener('click', () => {
  const countEl = document.getElementById('count');
  const minEl = document.getElementById('min');
  const maxEl = document.getElementById('max');
  if (countEl.value.trim() === '' || minEl.value.trim() === '' || maxEl.value.trim() === '') {
    alert('Please fill in all fields with numeric values.');
    return;
  }
  const count = Number(countEl.value);
  const min = Number(minEl.value);
  const max = Number(maxEl.value);
  if (Number.isNaN(count) || Number.isNaN(min) || Number.isNaN(max)) {
    alert('Only numeric values are allowed.');
    return;
  }
  if (!Number.isInteger(count) || count <= 0) {
    alert('The count must be a positive integer.');
    return;
  }
  if (min > max) {
    alert('The minimum value must be less than or equal to the maximum value.');
    return;
  }
  const output = generateRandom(count, min, max);
  document.getElementById('in').value = output;
});
