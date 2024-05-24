import React from "react";
import AccommodationTerm from "../AccommodationTerm/accommodationTerm";
import {Link} from "react-router-dom";
import ReactPaginate from 'react-paginate'
class Accommodations extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 5
        }
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page : selected
        })
    }
    getAccommodationPage = (offset, nextPageOffset) => {

        const availableAccommodations = this.props.accommodations.filter((term) => term.available);
        const accommodations = availableAccommodations.slice(offset, nextPageOffset);
        return accommodations.map((term) => (
            <AccommodationTerm
                key={term.id}
                term={term}
                onDelete={this.props.onDelete}
                onEdit={this.props.onEdit}
                onRent={this.props.onRent}
            />
        ));
    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.accommodations.length / this.state.size);
        const accommodations = this.getAccommodationPage(offset, nextPageOffset);


        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"row"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Host</th>
                                <th scope={"col"}>Category</th>
                                <th scope={"col"}>NumRooms</th>
                                <th scope={"col"}>isAvailable</th>
                            </tr>
                            </thead>
                            <tbody>
                            {accommodations}
                            </tbody>
                        </table>
                    </div>
                    <div className={"col md-3"}>
                        <div className={"row"}>
                            <div className={"col-sm-12 col-md-12"}>
                                <Link className={"btn btn-dark btn-block"} to={"/accommodations/add"}>Add Accommodation</Link>
                            </div>
                        </div>
                    </div>
                </div>
                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"mx-2"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}/>
            </div>
        );
    }


}

export default Accommodations;