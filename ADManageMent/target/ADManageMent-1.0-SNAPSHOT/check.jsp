<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Advertisement</title>
</head>
<body>
<h1>Advertisement</h1>
<img id="adImage" alt="Advertisement Image" />

<script>
  // script.js

  document.addEventListener("DOMContentLoaded", function() {
    fetch('http://localhost:8080/ADManageMent/AdResponse-servlet/?tag=food', {
      method: 'GET',
      headers: {
        'Accept': 'application/json'  // 确保请求头是正确的
      }
    })
            .then(response => {
              if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
              }
              return response.json();
            })
            .then(data => {
              console.log(data); // 调试输出返回的 JSON 数据
              const base64Photo = data.base64photo;
              const adImage = document.getElementById('adImage');
              adImage.src = `data:image/jpeg;base64,${base64Photo}`;
            })
            .catch(error => console.error('Error fetching ad data:', error));
  });
</script>

</body>
</html>
