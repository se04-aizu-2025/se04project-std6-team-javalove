const algorithms = {
  bubble: {
    complexity: 'Average: O(n²) / Worst: O(n²) / Best: O(n²)',
    stable: true,
    description:
      'Repeatedly compares adjacent elements and swaps them if they are in the wrong order.<br>' +
      'This basic implementation always performs the same number of comparisons, regardless of input order.<br><br>' +
      '隣接する要素を繰り返し比較し、順序が間違っていれば入れ替えます。<br>' +
      'この基本実装では、入力の並びに関係なく常に同じ回数の比較を行います。'
  },

  selection: {
    complexity: 'Average: O(n²) / Worst: O(n²) / Best: O(n²)',
    stable: false,
    description:
      'Selects the minimum element from the unsorted portion and moves it to the front.<br>' +
      'The number of comparisons is fixed and does not depend on how sorted the input is.<br><br>' +
      '未ソート部分から最小の要素を選択して先頭に移動します。<br>' +
      '比較回数は固定で、入力の並び順には依存しません。'
  },

  insertion: {
    complexity: 'Average: O(n²) / Worst: O(n²) / Best: O(n)',
    stable: true,
    description:
      'Builds a sorted portion one element at a time by inserting each new element into its correct position.<br>' +
      'When the array is nearly sorted, only a few shifts are needed, making it very efficient.<br><br>' +
      '新しい要素を適切な位置に挿入しながら、部分的にソートされた配列を構築します。<br>' +
      '配列がほぼ整列している場合は移動が少なく済み、非常に効率的です。'
  },

  quick: {
    complexity: 'Average: O(n log n) / Worst: O(n²) / Best: O(n log n)',
    stable: false,
    description:
      'Partitions the array around a pivot element and recursively sorts the subarrays.<br>' +
      'If poor pivots are chosen (e.g., already sorted data), performance can degrade to quadratic time.<br><br>' +
      'ピボットを基準に配列を分割し、部分配列を再帰的にソートします。<br>' +
      '悪いピボット（例：すでに整列済みのデータ）が選ばれると、性能が二次時間に低下することがあります。'
  },

  merge: {
    complexity: 'Average: O(n log n) / Worst: O(n log n) / Best: O(n log n)',
    stable: true,
    description:
      'Recursively divides the array into halves and merges them back in sorted order.<br>' +
      'Its performance is predictable and does not depend on the initial arrangement of elements.<br><br>' +
      '配列を半分に分割し、再帰的にソートしてマージします。<br>' +
      '性能は予測可能で、初期の並び順には依存しません。'
  },

  heap: {
    complexity: 'Average: O(n log n) / Worst: O(n log n) / Best: O(n log n)',
    stable: false,
    description:
      'Transforms the array into a heap structure and repeatedly extracts the largest (or smallest) element.<br>' +
      'The heap property guarantees consistent time complexity for all inputs.<br><br>' +
      '配列をヒープ構造に変換し、最大（または最小）の要素を繰り返し取り出します。<br>' +
      'ヒープ性質により、入力に関係なく安定した時間計算量が保証されます。'
  },

  optbubble: {
    complexity: 'Average: O(n²) / Worst: O(n²) / Best: O(n)',
    stable: true,
    description:
      'An optimized version of Bubble Sort that stops early if no swaps occur during a pass.<br>' +
      'This avoids unnecessary comparisons and makes the algorithm linear for already or nearly sorted arrays.<br><br>' +
      'スワップが発生しなかった場合に早期終了する最適化バブルソートです。<br>' +
      '不要な比較を避け、すでに整列済みまたはほぼ整列済みの配列では線形時間で処理されます。'
  },

  optquick: {
    complexity: 'Average: O(n log n) / Worst: O(n log n) / Best: O(n log n)',
    stable: false,
    description:
      'Improves Quick Sort by using median-of-three pivot selection and switching to insertion sort for small subarrays.<br>' +
      'These optimizations reduce recursion overhead and prevent the typical worst-case scenarios.<br><br>' +
      '3つの要素の中央値をピボットに選ぶとともに、小さな部分配列では挿入ソートに切り替えることで、クイックソートを改善します。<br>' +
      'これにより再帰のオーバーヘッドが減り、典型的な最悪ケースを回避できます。'
  },

  optmerge: {
    complexity: 'Average: O(n log n) / Worst: O(n log n) / Best: O(n)',
    stable: true,
    description:
      'Enhances Merge Sort by skipping the merge step when the two halves are already in order.<br>' +
      'This optimization allows nearly sorted data to be processed in linear time.<br><br>' +
      '左右の部分配列がすでに整列済みの場合はマージをスキップすることでマージソートを改善します。<br>' +
      'この最適化により、ほぼ整列済みのデータは線形時間で処理可能です。'
  }
};

const descriptionEl = document.getElementById('description');
document.querySelectorAll('input[name="algo"]').forEach(radio => {
  radio.addEventListener('change', e => {
    const algo = algorithms[e.target.value];
    descriptionEl.innerHTML = `
      ${algo.complexity}<br>
      Stable: ${algo.stable ? 'Yes' : 'No'}<br><br>
      ${algo.description}
    `;
  });
});

const checked = document.querySelector('input[name="algo"]:checked');
if (checked) {
  const algo = algorithms[checked.value];
  descriptionEl.innerHTML = `
    ${algo.complexity}<br>
    Stable: ${algo.stable ? 'Yes' : 'No'}<br><br>
    ${algo.description}
  `;
}
