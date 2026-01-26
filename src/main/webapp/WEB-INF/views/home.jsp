<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sort Visualizer</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@7.1.0/css/all.min.css">
  <link rel="stylesheet" href="style.css">
  <script src="js/cost-slider.js" defer></script>
  <script src="js/file-input.js" defer></script>
  <script src="js/random-input.js" defer></script>
  <script src="js/show-description.js" defer></script>
  <script src="js/visualize-button.js" defer></script>
</head>
<body>
  <div class="list-y">
    <div class="list-x">
      <h1>Sort Visualizer</h1>
      <div style="margin-left: auto;">Team javalove</div>
    </div>
    <div class="layout">
      <div class="layout__left">
        <div>
          <h2>Input</h2>
          <form class="card list-y" action="sort" method="post">
            <textarea class="textarea" id="in" name="in" rows="8" placeholder="Please enter numbers separated by spaces (e.g. &quot;6 1 7 4&quot;)." required><c:if test="${not empty in}"><c:out value="${in}" /></c:if></textarea>
            <div class="list-x">
              <span>Count:</span>
              <input class="textarea" type="number" id="count" name="count" value="<c:out value="${count}" />" min="1">
              <span>Range:</span>
              <input class="textarea" type="number" id="min" name="min" value="<c:out value="${min}" />">
              <span>~</span>
              <input class="textarea" type="number" id="max" name="max" value="<c:out value="${max}" />">
            </div>
            <div class="list-x">
              <div class="list-x" style="margin-left: auto;">
                <label class="btn">
                  <input style="display: none;" type="file" id="fileInput" accept=".txt">
                  <span><i class="fa-solid fa-file"></i> File Input</span>
                </label>
                <button class="btn" id="generateBtn" type="button"><i class="fa-solid fa-dice"></i> Generate</button>
              </div>
            </div>
            <hr>
            <div class="list-x">
              <label>
                <c:choose>
                  <c:when test="${empty algo || algo == 'bubble'}">
                    <input type="radio" name="algo" value="bubble" checked>
                  </c:when>
                  <c:otherwise>
                    <input type="radio" name="algo" value="bubble">
                  </c:otherwise>
                </c:choose>
                <span>Bubble Sort</span>
              </label>
              <label>
                <c:choose>
                  <c:when test="${algo == 'selection'}">
                    <input type="radio" name="algo" value="selection" checked>
                  </c:when>
                  <c:otherwise>
                    <input type="radio" name="algo" value="selection">
                  </c:otherwise>
                </c:choose>
                <span>Selection Sort</span>
              </label>
              <label>
                <c:choose>
                  <c:when test="${algo == 'insertion'}">
                    <input type="radio" name="algo" value="insertion" checked>
                  </c:when>
                  <c:otherwise>
                    <input type="radio" name="algo" value="insertion">
                  </c:otherwise>
                </c:choose>
                <span>Insertion Sort</span>
              </label>
              <label>
                <c:choose>
                  <c:when test="${algo == 'quick'}">
                    <input type="radio" name="algo" value="quick" checked>
                  </c:when>
                  <c:otherwise>
                    <input type="radio" name="algo" value="quick">
                  </c:otherwise>
                </c:choose>
                <span>Quick Sort</span>
              </label>
              <label>
                <c:choose>
                  <c:when test="${algo == 'merge'}">
                    <input type="radio" name="algo" value="merge" checked>
                  </c:when>
                  <c:otherwise>
                    <input type="radio" name="algo" value="merge">
                  </c:otherwise>
                </c:choose>
                <span>Merge Sort</span>
              </label>
              <label>
                <c:choose>
                  <c:when test="${algo == 'heap'}">
                    <input type="radio" name="algo" value="heap" checked>
                  </c:when>
                  <c:otherwise>
                    <input type="radio" name="algo" value="heap">
                  </c:otherwise>
                </c:choose>
                <span>Heap Sort</span>
              </label>
            </div>
            <div class="list-x">
              <label>
                <c:choose>
                  <c:when test="${algo == 'optbubble'}">
                    <input type="radio" name="algo" value="optbubble" checked>
                  </c:when>
                  <c:otherwise>
                    <input type="radio" name="algo" value="optbubble">
                  </c:otherwise>
                </c:choose>
                <span>Bubble Sort (optimized)</span>
              </label>
              <label>
                <c:choose>
                  <c:when test="${algo == 'optquick'}">
                    <input type="radio" name="algo" value="optquick" checked>
                  </c:when>
                  <c:otherwise>
                    <input type="radio" name="algo" value="optquick">
                  </c:otherwise>
                </c:choose>
                <span>Quick Sort (optimized)</span>
              </label>
              <label>
                <c:choose>
                  <c:when test="${algo == 'optmerge'}">
                    <input type="radio" name="algo" value="optmerge" checked>
                  </c:when>
                  <c:otherwise>
                    <input type="radio" name="algo" value="optmerge">
                  </c:otherwise>
                </c:choose>
                <span>Merge Sort (optimized)</span>
              </label>
            </div>
            <span id="description"></span>
            <hr>
            <div class="list-x">
              <label>
                <c:choose>
                  <c:when test="${rev}">
                    <input type="checkbox" name="opt" value="rev" checked>
                  </c:when>
                  <c:otherwise>
                    <input type="checkbox" name="opt" value="rev">
                  </c:otherwise>
                </c:choose>
                <span>Reverse Order</span>
              </label>
            </div>
            <div class="list-x">
              <label>
                <c:choose>
                  <c:when test="${visu}">
                    <input type="checkbox" name="opt" value="visu" id="visualizeChk" checked>
                  </c:when>
                  <c:otherwise>
                    <input type="checkbox" name="opt" value="visu" id="visualizeChk">
                  </c:otherwise>
                </c:choose>
                <span>Visualize</span>
              </label>
              <div class="list-x" id="delayBox">
                <span>Delay:</span>
                <input class="textarea" type="number" name="delay" min="20" max="2000" step="10" value="<c:out value="${delay}" />">
                <span>ms/frame</span>
              </div>
              <div class="tooltip">
                <i class="fa-regular fa-circle-question"></i>
                <div class="tooltiptext">
                  <span>Visualizes each step of the sorting algorithm.</span><br>
                  <span>Useful for understanding how elements move and how the algorithm progresses.</span><br>
                  <span>Not recommended for large inputs due to performance impact.</span><br><br>
                  <span>ソートアルゴリズムの各ステップを可視化します。</span><br>
                  <span>要素がどのように移動するか、アルゴリズムがどのように進行するかを理解するのに役立ちます。</span><br>
                  <span>性能に影響するため、大きな入力には推奨されません。</span>
                </div>
              </div>
              <input class="textarea" style="opacity: 0; padding-left: 0; padding-right: 0; width: 0;">
            </div>
            <div class="list-x" id="delayBox">
              <span id="costValue"></span>
              <label>
                <input type="range" name="cost" id="cost" min="0" max="10" value="<c:out value="${cost}" />">
              </label>
              <div class="tooltip">
                <i class="fa-regular fa-circle-question"></i>
                <div class="tooltiptext">
                  <span>Adds an exponential delay to each iteration: (2<sup>cost</sup> − 1) ms.</span><br>
                  <span>Helps highlight differences in algorithmic time complexity.</span><br>
                  <span>Small values are usually sufficient.</span><br><br>
                  <span>各イテレーションに指数的な遅延を追加します：(2<sup>cost</sup> − 1) ms。</span><br>
                  <span>アルゴリズムの計算量の違いを強調するのに役立ちます。</span><br>
                  <span>小さい値でも十分です。</span>
                </div>
              </div>
            </div>
            <div class="list-x" style="margin-left: auto;">
              <c:if test="${not empty out || not empty err}">
                <a href="reset" class="btn" type="submit"><i class="fa-solid fa-undo"></i> Reset</a>
              </c:if>
              <button class="btn" type="submit"><i class="fa-solid fa-play"></i> Sort</button>
            </div>
          </form>
        </div>
      </div>
      <div class="layout__right">
        <c:choose>
          <c:when test="${not empty out}">
            <div>
              <h2>Output</h2>
              <div class="card list-y">
                <c:if test="${visu}">
                  <img src="gif" alt="Sorting Animation">
                </c:if>
                <span><c:out value="${out.getList()}" /></span>
              </div>
            </div>
            <div>
              <h2>Execution Time</h2>
              <div class="card"><c:out value="${out.getTime()} ms" /></div>
            </div>
          </c:when>
          <c:when test="${not empty err}">
            <div>
              <h2>Error Message</h2>
              <div class="card"><span style="color: indianred;"><c:out value="${err}" /></span></div>
            </div>
          </c:when>
          <c:otherwise>
            <div>
              <h2>Output</h2>
              <div class="list-y" style="align-items: center; display: flex; margin: 100px auto;">
                <div style="margin: auto;">
                  <i class="fa-solid fa-box-open fa-8x"></i>
                </div>
                <div style="text-align: center;">The data is empty.<br>The results will appear here once you start sorting.</div>
              </div>
            </div>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
</body>
</html>
