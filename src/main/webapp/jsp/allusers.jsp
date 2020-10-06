<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="./css/styles.css" rel="stylesheet" type="text/css">
    <script src="http://code.jquery.com/jquery-2.2.4.js"
            type="text/javascript"></script>
</head>
<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Please add user
    </div>
    <div id="message"></div>
    <form id="form">
        <label for="first-name">First Name
            <input class="input-field" type="text" id="first-name" name="first-name">
        </label>
        <label for="last-name">Last Name
            <input class="input-field" type="text" id="last-name" name="last-name">
        </label>
        <label for="code-word">Code Word
            <input class="input-field" type="text" id="code-word" name="code-word">
        </label>
        <label for="fav-film">Favourite Film
            <input class="input-field" type="text" id="fav-film" name="fav-film">
        </label>
        <input type="button" value="Add user" id="button">
    </form>
</div>

<script>
    $(document).ready(function () {
        getAllUsers();
        $("#button").click(function () {
            $.ajax({
                url: '/jsp_ajax_war/users',
                method: 'post',
                dataType: 'json',
                data: $('#form').serialize(),
                success: function (data) {
                    alert('Saved successfully!');
                    getAllUsers();
                }
            });
        });


        function getAllUsers() {
            $.ajax({
                url: '/jsp_ajax_war/users',
                dataType: 'json',
                method: 'GET',
                success: function (data) {
                    var userInf = $('#allUsers  tbody');
                    userInf.empty();
                    $(data).each(function (index, element) {
                        userInf.append('<tr><td>' + element.firstName + '</td><td>' + element.lastName + '</td><td>'
                            + element.codeWord + '</td><td>' + element.favFilm + '</td></tr>');
                    })
                }
            });
        }
    })
</script>



<table id="allUsers">
    <thead>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Code Word</th>
        <th>Favourite Film</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>


</body>
</html>