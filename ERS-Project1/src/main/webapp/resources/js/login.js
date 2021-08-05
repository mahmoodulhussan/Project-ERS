const login = async (e) => {
	e.preventDefault();
	let uname = document.getElementById('username').value;
	let pass = document.getElementById('password').value;
	document.getElementById('login-form').reset();
	if (uname.length < 1 || pass.length < 1) {
		alert("Enter the credentials");
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

	if (req.status !== 200) {
		alert("Username or password are incorrect");
		return;
	}
	else {
		let res = await req.json();
	if (res.userRole == 1) { 
		
			location.href = '../html/employee-dashboard.html';
		} else {
			location.href = '../html/manager-dashboard.html';
		}
	}

}

//Setting the event listener for the login button
document.getElementById('submit').addEventListener('click', login);

//Setting the event listener for the register redirect button
document.getElementById('register-redir').addEventListener('click', (event) => {
	location.href = '../html/registration.html';
});
