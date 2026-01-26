package util;

import model.BubbleSort;
import model.HeapSort;
import model.InsertionSort;
import model.MergeSort;
import model.OptimizedBubbleSort;
import model.OptimizedMergeSort;
import model.OptimizedQuickSort;
import model.QuickSort;
import model.SelectionSort;
import model.SortAlgorithm;

public class SortFactory {
  private SortFactory() {}
  
  public static <T extends Comparable<? super T>> SortAlgorithm<T> create(String algo) {
    return switch (algo) {
      case "bubble"    -> new BubbleSort<T>();
      case "selection" -> new SelectionSort<T>();
      case "insertion" -> new InsertionSort<T>();
      case "quick"     -> new QuickSort<T>();
      case "merge"     -> new MergeSort<T>();
      case "heap"      -> new HeapSort<T>();
      case "optbubble"    -> new OptimizedBubbleSort<T>();
      case "optquick"     -> new OptimizedQuickSort<T>();
      case "optmerge"     -> new OptimizedMergeSort<T>();
      default          -> null;
    };
  }
}
