// User Table
function getUser(){
    fetch("/api/user",
    {method: 'GET', dataType: 'json'})
        .then((res) => res.json())
        .then((user) => {
            let temp = "";
            temp +=  temp += `<tr>
            <td>${user.id}</td>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>${user.age}</td>
            <td>${user.username}</td>
            <td>${user.roles.map(e => e.role)}</td>
            </tr>`;
            document.getElementById("userInfo").innerHTML = temp;
        });
}

getUser();

// Admin Table
function getAllUsers(){
    let temp = '';
    fetch("/api/users",
        {method: 'GET', dataType: 'json'})
        .then(res => res.json())
        .then(users => {

            users.forEach(function (user) {
                temp += `
                <tr>
                <td>${user.id}</td>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
                <td>${user.age}</td>
                <td>${user.username}</td>
                <td>${user.roles.map(e => " " + e.role)}</td>
                <td><button type="submit" onclick="modalWindowEditUser(${user.id})"
                class="btn btn-info" data-toggle="modal" data-target="#updateUser">Edit</button></td>
                <td><button type="submit" onclick="modalWindowDeleteUser(${user.id})" 
                class="btn btn-danger" data-toggle="modal" data-target="#deleteUser">Delete</button></td>
              </tr>`;
            });
            document.getElementById("users").innerHTML = temp;
        });
}

getAllUsers();

// Get Roles

function getAllRoles(){
    fetch("/api/roles")
        .then (res => res.json())
        .then(roles => {
            let temp = "";
            roles.forEach(function (role) {
                temp += `<option value ="${role.id}">${role.role}</option>`
            })
            document.getElementById("updateRoles").innerHTML = temp;
            document.getElementById("deleteRoles").innerHTML = temp;
            document.getElementById("newRoles").innerHTML = temp;
        })
}

getAllRoles();

function getRoles(list){
    let roles = [];
    list.forEach(r => {
        roles.push({id: r.value, role: r.text});
    });
    return roles;
}

// Edit User
function modalWindowEditUser(id) {
    fetch("/api/users/" + id,
        { method: 'GET', dataType: 'json'})
        .then((res) => {
            res.json().then((user) => {
                $('#updId').val(user.id)
                $('#updateFirstname').val(user.firstname)
                $('#updateLastname').val(user.lastname)
                $('#updateAge').val(user.age)
                $('#updateUsername').val(user.username)
                $('#updatePassword').val(user.password)
                $('#updateRoles').val(user.role)
            })
        });
}

async function updateUser() {

    let roles = getRoles(Array.from(document.getElementById('updateRoles').selectedOptions));
    let user = {
        id: document.getElementById('updId').value,
        firstname: document.getElementById('updateFirstname').value,
        lastname: document.getElementById('updateLastname').value,
        age: document.getElementById('updateAge').value,
        username: document.getElementById('updateUsername').value,
        password: document.getElementById('updatePassword').value,
        roles: roles
    }

    await fetch("/api/users/" + document.getElementById("updId").value,
        {
            method: 'PUT',
            headers: {'Accept': 'application/json', 'Content-Type': 'application/json;charset=UTF-8'},
            body: JSON.stringify(user)
        })

    $("#updateUser .close").click();
   updateTable();
}

// Delete User
function modalWindowDeleteUser(id){

    fetch("/api/users/" + id,
        {method: 'GET', dataType: 'json'})
        .then((res) => {
            res.json().then((user) => {
                $('#delId').val(user.id)
                $('#deleteFirstname').val(user.firstname)
                $('#deleteLastname').val(user.lastname)
                $('#deleteAge').val(user.age)
                $('#deleteUsername').val(user.username)
                $('#deletePassword').val(user.password)
                $('#deleteRoles').val(user.role)
            })
        });
}
async function deleteUser() {

    await fetch("/api/users/" + document.getElementById("delId").value,
        {
            method: 'DELETE',
            dataType: 'json'
        })
    $("#deleteUser .close").click();
    updateTable();
}

// New User
async function addNewUser() {
    let roles = getRoles(Array.from(document.getElementById('newRoles').selectedOptions));
    let user = {
        firstname: document.getElementById('newFirstname').value,
        lastname: document.getElementById('newLastname').value,
        age: document.getElementById('newAge').value,
        username: document.getElementById('newUsername').value,
        password: document.getElementById('newPassword').value,
        roles: roles
    }

    await fetch("/api/users",
        {
            method: 'POST',
            headers: {'Accept': 'application/json', 'Content-Type': 'application/json;charset=UTF-8'},
            body: JSON.stringify(user)
        })

    getAllUsers();
    getUser();
}

// Delete Row
function updateTable(){
    let table = document.getElementById("users");
    while (table.rows.length > 1){
        table.deleteRow(1)
    }
}

