function redirectManager(role) {

    if (role === "Human Resource") {
        window.location.replace("http://localhost:3000/human-resource/dashboard")
    } else if (role === "Trainer") {
        window.location.replace("http://localhost:3000/trainer/dashboard")
    } else if (role === "Applicant") {
        window.location.replace("http://localhost:3000/applicant/dashboard")
    }
}

export default redirectManager;
