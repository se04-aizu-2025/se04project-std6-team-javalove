const algorithms = {
  bubble: {
    complexity: 'Average: O(n²) / Worst: O(n²) / Best: O(n²)',
    stable: true,
    description:
      'Repeatedly compares adjacent elements and swaps them if they are in the wrong order. ' +
      'This basic implementation always performs the same number of comparisons, regardless of input order.'
  },

  selection: {
    complexity: 'Average: O(n²) / Worst: O(n²) / Best: O(n²)',
    stable: false,
    description:
      'Selects the minimum element from the unsorted portion and moves it to the front. ' +
      'The number of comparisons is fixed and does not depend on how sorted the input is.'
  },

  insertion: {
    complexity: 'Average: O(n²) / Worst: O(n²) / Best: O(n)',
    stable: true,
    description:
      'Builds a sorted portion one element at a time by inserting each new element into its correct position. ' +
      'When the array is nearly sorted, only a few shifts are needed, making it very efficient.'
  },

  quick: {
    complexity: 'Average: O(n log n) / Worst: O(n²) / Best: O(n log n)',
    stable: false,
    description:
      'Partitions the array around a pivot element and recursively sorts the subarrays. ' +
      'If poor pivots are chosen (e.g., already sorted data), performance can degrade to quadratic time.'
  },

  merge: {
    complexity: 'Average: O(n log n) / Worst: O(n log n) / Best: O(n log n)',
    stable: true,
    description:
      'Recursively divides the array into halves and merges them back in sorted order. ' +
      'Its performance is predictable and does not depend on the initial arrangement of elements.'
  },

  heap: {
    complexity: 'Average: O(n log n) / Worst: O(n log n) / Best: O(n log n)',
    stable: false,
    description:
      'Transforms the array into a heap structure and repeatedly extracts the largest (or smallest) element. ' +
      'The heap property guarantees consistent time complexity for all inputs.'
  },

  optbubble: {
    complexity: 'Average: O(n²) / Worst: O(n²) / Best: O(n)',
    stable: true,
    description:
      'An optimized version of Bubble Sort that stops early if no swaps occur during a pass. ' +
      'This avoids unnecessary comparisons and makes the algorithm linear for already or nearly sorted arrays.'
  },

  optquick: {
    complexity: 'Average: O(n log n) / Worst: O(n log n) / Best: O(n log n)',
    stable: false,
    description:
      'Improves Quick Sort by using median-of-three pivot selection and switching to insertion sort for small subarrays. ' +
      'These optimizations reduce recursion overhead and prevent the typical worst-case scenarios.'
  },

  optmerge: {
    complexity: 'Average: O(n log n) / Worst: O(n log n) / Best: O(n)',
    stable: true,
    description:
      'Enhances Merge Sort by skipping the merge step when the two halves are already in order. ' +
      'This optimization allows nearly sorted data to be processed in linear time.'
  }
};

const descriptionEl = document.getElementById('description');

document.querySelectorAll('input[name="algo"]').forEach(radio => {
  radio.addEventListener('change', e => {
    const algo = algorithms[e.target.value];
    descriptionEl.innerHTML = `
      ${algo.complexity}<br>
      Stable: ${algo.stable ? 'Yes' : 'No'}<br>
      ${algo.description}
    `;
  });
});

const checked = document.querySelector('input[name="algo"]:checked');
if (checked) {
  const algo = algorithms[checked.value];
  descriptionEl.innerHTML = `
    ${algo.complexity}<br>
    Stable: ${algo.stable ? 'Yes' : 'No'}<br>
    ${algo.description}
  `;
}
