function redirectManager(role) {

    if(role === "Human Resource") {
        window.location.replace("http://localhost:3000/human-resource/dashboard")
    }
    if(role === "Applicant") {
        window.location.replace("http://localhost:3000/human-resource/dashboard")
    }
}

export default redirectManager;
