<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.0/jquery.min.js" type="text/javascript"></script>
    <script>
        $(document).ready(function () {
            $("#submit").click(function () {
                search($("#searchRequest").val());
            })
        })

        function search(searchRequest) {
            $.get("/eshop?command=search", {searchRequest : searchRequest}, function (data) {
                $('#searchResult').html(data);
            });
        }
    </script>
</head>
<body>
<div style="text-align:center">
    <input placeholder="Search" type="text" id="searchRequest">
    <button type="submit" id="submit">Search</button>
</div>
<div id="searchResult">

</div>
</body>
</html>
