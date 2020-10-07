<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Kilaru</title>
    <link href="./css/styles.css" rel="stylesheet" type="text/css">
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

<script src="http://code.jquery.com/jquery-2.2.4.js"
        type="text/javascript"></script>

<script src="js/users.js"
        type="text/javascript"></script>

</body>
</html>