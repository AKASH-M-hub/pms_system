console.log("student.js loaded");

const token = localStorage.getItem("token");

if (!token) {
    alert("Please login first");
    window.location.href = "login.html";
}

// 📌 LOAD JOBS
fetch("/api/jobs", {
    headers: {
        "Authorization": "Bearer " + token
    }
})
.then(res => res.json())
.then(jobs => {

    const container = document.getElementById("jobs");
    container.innerHTML = "";

    jobs.forEach(job => {

        const card = document.createElement("div");
        card.className = "job-card";

        const button = job.applied
            ? `<button class="btn btn-muted" disabled>Registered</button>`
            : `<button class="btn" onclick="applyJob(${job.jobId})">Apply</button>`;

        card.innerHTML = `
            <div>
                <h3>${job.title}</h3>
                <p class="job-meta">Salary: ₹${job.salary}</p>
                <p class="job-meta">Eligibility CGPA: ${job.eligibilityCgpa}</p>
                <p class="job-meta">Deadline: ${job.deadlineDate}</p>
            </div>
            ${button}
        `;

        container.appendChild(card);
    });
});

// 📨 APPLY JOB
function applyJob(jobId) {

    fetch(`/api/student/apply/${jobId}`, {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => {
        if (!res.ok) throw new Error("Already applied");
        return res.json();
    })
    .then(() => {
        alert("Applied successfully ✅");
        location.reload();
    })
    .catch(() => {
        alert("Already applied ❌");
    });
}
