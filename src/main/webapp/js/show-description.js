const algorithms = {
  bubble: {
    complexity: 'Average: O(n²) / Worst: O(n²) / Best: O(n)',
    description: 'Compares adjacent elements and swaps them if needed. Best case: already sorted / Worst case: reverse order'
  },
  selection: {
    complexity: 'Average: O(n²) / Worst: O(n²) / Best: O(n²)',
    description: 'Selects the minimum element from the unsorted portion and moves it to the front. Comparison count is always the same regardless of input'
  },
  insertion: {
    complexity: 'Average: O(n²) / Worst: O(n²) / Best: O(n)',
    description: 'Inserts each element into its correct position in the sorted portion. Best case: already sorted / Worst case: reverse order'
  },
  quick: {
    complexity: 'Average: O(n log n) / Worst: O(n²) / Best: O(n log n)',
    description: 'Uses a pivot to divide and conquer. Best case: pivot near median / Worst case: already sorted or reverse order with first/last pivot'
  },
  merge: {
    complexity: 'Average: O(n log n) / Worst: O(n log n) / Best: O(n log n)',
    description: 'Divides the array and merges them back. Performance is consistent regardless of input'
  },
  heap: {
    complexity: 'Average: O(n log n) / Worst: O(n log n) / Best: O(n log n)',
    description: 'Builds a heap and repeatedly extracts the max/min. Performance is consistent regardless of input'
  }
};

const descriptionEl = document.getElementById('description');

document.querySelectorAll('input[name="algo"]').forEach(radio => {
  radio.addEventListener('change', e => {
    const algo = algorithms[e.target.value];
    descriptionEl.innerHTML = `<strong>${algo.complexity}</strong><br>${algo.description}`;
  });
});

const checked = document.querySelector('input[name="algo"]:checked');
if (checked) {
  const algo = algorithms[checked.value];
  descriptionEl.innerHTML = `<strong>${algo.complexity}</strong><br>${algo.description}`;
}
