$(document).ready(function () {
    getAllUsers();
    $("#button").click(function () {
        $.ajax({
            url: '/jsp_ajax_war/users',
            method: 'post',
            data: $('#form').serialize(),
            success: function () {
                console.log("User added successfully");
                getAllUsers();
            }
        });
    });


    function getAllUsers() {
        $.ajax({
            url: '/jsp_ajax_war/users',
            dataType: 'json',
            method: 'GET',
            success: function (users) {
                let userTable = $('#allUsers  tbody');
                userTable.empty();

                for (let user of users) {
                    userTable.append('<tr><td>' + user.firstName + '</td><td>' + user.lastName + '</td><td>'
                        + user.codeWord + '</td><td>' + user.favFilm + '</td></tr>');
                }
            }
        });
    }
})