import logo from '../../logo.svg';
import './App.css';
import React from "react";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Hosts from "../Hosts/hosts";
import BookingService from "../../repository/bookingRepository";
import Accommodations from "../Accommodations/AccommodationList/accommodations";
import Countries from "../Countries/countries";
import data from "bootstrap/js/src/dom/data";
import Header from "../Header/header";
import AccommodationAdd from "../Accommodations/AccommodationAdd/accommodationAdd";
import Categories from "../Categories/categories";
import bookingRepository from "../../repository/bookingRepository";
import AccommodationEdit from "../Accommodations/AccommodationEdit/accommodationEdit";

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            hosts : [],
            accommodations : [],
            countries : [],
            categories : [],
            selectedAccommodation : {}
        }
    }

    render() {
        return (
            <BrowserRouter>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                            <Route path="/" element={<Accommodations accommodations={this.state.accommodations}
                                                                     onDelete={this.deleteAccommodation}
                                                                     onEdit={this.getAccommodation}
                                                                     onRent={this.occupyAccommodation}/>} />
                            <Route path="/countries" element={<Countries countries={this.state.countries} />} />
                            <Route path="/categories" element={<Categories categories={this.state.categories} />} />
                            <Route path="/hosts" element={<Hosts hosts={this.state.hosts} />} />
                            <Route path="/accommodations/add" element={<AccommodationAdd hosts={this.state.hosts} categories={this.state.categories} onAddAcc={this.addAccommodation} />} />
                            <Route path="/accommodations/edit/:id" element={<AccommodationEdit hosts={this.state.hosts}
                                                                                               categories={this.state.categories}
                                                                                               accommodation={this.state.selectedAccommodation}
                                                                                               onEditAcc={this.editAccommodation} />} />
                            <Route path="/accommodations" element={<Accommodations accommodations={this.state.accommodations}
                                                                                   onDelete={this.deleteAccommodation}
                                                                                    onEdit={this.getAccommodation}
                                                                                   onRent={this.occupyAccommodation}/>} />
                        </Routes>
                    </div>
                </main>
            </BrowserRouter>
        );
    }

    loadHosts = () => {
        BookingService.fetchHosts()
            .then((data) => {
                this.setState({
                    hosts: data.data,
                })
            })
    }

    loadAccommodations = () => {
        BookingService.fetchAccommodations()
            .then((data) => {
                this.setState({
                    accommodations: data.data,
                })
            })
    }
    loadCountries = () => {
        BookingService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            })
    }

    loadCategories = () => {
        BookingService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories : data.data
                })
            })
    }

    deleteAccommodation = (id) => {
        BookingService.deleteAccommodation(id)
            .then(() => {
                this.loadAccommodations();
            })
    }

    addAccommodation = (name, category, host, numRooms) => {
        BookingService.addAccommodation(name, category, host, numRooms)
            .then(() => {
                this.loadAccommodations();
            })
    }
    getAccommodation = (id) => {
        BookingService.getAccommodation(id)
            .then((data) => {
                this.setState({
                    selectedAccommodation : data.data
                })
            })
    }
    editAccommodation = (id, name, category, host, numRooms, isAvailable) => {
        BookingService.editAccommodation(id, name, category, host, numRooms, isAvailable)
            .then(() => {
                this.loadAccommodations();
            })
    }
    occupyAccommodation = (id) => {
        BookingService.occupyAccommodation(id)
            .then(() => {
                this.loadAccommodations();
            })
    }
    componentDidMount() {
        this.loadHosts();
        this.loadCountries();
        this.loadAccommodations();
        this.loadCategories();
    }
}

export default App;
