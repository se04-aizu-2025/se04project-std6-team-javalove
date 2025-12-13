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
</head>
<body>
  <div class="list-y">
    <h1>Sort Visualizer</h1>
    <div>
      <h2>Input</h2>
      <form class="card list-y" action="sort" method="post">
        <textarea id="in" name="in" rows="8" required><c:if test="${not empty in}"><c:out value="${in}" /></c:if></textarea>
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
        </div>
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
          <label>
            <c:choose>
              <c:when test="${str}">
                <input id="str" type="checkbox" name="opt" value="str" checked>
              </c:when>
              <c:otherwise>
                <input id="str" type="checkbox" name="opt" value="str">
              </c:otherwise>
            </c:choose>
            <span>String Mode</span>
          </label>
        </div>
        <div class="list-x" style="margin-left: auto;">
          <button id="generateBtn" type="button"><i class="fa-solid fa-dice"></i> Generate Random Input</button>
          <button type="submit"><i class="fa-solid fa-play"></i> Sort</button>
        </div>
      </form>
    </div>
    <c:if test="${not empty time}">
      <div>
        <h2>Execution Time</h2>
        <div class="card"><c:out value="${time} ms" /></div>
      </div>
    </c:if>
    <c:if test="${not empty out}">
      <div>
        <h2>Output</h2>
        <div class="card"><c:out value="${out}" /></div>
      </div>
    </c:if>
    <c:if test="${not empty err}">
      <div>
        <h2>Error Message</h2>
        <div class="card"><c:out value="${err}" /></div>
      </div>
    </c:if>
  </div>
  <script>
    const CHARS = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    
    function generateRandom(count = 10, length = 5, isString = true) {
      const arr = [];
      for (let i = 0; i < count; i++) {
        arr.push(isString ? randomWord(length) : randomInt());
      }
      return arr.join(' ');
    }
    
    function randomInt(min = 0, max = 100) {
      return Math.floor(Math.random() * (max - min) + min);
    }
    
    function randomWord(length) {
      let s = '';
      for (let i = 0; i < length; i++) {
        s += CHARS.charAt(Math.floor(Math.random() * CHARS.length));
      }
      return s;
    }

    document.getElementById('generateBtn').addEventListener('click', () => {
      const isString = document.getElementById('str').checked;
      const output = generateRandom(10, 5, isString);
      document.getElementById('in').value = output;
    });
  </script>
</body>
</html>
