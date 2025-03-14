async function handleSignIn(event) {
  event.preventDefault();

  const username = document.getElementById("signInUsername").value;
  const password = document.getElementById("signInPassword").value;

  const response = await fetch("http://localhost:8080/api/auth/signin", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password }),
  });

  if (!response.ok) {
    alert("Invalid login credentials");
    return;
  }

  const data = await response.json();
  localStorage.setItem("token", data.token);
  localStorage.setItem("authenticated", "true");

  window.location.href = "index.html";
}

async function handleSignUp(event) {
  event.preventDefault();

  const username = document.getElementById("signUpUsername").value;
  const email = document.getElementById("signUpEmail").value;
  const password = document.getElementById("signUpPassword").value;

  const response = await fetch("http://localhost:8080/api/auth/signup", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, email, password }),
  });

  if (!response.ok) {
    alert("Sign-up failed");
    return;
  }

  const data = await response.json();
  alert("Sign-up successful! Please sign in.");
  window.location.href = "sign-in.html";
}
