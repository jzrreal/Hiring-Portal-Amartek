function redirectManager(role) {

    if (role === "Human Resource") {
        return "/human-resource/dashboard"
    } else if (role === "Trainer") {
        return "/trainer/dashboard"
    } else if (role === "Applicant") {
        return "/applicant/dashboard"
    } else if (role === "Administrator") {
        return "/administrator/dashboard"
    } else {
        return "/login"
    }
}

export default redirectManager;
