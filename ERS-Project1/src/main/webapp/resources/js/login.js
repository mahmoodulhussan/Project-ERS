/**
 * 
 
let form = document.getElementById("login").addEventListener('submit', login);

async function login(e){
	e.preventDefault();
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	
	let user = {
		username,
		password
	}
	
	console.log(user);
	
	
	try{
		let req = await fetch('http://localhost:8080/ERS-Project1/login', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		let res = await req.json();
	} catch(e){
		alert('Username or password incorrect!');
		return;
	}
	
	location.href = '../html/employee-dashboard.html';
}
*/


/*
Function that handles login
Checks to make sure the form has user data before making a call to the database
Makes a call to the database, and gets back user information if they user exists
The users id is stored in session for use later
*/
const login = async (e) => {
	e.preventDefault();
	let uname = document.getElementById('username').value;
	let pass = document.getElementById('password').value;
	document.getElementById('login-form').reset();
	if (uname.length < 1 || pass.length < 1) {
		alert("You must enter a username and password.");
		return;
	}

	let uObj = {
		username: uname,
		password: pass,
	};

	let req = await fetch('http://localhost:8080/ERS-Project1/login', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(uObj),
	});

//	if (req.status !== 200) {
//		alert("Username or password are incorrect");
//		return;
//	}
//	else {
		let res = await req.json();
//	if (res.userType == 0) { 
			location.href = '../html/employee-dashboard.html';
//		} else {
//			location.href = '../html/manager-dashboard.html';
//		}
//	}

}

//Setting the event listener for the login button
document.getElementById('submit').addEventListener('click', login);

//Setting the event listener for the register redirect button
document.getElementById('register-redir').addEventListener('click', (event) => {
	location.href = '../html/registration.html';
});
