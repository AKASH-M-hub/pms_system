console.log("admin.js loaded");

// 🔐 GET TOKEN
const token = localStorage.getItem("token");
const role = localStorage.getItem("role");

const isAdminRole = role === "ADMIN" || role === "ROLE_ADMIN";

if (!token || !isAdminRole) {
    alert("Admin login required");
    window.location.href = "login.html";
}

// 🧾 LOAD APPLICATIONS
fetch("/api/admin/applications", {
    headers: {
        "Authorization": "Bearer " + token
    }
})
.then(res => {
    if (!res.ok) throw new Error("Unauthorized");
    return res.json();
})
.then(data => {
    const table = document.getElementById("applications");

    data.forEach(app => {
        const row = document.createElement("tr");
        const studentName = app.student?.name || app.studentName || "N/A";
        const studentEmail = app.student?.email || app.studentEmail || "N/A";
        const jobTitle = app.job?.title || app.jobTitle || "N/A";
        row.innerHTML = `
            <td>${studentName}</td>
            <td>${studentEmail}</td>
            <td>${jobTitle}</td>
            <td>${app.status || "N/A"}</td>
        `;
        table.appendChild(row);
    });
})
.catch(err => console.error(err));


// ➕ ADD JOB
function addJob() {

    const title = document.getElementById("title").value.trim();
    const salary = document.getElementById("salary").value.trim();
    const eligibilityCgpa = document.getElementById("cgpa").value.trim();
    const deadlineDate = document.getElementById("deadline").value.trim();
    const registrationLink = document.getElementById("link").value.trim();

    if (!title || !salary || !eligibilityCgpa || !deadlineDate || !registrationLink) {
        alert("Please fill in all job fields");
        return;
    }

    if (Number.isNaN(Number(salary)) || Number.isNaN(Number(eligibilityCgpa))) {
        alert("Salary and CGPA must be valid numbers");
        return;
    }

    const job = {
        title,
        salary: Number(salary),
        eligibilityCgpa: Number(eligibilityCgpa),
        deadlineDate,
        registrationLink
    };

    fetch("/api/admin/job", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token   // 🔥 THIS WAS MISSING
        },
        body: JSON.stringify(job)
    })
    .then(res => {
        if (res.ok) return res.text();
        if (res.status === 401 || res.status === 403) {
            throw new Error("Only admin can add jobs");
        }
        return res.text().then(text => {
            throw new Error(text || "Invalid job data");
        });
    })
    .then(msg => {
        alert("Job added successfully ✅");
        location.reload();
    })
    .catch(err => {
        alert(err.message);
        console.error(err);
    });
}
