function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch("/api/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    })
    .then(res => res.json())
    .then(data => {
        localStorage.setItem("token", data.token);
        localStorage.setItem("role", data.role);

        if (data.role === "ADMIN" || data.role === "ROLE_ADMIN") {
            window.location.href = "admin-dashboard.html";
        } else if (data.role === "STUDENT" || data.role === "ROLE_STUDENT") {
            window.location.href = "student-dashboard.html";
        } else {
            document.getElementById("msg").innerText = "Unknown role";
        }
    })
    .catch(() => {
        document.getElementById("msg").innerText = "Login failed";
    });
}
