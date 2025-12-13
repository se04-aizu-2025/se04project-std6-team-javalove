<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sort Visualizer</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css/normalize.css">
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <div style="padding:64px; display: flex; flex-direction: column; gap: 16px;">
    <div style="font-size: 32px;">Sort Visualizer</div>
    <div>
      <div>Input</div>
      <form action="home" method="post">
        <textarea name="in" rows="8" required><c:if test="${not empty in}">${in}</c:if></textarea>
        <div style="display: flex; gap: 16px;">
          <label>
            <input type="radio" name="algo" value="bubble" checked>
            <span>Bubble Sort</span>
          </label>
          <label>
            <input type="radio" name="algo" value="selection">
            <span>Selection Sort</span>
          </label>
          <label>
            <input type="radio" name="algo" value="insertion">
            <span>Insertion Sort</span>
          </label>
        </div>
        <div style="display: flex; gap: 16px;">
          <label>
            <input type="checkbox" name="opt" value="str"<c:if test="${str}"> checked</c:if>>
            <span>String Mode</span>
          </label>
        </div>
        <button type="submit">Sort</button>
      </form>
    </div>
    <c:if test="${not empty time}">
      <div>
        <div>Execution Time</div>
        <div style="background-color: #222; border-radius: 8px; padding: 16px;">${time} ms</div>
      </div>
    </c:if>
    <c:if test="${not empty out}">
      <div>
        <div>Output</div>
        <div style="background-color: #222; border-radius: 8px; padding: 16px;">${out}</div>
      </div>
    </c:if>
    <c:if test="${not empty err}">
      <div>
        <div>Error Message</div>
        <div style="background-color: #222; border-radius: 8px; padding: 16px;">${err}</div>
      </div>
    </c:if>
  </div>
</body>
</html>
