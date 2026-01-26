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
    <div>
      <h2>Input</h2>
      <form class="card list-y" action="sort" method="post">
        <textarea class="textarea" id="in" name="in" rows="2" placeholder="Please enter numbers separated by spaces (e.g. &quot;6 1 7 4&quot;)." required><c:if test="${not empty in}"><c:out value="${in}" /></c:if></textarea>
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
            <button class="btn" id="generateBtn" type="button"><i class="fa-solid fa-dice"></i> Generate</button>
            <label class="btn">
              <input style="display: none;" type="file" id="fileInput" accept=".txt">
              <span><i class="fa-solid fa-file"></i> File Input</span>
            </label>
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
              <span>If checked, allows sorting visualization.</span><br>
              <span>Increasing the delay will result in slower visualization.</span><br>
              <span>Visualization is not recommended for very large inputs.</span>
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
              <span>Each iteration delays processing by 2^cost - 1 milliseconds.</span><br>
              <span>If cost is 0, there is no delay.</span><br>
              <span>A relatively low cost will result in a sufficient delay.</span>
            </div>
          </div>
        </div>
        <button class="btn" type="submit" style="margin-left: auto;"><i class="fa-solid fa-play"></i> Sort</button>
      </form>
    </div>
    <c:if test="${not empty out}">
      <div>
        <h2>Execution Time</h2>
        <div class="card"><c:out value="${out.getTime()} ms" /></div>
      </div>
      <div>
        <h2>Output</h2>
        <div class="card list-y">
          <c:if test="${visu}">
            <img src="gif" alt="Sorting Animation">
          </c:if>
          <span><c:out value="${out.getList()}" /></span>
        </div>
      </div>
    </c:if>
    <c:if test="${not empty err}">
      <div>
        <h2>Error Message</h2>
        <div class="card"><span style="color: indianred;"><c:out value="${err}" /></span></div>
      </div>
    </c:if>
  </div>
</body>
</html>
