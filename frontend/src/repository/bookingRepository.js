import axios from "../custom-axios/axios";

const BookingService = {
    fetchHosts: () => {
        return axios.get("/host");
    },
    fetchAccommodations: () => {
        return axios.get("/acc");
    },
    fetchCountries: () => {
        return axios.get("/country");
    },
    fetchCategories: () => {
        return axios.get("/cat");
    },
    deleteAccommodation: (id) => {
        return axios.delete(`/acc/delete/${id}`)
    },
    addAccommodation: (name, category, host, numRooms) => {
        return axios.post("/acc/add", {
            "name" : name,
            "category" : category,
            "hostId" : host,
            "numRooms" : numRooms,
        });
    },
    editAccommodation: (id, name, category, host, numRooms, isAvailable) => {
        return axios.put(`/acc/edit/${id}`, {
            "name" : name,
            "category" : category,
            "hostId" : host,
            "numRooms" : numRooms,
            "available" : isAvailable
        });
    },
    getAccommodation: (id) => {
        return axios.get(`/acc/${id}`);
    },
    occupyAccommodation: (id) => {
        return axios.post(`/acc/mark/${id}`)
    }
}

export default BookingService;