import React from "react";
import {useHistory} from "react-router-dom"

const BookEdit = (props) =>{

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name : "",
        category : 0,
        author : 0,
        availableCopies: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.book.name;
        const category = formData.category !== 0 ? formData.category : props.book.category;
        const author = formData.author !== 0 ? formData.author : props.book.author.id;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;


        props.onEditBook(props.book.id, name, category, author, availableCopies);
        history.push("/books");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" name="category" type="radio" id="NOVEL" value="NOVEL" onChange={handleChange}/>
                            <label className="form-check-label" htmlFor="NOVEL">NOVEL</label>
                        </div>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" name="category" type="radio" id="THRILER" value="THRILER" onChange={handleChange}/>
                            <label className="form-check-label" htmlFor="THRILER">THRILER</label>
                        </div>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" name="category" type="radio" id="HISTORY" value="HISTORY" onChange={handleChange}/>
                            <label className="form-check-label" htmlFor="HISTORY">HISTORY</label>
                        </div>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" name="category" type="radio" id="FANTASY" value="FANTASY" onChange={handleChange}/>
                            <label className="form-check-label" htmlFor="FANTASY">FANTASY</label>
                        </div>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" name="category" type="radio" id="BIOGRAPHY" value="BIOGRAPHY" onChange={handleChange}/>
                            <label className="form-check-label" htmlFor="BIOGRAPHY">BIOGRAPHY</label>
                        </div>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" name="category" type="radio" id="CLASSICS" value="CLASSICS" onChange={handleChange}/>
                            <label className="form-check-label" htmlFor="CLASSICS">CLASSICS</label>
                        </div>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" name="category" type="radio" id="DRAMA" value="DRAMA" onChange={handleChange}/>
                            <label className="form-check-label" htmlFor="CLASSICS">DRAMA</label>
                        </div>

                    </div>

                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) => {
                                if(props.book.author !== undefined &&
                                    props.book.author.id === term.id)
                                    return <option selected={props.book.author.id} value={term.id}>{term.name}</option>
                                else return <option value={term.id}>{term.name}</option>
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Available Copies</label>
                        <input type="number"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder={props.book.availableCopies}
                               onChange={handleChange}
                        />
                    </div>

                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}


export default BookEdit;