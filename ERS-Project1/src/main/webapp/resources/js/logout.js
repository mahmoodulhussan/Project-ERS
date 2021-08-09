/**
 * 
 */
 
 let logoutbtn = document.getElementById("logout").addEventListener("click", logout);

async function logout(){
	let req = await fetch('http://localhost:8080/ERS-Project/api/logout');
	let res = await req.text();
	console.log(res);
	req = await fetch('http://localhost:8080/ERS-Project/api/getSession');
	res = await req.text();
	console.log(res);
	location.href = '../html/index.html';
}