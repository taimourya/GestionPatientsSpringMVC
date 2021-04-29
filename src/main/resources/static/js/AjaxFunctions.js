
function getPatientByIdFromController(id) {
    return fetch("/patient/get/"+id).then(function (response) {
        return response.json();
    }).catch(function (error) {
        console.log(error);
    });
}